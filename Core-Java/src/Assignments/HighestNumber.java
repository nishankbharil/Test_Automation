package Assignments;

public class HighestNumber 
{

	public static void main(String[] args) 
	{
		int [] num = {166,167,1100, 2,100,4,-1,5,6,867,7,8,90,23, 244, 100, 342, 8656567};
		int l = num.length;
		int b = num[0];
		for (int a = 0; a<l; a++)
		{
			int c = num[a+1];
			if (b>c)
			{
			} 
			else
			{
				b = c;
			}	
		}
		System.out.println("Highest Number is : "+b);
	}
}
