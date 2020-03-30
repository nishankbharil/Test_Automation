package Star_Print_Programs_Practice;

public class NumberPattern_5_44_333_Left 
{

	public static void main(String[] args) 
	{

		
		/**
		 * 5
		 * 44
		 * 333
		 * 2222
		 * 11111
		 */
		
		
		for (int i=5; i>=1; i--)
		{
			for (int j=5; j>=i; j--)
			{
				System.out.print(j+ " ");
			}
			System.out.println();
		}

	}

}
