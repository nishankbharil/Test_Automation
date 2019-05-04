package jan19.Exceptions;

public class TestFinally
{
	static String s;
	public static void main(String[] args) 
	{
		System.out.println("Start");
		try
		{
			int a = 1/0;
			System.out.println(a);
			
			System.out.println(s.length());
			
		}catch(ArithmeticException ex)
		{
			System.out.println("Cant divide by zero");
			ex.printStackTrace();
		}finally {
			System.out.println("Inside Finally");
		}
		System.out.println("End");
	}
}