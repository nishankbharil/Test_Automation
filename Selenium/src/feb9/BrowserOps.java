package feb9;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Lib.Common;

public class BrowserOps
{
	public static void main(String[] args) 
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.get("http://localhost:8999/hawtio");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Common.sleep(2000);
		WebElement linkEle = driver.findElement(By.linkText("Threads"));
		linkEle.click();
		
		Common.sleep(2000);
		
		driver.navigate().refresh();
		driver.navigate().back();
		driver.navigate().forward();
		
		WebElement linkEle1 = driver.findElement(By.xpath("//input[@type='text']"));
		linkEle1.sendKeys("MyTest"+ Keys.BACK_SPACE);
	

	}

}
