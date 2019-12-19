package Practice;

public class Check_Armstrong_Number {

	public static void main(String[] args) {
		int num = 371;
		int t1 = 0;
		int cube = 0;
		int temp = num;
		while (num > 0) {
			t1 = num % 10;
			num = num / 10;
			cube = cube + (t1 * t1 * t1);
		}

		if (temp == cube) {
			System.out.println("Armstrong Number");

		} else {
			System.out.println("Not Armstrong");
		}

	}

}
