package Chrome_Options;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Chrome_Options {

	
	@Test
	private void test1() {
		
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();

		String s1 = options.getBrowserName();

		options.setHeadless(true);
		System.out.println(s1);

		WebDriver driver = new ChromeDriver(options);

		driver.get("https://opensource-demo.orangehrmlive.com/");

		driver.quit();

	}

}
