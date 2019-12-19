package CollectionConcept;

import java.util.Map;
import java.util.TreeMap;

public class MapCollection 

{
	public static void main(String[] args) 
	{
		Map<Integer, String> m = new TreeMap<Integer, String>();
		
		m.put(1, "abc");
		m.put(2, "def");
		m.put(3, "hij");
		m.replace(1, "okm");
		System.out.println(m.get(1));
	}
}
