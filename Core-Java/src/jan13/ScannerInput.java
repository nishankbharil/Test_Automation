package jan13;

import java.util.Scanner;

public class ScannerInput 
{

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Name:");
		String input = sc.nextLine();
		
		System.out.println("Enter Age:");
		int number = sc.nextInt();
		
		sc.delimiter();
		
		System.out.println("You entered "+input +" and "+ number);
		sc.close();
	}

}
