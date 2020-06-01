package bazylab3;

import java.awt.BorderLayout;

import javax.swing.*;

public class Interface
	{
		// tutaj bedzie interfejs generowany i zmieniany
		// TODO wszystko?

		/*
		 * tak ogolnie mysle zeby tutaj byl array tablic = Mysql.getTables i do kazdej
		 * tabeli byla array kolumn = Mysql.getColumns wtedy od razu bedzie wiadomo jak
		 * rozlozyci interfejs w sensie miejsca
		 */
		JFrame okno;
		Mysql db1;
		Permissions user1;

		public Interface()
			{
				stworzOkno();
				login("admin", "pwsz", "localhost", 3306);
				test();
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
				System.out.print("drawing table\n");
				// To w sumie wydaje sie banalne
				JTable tabela = new JTable(data, header);

				okno.setLayout(new BorderLayout());
				okno.add(tabela.getTableHeader(), BorderLayout.PAGE_START);
				okno.add(tabela, BorderLayout.CENTER);
			}

		public void test()
			{
				String[][] output = db1.select("select * from filmy");
				String[] kolumny = db1.getColumns("select * from filmy");
				drawTable(output, kolumny);
			}
	}
