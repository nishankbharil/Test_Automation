package Practice2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class Remove_Word_Count_GreaterThenTwo {
	public static void main(String[] args) {
		String str = "pune is good or very good city city life life";
		HashMap<String, Integer> data = new LinkedHashMap<String, Integer>();
		String[] arrData = str.split(" ");
		int len = arrData.length;
		if (len > 1) {
			String key;
			int value = 0;
			for (int i = 0; i < len; i++) {
				if (data.containsKey(arrData[i])) {

					key = arrData[i];
					value = data.get(key);
					// System.out.print(value);
					data.put(key, ++value);

				} else {
					data.put(arrData[i], 1);
				}

			}
			// System.out.print(data);
			// Adding code to print words whose occurance is more than 2
			Set<String> keySet = data.keySet();
			Iterator<String> hmIterator = keySet.iterator();

			while (hmIterator.hasNext()) {
				String keyData = hmIterator.next();
				int ValueData = data.get(keyData);
				if (ValueData >= 2) {
					System.out.println(keyData);

				}

			}
		} else {
			char[] chrData = str.toCharArray();
			HashMap<Character, Integer> datachar = new LinkedHashMap<Character, Integer>();
			char key;
			int value = 0;
			for (int j = 0; j < chrData.length; j++) {
				if (datachar.containsKey(chrData[j])) {
					key = chrData[j];
					value = datachar.get(key);
					datachar.put(key, ++value);

				} else {
					datachar.put(chrData[j], 1);
				}
			}
			System.out.print(datachar);
		}

	}

}
