package dec30;

public class Method_In_Java 

{
	public static void main(String[] args)
	{
		System.out.println("Inside Main Function...");
		Method_In_Java hw=new Method_In_Java();
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