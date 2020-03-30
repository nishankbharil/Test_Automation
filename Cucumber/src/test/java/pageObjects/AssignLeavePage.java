package pageObjects;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class AssignLeavePage {

	WebDriver driver;

	public AssignLeavePage(WebDriver driver) // this driver comes from the main driver in the test class
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@id='menu_leave_assignLeave']")
	WebElement assignLeaveLink;

	@FindBy(xpath = "//input[@id='assignleave_txtEmployee_empName']")
	WebElement employeeName;

	@FindBy(xpath = "//select[@id='assignleave_txtLeaveType']")
	WebElement leaveType;

	@FindBy(xpath = "//input[@id='assignleave_txtFromDate']")
	WebElement fromDate;

	@FindBy(xpath = "//input[@id='assignleave_txtToDate']")
	WebElement toDate;

	@FindBy(xpath = "//select[@name='assignleave[partialDays]']")
	WebElement partialDays;

	@FindBy(xpath = "//textarea[@id='assignleave_txtComment']")
	WebElement comment;

	@FindBy(xpath = "//input[@id='assignBtn']")
	WebElement assignButton;

	@FindBy(id = "confirmOkButton")
	WebElement confirmOkButton;

	@FindBy(xpath = "//*[@id='content']/div[1]/div[2]/table")
	WebElement leavesTable;

	public void clickOnAssignLeaveLink() {
		assignLeaveLink.click();
	}

	public void setEmployeeNameEditBox(String name) {
		employeeName.sendKeys(name);
	}

	public void selectLeaveType(String LeaveType) {

		Select sel = new Select(leaveType);
		sel.selectByVisibleText(LeaveType);
	}

	public void setFromDate(String FDate) {
		fromDate.clear();
		fromDate.sendKeys(FDate);
	}

	public void setToDate(String TDate) {
		toDate.clear();
		toDate.sendKeys(TDate);
		toDate.sendKeys(Keys.TAB);
	}

	public void selectPartialDays(String PartialDays) {

		Select sel = new Select(partialDays);
		sel.selectByVisibleText(PartialDays);
	}

	public void setComment(String Comment) {
		comment.sendKeys(Comment);
	}

	public void clickOnAssignButton() {
		assignButton.click();
	}

	public void clickOnConfirmOkButton() {
		if (confirmOkButton.isDisplayed())
		{
			confirmOkButton.click();
		}
		
	}

	public void VerifyLeaveTable() throws IOException {

		if (leavesTable.isDisplayed()) {
			System.out.println("Leave is displayed");
		} else {
			System.out.println("Leave is not displayed");	
		}
		
	}

}
