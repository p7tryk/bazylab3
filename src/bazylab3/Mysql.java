package bazylab3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mysql
{	
	Connection baza;
	Permissions user;
	String url = "jdbc:mysql://127.0.0.1:3306/lab3?serverTimezone=UTC";
	
	public Mysql(Permissions user)
	{
		this.user = user;
	}
	
	public void connect()
	{ 
	        try 
	        {
	        	baza = DriverManager.getConnection(url,user.username,user.password);
	            
	        } 
	        catch (SQLException ex) 
	        {
	        	System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	        }
	        System.out.print("polaczono z baza\n");
	}
	
	public void select(String query)
	{
		try
        {
        Statement zapytanie = baza.createStatement();
        ResultSet wynik = zapytanie.executeQuery(query);
        ResultSetMetaData wynikMeta = wynik.getMetaData();
        int kolumny = wynikMeta.getColumnCount();
        while(wynik.next()) 
        {
        	 for (int i = 1; i <= kolumny; i++) 
        	 {
                 if (i > 1) System.out.print(",  ");
                 String pole = wynik.getString(i);
                 System.out.print(pole);
        	 }
        	 System.out.print("\n");
        }
        }
        
        catch (SQLException ex)
        {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
	}
	public void close()
	{
		try
		{
			baza.close();
		} 
		catch (SQLException ex)
		{
			System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
}
