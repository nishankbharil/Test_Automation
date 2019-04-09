package jan12;

public class Palindrome 
{

	public static void main(String[] args) 
	{
		
		String s = "NITIN";
		String reverse = "";
		
		int strLen = s.length();
		for (int i = strLen-1; i>=0; i--)
		{
			reverse = reverse + s.charAt(i);
		}
		
		if (s.equals(reverse))
		{
			System.out.println("Palindrome");
		}else
		{
			System.out.println("Not Palindrome");
			
		}
		

	}

}
