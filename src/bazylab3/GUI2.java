package bazylab3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI2
	{

		static String currentQuery = "select * from aktorzy";

		static Login user = new Login("admin", "pwsz");
		static Mysql db1 = new Mysql(user, Main.ip, 3306);

		static String data[][] = null;
		static String kolumny[] = null;
		static JTable table = null;

		static JFrame okno = null;

		static JComboBox<String> jComboPola = new JComboBox<String>();

		public GUI2()
			{
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
				System.out.print("aktualizacja query " + currentQuery + "\n");
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
				// wszystko tutaj wrzuciłem, bo nie znam javy na na tyle, zeby to umiec
				// podzielic

				// combobox do wybrania tabeli
				JComboBox<String> jComboTabele = new JComboBox<String>(db1.getTables());

				jComboTabele.updateUI();

				// tworzenie okna
				okno = new JFrame("LAB3");
				okno.setSize(1024, 780);
				okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JOptionPane.showMessageDialog(null, "Polaczono z baza");

				// tworzenie tabeli
				JScrollPane scrollPane = new JScrollPane(table);
				table.setFillsViewportHeight(true);
				okno.getContentPane().add(BorderLayout.CENTER, table);
				okno.getContentPane().add(BorderLayout.SOUTH, jComboTabele);

				// filtracja
				JPanel jp2 = new JPanel(); // tworzymy panel
				JLabel jl2 = new JLabel("filtracja"); // dajemy etykietke filtracja, zeby uzytkownik wiedzial co to

				JButton jbadd = new JButton("dodaj");
				JButton jbdel = new JButton("usun");
				JButton jbfilter = new JButton("Filtruj");
				
				final JTextField tfod = new JTextField("od");
				final JTextField tfdo = new JTextField("do");

				jp2.add(jl2);
				jp2.add(jComboPola);
				jp2.add(tfod);
				jp2.add(tfdo);
				jp2.add(jbfilter);

				jp2.add(jbdel);
				jp2.add(jbadd);
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
										stringDEL[i] = (String) table.getValueAt(temp, i);
									}
									//Main.printArray(stringiDEL);
									db1.deleteRow(jComboTabele.getSelectedItem().toString(), kolumny , stringDEL);
									updateTable();
								}
							}
					});
				jbadd.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent evt)
							{
								String stringADD[] = new String[kolumny.length];
								for (int i = 0; i < kolumny.length; i++)
								{
									stringADD[i] = (String) table.getValueAt(data.length, i);
								}
								//Main.printArray(stringADD);
								db1.insertInto(jComboTabele.getSelectedItem().toString(), kolumny, stringADD);
								updateTable();

							}
					});
			}
	};;
