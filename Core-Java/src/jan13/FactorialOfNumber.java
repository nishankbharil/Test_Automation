package jan13;

public class FactorialOfNumber
{

	public static void main(String[] args)
	{
		int num = 3;
		int fact = 1;
		int i = 1;
		
		while (i <= num)
		{
			fact = fact * i;
			i++;
		}
		System.out.println("Factorial of Number is :" + fact );
	}
}