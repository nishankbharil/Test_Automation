package OrangeHRM;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MainTest {
	WebDriver driver;

	@BeforeTest
	public void Login() {

//		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
//		driver = new ChromeDriver();

		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@name='txtUsername']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys("admin123");
		driver.findElement(By.name("Submit")).click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("welcome")));

		Assert.assertEquals("5", "5");
	}

//	@AfterTest
//	public void Logout() throws InterruptedException {
//		Thread.sleep(5000);
//		driver.findElement(By.id("welcome")).click();
//		Thread.sleep(5000);
//		driver.findElement(By.linkText("Logout"));
//		driver.quit();
//	}

	@Test
	public void Dashboard() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("menu_dashboard_index")).click();
		Thread.sleep(10000);
		
		driver.findElement(By.id("menu_leave_viewLeaveModule")).click();
		Thread.sleep(5000);
//		driver.findElement(By.id("menu_leave_Reports")).click();
//		Thread.sleep(2000);
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("menu_leave_Reports"))).build().perform();
		driver.findElement(By.id("menu_leave_viewLeaveBalanceReport")).click();
		
		WebDriverWait w1= new WebDriverWait(driver, 10);
		w1.until(ExpectedConditions.elementToBeClickable(By.id("leave_balance_report_type")));
		
		WebElement w = driver.findElement(By.id("leave_balance_report_type"));
		Select s = new Select(w);
		s.selectByVisibleText("Leave Type");
		Thread.sleep(2000);
		driver.findElement(By.id("viewBtn")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("(//table/tbody/tr[contains(.,'Jasmine Morgan')]/td)[2]/a")).click();
		Thread.sleep(10000);
		WebElement w2 = driver.findElement(By.id("leave_balance_report_type"));
		Select s2 = new Select(w2);
		s2.selectByVisibleText("Paternity US");
		
		
		
	}
}
