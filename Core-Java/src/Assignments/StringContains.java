package Assignments;

public class StringContains 
{

	public static void main(String[] args) 
	{
		String s = "Heelele";
		int len = s.length();
		int count = 0;
		for (int i=0; i<len; i++)
		{
			char c = s.charAt(i);
			if (c=='e')
			{
				count = count +1;
			}
		}
		
		System.out.println("Total e in the word are : "+count);
		if (count>2)
		{
			System.out.println("Count is more then 2");
		}
	
	}

}
