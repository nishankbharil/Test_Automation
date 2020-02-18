package Practice2;

public class Reverse_Each_Word_InString {

	public static void main(String[] args) {

		String s1 = "Welcome To Pune";

		String s2[] = s1.split(" ");

		String s3 = "";

		for (String temp : s2) {
			StringBuffer sb = new StringBuffer(temp);
			StringBuffer s = sb.reverse();
			s.toString();

			s3 = s3 + s + " ";

		}
		System.out.println(s3);

	}

}
