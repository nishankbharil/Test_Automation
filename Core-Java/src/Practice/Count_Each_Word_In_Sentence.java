package Practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Count_Each_Word_In_Sentence {

	public static void main(String[] args) {
		String s = "I lives in Pune and Pune is a good City";

		String s1[] = s.split(" ");

		Map<String, Integer> hmap = new HashMap<String, Integer>();

		for (String temp : s1) {
			if (hmap.containsKey(temp)) {

				hmap.put(temp, hmap.get(temp) + 1);
			} else {
				hmap.put(temp, 1);
			}
		}

		for (Map.Entry entry : hmap.entrySet()) {
			System.out.print(entry.getKey() + " " + entry.getValue()+ " | ");
		}
		System.out.println(hmap);

	}
	
}
