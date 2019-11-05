package Practice;

public class Palindrome_Number {

	public static void main(String[] args) {

		int orgnl_num = 123321;
		int numRev = 0;
		int num = orgnl_num;

		while (num != 0) {
			numRev = numRev * 10 + num % 10;
			num = num / 10;

		}
		if (numRev == orgnl_num)
		{
			System.out.println("Palindrome");
		}
		else
		{
			System.out.println("Not Palindrome");
		}
	}

}
