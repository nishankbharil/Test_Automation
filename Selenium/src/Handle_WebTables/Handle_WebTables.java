package Handle_WebTables;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Handle_WebTables {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("https://opensource-demo.orangehrmlive.com/");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

		driver.findElement(By.id("txtUsername")).sendKeys("Admin");

		driver.findElement(By.id("txtPassword")).sendKeys("admin123");

		Thread.sleep(3000);

		driver.findElement(By.id("btnLogin")).click();

		driver.findElement(By.id("menu_admin_viewAdminModule")).click();

		int row = driver.findElements(By.xpath("//table/tbody/tr")).size();

		System.out.println(row);

		int column = driver.findElements(By.xpath("//table/thead/tr/th")).size();

		System.out.println(column);

		for (int i = 2; i <= row; i++) {

			for (int j = 1; j <= column; j++) {
				List<WebElement> ele = driver.findElements(By.xpath("//table/tbody/tr[" + i + "]/td[" + j + "]"));
				System.out.println(ele.get(j).getText());
			}

		}

	}

}
