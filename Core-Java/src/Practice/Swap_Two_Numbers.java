package Practice;

public class Swap_Two_Numbers {
	public static void main(String[] args) {

		int a = 10;
		int b = 20;

		int c = 0;

		a = a + b;
		b = a - b;
		a = a - b;

		System.out.println(a + " ," + b);
	}
}
