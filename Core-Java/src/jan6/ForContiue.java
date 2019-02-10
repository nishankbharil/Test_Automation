package jan6;

public class ForContiue 
{

	public static void main(String[] args) 

	{
		int [] a = {12,5,55,-1,3,6};
		for (int i = 0; i<a.length; i++)
		{
			if (a[i]<0)
			{
				continue;
			}
			System.out.println(a[i]);
		}
		for (int temp :a) {
			if(temp<0)
			{
				continue;
			}
			System.out.println(temp);
		}

	}

}
