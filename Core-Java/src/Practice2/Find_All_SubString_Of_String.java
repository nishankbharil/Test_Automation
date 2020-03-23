package Practice2;

public class Find_All_SubString_Of_String {

	public static void main(String[] args) {

		String str = "Nishankbharil";
		System.out.println("All substring of abbc are:");
		for (int i = 0; i < str.length(); i++) {
			for (int j = i + 1; j <= str.length(); j++) {
				System.out.println(str.substring(i, j));

			}
		}

	}

}
