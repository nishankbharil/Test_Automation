package Cursors_Types;

import java.util.ArrayList;
import java.util.ListIterator;

public class List_Iterator {

	public static void main(String[] args) {

		
		ArrayList<String> s1 = new ArrayList<String>();
		s1.add("AAA");
		s1.add("BBB");
		s1.add("CCC");
		s1.add("DDD");
		s1.add("EEE");
		
		System.out.println(s1);
		
		ListIterator<String> li = s1.listIterator();
		
		
		while(li.hasNext())
		{
			System.out.println(li.next());
		}
		li.add("FFF");
		System.out.println(s1);
		System.out.println(li.hashCode());
		
		
		if (li.hasPrevious());
		{
			System.out.println(li.previous());
		}
		
		
	
		

	}

}
