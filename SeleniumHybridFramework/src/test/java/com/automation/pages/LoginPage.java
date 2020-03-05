package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseClass {

	WebDriver driver;

	public LoginPage(WebDriver driver) // this driver comes from the main driver in the test class
	{
		this.driver = driver;
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
