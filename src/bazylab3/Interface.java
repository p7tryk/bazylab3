package bazylab3;

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
		JButton przycisk = new JButton("connect");
		okno.getContentPane().add(przycisk);
		okno.setVisible(true);
	}
}
