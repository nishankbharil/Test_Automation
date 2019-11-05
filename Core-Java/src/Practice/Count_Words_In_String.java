package Practice;

import java.util.Scanner;

public class Count_Words_In_String {

	public static void main(String[] args) {

		String s = "I lives in Pune and Pune is a good City";

//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter a String: ");
//		String s1 = sc.nextLine();

		int Count = 1;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == (' ') && s.charAt(i + 1) != ' ') {
				Count++;
			}
		}

		System.out.println(Count);

		String s1[] = s.split(" ");
		int len = s1.length;
		System.out.println(len);

	}
}