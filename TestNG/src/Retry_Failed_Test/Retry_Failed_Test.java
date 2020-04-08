package Retry_Failed_Test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Retry_Failed_Test {

	@Test(retryAnalyzer = MyRetry.class)
	public void test1()
	{
		System.out.println("test1");
	}
	
	@Test (retryAnalyzer = MyRetry.class)
	public void test2()
	{
		System.out.println("test2");
		
//		int i = 1/0;
	}
	
	@Test(retryAnalyzer = MyRetry.class)
	public void test3()
	{
		System.out.println("test3");
		
//		Assert.assertTrue(false);
	}
	
	@Test(retryAnalyzer = MyRetry.class)
	public void test4()
	{
		System.out.println("test4");
	}

}
