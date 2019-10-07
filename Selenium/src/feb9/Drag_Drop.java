package feb9;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import Lib.Common;

public class Drag_Drop {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("file:///C:/My%20Documents/Selenium/Selenium_Softwares/websites/dragdrop.html");
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Common.sleep(2000);

		Actions action = new Actions(driver);
		WebElement sou = driver.findElement(By.id("//li[.='Item #3']"));
		WebElement dest = driver.findElement(By.id("drop"));
		action.dragAndDrop(sou, dest).perform();
		action.moveToElement(sou);
	}

}