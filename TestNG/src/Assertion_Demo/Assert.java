package Assertion_Demo;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Assert {

	@Test
	public void HardAssert() {
		System.out.println("Hardassert method was started");
		assertTrue(false);
		System.out.println("Hardassert method was failed");
	}

	@Test
	public void SoftAssert() {
		SoftAssert SoftAssertion = new SoftAssert();
		System.out.println("softassert method was started");
		SoftAssertion.assertTrue(false);
		System.out.println("softassert method was failed");
		SoftAssertion.assertAll();// It throw the exception
	}
}