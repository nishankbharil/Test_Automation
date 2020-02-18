package Star_Print_Programs_Practice;

public class Pyramid_1_5_Right {

	public static void main(String[] args) {

		
	/**	
	            1
		      2 2
		    3 3 3
		  4 4 4 4
		5 5 5 5 5
	**/

		
		for (int i = 1; i<=5; i++)
		{
			for (int j = 5; j>=i; j--)
			{
				System.out.print(" ");
			}
			for (int k = 1; k<=i; k++)
			{
				System.out.print(i);
			}
			System.out.println();
		}
	}

}

//i - 0
//j - 4
//k - 0
