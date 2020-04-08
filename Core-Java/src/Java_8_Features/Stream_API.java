package Java_8_Features;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Stream_API {
	
	public static void main(String[] args) {
		
		
		List<Integer> li = new ArrayList<Integer>(); 
		
		for (int i=1; i<200; i++)
		{
			li.add(i);
		}
		
		li.parallelStream().forEach(i ->{
			System.out.println(i);
		});

		
	}

}
