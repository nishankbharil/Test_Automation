package Assignments;

public class ForBreak 
{

	public static void main(String[] args) 
	{
		int [] a = {12,5,55,-1,3,6};
		int i = 0;
		for (i = 0; i<a.length;i++)
		{
			if(a[i] < 0)
			{
				break;
			}
			System.out.println(a[i]);
		}

		for (int temp :a) {
			if(temp<0)
			{
				break;
			}
			System.out.println(temp);
		}

	}
}