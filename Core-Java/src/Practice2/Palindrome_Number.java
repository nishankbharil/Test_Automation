package Practice2;

public class Palindrome_Number {

	public static void main(String[] args) {

		
		int num = 12321;
		int orgnlNum = num;
		
		int rev = 0;
		
		while(num!=0)
		{
			rev = rev*10 + num%10;
			
			num = num/10;
		}
		
		if (orgnlNum==rev)
		{
			System.out.println("Palindrome Number");
		}
		else
		{
			System.out.println("Not Palindrome Number");
		}

	}

}
