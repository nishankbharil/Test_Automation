package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.OHLoginPage;


public class OHLoginPageTest extends TestBase
{
	OHLoginPage loginpage;
	
	public OHLoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginpage = new OHLoginPage();	
	}
	
	@Test(priority=1)
	public void VerifyHRMLogoTest(){
		boolean b = loginpage.verifyHRMLogo();
		Assert.assertTrue(b, "Orange HRM Logo is present on login page");
	}
	@Test(priority=2)
	public void LoginOrangeHRMTest(){
		loginpage.verifyLogin();
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
		
}
