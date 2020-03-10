package stepDefinitions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.tools.javac.util.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AssignLeavePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.BrowserFactory;

public class stepDefinitions {

	public WebDriver driver;
	public LoginPage loginPage;
	public HomePage homePage;
	public AssignLeavePage leavePage;
	public BrowserFactory bwserfctry;

	@Given("I launched chrome browser")
	public void i_launched_chrome_browser() {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		leavePage = new AssignLeavePage(driver);
//		bwserfctry = new BrowserFactory(driver, );
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

	}

	@When("I open orange hrm homepage")
	public void i_open_orange_hrm_homepage() {

		driver.get("https://opensource-demo.orangehrmlive.com/");

	}

	@Then("I verify that logo present on page")
	public void i_verify_that_logo_present_on_page() {

		boolean status = driver.findElement(By.xpath("//div[@id='divLogo']//img")).isDisplayed();

		if (status == true) {
			System.out.println("Logo Present");
		} else {
			System.out.println("Logo not Present");
		}

	}

	@Then("I login to hrm application with user {string} and password {string}")
	public void i_login_to_hrm_application_with_user_and_password(String UName, String Pwd) {
		loginPage.loginToOHRM(UName, Pwd);

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
	public void assign_a_leave() throws IOException {
		leavePage.clickOnAssignLeaveLink();
		leavePage.setEmployeeNameEditBox("John Smith");
		leavePage.selectLeaveType("Vacation US");

		leavePage.setFromDate("2020-03-06");
		leavePage.setToDate("2020-03-13");

		leavePage.selectPartialDays("Start Day Only");

		leavePage.setComment("Test");

		leavePage.clickOnAssignButton();
//		leavePage.clickOnConfirmOkButton();
	}

}
