package feb16;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Lib.Common;

public class Alert_Class {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///C:/My%20Documents/Selenium/Selenium_Softwares/websites/alert.htm");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//button[.='Click for JS Alert']")).click();
		Common.sleep(2000);
		Alert alert = driver.switchTo().alert();
		alert.accept();

		Common.sleep(2000);

		driver.findElement(By.xpath("//button[.='Click for JS Confirm']")).click();
		alert.dismiss();

		Common.sleep(2000);

		driver.findElement(By.xpath("//button[.='Click for JS Prompt']")).click();
		alert.sendKeys("OK");
		Thread.sleep(1000);
		alert.accept();
		
//		Alert alrt = driver.switchTo().alert();
//		alrt.accept();
//		alrt.dismiss();
//		alrt.sendKeys("test");
	}
}
