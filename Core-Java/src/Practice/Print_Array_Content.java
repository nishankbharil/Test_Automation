package Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Print_Array_Content {

	public static void main(String[] args) {

		int a[] = { 70, 80, 30, 40, 20 };

		String b[] = { "hi", "this", "is", "java" };
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		for (int temp: a)
		{
			al.add(temp);
		}
		
		

		System.out.println(Arrays.toString(a));

		System.out.println(Arrays.toString(b));
		System.out.println("-------------------------");
		
		Collections.sort(al);
		System.out.println(al);
		
		Collections.sort(al, Collections.reverseOrder());
		
		System.out.println(al);

	}

}
