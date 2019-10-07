package staticNonStatic;

public class StaticNonStatic {

	public static void main(String[] args) 
	{
		StaticNonStatic.test2();//static function can be called directly without object creation with class name
		
		StaticNonStatic obj1 = new StaticNonStatic();
		obj1.test1();//to call non static function we need to create object of the class
		
	}
	
	public void test1()
	{
		System.out.println("Test1 - This is non static function");
	}
	
	public static void test2()
	{
		System.out.println("Test2 - This is static function");
		
	}

}
