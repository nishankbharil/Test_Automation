package pageObjects;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utilities.*;

public class BaseClass {

	public static WebDriver driver;
	public static ExcelDataProvider excel;
	public static ConfigDataProvider config;
	public static Helper help;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static ExcelUtility eu;

	@BeforeSuite
	public void setupSuite() {

		Reporter.log("Setting up reports and Test started", true);

		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();

		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(
				System.getProperty("user.dir") + "/Results/Summary/Summary_" + Helper.getCurrentDateTime() + ".html"));
		report = new ExtentReports();
		report.attachReporter(extent);
		eu = new ExcelUtility();

		Reporter.log("Setting Done - Test can be started", true);
	}

	@BeforeClass
	public void setup() {
		Reporter.log("Trying to start Browser and getting application ready", true);
		driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getTestURL());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Reporter.log("Browser and application is up and running", true);
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException {
		Reporter.log("Test is about to end", true);
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.fail("Test Failed",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.pass("Test Passed",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		report.flush();
		Reporter.log("Test completed and reports generated closing the browser", true);
	}

	@AfterClass
	public void tearDown() {
		BrowserFactory.quitBrowser(driver);
		Reporter.log("Browser Closed", true);
		Reporter.log("----------End of test----------", true);

	}
}