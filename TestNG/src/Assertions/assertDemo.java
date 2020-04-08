package Assertions;

import org.testng.Assert;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

public class assertDemo {
	
	@Test
	public static void main1()
	{
		Assert.assertEquals(10, 101, "not verified");
		Assert.assertTrue(true);
	
	}

}
