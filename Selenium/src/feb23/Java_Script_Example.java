package feb23;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Lib.Common;

public class Java_Script_Example {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://localhost:8999/hawtio/welcome");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Common.sleep(2000);
		driver.findElement(By.linkText("Connect")).click();
		Common.sleep(2000);
				
//		document.getElementsByName('name')[0].value='Tom';---for text field
//		document.getElementsByName('scheme')[0].selectedIndex=1;--for drop down
		
//		Java script executor supports id and name and Tagname
		
		((JavascriptExecutor)driver).executeScript("document.getElementsByName('Name')[0].value='Tom';");
		((JavascriptExecutor)driver).executeScript("document.getElementsByName('Scheme')[0].selectedIndex=1;");
		
		

	}

}
