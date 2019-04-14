package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.OHHomePage;
import com.crm.qa.pages.OHLoginPage;
import com.crm.qa.util.TestUtil;

public class OHHomePageTest extends TestBase{
	OHHomePage homepage;
	OHLoginPage loginpage;
	TestUtil testUtil;
	ContactsPage contactsPage;

	public OHHomePageTest(){
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginpage = new OHLoginPage();
		homepage = loginpage.verifyLogin();
	}
	
	@Test(priority = 1)
	public void LoginOrangeHRMTest() {
		loginpage.verifyLogin();
		boolean b = loginpage.verifyHRMLogo();
		Assert.assertTrue(b, "Orange HRM Logo is present on login page");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
