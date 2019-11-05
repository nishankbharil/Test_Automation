package Practice;

import java.util.Scanner;

public class Count_Even_And_Odd_Digits {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number: ");
		int num = sc.nextInt();

		int countEven = 0;
		int countOdd = 0;

		while (num > 0) {
			int rem = num % 10;
			if (rem % 2 == 0) {
				countEven++;
			} else {
				countOdd++;
			}
			num = num / 10;
		}
		System.out.println("Even Count: " + countEven);
		System.out.println("Odd Count: " + countOdd);

	}

}
