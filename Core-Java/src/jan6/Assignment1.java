package jan6;

public class Assignment1 
{

	public static void main(String[] args) 
	
	{
		int [] numbers = {5,6,11,3};
		int j = 0;
		for (int i =0; i<numbers.length; i++)
		{
			j = j+numbers[i];
		}
		System.out.println("Addition of all numbers in Array is :"+ " " +j);
	}
}