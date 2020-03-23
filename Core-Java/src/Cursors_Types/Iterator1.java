package Cursors_Types;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Iterator1 {

	public static void main(String[] args) {

		
//		Map<Integer, String> hp = new HashMap<Integer, String>();
		
//		hp.put(1, "AAA");
//		hp.put(2, "BBB");
//		hp.put(3, "CCC");
//		hp.put(4, "DDD");
//		hp.put(5, "EEE");
//		
//		
//		System.out.println(hp);
//		
//		Set<Integer> s = hp.keySet();
//		
//		Iterator<Integer> it = s.iterator();
//		
//		while(it.hasNext())
//		{
//			int i = it.next();
//			if (i==5)
//			{
//				it.remove();
//			}
//		}
//		
//		System.out.println(hp);
		
		System.out.println("-------------------------------------");
		
		Map<String, Integer> hp1 = new HashMap<String, Integer>();
		
		hp1.put("AAA", 1);
		hp1.put("BBB", 2);
		hp1.put("CCC", 3);
		hp1.put("DDD", 4);
		hp1.put("EEE", 5);
		
		
		System.out.println(hp1);
		
		Set<String> s = hp1.keySet();
		
		Iterator<String> it = s.iterator();
		
		while(it.hasNext())
		{
			String s1 = it.next();
			int i = hp1.get(s1);
			
			
			if (i>1)
			{
				it.remove();
			}
		}
		
		System.out.println(hp1);
		

	}

}
