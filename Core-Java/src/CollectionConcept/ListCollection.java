package CollectionConcept;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
//import java.util.List;

public class ListCollection {

	public static void main(String[] args) {
		List<Integer> l = new ArrayList<Integer>();
		l.add(123);
		l.add(456);
		l.add(789);

		for (int temp : l) {
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
		
		Iterator<Integer>s2 = l.iterator();
		
		while(s2.hasNext())
		{
			System.out.println(s2.next());
		}

	}

}
