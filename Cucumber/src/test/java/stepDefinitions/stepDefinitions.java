package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AssignLeavePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.BrowserFactory;
import utilities.ConfigDataProvider;
import utilities.ExcelDataProvider;
import utilities.ExcelUtility;
import utilities.Helper;

public class stepDefinitions {

	public LoginPage loginPage;
	public HomePage homePage;
	public AssignLeavePage leavePage;
	public BrowserFactory bwserfctry;
//	public ITestResult result;
	
	public static WebDriver driver;
	public static ExcelDataProvider excel;
	public static ConfigDataProvider config;
	public static Helper help;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static ExcelUtility eu;

//	@BeforeClass
//	public void setupSuite() {
//
//		Reporter.log("Setting up reports and Test started", true);
//
//		excel = new ExcelDataProvider();
//		config = new ConfigDataProvider();
//
//		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(
//				System.getProperty("user.dir") + "/Results/Summary/Summary_" + Helper.getCurrentDateTime() + ".html"));
//		report = new ExtentReports();
//		report.attachReporter(extent);
//		eu = new ExcelUtility();
//
//		Reporter.log("Setting Done - Test can be started", true);
//	}
	

	@Given("I launched chrome browser")
	public void i_launched_chrome_browser() {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		leavePage = new AssignLeavePage(driver);

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

	}

	@When("I open orange hrm homepage")
	public void i_open_orange_hrm_homepage() {

		driver.get("https://opensource-demo.orangehrmlive.com/");

	}

	@Then("I verify that logo present on page")
	public void i_verify_that_logo_present_on_page() throws IOException {

		boolean status = driver.findElement(By.xpath("//div[@id='divLogo']//img")).isDisplayed();

		if (status == true) {
			System.out.println("Logo Present");
		} else {
			System.out.println("Logo not Present");
		}


	}

	@Then("I login to hrm application with user {string} and password {string}")
	public void i_login_to_hrm_application_with_user_and_password(String UName, String Pwd) throws IOException {
		loginPage.loginToOHRM(UName, Pwd);
		
//		report.flush();

	}

	@Then("Verify logout link")
	public void verify_logout_link() {
		loginPage.verifyLogout();
	}

	@Then("user click on Admin tab")
	public void user_click_on_tab() {

		homePage.clickOnAdmin();

	}

	@Then("user click on Leave tab")
	public void user_click_on_Leave_tab() {
		homePage.clickOnLeave();
	}

	@Then("logout from OHRM application")
	public void logout_from_OHRM_application() {
		homePage.clickOnLogout();
	}

	@Then("close the browser")
	public void close_the_browser() {

		driver.quit();

	}

	@Then("Assign a leave")
	public void assign_a_leave() throws IOException, InterruptedException {
		leavePage.clickOnAssignLeaveLink();
		leavePage.setEmployeeNameEditBox("John Smith");
		leavePage.selectLeaveType("Vacation US");

		leavePage.setFromDate("2020-03-06");
		leavePage.setToDate("2020-03-13");

		leavePage.selectPartialDays("Start Day Only");

		leavePage.setComment("Test");

		leavePage.clickOnAssignButton();
		Thread.sleep(2000);
		leavePage.clickOnConfirmOkButton();
	}
	
//	@AfterClass
//	public void tearDownMethod(ITestResult result) throws IOException {
//		Reporter.log("Test is about to end", true);
//		if (result.getStatus() == ITestResult.FAILURE) {
//			logger.fail("Test Failed",
//					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
//		} else if (result.getStatus() == ITestResult.SUCCESS) {
//			logger.pass("Test Passed",
//					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
//		}
//		report.flush();
//		Reporter.log("Test completed and reports generated closing the browser", true);
//	}

}
