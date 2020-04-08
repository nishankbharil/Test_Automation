package Head_Less_Browser;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.Test;

public class Html_Unit_Browser {

	@Test
	public void headLessTest() throws InterruptedException {

		WebDriver driver = new HtmlUnitDriver();

		driver.get("https://www.w3schools.com/bootstrap/bootstrap_dropdowns.asp");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//button[@id='menu1']")).click();
		List<WebElement> list = driver.findElements(By.xpath("//div/ul[@aria-labelledby='menu1']//li"));
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().contains("HTML")) {
				System.out.println("I clicked on HTML");
				list.get(i).click();
				break;
			}

		}

		driver.quit();
	}
}
