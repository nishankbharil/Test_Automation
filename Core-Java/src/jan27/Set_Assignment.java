package jan27;

import java.util.HashSet;
import java.util.Set;

public class Set_Assignment {

	public static void main(String[] args) {
		
		Set<Integer> MySet = new HashSet<Integer>();
		MySet.add(109);
		MySet.add(105);
		MySet.add(103);
		
		for (int temp: MySet)
		{
			System.out.println(temp);
		}
		
		System.out.println(MySet.iterator().hasNext());
		System.out.println(MySet.iterator().next());
		MySet.remove(109);
		
	}

}
