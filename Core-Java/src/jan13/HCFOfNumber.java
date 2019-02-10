package jan13;

public class HCFOfNumber
{

	public static void main(String[] args)
	{
		int num1 = 10;
		int num2 = 12;
		int HCF = 0;
		
		for (int i = 1; i<=num1 && i<=num2; i++)
		{
			if (num1%i == 0 && num2%i == 0)
			{
				HCF = i;
			}
		}
		System.out.println("HCF is : "+HCF);
	}
}