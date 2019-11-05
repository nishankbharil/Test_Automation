package Practice;

public class Remove_Junk_Characters {

	public static void main(String[] args) {
		String s = "#@#@$%87287687TYU^%&^5YF7676uyytyut#%457%465^(*&&&^";

		String s1 = s.replaceAll("[^a-z0-9A-Z]", "");
		System.out.println(s1);

	}

}
