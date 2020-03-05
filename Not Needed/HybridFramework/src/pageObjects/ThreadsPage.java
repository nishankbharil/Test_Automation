package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ThreadsPage

{

	@FindBy(xpath = "//input[@type='text'][@ng-model='ngModel']")
	WebElement searchBox;

	public void filter(String searchData) {
		searchBox.sendKeys(searchData);
	}

}
