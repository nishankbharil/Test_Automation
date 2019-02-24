package feb23;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Lib.Common;

public class Firefox2_Browse_Upload_File {

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
		
// 		when error : can not find firefox binary
//		System.setProperty("webdriver.firefox.bin", "/location/of/firefox.exe");
		
		
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.get("http://localhost:8999/dwrdemo/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Common.sleep(2000);
		driver.findElement(By.linkText("File upload")).click();
		Common.sleep(2000);
		driver.findElement(By.id("uploadFile")).click();
		
//		WebElement E1 = driver.findElement(By.id("uploadFile"));
//		E1.sendKeys("C:\\Users\\IBM_ADMIN\\Desktop\\2-17-2019 1-36-49 PM.png");
		
		try {
			Runtime.getRuntime().exec("C:\\My Documents\\Selenium\\Selenium_Softwares\\AutoIt_Demo.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
