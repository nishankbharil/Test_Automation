package CollectionConcept;

import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedList1 {

	public static void main(String[] args) {

		LinkedList<Integer> lli = new LinkedList<Integer>();
//		ArrayList<Integer> lli = new ArrayList<Integer>();

		lli.add(1);
		lli.add(4);
		lli.add(6);
		lli.add(2);
		lli.add(9);
		lli.add(3);

		System.out.println(lli);
		
		System.out.println(lli.peek());
		System.out.println(lli.pop());

	}

}
