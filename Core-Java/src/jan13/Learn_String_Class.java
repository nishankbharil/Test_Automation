package jan13;

import java.util.Scanner;

public class Learn_String_Class {

	// static String s = " Hello World";

	public static void main(String[] args) {

		Learn_String_Class S1 = new Learn_String_Class();

		S1.length1();
		S1.toUpperCase1();
		S1.toLowerCase1();
		S1.trim1();
		S1.concat1();
		S1.equals1();
		S1.equalsIgnoreCase1();
		S1.contains1();
		S1.replace1();
		S1.split1();
		S1.startsWith1();
		S1.endsWith1();
		S1.subString1();

	}

	public void length1() {
		Scanner sc = new Scanner(System.in);
		String s4 = sc.nextLine();
		int S2 = s4.length();

		System.out.println(S2);
		sc.close();
	}

	public void toUpperCase1() {
		Scanner sc = new Scanner(System.in);
		String s4 = sc.nextLine();
		String S2 = s4.toUpperCase();

		System.out.println(S2);
		sc.close();
	}

	public void toLowerCase1() {
		Scanner sc = new Scanner(System.in);
		String s4 = sc.nextLine();
		String S2 = s4.toLowerCase();

		System.out.println(S2);
		sc.close();
	}

	public void trim1() {
		Scanner sc = new Scanner(System.in);
		String s4 = sc.nextLine();
		String S2 = s4.trim();

		System.out.println(S2);
		sc.close();
	}

	public void concat1() {
		Scanner sc = new Scanner(System.in);
		String s4 = sc.nextLine();
		String S2 = s4.concat("ABC");

		System.out.println(S2);
		sc.close();
	}

	public void equals1() {
		Scanner sc = new Scanner(System.in);
		String s4 = sc.nextLine();
		Boolean S2 = s4.equals("  Hello World");

		System.out.println(S2);
		sc.close();
	}

	public void equalsIgnoreCase1() {
		Scanner sc = new Scanner(System.in);
		String s4 = sc.nextLine();
		Boolean S2 = s4.equalsIgnoreCase("  HELLO World");

		System.out.println(S2);
		sc.close();
	}

	public void contains1() {
		Scanner sc = new Scanner(System.in);
		String s4 = sc.nextLine();
		Boolean S2 = s4.contains("  H");

		System.out.println(S2);
		sc.close();
	}

	public void replace1() {
		Scanner sc = new Scanner(System.in);
		String s4 = sc.nextLine();
		String S2 = s4.replace("  H", "H");

		System.out.println(S2);
		sc.close();
	}

	public void split1() {
		Scanner sc = new Scanner(System.in);
		String s4 = sc.nextLine();
		String[] S2 = s4.split(" ");

		for (String temp : S2) {
			System.out.println(temp);
		}
		sc.close();
	}

	public void startsWith1() {
		Scanner sc = new Scanner(System.in);
		String s4 = sc.nextLine();
		boolean S2 = s4.startsWith("  ");

		System.out.println(S2);
		sc.close();
	}

	public void endsWith1() {
		Scanner sc = new Scanner(System.in);
		String s4 = sc.nextLine();
		boolean S2 = s4.endsWith("World");

		System.out.println(S2);
		sc.close();
	}

	public void subString1() {
		Scanner sc = new Scanner(System.in);
		String s4 = sc.nextLine();
		String S2 = s4.substring(3);
		String S3 = s4.substring(2, 7);

		System.out.println(S2);
		System.out.println(S3);
		sc.close();
	}

}
