package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) // this driver comes from the main driver in the test class
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "welcome")
	WebElement welcomeAdminLink;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	WebElement logoutLink;

	@FindBy(xpath = "//b[contains(text(),'Admin')]")
	WebElement adminLink;

	@FindBy(xpath = "//b[contains(text(),'Leave')]")
	WebElement leaveLink;

	public void clickOnLogout() {
		welcomeAdminLink.click();
		logoutLink.click();
	}

	public void clickOnAdmin() {
		adminLink.click();
	}

	public void clickOnLeave() {
		leaveLink.click();
	}
}
