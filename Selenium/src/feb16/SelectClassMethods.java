package feb16;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SelectClassMethods

{
	@Test
	public void SelectMethods() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement ele1 = driver.findElement(By.xpath("//select[@id='day']"));
		Select ele2 = new Select(ele1);
		List<WebElement> obj1 = ele2.getOptions();
		for (int i = 0; i < obj1.size(); i++) {
			System.out.println(obj1.get(i).getText());
			ele2.selectByIndex(i);
		}
		System.out.println(ele1.getAttribute("id"));
		driver.findElement(By.xpath("//input[@id='u_0_l']")).sendKeys("Nishank");
//		System.out.println(driver.findElement(By.xpath("//input[@id='u_0_l']")).getSize());
		System.out.println(driver.findElement(By.xpath("//input[@id='u_0_l']")).getAttribute("value"));
		Thread.sleep(5000);
		driver.close();
		driver.quit();
	}
}
