package p1;

import org.testng.annotations.Test;

/* Priority does not skip the test
 */
public class Test2 
{
	@Test(priority=2, description = "This is M7 Test")
	public void m7()
	{
		System.out.println("m7");
	}
	
	@Test(priority=1, description = "This is M8 Test")
	public void m8()
	{
		System.out.println("m8");
//		int a = 1/0;
	}
	
	@Test(priority=10, description = "This is M9 Test")
	public void m9()
	{
		System.out.println("m9");
	}
	
}
