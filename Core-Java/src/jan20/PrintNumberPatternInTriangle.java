package jan20;

public class PrintNumberPatternInTriangle
{

	public static void main(String[] args) 
	{
		for (int i=1; i<=10; i++)
		{
			for (int j=1; j<=i; j++)
			{
				System.out.print(j);
			}
			System.out.println();
		}

	}

}