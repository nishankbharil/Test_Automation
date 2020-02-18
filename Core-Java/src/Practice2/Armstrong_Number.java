package Practice2;

public class Armstrong_Number {

	public static void main(String[] args) {

		int num = 371;
		int num1 = num;
		int t1 = 0;
		int cube = 0;

		while (num != 0) {
			t1 = num % 10;
			num = num / 10;
			cube = cube + (t1 * t1 * t1);
		}

		if (num1 == cube) {
			System.out.println("Armstrong");
		} else {
			System.out.println("Not Armstrong");
		}

	}

}
