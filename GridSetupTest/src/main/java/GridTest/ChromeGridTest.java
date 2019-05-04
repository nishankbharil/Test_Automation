package GridTest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeGridTest {

	public static void main(String[] args) throws MalformedURLException {
		
		//1. Define desired capabilities
		DesiredCapabilities cap = new DesiredCapabilities();
		
		cap.setBrowserName("chrome");
		cap.setPlatform(Platform.WINDOWS);
		
		//2. Chrome options definitions
		ChromeOptions options = new ChromeOptions();
		options.merge(cap);
		
		String hubUrl = "http://9.85.120.54:4444/wd/hub";
		
		WebDriver driver = new RemoteWebDriver(new URL(hubUrl),options);
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		System.out.println(driver.getTitle());
		
		driver.quit();	

	}

}
