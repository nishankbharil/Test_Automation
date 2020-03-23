package CollectionConcept;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapCollection 

{
	public static void main(String[] args) 
	{
		Map<String, String> m = new HashMap<String, String>();
		
		m.put("Car", "BMW");
		m.put("Bike", "Suzuki");
		m.put("Truck", "TATA");
//		m.replace("Car", "okm");
		System.out.println(m.get(1));
		
		Set<String> s1 = m.keySet();
		
		Iterator<String>s2 = s1.iterator();
//		System.out.println("--------------");
		while(s2.hasNext())
		{
			String a = s2.next();
			
			if (a.equals("Truck")|| a.equals("Bike")||a.equals("Car"))
			{
				m.remove(a);
				if(m.isEmpty())
				{
					break;
				}
			}
		}
		System.out.println(m);
		
	}
}
