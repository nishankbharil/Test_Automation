package jan20;

public class SearchNumberInArray
{

	public static void main(String[] args)
	{
		int arr[] = {10,20,30,40,50,60,70,70};
		int num = 70;
		int counter = 0;
		for (int i: arr)
		{
			if (i==num)
			{
				counter = counter+1;
			}
		}
		System.out.println(counter);
	}
}