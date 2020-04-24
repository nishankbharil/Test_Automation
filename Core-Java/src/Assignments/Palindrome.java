package Assignments;

import java.util.Scanner;

public class Palindrome 
{
	public static void main(String[] args)
	{
//		String original, reverse = "";
//		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter The String/Number");
		

//		 int j = sc.nextInt(); // double d = sc.nextDouble(); // 
//		 String s =	 sc.nextLine();
//		 
//		 
//		
//		original = sc.nextLine();
//		int length = original.length();
//		for (int i = length-1; i>=0; i--)
//		{
//			reverse = reverse + original.charAt(i);
//		}
//		if (original.equals(reverse))
//		{
//			System.out.println("Palindrome");
//		}
//		else
//		{
//			System.out.println("Not Palindrome");
//		}
//		sc.close();
		
//		--------------------------------
		
		String s1 = "NITIN";
		
		String rev = "";
		
		int len = s1.length();
		
		for(int i = len-1; i>=0; i--)
		{
			
			rev = rev + s1.charAt(i);
			
			
		}
		
		System.out.println(rev);
	}
}
