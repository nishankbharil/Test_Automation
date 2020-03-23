package CollectionConcept;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TreeSet1 {

	public static void main(String[] args) {

		Set<Integer> s1 = new TreeSet<Integer>();

		s1.add(4);
		s1.add(7);
		s1.add(4);
		s1.add(1);
		s1.add(5);
		s1.add(8);

		System.out.println(s1);

	}

}
