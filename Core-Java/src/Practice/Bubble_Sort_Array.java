package Practice;

public class Bubble_Sort_Array {

	public static void main(String[] args) {
		int a[] = { 1, 3, 2, 6, 3, 8, 4 };

		int temp;

		int len = a.length;

		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] > a[j]) {
					temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		for (int b : a) {
			System.out.println(b);
		}
	}

}
