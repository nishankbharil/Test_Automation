package Java_Concepts1;

public class Java_Concept10 {

	public static void main(String[] args) {

		Java_Concept10.sum(10, "hello");

		Java_Concept10.sum(10, 20, 30);

	}

	public static int sum(int a, int b, int c) {

		System.out.println("In Three");
		return c;
	}

	public static String sum(int a, String b) {
		System.out.println("In Two");
		return b;
	}

}
