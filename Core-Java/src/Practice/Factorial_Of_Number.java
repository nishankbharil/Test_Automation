package Practice;

import java.util.Scanner;

public class Factorial_Of_Number {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter a Number: ");
		int num = sc.nextInt();

		long fact = 1;

		for (int i = num; i > 0; i--) {
			fact = fact * i;

		}
		System.out.println("Factorial of Number is : " + fact);
	}

}
