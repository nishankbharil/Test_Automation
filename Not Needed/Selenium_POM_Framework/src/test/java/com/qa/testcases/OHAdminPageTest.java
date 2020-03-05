package com.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.OHAdminPage;
import com.qa.pages.OHHomePage;
import com.qa.pages.OHLoginPage;
import com.qa.util.TestUtil;

public class OHAdminPageTest extends TestBase 
{
	
	OHHomePage homepage;
	OHLoginPage loginpage;
	OHAdminPage adminPage;
	TestUtil testUtil;
	
	String sheetName = "contacts";

	public OHAdminPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		loginpage = new OHLoginPage();
		adminPage = new OHAdminPage();
		homepage = loginpage.verifyLogin();
	}
	
	@Test(priority = 1)
	public void AddNewUserTest() {
		homepage.clickAdminUserManagement();
		adminPage.addUser();		
	}
	
	@DataProvider
	public Object[][] getData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title, String firstName, String lastName, String company){
//		homePage.clickOnNewContactLink();
//		//contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
//		contactsPage.createNewContact(title, firstName, lastName, company);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
