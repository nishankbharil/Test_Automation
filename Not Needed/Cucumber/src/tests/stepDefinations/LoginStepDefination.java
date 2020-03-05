package tests.stepDefinations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStepDefination 

{

	WebDriver driver;
	
	@Given("^User is already on login page$")
	public void user_already_on_login_page()
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
	}
	
	@When("^Title of login page is Free CRM$")
	public void title_of_login_page_is_free_crm()
	{
		String title = driver.getTitle();
		
		System.out.println(title);

	}
	
	//   \"(.*)\"  //regular expression
	
	@Then("^User enters \"(.*)\" and \"(.*)\"$")
	public void User_enters_username_and_password(String username, String password)
	{
		driver.findElement(By.name("txtUsername")).sendKeys(username);
		driver.findElement(By.name("txtPassword")).sendKeys(password);
	}
	
	
	@Then("^User clicks on login button$")
	public void User_clicks_on_login_button() throws InterruptedException
	{
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(2000);
	}
	
	
	@And("^User is on home page$")
	public void User_is_on_home_page()
	{
		String text = driver.findElement(By.id("welcome")).getText();
		
		if (text.equals("welcome"))
		{
			System.out.println("At home page");
		}
		
	}
	
	@And("^User clicks on Admin tab$")
	public void User_clicks_on_Admin_tab()
	{
		driver.findElement(By.linkText("Admin")).click();
	}
	
	@And("^User clicks on Users link inside User Management$")
	public void User_clicks_on_Users_link_inside_User_Management()
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("menu_admin_UserManagement"))).build().perform();
		driver.findElement(By.id("menu_admin_viewSystemUsers")).click();
	}
	
	@And("^User clicks on Add button$")
	public void User_clicks_on_Add_button() throws InterruptedException {
		Thread.sleep(2000);;
		driver.findElement(By.id("btnAdd")).click();
	}
	@And("^User selects User role$")
	public void User_selects_User_role() {

		WebElement UserType = driver.findElement(By.id("systemUser_userType"));
		Select s = new Select(UserType);
		s.selectByVisibleText("Admin");
	}

	@And("^User selects Username$")
	public void User_selects_Username() {

		driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys("TestAutoamtion User");
	}

	@And("^User Selects status$")
	public void User_Selects_status() {
		
		WebElement s2 = driver.findElement(By.id("systemUser_status"));
		Select se = new Select(s2);
		se.selectByVisibleText("Enabled");

	}

	@Then("^User clicks on Save button$")
	public void User_clicks_on_Save_button() {
		
		driver.findElement(By.id("btnSave")).click();

	}
	
	@And("^User close the browser$")
	public void User_Close_The_Browser()
	{
		driver.quit();
	}

}
