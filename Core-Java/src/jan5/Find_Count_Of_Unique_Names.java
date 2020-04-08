package jan5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		
		
		for (Entry<String, Integer> temp: hp.entrySet())
		{
			System.out.println(temp.getKey() + "   "+  temp.getValue());
		}
	}
}
