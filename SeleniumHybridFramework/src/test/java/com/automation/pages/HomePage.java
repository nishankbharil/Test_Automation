package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseClass {

	WebDriver driver;

	public HomePage(WebDriver driver) // this driver comes from the main driver in the test class
	{
		this.driver = driver;
	}

	@FindBy(id = "welcome")
	WebElement welcomeAdminLink;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	WebElement logoutLink;

	@FindBy(xpath = "//b[contains(text(),'Admin')]")
	WebElement adminLink;

	public void clickOnLogout() {
		welcomeAdminLink.click();
		logoutLink.click();
	}

	public void clickOnAdmin() {
		adminLink.click();
	}

}
