package feb16;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import Lib.Common;

public class Handle_Multiple_Windows {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("http://localhost:8999/hawtio");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Common.sleep(2000);
		WebElement linkEle = driver.findElement(By.linkText("Connect"));
		linkEle.click();

		driver.findElement(By.xpath("//div/input[@title='Name for this connection']")).sendKeys("Nishank");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div/button[contains(text(),'Connect to remote server')]")).click();
		Thread.sleep(3000);

		String mainWindowHandle = driver.getWindowHandle();
		System.out.println(mainWindowHandle);

		Set<String> allHandles = driver.getWindowHandles();
		Thread.sleep(10000);
		allHandles.remove(mainWindowHandle);

		for (String handle : allHandles) {
			driver.switchTo().window(handle);
		}

		driver.findElement(By.xpath("//input[@value='Close This Window']")).click();
		Thread.sleep(3000);
		driver.switchTo().window(mainWindowHandle);

		WebElement w = driver.findElement(By.xpath("//select[@title='HTTP or HTTPS']"));
		Select s = new Select(w);
		s.selectByVisibleText("https");
		
		driver.quit();
	}

}
