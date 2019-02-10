package dec30;

public class HelloWorld 

{
	public static void main(String[] args)
	{
		System.out.println("Inside Main Function...");
		HelloWorld hw=new HelloWorld();
		hw.test1();
		hw.test2("Tom");
	}
	public void test1()
	{
		System.out.println("Hello World!!");
	}
	
	public void test2(String name)
	{
		System.out.println("Bye!!" + " " + name);
	}
}