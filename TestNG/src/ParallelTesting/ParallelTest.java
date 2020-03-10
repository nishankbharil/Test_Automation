package ParallelTesting;

import org.testng.annotations.Test;

public class ParallelTest {
	
	@Test
	public void test1()
	{
		System.out.println("I am in test 1");
		System.out.println(Thread.currentThread());
	}
	
	@Test
	public void test2()
	{
		System.out.println("I am in test 2");
		System.out.println(Thread.currentThread());
	}

}
