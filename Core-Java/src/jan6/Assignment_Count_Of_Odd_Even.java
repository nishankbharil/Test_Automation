package jan6;

public class Assignment_Count_Of_Odd_Even 
{
	//Count_Of_Odd_Even 
	
	public static void main(String[] args) 
	{
		int [] num = {1,2,3,4,5,6,7,8,9,222,34,8,1,11, 12,18,22};
		int countEven = 0;
		int countOdd = 0;
		int j = num.length -1;
		for (int i = 0; i<=j; i++ )
		{
			if ((num[i]%2) != 1 && num[i] !=1)
			{
				countEven = countEven+1;
				//System.out.println("Even Number :"+num[i]);
			}
			/*else if(num[i]==1)
			{
				
			}*/
			else
			{
				countOdd = countOdd+1;
				//System.out.println("Odd Number :"+num[i]);
			}
		}
		System.out.println("Total Even Number :"+countEven);
		System.out.println("Total Odd Number :"+countOdd);
	}

}
