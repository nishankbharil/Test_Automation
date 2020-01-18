package InternetExplorer;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class IE_Test {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");

//		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("admin");
//		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin123");
//		driver.findElement(By.cssSelector("input#btnLogin")).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
	}
}
