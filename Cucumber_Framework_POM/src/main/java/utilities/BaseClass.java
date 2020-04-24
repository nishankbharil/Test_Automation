package utilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BaseClass {

	public static WebDriver driver;
//	public static ExcelDataProvider excel;
	public static ConfigDataProvider config;
	public static Helper help;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static ExcelUtility eu;

	@Before
	public void setupSuite() {

		logger.info("Setting up reports and Test started");

//		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();

		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(
				System.getProperty("user.dir") + "/Results/Summary/Summary_" + Helper.getCurrentDateTime() + ".html"));
		report = new ExtentReports();
		report.attachReporter(extent);
		eu = new ExcelUtility();

		driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getTestURL());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		logger.info("Setting Done - Test can be started");
	}

	@After
	public void tearDownMethod() throws InterruptedException {
		logger.info("Test is about to end");
		report.flush();

		Thread.sleep(2000);
		BrowserFactory.quitBrowser(driver);

	}

}