package bazylab3;

import java.util.Arrays;
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("wpisz tryb:\n1 gui\ninny klawisz konsola\n");
		int i =  scanner.nextInt();
		if(i==1)
		{
			Interface gui = new Interface();	
		}
		else
		{
		
		Permissions user1 =  new Permissions("admin","pwsz");
		Mysql db1 = new Mysql(user1);
		
		String test[][];
		String test2[];
		test = db1.select("select * from filmy;");
		test2 = db1.getColumns("select * from filmy");
		printArray(test2);
		System.out.print("\n");
		printArrayArray(test);
		db1.close();
		}
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
	public static void printArray(String[] tablica)
	{
			System.out.println(Arrays.deepToString(tablica));
	}
}
