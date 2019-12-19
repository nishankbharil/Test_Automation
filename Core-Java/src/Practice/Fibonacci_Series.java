package Practice;

import java.util.Scanner;

public class Fibonacci_Series {

	public static void main(String[] args) {

		// 0 1 1 2 3 5 8 13 21
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number: ");
		int num = sc.nextInt();

		int x = 0;
		int y = 1;
		int sum = 0;

		System.out.println(x + " " +y);

		for (int i = 0; i <= num; i++) {
			sum = x + y;
			System.out.println(" " + sum);
			x = y;
			y = sum;
		}
	}
	
//	int f = "I love you";
	
	int a[], b;
	int []c, d;
	
	
	
}
