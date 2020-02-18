package Practice2;

import java.util.HashMap;
import java.util.Map;

public class Find_Each_Char_Count_In_String {

	public static void main(String[] args) {

		String s1 = "testing";

		char[] c = s1.toCharArray();

		Map<Character, Integer> hm = new HashMap<Character, Integer>();

		for (char temp : c) {
			if (hm.containsKey(temp)) {
				hm.put(temp, hm.get(temp) + 1);
			} else {
				hm.put(temp, 1);
			}
		}

		System.out.println(hm);

		for (Map.Entry entry : hm.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

	}

}
