package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.ContactsPage;
import com.qa.pages.OHAdminPage;
import com.qa.pages.OHHomePage;
import com.qa.pages.OHLoginPage;
import com.qa.util.TestUtil;

public class OHHomePageTest extends TestBase {
	OHHomePage homepage;
	OHLoginPage loginpage;
	OHAdminPage adminPage;
	TestUtil testUtil;

	public OHHomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		loginpage = new OHLoginPage();
		homepage = loginpage.verifyLogin();
	}

	@Test(priority = 1)
	public void VerifyHomePageTest() {
		boolean l = homepage.verifyLeaveList();
		boolean w = homepage.verifyWelcomeAdmin();
		boolean a = homepage.verifyAssignLeave();
		
		Assert.assertTrue(l, "Leave list found on home page");
		Assert.assertTrue(w, "Welcome Admin found on home page");
		Assert.assertTrue(a, "Assign leave found on home page");
	}

	@Test(priority = 2)
	public void AddUserinAdminTest() {
		homepage.clickAdminUserManagement();		
	}

	@AfterMethod
	public void tearDown() {
//		driver.quit();
	}
}
