package Practice2;

public class Seriese_Of_Prime_Numbers {

	public static void main(String[] args) {

		int num = 50;
		int a = 0;
		String PrimeNumbers = "";

		for (int i = 1; i <= num; i++) {
			int count = 0;

			for (a = i; a >= 1; a--) {
				if (i % a == 0) {
					count++;
				}
			}
			if (count == 2) {
				PrimeNumbers = PrimeNumbers + i + " ";
			}
		}
		System.out.println(PrimeNumbers);
	}
}
