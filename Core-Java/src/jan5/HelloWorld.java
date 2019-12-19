package jan5;

public class HelloWorld 
{

	public static void main(String[] args) 
	{

		System.out.println("Hello World!!");
		HelloWorld hw=new HelloWorld();
		hw.test();
		hw.t2();
		int result = hw.add(10, 20);
		
		System.out.println("Addition of numbers is "+result);
		HelloWorld newObj = hw.getObjectOfHellowWorld();
		newObj.test();
		
	}

	public void test()
	{
		System.out.println("Inside Test");
	}

	public void t2()
	{
		System.out.println("Inside t2");
	}

	public int add(int a, int b)
	{
		int c = a + b;
		System.out.println("Addition of numbers is "+c);
		return c;
	}
	public HelloWorld getObjectOfHellowWorld() //returns class object//
	{
		HelloWorld h = new HelloWorld();
		return h;
	}
}