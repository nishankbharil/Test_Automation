package Star_Print_Programs_Practice;

public class Pyramid_1_5 {

	public static void main(String[] args) {
		
		
		//Pyramid Type 1
		
		/**	
		 		*
	           * *
	          * * *
		     * * * *
		    * * * * * 
		**/
		
		for (int i = 1; i<=5; i++)
		{
			for (int j=5; j>=i; j--)
			{
				System.out.print(" ");
			}
			for (int k = 1; k<=i; k++)
			{
				System.out.print(" *");
			}
			System.out.println();
		}
	}

}
