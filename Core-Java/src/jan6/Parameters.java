package jan6;

public class Parameters 
{

	public static void main(String[] args) 
	{
		Parameters obj = new Parameters();
		int result = obj.add(5,7);
		System.out.println(result);
	}
	public int add(int a, int b) 
	{
		int c = a + b;
		return c;
	}

}
