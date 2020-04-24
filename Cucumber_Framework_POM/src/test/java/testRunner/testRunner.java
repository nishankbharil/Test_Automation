package testRunner;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import com.vimalselvam.cucumber.listener.*;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber"},
//		"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:Results/Summary/SummaryReport.html" }, 
features = "Features/01_OrangeHRM.feature", 
glue = {"stepDefinitions" }, 
dryRun = false, 
monochrome = true, 
tags = { "@cluster3" }, strict = true)

public class testRunner {

	static ExtentReports extent;
	static ExtentHtmlReporter htmlReporter;
	static DateFormat dateFormat;
	static String date1 = null;
	static Date date;
	static ExtentTest test;

	@BeforeClass
	public static void initialize() {

		System.out.println("Test Execution Started");
		System.out.println("Cucumber runner triggered successfully");

		// Create object of SimpleDateFormat class and decide the format
		dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
		// get current date time with Date()
		date = new Date();
		// Now format the date
		date1 = dateFormat.format(date);
		extent = new ExtentReports();
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Results\\Summary\\ExtentReport_" + date1 + ".html");
		extent.attachReporter(htmlReporter);
		 test = extent.createTest("Demo Sample Test Name Iteration - ");
		// System.out.println("Extent Report Initialized");
		 extent.flush();
	}

	@AfterClass
	public static void teardown() {

		// System.out.println("Teardown");
		String strURL = System.getProperty("URL");
		Reporter.loadXMLConfig(new File(System.getProperty("user.dir")+"\\src\\test\\java\\extent-config.xml"));
		Reporter.setSystemInfo("user", System.getProperty("user.name"));
		Reporter.setSystemInfo("os", "Windows 10");
		Reporter.setSystemInfo("URL", strURL);
		Reporter.setTestRunnerOutput("Sample test runner output message");
		System.out.println("===============TEST EXECUTION ENDED=============");
		extent.flush();
	}
}