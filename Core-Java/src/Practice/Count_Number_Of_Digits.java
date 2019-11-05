package Practice;

import java.util.Scanner;

public class Count_Number_Of_Digits {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number: ");
		int num = sc.nextInt();

		int Count = 0;
		while (num != 0) {
			num = num / 10;
			Count++;
		}

		System.out.println(Count);

	}

}
