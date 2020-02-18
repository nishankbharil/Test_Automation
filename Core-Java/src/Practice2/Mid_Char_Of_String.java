package Practice2;

public class Mid_Char_Of_String {

	public static void main(String[] args) {

		
		String s1 = "Nishankbharil";
		
		int strLen = s1.length();
		
		int m = strLen/2;
		
		String s2 = s1.substring(m-1, m+2);
		
		System.out.println(s2);
		
		

	}

}
