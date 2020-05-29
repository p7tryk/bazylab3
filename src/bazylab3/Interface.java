package bazylab3;

import javax.swing.*;

public class Interface
{
	//tutaj bedzie interfejs generowany i zmieniany
	
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
