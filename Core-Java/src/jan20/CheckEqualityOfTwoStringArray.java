package jan20;

import java.util.Scanner;

class CheckEqualityOfTwoStringArray
{

	public static void main(String[] args) 
	{
		String [] arr1 = {"aaa", "bbbb", "aa","ssss", "ee", "tyr"};
		String [] arr2 = {"aaa", "bbbb", "aa","ssss", "ee", "tyr"};
		CheckEqualityOfTwoStringArray a = new CheckEqualityOfTwoStringArray();
		boolean flag = a.isEqual(arr1, arr2);
		System.out.println(flag);					
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the string");
		String m = sc.nextLine();
		System.out.println(m);
		sc.close();
	}
	public boolean isEqual(String [] arr1, String [] arr2)
	{	
		if (arr1.length == arr2.length)
		{
			return true;
		}

		for (int i = 1; i<=arr1.length; i++) 
		{
			if (!arr1[i].equalsIgnoreCase(arr2[i]) )
			{
				return false;
			}
		}
		return true;
	}
}