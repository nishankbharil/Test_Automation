package Practice;

public class Filter_Even_Odd_Number_From_Array {

	public static void main(String[] args) {
		int a[] = { 1, 2, 3, 4, 4, 6, 7, 8, 9 };

		int len = a.length;
		int even = 0;
		int odd = 0;

		for (int i = 0; i <= len - 1; i++) {
			if (a[i] % 2 == 0) {
				System.out.println("Even numbers: ");

				System.out.println(a[i] + ",");
				even++;

			} else {
				System.out.println("Odd numbers: ");
				System.out.println(a[i] + ",");
				odd++;
			}
		}
		System.out.println("Total Even Numbers: " + even);
		System.out.println("Total Odd Numbers: " + odd);

		for (int temp : a) {
			if (temp % 2 == 0) {
				System.out.println("Even: " + temp);
			} else {
				System.out.println("Odd: " + temp);
			}
		}
	}
}
