package bazylab3;

import java.util.Arrays;

public class Main
{

	public static void main(String[] args)
	{
//		Interface gui = new Interface();
		
		Permissions user1 =  new Permissions("admin","pwsz");
		
		Mysql db1 = new Mysql(user1);
		
		String test[][];
		test = db1.select("select * from filmy;");
		printArrayArray(test);
		db1.close();
	}
	
	public static void printArrayArray(String[][] tablica)
	{
			//evil copypaste magic https://stackoverflow.com/a/46018033
			System.out.println(Arrays.deepToString(tablica).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
			
			// stara wersja ale i tak nie bedzie potrzebne jak bedzie interfejs
//			for (String[] i : tablica)
//			{
//				for(String n : i)
//				{
//					System.out.print(n + "");
//				}
//				System.out.print("\n");
//			}
	}
}
