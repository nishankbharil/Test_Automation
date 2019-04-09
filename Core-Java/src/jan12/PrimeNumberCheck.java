package jan12;

public class PrimeNumberCheck 
{

	public static void main(String[] args) 
	{
		PrimeNumberCheck pnc = new PrimeNumberCheck();
		boolean res1 = pnc.isPrime(89);
		System.out.println(res1);
		
		boolean res2 = pnc.isPrime(27);
		System.out.println(res2); 
		
		//PrimeNumberBetweenOneAndHundred obj1 = new PrimeNumberBetweenOneAndHundred();
		
	}	  
		public boolean isPrime(int j)
		{
//			boolean b = true;
			
			for (int index = 2; index < j; index++)
			{
				if (j% index == 0)
				{
					return false;
				}
			}
			return true;
		}
	}
