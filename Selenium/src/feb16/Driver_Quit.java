package feb16;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Driver_Quit {
	@Test
	public void Driver_Quit1() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		try {
			driver.get("https://opensource-demo.orangehrmlive.com/");
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.name("txtUsername")).sendKeys("admin");
			driver.findElement(By.name("txtPassword")).sendKeys("admin123");
			driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@id='test']")).click();
		} catch (Exception e) {
			
			try {
				driver.findElement(By.xpath("//a[@id='welcome']")).click();
				Thread.sleep(1000);
				driver.quit();

			} catch (Exception ex) {

				driver.quit();
			}
		}

	}
}
