package jan6;

public class SumOfAllPositiveNumbers 
{
	//Sum of all Positive numbers
	
	public static void main(String[] args) 
	{
		int [] num = {1,-3,-56,0,34,5,7,2,-32};
		int c = 0;
		
		for (int i : num)
		{
			if (i>0)
			{
				c = c+i;
			}
		}
		System.out.println(c);
	}
}
