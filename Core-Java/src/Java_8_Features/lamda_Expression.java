package Java_8_Features;

import java.util.ArrayList;
import java.util.List;

public class lamda_Expression {

	public static void main(String[] args) {

		
		List<Integer> li = new ArrayList<Integer>(); 
		
		for (int i=1; i<200000; i++)
		{
			li.add(i);
		}
		
		li.forEach(i -> System.out.println(i));

	}

}
