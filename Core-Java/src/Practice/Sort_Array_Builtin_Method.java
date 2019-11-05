package Practice;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Sort_Array_Builtin_Method {

	public static void main(String[] args) {

		// Approach1
		int a[] = { 1, 4, 3, 6, 2, 8, 11 };
		System.out.println(Arrays.toString(a));

		Arrays.parallelSort(a);
		System.out.println(Arrays.toString(a));

		// Approach2
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));

		// Approach3 (Descending Order)

		Integer b[] = { 1, 4, 3, 6, 2, 8, 11 };
		Arrays.sort(b, Collections.reverseOrder());
		System.out.println(Arrays.toString(b));

	}

}
