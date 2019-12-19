package Assignments;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TestSet 
{

	public static void main(String[] args) 
	{
		Set<Integer> rollNos = new TreeSet<Integer>();
		rollNos.add(101);
		rollNos.add(158);
		rollNos.add(111);
		rollNos.add(101);
		rollNos.add(110);
		
		System.out.println("Size = "+ rollNos.size());
		
		for (int temp : rollNos )
		{
			System.out.println(temp);
		}
		
		rollNos.remove(111);
		
		System.out.println("Size = "+ rollNos.size());
		
		boolean res = rollNos.contains(110);
		System.out.println(res);
		
		boolean isEmpty1 = rollNos.isEmpty();
		System.out.println(isEmpty1); 
		
		Set<Integer> newRolls = new HashSet<Integer>();
		newRolls.add(125);
		newRolls.add(120);
		newRolls.add(129);
		
		rollNos.addAll(newRolls);
		System.out.println("Size = "+ rollNos.size());
	}
}
