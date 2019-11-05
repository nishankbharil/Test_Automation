package Practice;

public class Missing_Number_In_Array {

	public static void main(String[] args) {

		int a[] = { 1, 2, 3, 4, 5, 6, 8 };

		int len = a.length;
		int sum1 = 0;
		int sum2 = 0;
		int sum3 = 0;

		for (int temp : a) {
			sum1 = sum1 + temp;
		}

		for (int i = 0; i<=len+1; i++) {
			sum2 = sum2 + i;
		}
		
		sum3 = sum2-sum1;
		
		System.out.println("Missing number is: "+sum3);
	}

}
