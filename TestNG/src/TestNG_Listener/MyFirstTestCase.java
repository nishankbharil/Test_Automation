package TestNG_Listener;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNG_Listener.TestNGListerner.class)
public class MyFirstTestCase {

	
	@Test
	public void GoogleTitleVerify() {
		WebDriver driver = new ChromeDriver();

		driver.get("http://www.google.com");
		System.out.println(driver.getTitle());
		driver.quit();

	}

}
