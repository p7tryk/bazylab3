package bazylab3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main
	{
		public static String ip;

		public static void main(String[] args)
			{

				Scanner scanner = new Scanner(System.in);

				System.out.print("wpisz ip do ktorego chcesz sie polaczyc\n");
				Main.ip = scanner.next();
				System.out.print("wpisz tryb:\n1 gui\ninny klawisz konsola\n");
				int i = scanner.nextInt();
				scanner.close();
				if (i == 1)
				{
					GUI gui = new GUI();
				} else
				{
					Permissions user1 = new Permissions("admin", "pwsz");
					Mysql db1 = new Mysql(user1, ip, 3306);

					String test[][];
					String test2[];
					String test3[];
					String test4[];
					test = db1.select("select * from filmy;");
					test2 = db1.getColumns("select * from filmy;");
					test3 = db1.getTables();
					test4 = db1.getColumnTypes("select * from filmy;");
					printArray(test2);
					printArray(test4);
					System.out.print("\n");
					printArrayArray(test);
					System.out.print("\n");
					printArray(test3);

//		//TODO insert prymitiwa
//		List<Object> values = new ArrayList<Object>();
//		
//		values.add(new Integer(69)); //nie wiem jak to zrobic porzadnie
//		values.add(new String("tytul"));
//		values.add(new Integer(2069));
//		values.add(new Integer(420));
//		values.add(new Integer(66642066));
//		values.add(new String("test")); 
//		
//		db1.insertInto("filmy", test2, values);

					db1.close();
				}
			}

		public static void printArrayArray(String[][] tablica)
			{
				// evil copypaste magic https://stackoverflow.com/a/46018033
				System.out.println(
						Arrays.deepToString(tablica).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
			}

		public static void printArray(String[] tablica)
			{
				System.out.println(Arrays.deepToString(tablica));
			}
	}
