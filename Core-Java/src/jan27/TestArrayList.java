package jan27;

import java.util.List;
import java.util.ArrayList;

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
