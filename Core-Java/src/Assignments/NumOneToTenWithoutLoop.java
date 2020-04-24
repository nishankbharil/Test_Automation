package Assignments;

public class NumOneToTenWithoutLoop 
{

	public static void main(String[] args) 
	{
		NumOneToTenWithoutLoop obj = new NumOneToTenWithoutLoop();

		obj.PrintNumber(1, 10);
	}
	
	public void PrintNumber(int i, int j)
	{
		if (i<=j)
		{
		System.out.println(i);
		
		PrintNumber(i+1, j);
		}
	}
	
	public void tesy(int i, int j) {
		
		if(i<=j)
		{
			System.out.println(i);
			
			tesy(i+1, j);
		}
	}
}


