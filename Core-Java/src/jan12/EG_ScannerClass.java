package jan12;

import java.util.Scanner;

public class EG_ScannerClass 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Name: ");
		String name = sc.nextLine();
		System.out.println(name);
		System.out.println("Enter Number: ");
		int number = sc.nextInt();
		System.out.println(number);
		
		sc.close();
	}
}
