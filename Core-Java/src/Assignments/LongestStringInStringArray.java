package Assignments;

public class LongestStringInStringArray
{

	public static void main(String[] args) 
	{
		String [] arr = {"aaa", "bbbb", "aa","ssss", "ee", "tyr"};
		
		String longest = arr[0];
				
		for (String temp : arr)
		{
			if (temp.length()>longest.length())
			{
				longest = temp;
			}
		}
		System.out.println("Longest string in array is - "+ longest);
	}

}
