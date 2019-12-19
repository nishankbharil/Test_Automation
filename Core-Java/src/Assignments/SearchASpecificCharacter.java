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
	}
}
