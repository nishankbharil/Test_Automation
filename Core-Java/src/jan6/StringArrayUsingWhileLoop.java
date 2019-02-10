package jan6;

public class StringArrayUsingWhileLoop 
{
	public static void main(String[] args) 
	{
		String[] names = {"AAA", "BBB", "CCC", "DDD"};
		int i = 0;
		while (i<names.length)
		{
			System.out.println(names[i]);
			i++;
		}
	}
}
