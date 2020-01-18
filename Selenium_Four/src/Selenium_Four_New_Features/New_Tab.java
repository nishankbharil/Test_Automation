package Selenium_Four_New_Features;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class New_Tab {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		String parent = driver.getWindowHandle();
		System.out.println(parent);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/");

		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("admin");
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin123");
		driver.findElement(By.cssSelector("input#btnLogin")).click();

		WebDriver newDriver = driver.switchTo().newWindow(WindowType.TAB);
		String child = newDriver.getWindowHandle();
		System.out.println(child);
		newDriver.get("https://keep.google.com/u/0/#home");

		newDriver.findElement(By.cssSelector("span.RveJvd.snByac")).click();

		WebDriver newDriver2 = driver.switchTo().newWindow(WindowType.TAB);
		newDriver2.get("https://www.google.co.in/");
		newDriver2.findElement(By.cssSelector("input.gLFyf.gsfi")).sendKeys("Testing");
		newDriver2.findElement(By.cssSelector("input.gNO89b")).click();
		newDriver2.close();
		
		newDriver.switchTo().window(child);
		Thread.sleep(5000);
		newDriver.close();
		
		driver.switchTo().window(parent);
		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("admin");
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin123");
		driver.findElement(By.cssSelector("input#btnLogin")).click();
		driver.close();
		
		Actions a = new Actions(driver);
		a.build().perform();
	}

}
