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
		Permissions user;
		String url;

		public Mysql(Permissions user, String ip, int port)
			{
				this.user = user;
				this.url = "jdbc:mysql://"+ ip + ":" + port + "/lab3?serverTimezone=UTC";
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
				for(int i = 0;i<kolumna.length;i++)
				{
					kolumna[i] = temp[i][0];
				}
				return kolumna;
			}

		public String[][] select(String query)
			{
				try
				{
					PreparedStatement zapytanie = baza.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
					ResultSet wynik = zapytanie.executeQuery();
					ResultSetMetaData wynikMeta = wynik.getMetaData();
					int kolumny = wynikMeta.getColumnCount();
					int n = 0;

					// tutaj jest hack zeby dostac liczbe rekordow zwroconych, w sumie to nie wiem
					// czy da sie lepiej zrobic
					while (wynik.next())
						n++;
					while (wynik.previous())
						assert true; //NOOP

					String output[][] = new String[n][kolumny];
					n = 0;
					while (wynik.next())
					{
						for (int i = 1; i <= kolumny; i++)
						{
							String pole = wynik.getString(i);

							output[n][i-1] = pole;
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
				output = Arrays.deepToString(array).replace("[", "").replace("]", "");
//				System.out.print("array :\n" +  output + "\n");
				return output;
		}
		public String objectToString(List<Object> object)
		{
			String output =  new String();
			output = object.toString().replace("[", "").replace("]", "");
			return output;
		}
		
		public int insertInto(String tabela, String into[], List<Object> values)
			{
//				TODO to jest zle i podobno nie tak sie robi ale narazie zobaczy czy uda mi sie to tak zrobic
//				SQLException: Unknown column 'animowany' in 'field list'
//				SQLState: 42S22
//				VendorError: 1054
				String query = "insert into " + tabela  
			+ " values(" + objectToString(values)+");";
				System.out.print("insert:\n" + query + "\n"); //debug
				
				Statement zapytanie;
				try
				{
					zapytanie = baza.createStatement();
					int wynik = zapytanie.executeUpdate(query);
					return wynik;
				} catch (SQLException ex)
				{
					debug(ex);
				}
				
				return -1;
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

		
		
		
		public void debug(SQLException ex) //bo mnie wkurzaja te try catche
			{
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
	}
