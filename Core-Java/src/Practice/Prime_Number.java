package Practice;

import java.util.Scanner;

public class Prime_Number {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number: ");
		int num = sc.nextInt();
		boolean flag = false;

		for (int i = 2; i < num; i++) {

			if (num % i == 0) {
				System.out.println("Prime");
				flag = true;
				break;
			}
		}

		if (flag == false) {
			System.out.println("Composit");
		}

	}

}
