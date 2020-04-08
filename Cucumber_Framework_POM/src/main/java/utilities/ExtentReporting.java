package utilities;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReporting extends BrowserFactory {

	public static WebDriver driver = null;

	public static void main(String[] args) {

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./Results/Summary/Summary.html");

		ExtentReports extent = new ExtentReports();

		extent.attachReporter(htmlReporter);

		ExtentTest test = extent.createTest("Test Extent Report", "This is to test extent report");

		test.log(Status.INFO, "Starting Test Case");

		BrowserFactory.startApplication(driver, "chrome", "https://google.co.in/");

		test.pass("URL Launched");

		test.pass("Test is passed");

		extent.flush();

//		driver.quit();

	}

}
