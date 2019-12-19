package Assignments;

import java.util.Scanner;
public class SumOfDigitsOfNumbers 
{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Input an integer: ");
		int n = input.nextInt();
		System.out.println("The sum of the digits is: " + sumDigits(n));
		input.close();
	}

	public static int sumDigits(int n) {
		int sum = 0;
		while (n != 0) {
			sum = sum + (n % 10);
			n = n / 10;
		}
		return sum;
	}
	
}