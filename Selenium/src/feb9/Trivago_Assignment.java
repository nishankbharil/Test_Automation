package feb9;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Lib.Common;

public class Trivago_Assignment
{

	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.trivago.in/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement SearchCity = driver.findElement(By.name("sQuery"));
		SearchCity.clear();
		SearchCity.sendKeys("Pune"+ Keys.ENTER);
		
//		WebElement Search = driver.findElement(By.linkText("Search"));
//		Search.click();
	
		WebElement CheckinDate = driver.findElement(By.xpath("//time[@class='btn-horus__value']"));
		CheckinDate.click();
		
		Common.sleep(2000);
		WebElement SelectDate = driver.findElement(By.xpath("(((//tbody/tr)[4])/td)[7]"));
		SelectDate.click();
		
		
		
		

		driver.close();
		driver.quit();

	}

}
