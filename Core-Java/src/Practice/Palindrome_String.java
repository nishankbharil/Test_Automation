package Practice;

import java.util.Scanner;

public class Palindrome_String {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please Enter a String: ");
		String s = sc.nextLine().toUpperCase();

		System.out.println(s);

//		StringBuffer sb = new StringBuffer(s);
//		StringBuffer s1 = sb.reverse();
//		System.out.println(s1);

		String strRev = "";

		char a[] = s.toCharArray();

		int len = a.length;

		for (int i = len - 1; i >= 0; i--) {
			strRev = strRev + a[i];
		}

		if (s.equals(strRev)) {
			System.out.println("Palindrome");
		} else {
			System.out.println("Not Palindrome");
		}

	}
}
