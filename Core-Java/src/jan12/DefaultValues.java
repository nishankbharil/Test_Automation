package jan12;

public class DefaultValues
{
	int a = 10;
	static int b = 20;
	static String s = "nishank";
	static DefaultValues d;//null pointer exception
	
	public static void main(String[] args) 
	{
		int c = 0;
		
		DefaultValues dv = new DefaultValues();
		
		System.out.println(dv.a);
		System.out.println(DefaultValues.b);
		System.out.println(c);
		System.out.println(DefaultValues.s);
		d.test();
	}
	public void test()
	{
		
	}

}
