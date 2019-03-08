package p2;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Test4
{
	@BeforeMethod
	public void login()
	{
		System.out.println("Login");
	}
	
	@AfterMethod
	public void Logout()
	{
		System.out.println("Logout");
	}
	
	@BeforeClass
	public void Launch_URL()
	{
		System.out.println("Launch_URL");
	}
	
	@AfterClass
	public void Close_Browser()
	{
		System.out.println("Close_Browser");
	}
	
	@Test
	public void m10()
	{
		System.out.println("m10");
		int age = 20;
		Assert.assertTrue(age >=21, "Age Criteria not satisfied");
		Assert.assertFalse(age >=21, "Age Criteria not satisfied");
		Assert.assertEquals(age, 21);
		Assert.assertNotEquals(age, 21);
		
		String s = "test";
		Assert.assertNull(s, null);
		Assert.assertNotNull(s, null);
	}
	
	@Test
	public void m11()
	{
		System.out.println("m11");
	}
	
}