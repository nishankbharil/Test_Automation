package p1;

import org.testng.annotations.Test;

public class Test1 

{
	@Test(dependsOnMethods= {"m3"}, alwaysRun=true)  //String Array
	public void m1()
	{
		System.out.println("m1");
	}
	
	@Test
	public void m2()
	{
		System.out.println("m2");
	}
	
	@Test
	public void m3()
	{
		System.out.println("m3");
		int a = 1/0;
	}
}
