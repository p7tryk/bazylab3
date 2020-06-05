package bazylab3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI implements ActionListener
	{
		// tutaj bedzie interfejs generowany i zmieniany
		// TODO wszystko?

		/*
		 * tak ogolnie mysle zeby tutaj byl array tablic = Mysql.getTables i do kazdej
		 * tabeli byla array kolumn = Mysql.getColumns wtedy od razu bedzie wiadomo jak
		 * rozlozyci interfejs w sensie miejsca
		 */
		String currenttable = new String("filmy");
		JFrame okno;
		Mysql db1;
		Permissions user1;

		JTextField tf1 = new JTextField("od");
		JTextField tf2 = new JTextField("do");

		
		public GUI()
			{
				stworzOkno();
				login("admin", "pwsz", "localhost", 3306);
				test();
				nazajeciach();
				okno.setVisible(true);
				logout();
			}

		public void stworzOkno()
			{
				okno = new JFrame("Baza Filmow");
				okno.setSize(1024, 768);
				okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}

		public void login(String user, String password, String ip, int port)
			{
				user1 = new Permissions(user, password);
				db1 = new Mysql(user1, ip, port);
			}

		public void logout()
			{
				db1.close();
				System.out.print("logging out\n");
			}

		public void drawTable(String data[][], String header[])
			{
				JPanel jpl2 =  new JPanel();
				System.out.print("drawing table\n");
				// To w sumie wydaje sie banalne
				JTable tabela = new JTable(data, header);

				jpl2.setLayout(new BorderLayout());
				jpl2.add(tabela.getTableHeader(), BorderLayout.PAGE_START);
				jpl2.add(tabela, BorderLayout.CENTER);
				okno.add(jpl2);
			}

		public void nazajeciach()
			{
				//TODO to tylko jest
				JPanel jpl = new JPanel();
				JLabel jl1 = new JLabel("termin oddania");

				tf1.setPreferredSize(new Dimension(128, 32));
				tf2.setPreferredSize(new Dimension(128, 32));

				JButton btn1 = new JButton("filtruj");
				btn1.addActionListener(this);
				jpl.add(jl1);
				jpl.add(tf1);
				jpl.add(tf2);
				jpl.add(btn1);
				okno.add(jpl);
			}

		public void test()
			{

				String[][] output = db1.select("select * from " + currenttable);
				String[] kolumny = db1.getColumns("select * from " + currenttable);
				drawTable(output, kolumny);
			}
		
		@Override
		public void actionPerformed(ActionEvent e)
			{
				System.out.print("klikniety\n");
				System.out.print("od " + tf1.getText() + " do " + tf2.getText() + "\n");
			}
	}
