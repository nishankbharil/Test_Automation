package Collection_Conversion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class Arraylist_Set {

	public static void main(String[] args) {

		ArrayList<Object> al = new ArrayList<Object>();

		al.add(10);
		al.add(20);
		al.add("Hello");
		al.add(10);
		al.add(20);
		al.add("Hello");

		System.out.println(al);
		
//		1. array list to hashset object
		HashSet<Object> s1 = new HashSet<Object>(al);

		System.out.println(s1);
		
//		2. set to arraylist object
		
		ArrayList<Object> al1 = new ArrayList<Object>(s1);
		
		al1.add(10);
		
		System.out.println(al1);
		
//		3. arrayList to LinkedList
		
		List<Object> al2 = new LinkedList<Object>(al);
		
		System.out.println(al2);
		
		LinkedHashSet<Object> hs = new LinkedHashSet<Object>(al2);
		
		System.out.println(hs);
	}

}
