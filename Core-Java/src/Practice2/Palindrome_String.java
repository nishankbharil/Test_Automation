package Practice2;

public class Palindrome_String {

	public static void main(String[] args) {

		
		String s1 = "NITIN";
		
		int len = s1.length();
		
		char [] c = s1.toCharArray();
		
		String strRev = "";
		
		for (int i=len-1; i>=0; i--)
		{
			strRev = strRev + c[i];
		}
		
		if(strRev.equals(s1))
		{
			System.out.println("Palindrome");
		}
		else
		{
			System.out.println("Not Palindrome");
		}

	}

}
