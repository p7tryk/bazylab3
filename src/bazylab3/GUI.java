package bazylab3;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;

public class GUI
	{

		static String currentQuery = "select * from aktorzy";

		static Login user = null;
		static Mysql db1 = null;

		static String data[][] = null;
		static String kolumny[] = null;
		static JTable table = null;

		static JFrame okno = null;

		static JComboBox<String> jComboPola = new JComboBox<String>();

		public void connect() throws SQLException
		{
				user = new Login("admin","pwsz","notarch.lan",3306);
				//user = new Login();
				db1 = new Mysql(user);
		}
		public GUI()
			{
				try
				{
					connect();
				} catch (SQLException ex)
				{
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
				}
				createTable();
				updateTable();
				displayWindow();
			}

		protected void finilize()
			{
				db1.close();
			}

		private void createTable()
			{
				table = new JTable();
			}

		public static void updateTable()
			{
				// zapytanie sql
				System.out.print("drawing: " + currentQuery + "\n");
				data = db1.select(currentQuery);
				kolumny = db1.getColumns(currentQuery);

				// naniesc zmiany do tabeli
				DefaultTableModel model = new DefaultTableModel(data, kolumny);
				model.addRow(new Object[kolumny.length]);
				table.setModel(model);

				// naprawic filtracje
				if (jComboPola.getItemCount() > 0)
					jComboPola.removeAllItems();

				for (int i = 0; i < kolumny.length; i++)
				{
					jComboPola.addItem(kolumny[i]);
				}
			}

		public static void displayWindow()
			{

				// combobox do wybrania tabeli
				JComboBox<String> jComboTabele = new JComboBox<String>(db1.getTables());

				jComboTabele.updateUI();

				// tworzenie okna
				okno = new JFrame("LAB3");
				okno.setSize(1024, 780);
				okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				//JOptionPane.showMessageDialog(null, "Polaczono z baza");

				// tworzenie tabeli
				JScrollPane scrollPane = new JScrollPane(table);
				table.setFillsViewportHeight(true);
				okno.getContentPane().add(BorderLayout.CENTER, table);
				okno.getContentPane().add(BorderLayout.SOUTH, jComboTabele);

				JPanel jp2 = new JPanel(); // tworzymy panel
				JLabel jl2 = new JLabel("filtracja"); // dajemy etykietke filtracja, zeby uzytkownik wiedzial co to

				// przyciski
				JButton jbadd = new JButton("dodaj");
				JButton jbdel = new JButton("usun");
				JButton jbfilter = new JButton("Filtruj");
				JButton jbalter = new JButton("zmien");
				JButton jbdelfilter = new JButton("usun filtr");

				final JTextField tfod = new JTextField("od");
				final JTextField tfdo = new JTextField("do");

				tfod.setPreferredSize(new Dimension(120,30));
				tfdo.setPreferredSize(new Dimension(120,30));
				
				jp2.add(jl2);
				jp2.add(jComboPola);
				jp2.add(tfod);
				jp2.add(tfdo);
				
				jp2.add(jbfilter);
				jp2.add(jbdel);
				jp2.add(jbadd);
				jp2.add(jbalter);
				jp2.add(jbdelfilter);
				okno.getContentPane().add(BorderLayout.NORTH, jp2);

				okno.setVisible(true);
				table.setVisible(true);

				// Wybieranie tabeli
				jComboTabele.addActionListener(new ActionListener()
					{

						public void actionPerformed(ActionEvent e)
							{
								currentQuery = "SELECT * FROM " + jComboTabele.getSelectedItem() + ";";
								updateTable();
							}
					});
				jbdelfilter.addActionListener(new ActionListener()
					{

						public void actionPerformed(ActionEvent e)
							{
								currentQuery = "SELECT * FROM " + jComboTabele.getSelectedItem() + ";";
								updateTable();
							}
					});

				// wybieranie pola do filtrowania
				jbfilter.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent evt)
							{
								currentQuery = "SELECT * FROM " + jComboTabele.getSelectedItem() + "  WHERE "
										+ jComboPola.getSelectedItem() + " BETWEEN " + tfod.getText() + " AND "
										+ tfdo.getText() + ";";
								updateTable();
							}
					});
				//usuwanie zaznaczonego rzedu
				jbdel.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent evt)
							{
								if (table.getSelectedRow() != -1)
								{ // jak jest jakis zaznaczony
									int temp = table.getSelectedRow(); // tutaj numer rzedu se zapisujemy(juz chyba nie
																		// potrzebne, ale jest
									String stringDEL[] = new String[kolumny.length];
									for (int i = 0; i < kolumny.length; i++)
									{
										stringDEL[i] = data[temp][i];
									}
									// Main.printArray(stringiDEL);
									db1.deleteRow(jComboTabele.getSelectedItem().toString(), kolumny, stringDEL);
									updateTable();
								}
							}
					});
				//dodawanie nowego rzedu
				jbadd.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent evt)
							{
								String stringADD[] = new String[kolumny.length];
								for (int i = 0; i < kolumny.length; i++)
								{
									stringADD[i] = (String) table.getValueAt(data.length, i);
								}
								// Main.printArray(stringADD);
								db1.insertInto(jComboTabele.getSelectedItem().toString(), kolumny, stringADD);
								updateTable();

							}
					});
				jbalter.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent evt)
							{
								if (table.getSelectedRow() != -1)
								{ 
									int temp = table.getSelectedRow();
									String stringDEL[] = new String[kolumny.length];
									String stringADD[] = new String[kolumny.length];
									
									for (int i = 0; i < kolumny.length; i++)
									{
										stringDEL[i] = data[temp][i];
										stringADD[i] = (String) table.getValueAt(temp, i);
									}
									// Main.printArray(stringiDEL);
									
									db1.alterRow(jComboTabele.getSelectedItem().toString(), kolumny, stringADD, stringDEL);
									updateTable();
								}
							}
					});
			}
	};;
