package com.automation.testcases;

import org.testng.annotations.Test;
import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import com.automation.pages.AssignLeavePage;
import com.automation.pages.BaseClass;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.utility.Helper;
import com.aventstack.extentreports.MediaEntityBuilder;

public class AssignLeaveTest extends BaseClass {

	HomePage homePage;
	LoginPage loginPage;
	AssignLeavePage assignLeavePage;

	@Test(groups = "Sanity")
	public void loginApp() throws InterruptedException, IOException {

		logger = report.createTest("Test Assign Leave");

		loginPage = PageFactory.initElements(driver, LoginPage.class);
		homePage = PageFactory.initElements(driver, HomePage.class);
		assignLeavePage = PageFactory.initElements(driver, AssignLeavePage.class);

		logger.info("Starting Application");

		loginPage.loginToOHRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));

		logger.pass("Login OHRM Successful");

		homePage.clickOnLeave();
		assignLeavePage.clickOnAssignLeaveLink();
		assignLeavePage.setEmployeeNameEditBox("John Smith");
		assignLeavePage.selectLeaveType("Vacation US");

		assignLeavePage.setFromDate("2020-03-06");
		assignLeavePage.setToDate("2020-03-13");

		assignLeavePage.selectPartialDays("Start Day Only");

		assignLeavePage.setComment("Test");

		logger.info("Step Passed",
				MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());

		assignLeavePage.clickOnAssignButton();
		assignLeavePage.clickOnConfirmOkButton();
		assignLeavePage.VerifyLeaveTable();

//		logger.pass(Helper.randomNumbers(10));
//		logger.pass(Helper.randomCharacters(10));
//		logger.pass(Helper.randomAlphanumeric(10));
//		Thread.sleep(7000);
		homePage.clickOnLogout();
	}

	@Test(groups = "login")
	public void loginApp1() throws InterruptedException, IOException {

		logger = report.createTest("Unassign Leave");

		loginPage = PageFactory.initElements(driver, LoginPage.class);
		homePage = PageFactory.initElements(driver, HomePage.class);
		assignLeavePage = PageFactory.initElements(driver, AssignLeavePage.class);

		logger.info("Starting Application");

		loginPage.loginToOHRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));

		logger.pass("Login OHRM Successful");

		homePage.clickOnLeave();
		assignLeavePage.clickOnAssignLeaveLink();
		assignLeavePage.setEmployeeNameEditBox("John Smith");
		assignLeavePage.selectLeaveType("Vacation US");

		assignLeavePage.setFromDate("2020-03-06");
		assignLeavePage.setToDate("2020-03-13");

		assignLeavePage.selectPartialDays("Start Day Only");

		assignLeavePage.setComment("Test");

		logger.info("Step Passed",
				MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());

		assignLeavePage.clickOnAssignButton();
		assignLeavePage.clickOnConfirmOkButton();
		assignLeavePage.VerifyLeaveTable();

//		logger.pass(Helper.randomNumbers(10));
//		logger.pass(Helper.randomCharacters(10));
//		logger.pass(Helper.randomAlphanumeric(10));
//		Thread.sleep(7000);
		homePage.clickOnLogout();
	}

}
