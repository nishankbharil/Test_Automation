package Practice2;

public class Bubble_Sort_Array {

	public static void main(String[] args) {

		int[] a = { 10, 20, 50, 30, 70, 60, 80, 10, 15, 2 };

		int len = a.length;
		int temp = 0;

		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
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
