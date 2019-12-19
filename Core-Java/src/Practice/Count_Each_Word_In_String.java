package Practice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Count_Each_Word_In_String {

	public static void main(String[] args) {
		String s = "I lives in Pune and Pune is a is is is good city";

		String s1[] = s.split(" ");

		Map<String, Integer> s2 = new HashMap<String, Integer>();

		for (String s3 : s1) {

			if (s2.containsKey(s3)) {
				s2.put(s3, s2.get(s3) + 1);

			} else {
				s2.put(s3, 1);
			}

		}

//		for (Map.Entry entry : s2.entrySet()) {
//			System.out.println(entry.getKey() + " " + entry.getValue());
//		}
//		
		Set key = s2.keySet();
		Iterator it = key.iterator();

		while (it.hasNext()) {
			String key1 = (String) it.next();
			int value = s2.get(key1);

			if (value == 2) {
				s2.remove(s2.get(key1));

			}
		}

		for (Map.Entry entry : s2.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

	}

}
