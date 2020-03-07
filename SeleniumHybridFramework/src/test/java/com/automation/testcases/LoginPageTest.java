package com.automation.testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.support.PageFactory;

import com.automation.pages.BaseClass;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.utility.Helper;

public class LoginPageTest extends BaseClass {

	HomePage homePage;
	LoginPage loginPage;

	@Test(groups = "Sanity")
	public void loginApp() throws InterruptedException {

		logger = report.createTest("Scenario_1");

		loginPage = PageFactory.initElements(driver, LoginPage.class);

		logger.info("Starting Application");

		loginPage.loginToOHRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1)); // Example of
																										// Abstraction

		logger.pass("Login OHRM Successful");

//		logger.pass(Helper.randomNumbers(10));
//		logger.pass(Helper.randomCharacters(10));
//		logger.pass(Helper.randomAlphanumeric(10));
//		Thread.sleep(7000);
		homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.clickOnLogout();
	}

	@Test
	public void logout() throws InterruptedException {

		logger = report.createTest("Scenario_2");

		homePage = PageFactory.initElements(driver, HomePage.class);

		loginPage.loginToOHRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
		Thread.sleep(5000);
		homePage.clickOnLogout();
		boolean b = loginPage.verifyLogout();

		if (b == true) {
			logger.pass("Logout Successfull");
		} else {
			Assert.fail("Logout Unsuccessfull");
		}

	}

	@Test
	public void logout3() throws InterruptedException {

		logger = report.createTest("Scenario_3");
		homePage = PageFactory.initElements(driver, HomePage.class);

		loginPage.loginToOHRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
		Thread.sleep(5000);
		
		
		
		homePage.clickOnLogout();
		boolean b = loginPage.verifyLogout();

		if (b == true) {
			logger.pass("Logout Successfull");
		} else {
			Assert.fail("Logout Unsuccessfull");
		}

	}
	
//	@Test(priority = 3)
//	public void TestExcelUtil() {
//
//		logger = report.createTest("Logout from OHRM");
//
//		logger.fail("Logout Unsuccessfull");
//
//		eu.Xls_Reader(System.getProperty("user.dir") + "/TestData/TestData.xlsx");
//
//		eu.addColumn("Excel", "TestData7");
//		eu.addSheet("Excel1");
//		logger.info(eu.getCellData("Excel", 2, 1));
//		logger.info(eu.getCellData("Excel", "TestData5", 2));
//
//		System.out.println(eu.getCellRowNum("Excel", "TestData4", "nnn"));
//		System.out.println(eu.getColumnCount("Excel"));
//		System.out.println(eu.getRowCount("Excel"));
//		System.out.println(eu.isSheetExist("Excel"));
//		eu.removeColumn("Excel", 8);
//		eu.removeSheet("Excel1");
//		eu.setCellData("Excel", "TestData1", 5, "Hello World");
//
//	}

}
