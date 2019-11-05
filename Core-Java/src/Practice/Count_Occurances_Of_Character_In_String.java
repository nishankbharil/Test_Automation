package Practice;

public class Count_Occurances_Of_Character_In_String {

	public static void main(String[] args) {
		String s = "Java is a progaramming languageaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		int len1 = s.length();
		String s1 = s.replaceAll("a", "");
		int len2 = s1.length();
		int Occ = len1 - len2;

		System.out.println(Occ);

	}

}
