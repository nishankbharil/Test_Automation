package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class OHHomePage extends TestBase
{
	
	@FindBy(xpath = "//img[@src='/webres_5ca6658021cd89.53008077/orangehrmLeavePlugin/images/ApplyLeave.png']")
	WebElement AssignLeave;
	
	@FindBy(xpath = "//img[@src='/webres_5ca6658021cd89.53008077/orangehrmLeavePlugin/images/MyLeave.png']")
	WebElement LeaveList;
	
	@FindBy(xpath = "//img[@src='/webres_5ca6658021cd89.53008077/orangehrmTimePlugin/images/MyTimesheet.png']")
	WebElement TimeSheets;
	
	@FindBy(xpath = "//a[@id='welcome']")
	WebElement WelcomeAdmin;
	
	@FindBy(id = "menu_admin_viewAdminModule")
	WebElement Admin;
	
	@FindBy(id = "menu_leave_viewLeaveModule")
	WebElement Leave;
	
	@FindBy(id = "menu_pim_viewPimModule")
	WebElement PIM;
	
	@FindBy(id = "menu_admin_UserManagement")
	WebElement Usermanagement;
	
	// Initializing the Page Objects:
	public OHHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	
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
	
	public OHAdminPage clickAdminUserManagement()
	{
		Admin.click();
		Actions action = new Actions(driver);
		action.moveToElement(Usermanagement).build().perform();
		Usermanagement.click();
		return new OHAdminPage();
	}
	
	public void clickLeave()
	{
		Leave.click();
	}
	
	public void clickPIM()
	{
		PIM.click();
	}
}
