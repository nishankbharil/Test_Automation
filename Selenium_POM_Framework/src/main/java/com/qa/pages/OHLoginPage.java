package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class OHLoginPage extends TestBase
{
	@FindBy(name = "txtUsername")
	WebElement UName;
	
	@FindBy(name = "txtPassword")
	WebElement password;
	
	@FindBy(name = "Submit")
	WebElement login;
	
	@FindBy(xpath = "//img[@src='/webres_5ca6658021cd89.53008077/themes/default/images/login/logo.png']")
	WebElement HRMLogo;
	
	// Initializing the Page Objects:
	public OHLoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public OHHomePage verifyLogin()
	{
		UName.sendKeys(prop.getProperty("username"));
		password.sendKeys(prop.getProperty("password"));
		login.click();
		return new OHHomePage();
	}
	
	public boolean verifyHRMLogo()
	{
		return HRMLogo.isDisplayed();
	}
}
