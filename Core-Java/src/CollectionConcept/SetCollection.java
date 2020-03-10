package CollectionConcept;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetCollection 
{

	public static void main(String[] args) 
	{
		Set<String> s = new HashSet<String>();
		s.add("Tom");
		s.add("Hary");
		s.add("test");
		
		for (String temp: s)
		{
			System.out.println(temp);
		}
		
		System.out.println("------------------");
		System.out.println(s.size());
		
		s.remove("test");
		
		System.out.println("------------------");
		
		for (String temp: s)
		{
			System.out.println(temp);
		}
		
		System.out.println("------------------");
		
		Iterator<String>s2 = s.iterator();
		
		while(s2.hasNext())
		{
			System.out.println("############");
			System.out.println(s2.next());
		}
		boolean f = s.isEmpty();
		System.out.println(f);
		
		
		
	}
}
