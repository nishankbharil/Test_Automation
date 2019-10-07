package dec30;

public class PrimeNumber2 {

	public static void main(String[] args) {

		int number = 13;
		boolean flg = false;
		
		for (int i=2; i<number ; i++)
		{
			if (number%i==0)
			{
				System.out.println("Not Prime");
				break;
			}
			else
			{
				flg = true;
			}
		}
		
		if (flg==true)
		{
			System.out.println("Prime");
		}
		
		

	}

}
