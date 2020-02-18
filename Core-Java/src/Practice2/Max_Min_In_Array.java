package Practice2;

public class Max_Min_In_Array {

	public static void main(String[] args) {

		int[] a = { 20, 90, 40, 70, 110, 30, 30, 5 };

		int maxNum = a[0];

		int minNum = a[0];

		int arrLen = a.length;

		for (int i = 0; i < arrLen; i++) {
			if (a[i] > maxNum) {
				maxNum = a[i];
			}
		}

		System.out.println(maxNum);

		for (int i = 0; i < arrLen; i++) {
			if (a[i] < minNum) {
				minNum = a[i];
			}
		}
		
		System.out.println(minNum);

	}

}
