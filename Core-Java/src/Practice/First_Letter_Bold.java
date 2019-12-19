package Practice;

import java.util.ArrayList;
import java.util.List;

public class First_Letter_Bold {

	public static void main(String[] args) {

		String str = "welcome to candid java program";

		// Approach 1

		StringBuilder result = new StringBuilder(str.length());
		String words[] = str.split("\\ ");
		for (int i = 0; i < words.length; i++) {
			result.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1)).append(" ");
		}

		System.out.println(result);

		List<String> li = new ArrayList<String>();

		for (String temp : words) {
			li.add(temp);
		}

		String s1 = li.get(0).substring(0, 1);

		// Approach 2

		char c[] = str.toCharArray();
		c[0] = Character.toUpperCase(c[0]);
		for (int i = 0; i < c.length; i++) {
			if ((c[i]==' ')&& (c[i+1]>='a') && (c[i+1]<='z'))
			{
				c[i+1] = Character.toUpperCase(c[i+1]);
			}

		}
		System.out.println(c);
	}

}
