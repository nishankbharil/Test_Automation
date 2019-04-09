package CollectionConcept;

import java.util.ArrayList;
import java.util.List;

public class ListCollection 
{

	public static void main(String[] args) 
	{
		ArrayList<Integer> l = new ArrayList<Integer>();
		l.add(123);
		l.add(456);
		l.add(789);
		
		for (int temp:l)
		{
			System.out.println(temp);
		}
		
		System.out.println("--------------------");
		
		System.out.println(l.size());
		
		System.out.println("--------------------");
		
		System.out.println(l.get(0));
		
		System.out.println("--------------------");
		
		System.out.println(l.isEmpty());
		
		System.out.println("--------------------");
		
		System.out.println(l.contains(357));
		
		System.out.println("--------------------");

	}

}
