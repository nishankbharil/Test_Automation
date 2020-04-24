package Assignments;

public class ReverseStringWhileLoop
{

	public static void main(String[] args) 
	{
		String [] names = {"A","B","C","D","Nishank","Bharil"};
		
		int i = names.length;
		int k = i-1;
		while(k>=0)
		{
			System.out.print(names[k]);
			k=k-1;
		}
	}
}
