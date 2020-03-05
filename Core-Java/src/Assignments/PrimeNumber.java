package Assignments;

public class PrimeNumber {

	public static void main(String[] args) {

		
		int num = 10;
		int a=0;
		
		String PrimeNumber = "";
		
		for (int i=1; i<=10; i++)
		{
			int count = 0;
			
			for (a=i; a>=1; i--)
			{
				if (i%a==0)
				{
					count = count+1;
				}
			}
			if(count==2)
			{
				PrimeNumber = PrimeNumber + " " + i;
			}
			System.out.println(PrimeNumber);
		}
		

	}

}
