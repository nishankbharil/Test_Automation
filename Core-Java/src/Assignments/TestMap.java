package Assignments;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TestMap {

	public static void main(String[] args) {
		Map<Integer, String> nameValPairs = new TreeMap<Integer, String>();
		nameValPairs.put(125, "Tom");
		nameValPairs.put(123, "Peter");
		nameValPairs.put(120, "James");
		nameValPairs.put(125, "Monica");
		nameValPairs.put(133, "Jessica");

		System.out.println("Size = " + nameValPairs.size());

		String value = nameValPairs.get(120);
		System.out.println("Value for key 120 = " + value);
		
		nameValPairs.remove(133);
		
		System.out.println("Size = " + nameValPairs.size());
		
		String value1 = nameValPairs.get(133);
		System.out.println("Value for key 133 = " + value1);
		
		boolean res = nameValPairs.containsKey(555);
		System.out.println(res);
		
		Set<Integer> allKeys =  nameValPairs.keySet();
		
		for (int temp: allKeys)
		{
			System.out.println(temp);
			System.out.println(nameValPairs.get(temp));//get the value for temp key
		}
		
	}

}
