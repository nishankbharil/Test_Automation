package ParallelTesting;

import org.testng.annotations.Test;

public class ParallelTest {
	
	@Test( groups= {"Execute"}, description="This is first testng test")
	public void test1()
	{
		System.out.println("I am in test 1");
		System.out.println(Thread.currentThread());
	}
	
	@Test(description="This is first second test")
	public void test2()
	{
		System.out.println("I am in test 2");
		System.out.println(Thread.currentThread());
	}

}
