package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.OHLoginPage;

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
	public void LoginOrangeHRMTest(){
		loginpage.verifyLogin();
		boolean b = loginpage.verifyHRMLogo();
		Assert.assertTrue(b, "Orange HRM Logo is present on login page");
	}
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
}
