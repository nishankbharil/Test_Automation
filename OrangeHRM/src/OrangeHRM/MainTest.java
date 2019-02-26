package OrangeHRM;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainTest {

	

	
	@Test
	public void Login() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@name='txtUsername']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys("admin123");
		driver.findElement(By.name("Submit")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("welcome")));

		Assert.assertEquals("5", "5");
	}

	@Test
	public void Logout()
	{
//		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		
//		driver.findElement(By.id("welcome"));
//		driver.findElement(By.linkText("Logout"));
	}

}
