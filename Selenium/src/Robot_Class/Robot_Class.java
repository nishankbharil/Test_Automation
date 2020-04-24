package Robot_Class;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;	

public class Robot_Class {

	public static void main(String[] args) throws Exception {
		// It is in built in java 
		// used for alerts and popup
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");

		
		WebDriver driver= new ChromeDriver();
		
		driver.get("https://easyupload.io/?__cf_chl_jschl_tk__=6f3452e103e1d75272086b2edd36de9b86e753ec-1586499607-0-Ab202iCo9S6EC8SQpPqlNTtUYBV9X9lxIwl16t_ZutPJ68SlyG1LsbXlNWw-4ifcrsvEz-8gt203JSTXxtqREJjBQ4oZIcEJPcVBmmPynRnD6_Jj8-YLUB5JwO9rTP1owIYr_CwHoZbjtEH5_RMlp-17WKc7DvaHrg1A4oEY-9qf03z4L3lUby202GMPiRAwPKHGPY5Aupqxg-rUaHzFOKASdzlBixS-D-4LKGlVr8K5asyvYHsZ9Xw7ou31sW9tVSKHjsf9N517egKROFQmTb4");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//form[@class='valign-wrapper']")).click();
		
		Thread.sleep(10000);
		Robot rob = new Robot();
			
	//		rob.keyPress(KeyEvent.VK_DOWN);
		rob.keyPress(KeyEvent.VK_TAB);
		rob.keyPress(KeyEvent.VK_TAB);
		rob.keyPress(KeyEvent.VK_TAB);
		rob.keyPress(KeyEvent.VK_ENTER);
		
		rob.delay(10);
		
		
		
		

	}

}
