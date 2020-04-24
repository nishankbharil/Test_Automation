package TestNG_Listener;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Factory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNG_Listener.TestNGListerner.class)
public class MyFirstTestCase {
	
	@Factory
	@Test
	public Object GoogleTitleVerify() {
		
		int a = 10;
		WebDriver driver = new ChromeDriver();

		driver.get("http://www.google.com");
		System.out.println(driver.getTitle());
		driver.quit();
		return a;
	}

}
