package bazylab3;

import java.awt.BorderLayout;

import javax.swing.*;

public class Interface
{
	//tutaj bedzie interfejs generowany i zmieniany
	//TODO wszystko?
		
	/*
	 * tak ogolnie mysle zeby tutaj byl array tablic = Mysql.getTables
	 * i do kazdej tabeli byla array kolumn = Mysql.getColumns
	 * wtedy od razu bedzie wiadomo jak rozlozyci interfejs w sensie miejsca
	 */
	JFrame okno;
	Mysql db1;
	public Interface()
	{
		//
		stworzOkno();
		test();
	}
	public void stworzOkno()
	{
		okno = new JFrame("Baza Filmow");
		okno.setSize(800,600);
	}
	public void test()
	{
		String output[][];
		String kolumny[];
		Permissions user1 = new Permissions("admin","pwsz");
		db1 =  new Mysql(user1,"127.0.0.1",3306);
		output = db1.select("select * from filmy");
		kolumny = db1.getColumns("select * from filmy");
		
		//To w sumie wydaje sie banalne
		JTable tabela = new JTable(output, kolumny);
		
		okno.setLayout(new BorderLayout());
		okno.add(tabela.getTableHeader(), BorderLayout.PAGE_START);
		okno.add(tabela, BorderLayout.CENTER);
		okno.setVisible(true);
		db1.close();
	}
}
