package Practice;

public class Max_Min_Element_In_Array {

	public static void main(String[] args) {
		int a[] = { 90, 20, 1000, 30, 6000 };

		int maxNum = a[0];
		int minNum = a[0];
		int len = a.length;

		for (int i = 0; i <= len - 1; i++) {
			if (maxNum < a[i]) {
				maxNum = a[i];
			}
		}

		for (int i = 0; i <= len - 1; i++) {
			if (minNum > a[i]) {
				minNum = a[i];
			}
		}

		System.out.println("Max Number is : "+maxNum);
		System.out.println("Min Number is : "+minNum);
	}

}
