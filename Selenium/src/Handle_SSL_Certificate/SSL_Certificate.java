package Handle_SSL_Certificate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class SSL_Certificate {

	@Test
	public void handleCertificate() {
		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

//		System.setProperty("webdriver.chrome.driver", "..\\Selenium\\drivers\\chromedriver.exe");
//		WebDriver driver1 = new ChromeDriver(cap);
//		driver1.get("https://www.cacert.org/");

		System.setProperty("webdriver.gecko.driver", "..\\Selenium\\drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver(cap);
		driver.get("https://www.cacert.org/");
		
	}

}
