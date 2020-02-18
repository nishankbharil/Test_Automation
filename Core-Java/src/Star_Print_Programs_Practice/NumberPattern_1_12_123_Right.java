package Star_Print_Programs_Practice;

public class NumberPattern_1_12_123_Right {

	public static void main(String[] args) {
		
		
		/**
		 *      1
		 *    2 1
		 *  3 2 1
		 *4 3 2 1
		 */
		
		
		for (int i = 1; i<=5; i++)
		{
			for (int j=5; j>=i; j--)
			{
				System.out.print(" ");
			}
			for (int k=1; k<=i; k--)
			{
				System.out.print(k);
			}
			System.out.println();
		}
		

	}

}
