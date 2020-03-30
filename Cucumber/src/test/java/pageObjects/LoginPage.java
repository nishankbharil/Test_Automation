package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver ldriver) // this driver comes from the main driver in the test class
	{
		this.driver = ldriver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "txtUsername")
	WebElement uname;

	@FindBy(name = "txtPassword")
	WebElement pass;

	@FindBy(id = "btnLogin")
	WebElement loginButton;

	public void loginToOHRM(String username, String password) {
		uname.sendKeys(username);
		pass.sendKeys(password);
		loginButton.click();
	}

	public boolean verifyLogout() {
		if (uname.isDisplayed()) {
			return true;
		}
		return false;
	}

}
