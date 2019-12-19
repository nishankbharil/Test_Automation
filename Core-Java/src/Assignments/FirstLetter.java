package Assignments;

public class FirstLetter 
{

	public static void main(String[] args)
	{
		String s = "Bangalore is a good city and is good place to live";
		String m = "";
		String splitted[] = s.split(" ");
		
		for (String temp: splitted)
		{
			String l = temp.substring(0, 1);
			m = m+ l +".";
		}
		System.out.println(m.toUpperCase());
	}
}