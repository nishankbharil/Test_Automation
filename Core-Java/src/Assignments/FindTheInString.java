package Assignments;

public class FindTheInString 
{

	public static void main(String[] args) 
	{
		String s = "These are the days of the endless summer";

		String [] s1 = s.split(" ");

		int counter = 0;

		for (String temp : s1)
		{
			temp = temp.toLowerCase();
			if (temp.contains("the"))
			{
				counter = counter + 1;
			}
		}
		System.out.println(counter);

	}

}