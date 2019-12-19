package Practice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Count_Of_Each_Character_In_String_Hash_Map {

	public static void main(String[] args) {
		String s = "hhhhhithisisjava";

		{
			// Creating a HashMap containing char
			// as a key and occurrences as a value
			HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();

			// Converting given string to char array

			char[] strArray = s.toCharArray();

			// checking each char of strArray
			for (char c : strArray) {
				if (charCountMap.containsKey(c)) {

					// If char is present in charCountMap,
					// incrementing it's count by 1
					charCountMap.put(c, charCountMap.get(c) + 1);
				} else {

					// If char is not present in charCountMap,
					// putting this char to charCountMap with 1 as it's value
					charCountMap.put(c, 1);
				}
			}

			// Printing the charCountMap
//			for (Map.Entry entry : charCountMap.entrySet()) {
//				System.out.println(entry.getKey() + " " + entry.getValue());
//				
//			}

			Set keyset = charCountMap.keySet();
			Iterator it = keyset.iterator();

			while (it.hasNext()) {
				char key = (char) it.next();
				int value = (Integer)charCountMap.get(key);

				if (value == 2) {
					System.out.println(key);
				}
			}

		}

	}

}
