package com.automation.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {

	WebDriver driver;

	public static WebDriver startApplication(WebDriver driver, String browserName, String appURL) {

		if (browserName.equalsIgnoreCase("Chrome")) {

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");

			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("Firefox")) {

			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Drivers/geckodriver.exe");

			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("IE")) {

			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/Drivers/IEDriverServer.exe");

			driver = new InternetExplorerDriver();

		} else if (browserName.equalsIgnoreCase("Edge")) {

			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "/Drivers/MicrosoftWebDriver.exe");
		}

		else {
			System.out.println("We do not support this browser");
		}

		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(appURL);

		return driver;

	}

	public static void quitBrowser(WebDriver driver) {
		driver.quit();

	}

}
