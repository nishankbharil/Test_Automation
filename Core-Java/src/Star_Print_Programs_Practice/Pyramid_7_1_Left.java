package Star_Print_Programs_Practice;

public class Pyramid_7_1_Left {

	public static void main(String[] args) {

		/**
		7777777
		666666
		55555
		4444
		333
		22
		1
		**/
		
		int count =7;
		for(int i=1; i<=7; i++)
		{
			for (int j=7; j>=i; j--)
			{
				System.out.print(count);
			}
			count--;
			System.out.println();
		}

	}

}
