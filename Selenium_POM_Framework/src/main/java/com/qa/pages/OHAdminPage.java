package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.base.TestBase;

public class OHAdminPage extends TestBase
{

	@FindBy(id = "menu_admin_Job")
	WebElement Job;
	
	@FindBy(id = "menu_admin_Organization")
	WebElement Organization;
	
	@FindBy(id = "menu_admin_Qualifications")
	WebElement Qualifications;
	
	@FindBy(id = "menu_admin_nationality")
	WebElement Nationalities;
	
	@FindBy(id = "menu_admin_Configuration")
	WebElement Configuration;
	
	@FindBy(id = "menu_admin_viewSystemUsers")
	WebElement Users;
	
	@FindBy(id = "btnAdd")
	WebElement Add;
	
	@FindBy(id = "btnDelete")
	WebElement Delete;
	
	@FindBy(id = "btnSave")
	WebElement Save;
	
	@FindBy(id = "systemUser_userType")
	WebElement UserRole;
	
	@FindBy(id = "systemUser_employeeName_empName")
	WebElement EmployeeName;
	
	@FindBy(id = "systemUser_userName")
	WebElement username1;
	
	@FindBy(id = "systemUser_status")
	WebElement Status;
	
	@FindBy(id = "systemUser_password")
	WebElement password1;
	
	@FindBy(id = "systemUser_confirmPassword")
	WebElement confirmPassword;
	
	public OHAdminPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void addUser()
	{
		Add.click();
		Select s = new Select(UserRole);
		s.selectByVisibleText("ESS");
		
		EmployeeName.sendKeys("Test1 Test1");
		username1.sendKeys("Testing1");
		
		Select s1 = new Select(Status);
		s1.selectByVisibleText("Enabled");
		
		password1.sendKeys("test123");
		confirmPassword.sendKeys("test123");
		
		Save.click();
	}
	
	public void deleteUser()
	{
		Delete.click();
	}
}
