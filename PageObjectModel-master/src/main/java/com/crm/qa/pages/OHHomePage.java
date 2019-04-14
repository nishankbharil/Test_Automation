package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OHHomePage
{
	
	@FindBy(xpath = "//img[@src='/webres_5ca6658021cd89.53008077/orangehrmLeavePlugin/images/ApplyLeave.png']")
	WebElement AssignLeave;
	
	@FindBy(xpath = "//img[@src='/webres_5ca6658021cd89.53008077/orangehrmLeavePlugin/images/MyLeave.png']")
	WebElement LeaveList;
	
	@FindBy(xpath = "//img[@src='/webres_5ca6658021cd89.53008077/orangehrmTimePlugin/images/MyTimesheet.png']")
	WebElement TimeSheets;
	
	@FindBy(xpath = "//a[@id='welcome']")
	WebElement WelcomeAdmin;
	
	public boolean verifyAssignLeave()
	{
		return AssignLeave.isDisplayed();
	}
	
	public boolean verifyLeaveList()
	{
		return LeaveList.isDisplayed();
	}
	
	public boolean verifyTimeSheets()
	{
		return TimeSheets.isDisplayed();
	}
	
	public boolean verifyWelcomeAdmin()
	{
		return WelcomeAdmin.isDisplayed();
	}
}
