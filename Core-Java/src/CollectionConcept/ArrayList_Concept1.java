package CollectionConcept;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ArrayList_Concept1 {

	public static void main(String[] args) {

		
		Map<Integer, String> mp = new HashMap<Integer, String>();
		mp.put(1, "abc");
		mp.put(2, "def");
		mp.put(3, "ghi");
		
		ArrayList li = new ArrayList<>();
		
		System.out.println(mp.keySet());
		System.out.println(mp.values());
		
		for (Object temp: li)
		{
			System.out.println(li.get((int) temp));
		}
		
		

	}

}
