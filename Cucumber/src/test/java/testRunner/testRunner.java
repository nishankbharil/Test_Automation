package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber" }, features = "Features/", glue = {
		"stepDefinitions" }, dryRun = false, monochrome = true)
//				tags = { "@cluster3" })
public class testRunner {

//	static ExtentReports extent = null;
//	static ExtentHtmlReporter htmlReporter = null;
//	static ExtentTest test = null;
//	String Username = null, Password = null;
//	ExtentTest scenario = null;
//	static DateFormat dateFormat;
//	static String date1 = null;
//	static Date date;
//
//	@BeforeClass
//	public static void initialize() {
//
//		System.out.println("Test Execution Started");
//		System.out.println("Cucumber runner triggered successfully");
//
//		// Create object of SimpleDateFormat class and decide the format
//		dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
//		// get current date time with Date()
//		date = new Date();
//		// Now format the date
//		date1 = dateFormat.format(date);
//		extent = new ExtentReports();
//		htmlReporter = new ExtentHtmlReporter("Reports/Summary/ExtentReport_" + date1 + ".html");
//		extent.attachReporter(htmlReporter);
//		// test = extent.createTest("Demo Sample Test Name Iteration - ");
//		// System.out.println("Extent Report Initialized");
//	}
//
//	@AfterClass
//	public static void teardown() {
//
//		// System.out.println("Teardown");
//		String strURL = System.getProperty("URL");
//		Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
//		Reporter.setSystemInfo("user", System.getProperty("user.name"));
//		Reporter.setSystemInfo("os", "Windows 10");
//		Reporter.setSystemInfo("URL", strURL);
//		Reporter.setTestRunnerOutput("Sample test runner output message");
//		System.out.println("===============TEST EXECUTION ENDED=============");
//		extent.flush();
//	}

}