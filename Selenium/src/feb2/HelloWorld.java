package feb2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import Lib.Common;

public class HelloWorld 
{
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
//		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
//		System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
		
		WebDriver driver = new ChromeDriver();
//		WebDriver driver = new FirefoxDriver();
//		WebDriver driver = new InternetExplorerDriver();
		
		driver.get("http://localhost:8999/hawtio");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//implicit wait
	
//		driver.navigate().to("http://localhost:8999/hawtio"); this line of code is same as above
		
		Common.sleep(2000);
		WebElement linkEle = driver.findElement(By.linkText("Connect"));
		linkEle.click();
		
		WebElement linkEle1 = driver.findElement(By.name("name"));
		linkEle1.clear();
		linkEle1.sendKeys("Name");
		
		WebElement linkEle5 = driver.findElement(By.name("scheme"));
		Select list = new Select(linkEle5);
		list.selectByVisibleText("https");
		
		WebElement linkEle2 = driver.findElement(By.name("host"));
		linkEle2.clear();
		linkEle2.sendKeys("Host");
		
		WebElement linkEle3 = driver.findElement(By.name("port"));
		linkEle3.clear();
		linkEle3.sendKeys("8081");
		
		WebElement linkEle4 = driver.findElement(By.name("path"));
		linkEle4.clear();
		linkEle4.sendKeys("Path");
		
		WebElement Ele1 = driver.findElement(By.xpath("//a[contains(@data-original-title, 'View and edit wi')]"));
		Ele1.click();
		Common.sleep(2000);
		
		WebElement Ele6 = driver.findElement(By.xpath("//input[@type='checkbox' and @class='ng-pristine ng-valid']"));
		Ele6.clear();
		
		WebElement Ele2 = driver.findElement(By.xpath("//a[contains(@title, 'Create new page')]"));
		Ele2.click();
		Common.sleep(2000);
		
		WebElement Ele3 = driver.findElement(By.xpath("//input[@name='fileName']"));
		Ele3.clear();
		Ele3.sendKeys("Selenium");
		Common.sleep(2000);
		
		WebElement Ele4 = driver.findElement(By.xpath("//input[contains(@type,'submit')][contains(@value, 'Create')]"));
		Ele4.click();

		WebElement Ele5 = driver.findElement(By.xpath("//div[@class='well ng-binding']"));
		String s = Ele5.getText();
		System.out.println(s);
		

		
	}

}
