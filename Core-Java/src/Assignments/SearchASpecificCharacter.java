package Assignments;

public class SearchASpecificCharacter 
{

	public static void main(String[] args) 
	{
		String s = "Heeleeloo";
		
		int count = 0;
		char c = s.charAt(1);
		char[] arr = s.toCharArray();
		for (int i = 0; i<arr.length; i++)
		{
			if (arr[i]==c)
			{
				count= count+1;
			}
		}
		if (count > 3)
			System.out.println(false);
		else
			System.out.println(true);
		
		System.out.println("--------------------");
		int count1 = 0;
		
		char c1[] = s.toCharArray();
		
		for (char temp: c1)
		{
			if (temp=='e')
			{
				count1 = count1+1;
			}
		}
		
		System.out.println(count1);
		
		
	}
	

}
