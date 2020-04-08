package Java_8_Features;

public interface Method_With_Body extends Static_Method{
	
	default public void test1()
	{
		System.out.println("This is not static method");
	}

	public abstract void test2();

}
