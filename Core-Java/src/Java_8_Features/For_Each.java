package Java_8_Features;

import java.util.ArrayList;
import java.util.List;

public class For_Each {

	public static void main(String[] args) {

		List<Integer> li = new ArrayList<Integer>();
		
		
		li.add(10);
		li.add(20);
		li.add(30);
		
		li.forEach(i -> System.out.println(i));
		

	}

}
