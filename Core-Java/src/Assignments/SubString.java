package Assignments;

public class SubString {

	public static void main(String[] args) {

		String s1 = "Nishank";

		char[] c = s1.toCharArray();

		String s3 = "";

		for (char temp : c) {

			s3 = s3 + "." + temp;

		}

//		System.out.println(s3);

		String s2 = s3.substring(1);

		System.out.println(s2);

	}

}
