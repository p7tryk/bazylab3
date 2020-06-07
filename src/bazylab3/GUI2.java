package bazylab3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI2
	{

		static String currentQuery = "select * from aktorzy";

		static Permissions user = new Permissions("admin", "pwsz");
		static Mysql db1 = new Mysql(user, Main.ip, 3306);

		static String data[][] = null;
		static String kolumny[] = null;
		static JTable table = null;

		static JFrame okno = null;

		static JComboBox<String> jComboBox2 = new JComboBox();
		
		public GUI2()
			{
				createTable();
				updateTable();
				displayWindow();
			}

		private void createTable()
			{
				table = new JTable();
			}

		public static void updateTable()
			{
				//zapytanie sql
				System.out.print("aktualizacja query " + currentQuery + "\n");
				data = db1.select(currentQuery);
				kolumny = db1.getColumns(currentQuery);
				
				//naniesc zmiany do tabeli
				DefaultTableModel model = new DefaultTableModel(data, kolumny);
				table.setModel(model);
				
				//naprawic filtracje
				if(jComboBox2.getItemCount()>0)
					jComboBox2.removeAllItems();
				
				for(int i=0; i<kolumny.length; i++)
				{
					jComboBox2.addItem(kolumny[i]);
				}
			}

		public static void displayWindow()
			{
				// wszystko tutaj wrzuciłem, bo nie znam javy na na tyle, zeby to umiec
				// podzielic

				// combobox do wybrania tabeli
				JComboBox<String> jComboBox1 = new JComboBox<String>(db1.getTables());

				jComboBox1.updateUI();

				// tworzenie okna
				okno = new JFrame("LAB3");
				okno.setSize(1024, 780);
				okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JOptionPane.showMessageDialog(null, "Polaczono z baza");

				// tworzenie tabeli
				JScrollPane scrollPane = new JScrollPane(table);
				table.setFillsViewportHeight(true);
				okno.getContentPane().add(BorderLayout.CENTER, table);
				okno.getContentPane().add(BorderLayout.SOUTH, jComboBox1);

				// filtracja
				JPanel jp2 = new JPanel(); // tworzymy panel
				JLabel jl2 = new JLabel("filtracja"); // dajemy etykietke filtracja, zeby uzytkownik wiedzial co to

				JButton jb2 = new JButton("Filtruj");
				final JTextField tf21 = new JTextField("od");
				final JTextField tf22 = new JTextField("do");


				jp2.add(jl2);
				jp2.add(jComboBox2);
				jp2.add(tf21);
				jp2.add(tf22);
				jp2.add(jb2);
				okno.getContentPane().add(BorderLayout.NORTH, jp2);

				okno.setVisible(true);
				table.setVisible(true);

				// Wybieranie tabeli
				jComboBox1.addActionListener(new ActionListener()
					{

						public void actionPerformed(ActionEvent e)
							{
								currentQuery = "SELECT * FROM " + jComboBox1.getSelectedItem() + ";";
								updateTable();
							}
					});

				// wybieranie pola do filtrowania
				jb2.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent evt)
							{
								currentQuery = "SELECT * FROM " + jComboBox1.getSelectedItem() + "  WHERE "
										+ jComboBox2.getSelectedItem() + " BETWEEN " + tf21.getText() + " AND "
										+ tf22.getText() + ";";
								updateTable();
							}
					});
			}
	};;
