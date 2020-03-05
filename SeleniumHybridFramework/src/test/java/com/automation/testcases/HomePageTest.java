package com.automation.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.automation.pages.BaseClass;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;

public class HomePageTest extends BaseClass {

	LoginPage loginPage;
	HomePage homePage;

	@Test(groups = "login", priority = 1)
	public void loginApp() throws InterruptedException {

		logger = report.createTest("Scenario_4");

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

}
