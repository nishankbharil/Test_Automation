package p1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 

//TestNG supports negative priority also
{
	@Test(dependsOnMethods= {"m3"}, alwaysRun=true)  //String Array
	public void m1()
	{
		System.out.println("Logout");
		Assert.assertEquals(12, 13);
	}
	
	@Test()
	public void m2()
	{
		System.out.println("m2");
	}
	
	@Test
	public void m3()
	{
		System.out.println("Login to app");
		int a = 1/0;
	}
}
