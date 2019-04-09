package CollectionConcept;

import java.util.HashSet;

public class SetCollection 
{

	public static void main(String[] args) 
	{
		HashSet<String> s = new HashSet<String>();
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
		
		s.iterator();
		boolean f = s.isEmpty();
		System.out.println(f);
	}
}
