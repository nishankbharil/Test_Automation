package Assignments;

public class Table_Of_Five 
{

	//Table of 5
	
	public static void main(String[] args) 
	{
		int j = 1;
		for(int i = 5; i<=50; i+=5)
		{
			System.out.println("5 * "+ j +" = "+i);
			j++;
		}
		
		
		int h = 13;
		
		int k =1;
		for (int i=h; i<=h*10; i+=h)
		{
			System.out.println("13 * "+ k +" = "+i);
			k++;
		}
	}
}
