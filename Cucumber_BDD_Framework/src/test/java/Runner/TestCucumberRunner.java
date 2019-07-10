package Runner;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import gherkin.formatter.Reporter;

@RunWith(Cucumber.class)	
@CucumberOptions(						
			 			features="src/test/resources/Features/5DataPointImport.feature",
						glue={"StepDefinition"},	
						plugin = {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:Reports/Summary/SummaryReport.html","html:target/Cucumber reports"},
						tags= {"@Cluster15"}
				)

public class TestCucumberRunner {
	
	static ExtentReports extent = null; 
    static ExtentHtmlReporter htmlReporter=null;    
    static ExtentTest test = null;
    String Username=null, Password=null;	    
    ExtentTest scenario = null;
    static DateFormat dateFormat;
    static String date1 = null;
    static Date date;
    
	
	@BeforeClass
    public static void initialize() { 
		
		System.out.println("1:Cucumber runner");
		
		// Create object of SimpleDateFormat class and decide the format
   	 	dateFormat = new SimpleDateFormat("MMddyyyy_HHmmss");   	 
	   	//get current date time with Date()
	   	date = new Date();	   	 
	   	// Now format the date
	   	date1= dateFormat.format(date);
		extent = new ExtentReports();	    
    	htmlReporter = new ExtentHtmlReporter("Reports/Summary/ExtentReport_"+ date1 +".html");
		extent.attachReporter(htmlReporter); 
		//test = extent.createTest("Demo Sample Test Name Iteration - ");  
		System.out.println("2 Initializeed....");
    } 
	
	@AfterClass
    public static void teardown() {
		
		//System.out.println("Teardown");
		String strURL = System.getProperty("URL");
        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "Windows 10");
        Reporter.setSystemInfo("URL", strURL);
        Reporter.setTestRunnerOutput("Sample test runner output message");
        System.out.println("3");
        extent.flush();
  } 
	
}