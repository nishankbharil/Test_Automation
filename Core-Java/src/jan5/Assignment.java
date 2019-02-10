package jan5;

public class Assignment 
{

	public static void main(String[] args)
	{
		int empid = 110;

		if (empid >=1 && empid <=50 )
		{
			System.out.println("Goto Bus1");
		}
		else if (empid >=51 && empid <=100)
		{
			System.out.println("Goto Bus2");
		}
		else if (empid >100)
		{
			System.out.println("wait for 30 mins");
		}
		else
		{
			System.out.println("Invalid Employee Id");
		}
	}
}