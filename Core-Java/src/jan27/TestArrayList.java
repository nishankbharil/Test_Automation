package jan27;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class TestArrayList
{

	public static void main(String[] args) 
	{
//		TestArrayList obj = new TestArrayList();
		List<String> names = new ArrayList<String>();
		names.add("Tom");
		names.add("Peter");
		names.add("James");
		names.add("Tom");
		names.add("Jessica");//index = 4
		
		
//		names.add(8);
//		names.add(5);
//		names.add(777777777);
//		names.add(7);
//		names.add(8);//index = 4
		
		Collections.sort(names);
		
		System.out.println(names);
		
		String ele2 = names.get(2);
		System.out.println(ele2);
		System.out.println("Size = "+names.size());
		names.remove(4);
		System.out.println("Size = "+names.size());
		
		boolean res = names.contains("Harry");
		System.out.println(res);
		
		boolean isEmptyList = names.isEmpty();
		System.out.println(isEmptyList);
		
		for (String temp: names)
		{
			System.out.println(temp);
		}
		
	}

}
