package Assignments;

import java.util.List;
import java.util.ArrayList;

public class StoreAddress 
{

	public static void main(String[] args)
	{
		
		List<String> address = new ArrayList<String>();
		
		address.add("Parihar Chowk, Aundh");
		address.add("Jaihind Chowk, Aundh");
		address.add("Maratha Mandir, Bavdhan");
		address.add("Wirpo Circle, Hinjewadi");
		address.add("ABC Chowk, Aundh");
		int counter = 0;
		for (String temp: address)
		{
			if (temp.toLowerCase().contains("aundh"))
			{
				counter = counter+1;
			}
		}
		System.out.println(counter);
	}

}
