package Practice;

import java.util.Scanner;

public class Reverse_Number {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a Number: ");
		int num = sc.nextInt();

		int rev = 0;

		while (num != 0) {

			rev = rev * 10 + num % 10; //0+last digit of number
			num = num / 10;				// remaining digit of number except last digit
		}
		System.out.println(rev);

	}

}
