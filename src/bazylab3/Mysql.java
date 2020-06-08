package bazylab3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class Mysql
	{
		Connection baza;
		Login user;
		String url;

		public Mysql(Login login)
			{
				this.user = login;
				this.url = "jdbc:mysql://" + user.ip + ":" + user.port + "/lab3?serverTimezone=UTC";
				System.out.print("probuje: "+ url + "\n" + user.username + " " + user.password + "\n");
				connect();
			}

		public void connect()
			{
				try
				{
					baza = DriverManager.getConnection(url, user.username, user.password);

				} catch (SQLException ex)
				{
					debug(ex);
				}
				System.out.print("polaczono z baza\n");
			}

		public String[] getColumns(String query)
			{
				Statement zapytanie;
				try
				{
					zapytanie = baza.createStatement();
					ResultSet wynik = zapytanie.executeQuery(query);
					ResultSetMetaData wynikMeta = wynik.getMetaData();
					int kolumny = wynikMeta.getColumnCount();
					String output[] = new String[kolumny];
					for (int i = 1; i <= kolumny; i++)
					{
						output[i - 1] = wynikMeta.getColumnName(i);
					}
					return output;
				} catch (SQLException ex)
				{
					debug(ex);
				}

				return null; // just in case

			}

		public String[] getColumnTypes(String query)
			{
				Statement zapytanie;
				try
				{
					zapytanie = baza.createStatement();
					ResultSet wynik = zapytanie.executeQuery(query);
					ResultSetMetaData wynikMeta = wynik.getMetaData();
					int kolumny = wynikMeta.getColumnCount();
					String output[] = new String[kolumny];
					for (int i = 1; i <= kolumny; i++)
					{
						output[i - 1] = wynikMeta.getColumnClassName(i);
					}
					return output;
				} catch (SQLException ex)
				{
					debug(ex);
				}

				return null; // just in case

			}

		public String[] getTables()
			{
				String temp[][] = select("show tables;");
				String kolumna[] = new String[temp.length];
				for (int i = 0; i < kolumna.length; i++)
				{
					kolumna[i] = temp[i][0];
				}
				return kolumna;
			}

		public String[][] select(String query)
			{
				try
				{
					PreparedStatement zapytanie = baza.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					ResultSet wynik = zapytanie.executeQuery();
					ResultSetMetaData wynikMeta = wynik.getMetaData();
					int kolumny = wynikMeta.getColumnCount();
					int n = 0;

					// tutaj jest hack zeby dostac liczbe rekordow zwroconych, w sumie to nie wiem
					// czy da sie lepiej zrobic
					while (wynik.next())
						n++;
					while (wynik.previous())
						assert true; // NOOP

					String output[][] = new String[n][kolumny];
					n = 0;
					while (wynik.next())
					{
						for (int i = 1; i <= kolumny; i++)
						{
							String pole = wynik.getString(i);

							output[n][i - 1] = pole;
						}
						n++;
					}
					return output;
				} catch (SQLException ex)
				{
					debug(ex);
				}
				return null; // just incase
			}

		public String arrayToString(String array[])
			{
				String output = new String();
				for (int i = 0; i < array.length; i++)
				{
					if (i != 0)
						output = output + ",";
					output = output + "\"" + array[i] + "\"";
				}
				//System.out.print("array :\n" + output + "\n");
				return output;
			}

		public String objectToString(List<Object> object)
			{
				String output = new String();
				output = object.toString().replace("[", "").replace("]", "");
				return output;
			}

		public Boolean insertInto(String tabela, String into[], String[] values)
			{
				String query = "insert into " + tabela + " values(" + arrayToString(values) + ");";
				System.out.print(query + "\n");

				Statement zapytanie;
				try
				{
					zapytanie = baza.createStatement();
					int wynik = zapytanie.executeUpdate(query);
					return true;
				} catch (SQLException ex)
				{
					debug(ex);
				}

				return false;
			}

		public Boolean deleteRow(String tabela, String[] kolumny, String[] values)
			{
				// DELETE FROM Customers WHERE CustomerName='Alfreds Futterkiste';
				String query = "delete from " + tabela + " where ";

				for (int i = 0; i < values.length; i++)
				{
					if (i != 0)
						query = query + " AND ";
					query = query + kolumny[i] + " = \"" + values[i] + "\"";
				}

				System.out.print(query + "\n"); // debug

				Statement zapytanie;
				try
				{
					zapytanie = baza.createStatement();
					int wynik = zapytanie.executeUpdate(query);
					return true;
				} catch (SQLException ex)
				{
					debug(ex);
				}

				return false;
			}

		public Boolean alterRow(String tabela, String[] kolumny, String[] values, String[] delete)
			{
//				deleteRow(tabela, kolumny, delete);
//				insertInto(tabela, kolumny, values);
				
//				UPDATE Customers
//				SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
//				WHERE CustomerID = 1;
				
				String query = "update " + tabela + " set ";

				for (int i = 0; i < values.length; i++)
				{
					if (i != 0)
						query = query + " , ";
					query = query + kolumny[i] + " = \"" + values[i] + "\"";
				}
				
				query = query + " where ";
				
				for (int i = 0; i < values.length; i++)
				{
					if (i != 0)
						query = query + " and ";
					query = query + kolumny[i] + " = \"" + delete[i] + "\"";
				}
				

				System.out.print(query + "\n"); // debug

				Statement zapytanie;
				try
				{
					zapytanie = baza.createStatement();
					int wynik = zapytanie.executeUpdate(query);
					return true;
				} catch (SQLException ex)
				{
					debug(ex);
				}

				
				
				return false;
			}

		public void close()
			{
				try
				{
					System.out.printf("zamykam baze\n");
					baza.close();
				} catch (SQLException ex)
				{
					debug(ex);
				}
			}

		public void debug(SQLException ex) // bo mnie wkurzaja te try catche
			{
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
	}
