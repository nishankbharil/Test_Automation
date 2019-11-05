package Practice;

public class Remove_Junk_Special_Characters {

	public static void main(String[] args) {
		String s = "@@@@@ te)((!$%+{_st &&& ** Se^^$&^$len$$%#^%%^%*ium1234567890";
		String s1 = s.replaceAll("[^a-zA-Z0-9]", "");

		System.out.println(s1);

	}

}
