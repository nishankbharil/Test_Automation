package Practice2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Count_Each_Word_in_Sentence {

	public static void main(String[] args) {

		String s1 = "I lives in Pune and Pune is a good city is city city I and";

		String[] s2 = s1.split(" ");

		Map<String, Integer> hp = new HashMap<String, Integer>();

		for (String word : s2) {
			if (hp.containsKey(word)) {
				hp.put(word, hp.get(word) + 1);
			} else {
				hp.put(word, 1);
			}
		}

		for (Map.Entry entry : hp.entrySet()) {

			System.out.println(entry.getKey() +" - "+ entry.getValue());
		}

		System.out.println(hp);
		
		Set keyset = hp.keySet();
		System.out.println(keyset);
		Iterator it = keyset.iterator();
		
		while(it.hasNext())
		{
			String s3 = (String) it.next();
			int value = hp.get(s3);
			
			System.out.println(value);
			if (value>=3)
			{
				hp.remove(value);
			}
		}
		
		System.out.println(hp);

	}

}
