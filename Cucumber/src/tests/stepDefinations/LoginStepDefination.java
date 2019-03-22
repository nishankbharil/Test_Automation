package tests.stepDefinations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
	
	@And("^User close the browser$")
	public void User_Close_The_Browser()
	{
		driver.quit();
	}

}
