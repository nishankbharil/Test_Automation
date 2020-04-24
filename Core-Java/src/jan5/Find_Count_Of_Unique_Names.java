package jan5;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Find_Count_Of_Unique_Names {

	public static void main(String[] args) {

		String[] arr = { "Tom", "James", "jessica", "Tom" };

		Map<String, Integer> hp = new HashMap<String, Integer>();
		
		for (String temp: arr)
		{
			if (hp.containsKey(temp))
			{
				hp.put(temp, hp.get(temp)+1);
			}
			else
			{
				hp.put(temp, 1);
			}
		}
		
		
		Set<String> s1 = hp.keySet();
		
		Collection<Integer> col = hp.values();
		
		Iterator<String> itr = s1.iterator();
		
		while(itr.hasNext())
		{
			String a = itr.next();
			int i = hp.get(a);
			
			if (i>1)
			{
				hp.remove(a);
			}
		}
		
		
		for (Entry<String, Integer> temp: hp.entrySet())
		{
			System.out.println(temp.getKey() + "   "+  temp.getValue());
		}
	}
}
