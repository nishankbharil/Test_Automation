package feb9;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import Lib.Common;

public class Trivago_Assignment
{

	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.trivago.in");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement SearchCity = driver.findElement(By.name("sQuery"));
		Common.sleep(2000);
		SearchCity.clear();
		SearchCity.sendKeys("Pune"+ Keys.ENTER);
		
//		WebElement Search = driver.findElement(By.linkText("Search"));
//		Search.click();
		Common.sleep(1000);
//		WebElement CheckinDate = driver.findElement(By.xpath("(//time[contains(text(),'-- / -- / ----')])[1]"));
//		CheckinDate.click();
		
		Common.sleep(2000);
		
		
		driver.findElement(By.xpath("//time[@datetime ='2019-02-20']")).click();
		Common.sleep(2000);
		driver.findElement(By.xpath("//time[@datetime ='2019-02-22']")).click();
		
//		driver.findElement(By.xpath("//button/span[@class='icon-ic btn-horus__icon btn-horus__icon--room-type icon-rtl']")).click();
		Common.sleep(2000);
		driver.findElement(By.xpath("//button/div/span[2][contains(text(),'Single room')]")).click();
		//------------------------------------------------------------------
//		String myDate = "15-March-2019";
//		String[] strSplit = myDate.split("-");
//		
//		String beforeXpath = "//table/tbody/tr[";
//		String xpath = "]/td[";
//		String afterXpath = "]/time";
//		
//		String d= "15";
//		
//		for (int i = 1; i<6; i++)
//		{
//			for (int j = 1; j<=7; j++)
//			{
//				String s = driver.findElement(By.xpath(beforeXpath+i+xpath+j+afterXpath)).getText();
//				System.out.println(s);
//				if(s.equals(d))
//				{
//					driver.findElement(By.xpath(beforeXpath+i+xpath+j+afterXpath)).click();
//					break;
//					
//				}
//			}
//		}
		
	

//		driver.close();
//		driver.quit();
		
//		Wait wait = new FluentWait<T>(30, TimeUnit.SECONDS,)

	}


}
