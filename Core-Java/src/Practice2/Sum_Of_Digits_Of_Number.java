package Practice2;

public class Sum_Of_Digits_Of_Number {

	public static void main(String[] args) {

		int a = 123456;
		int sum = 0;

		while (a != 0) {
			sum = sum + a % 10;
			a = a / 10;

		}

		System.out.println(sum);

	}

}
