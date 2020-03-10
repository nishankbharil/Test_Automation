package Practice;

import java.util.Arrays;

public class Check_Equality_Of_Array {

	public static void main(String[] args) {

		int a[] = { 1, 2, 3, 4, 5 };
		int b[] = { 1, 2, 3, 4, 5,8 };

		// Approach1
		boolean flag = Arrays.equals(a, b);
//
//		System.out.println(flag);

		// Approach2
		int lenA = a.length;
		int lenB = b.length;
		boolean f = false;

		if (lenA == lenB) {
			for (int i = 0; i < lenA; i++) {
				if (a[i] != b[i]) {
					f = true;
				}
			}
		} else {
			f=true;
		}

		if (f == true) {
			System.out.println("Arrays are not equal");
		}
		else
		{
			System.out.println("Arrays are equal");
		}

	}

}
