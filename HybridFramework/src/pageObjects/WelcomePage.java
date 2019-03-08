package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePage {

	@FindBy(linkText = "Connect")
	WebElement Connect;

	@FindBy(linkText = "Threads")
	WebElement Threads;

	public void clickConnect() {
		Connect.click();

	}

	public void clickThreads() {

		Threads.click();

	}
}
