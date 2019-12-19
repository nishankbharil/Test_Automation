package DockerDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.URL;

public class FFDemo {

	@Test
	public void testapp()
	{
		DesiredCapabilities cap = new DesiredCapabilities();
		
//		cap.setBrowserName(BrowserType.FIREFOX);
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
//		WebDriver driver = RemoteWebDriver(new URL("http://localhost:4546?wd?hub"), cap);
		
//		driver.findElement(By.name("q")).sendKeys("Learn Automation");
		
		
		
	}
	
}
