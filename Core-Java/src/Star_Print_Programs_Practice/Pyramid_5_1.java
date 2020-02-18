package Star_Print_Programs_Practice;

public class Pyramid_5_1 {

	public static void main(String[] args) {
		
		//Pyramid Type 1 Reverse

	/**	

    * * * * * 
     * * * *
      * * * 
       * * 
        * 
    **/
		
		for(int i=1; i<=5; i++)
		{
			for (int j=1; j<=i; j++)
			{
				System.out.print(" ");
			}
			for (int k= 5; k>=i; k--)
			{
				System.out.print(" *");
			}
			System.out.println();
		}
	}
}
