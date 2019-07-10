package Business_Functions;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import Utility.ExcelUtils;
import Utility.PropertyReader;
import Utility.Screenshots;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Common_Business_Functions extends TestCase {
	PropertyReader objPageObjsProRead = new PropertyReader("src/test/java/Page_Objects/Common_Page_Objects.properties");
	PropertyReader objPageReadFileSub = new PropertyReader("src/test/java/Page_Objects/File_Submission.properties");
	PropertyReader objPageReadDefinition = new PropertyReader("src/test/java/Page_Objects/Data_Definition.properties");
	PropertyReader objPageReadUserRoles = new PropertyReader("src/test/java/Page_Objects/User_Management.properties");
	PropertyReader objPageReadDict = new PropertyReader("src/test/java/Page_Objects/Dictionaries.properties");
	PropertyReader objPageReadModules = new PropertyReader("src/test/java/Page_Objects/Modules.properties");
	Screenshots objCreateScreenshot = new Screenshots();
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;

	public WebDriver getDriver(WebDriver driver, String browserType) {
		switch (browserType) {
		case "FF":
			driver = new FirefoxDriver();
			break;
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setBinary("src/test/resources/Driver servers/Google Chrome/Application/chrome.exe");
			System.setProperty("webdriver.chrome.driver", "src/test/resources/Driver servers/chromedriver.exe");
			driver = new ChromeDriver(chromeOptions);
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver", "src/test/resources/Driver servers/IEDriverServer.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requireWindowFocus", true);

			driver = new InternetExplorerDriver(capabilities);
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		// driver.manage().window().fullscreen();
		return driver;
	}

	public void loginCasper(WebDriver driver, String strURL, String userName, String password, ExtentTest test,
			String rowNoGbl, String date1, ExtentReports extent) throws Exception {
		try {

			// Create driver for FF browser and open a URL
			driver.get(strURL);
			// Log the action performed in the extent report
			test.pass("Opened the browser and navigated to the URL");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Enter the Username
			driver.findElement(objPageObjsProRead.getLocator("objUserID")).clear();
			driver.findElement(objPageObjsProRead.getLocator("objUserID")).sendKeys(userName);
			// Log(Status, details)
			test.pass("Entered Username");

			// Enter the Password
			driver.findElement(objPageObjsProRead.getLocator("objPassword")).clear();
			driver.findElement(objPageObjsProRead.getLocator("objPassword")).sendKeys(password);
			// info(details)
			test.pass("Entered Password");

			// Click the submit button
			driver.findElement(objPageObjsProRead.getLocator("objSubmit")).click();
			Thread.sleep(2000);
			extent.flush();
			// Verify the Welcome to Casper text is displayed
			// if
			// (driver.findElements(objPageObjsProRead.getLocator("objWelcome")).size()
			// != 0) {
			/*
			 * if
			 * (driver.findElement(objPageObjsProRead.getLocator("objWelcome")).isDisplayed(
			 * )) { // Add a pass step to the Extent Report // Add the screenshot to the
			 * Reporting steps with a hyperlink
			 * test.pass("Verified Welcome to Casper is displayed on the Home page",
			 * MediaEntityBuilder
			 * .createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
			 * "VerifyLoginSuccessPass", test,rowNoGbl, date1)).build()); } else { //
			 * System.out.println("In the else block"); // Log with snapshot
			 * test.fail("Welcome to Casper is not displayed on the Home page",
			 * MediaEntityBuilder
			 * .createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
			 * "VerifyLoginSuccessFail", test,rowNoGbl, date1)).build()); }
			 */
		} catch (NoSuchElementException e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public String generateRandomString(String rowNoGbl, String workbookName, String colName) throws Exception {
		String value = ExcelUtils.getCellValueUsingColName(colName, Integer.parseInt(rowNoGbl));
		String number = value.substring(3, value.length());
		int newNumber = Integer.parseInt(number) + 1;
		String newValue = value.substring(0, 3) + newNumber;
		String Path = "src/test/java/TestData/" + workbookName;
		ExcelUtils.setCellValueUsingColName(Path, colName, rowNoGbl, newValue);
		return newValue;
	}

	public void enterNumericValue(WebDriver driver, ExtentTest test, String date1, String rowNoGbl,
			ExtentReports extent, By xpath, int value) throws IOException {
		try {
			WebElement element = driver.findElement(xpath);
			element.clear();
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].value='" + value + "';", element);
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void enterData(WebDriver driver, ExtentTest test, String date1, String rowNoGbl, ExtentReports extent,
			By xpath, String value) throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement element = driver.findElement(xpath);
		element.clear();
		element.sendKeys(value);
		extent.flush();
	}

	public void removeValue(WebDriver driver, ExtentTest test, String date1, String rowNoGbl, ExtentReports extent,
			By xpath) throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement element = driver.findElement(xpath);
		// element.clear();
		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		;
		extent.flush();
	}

	public boolean verifyMaxLength(WebDriver driver, String text, WebElement fieldName) throws Exception {
		// WebElement eleName = driver.findElement(objPageRead.getLocator(fieldName));
		String lengthValue = fieldName.getAttribute("ng-reflect-maxlength");
		int maxLengthOfTextField = Integer.parseInt(lengthValue);
		fieldName.sendKeys(text);
		String enteredText = fieldName.getAttribute("value");
		int lengthOfenteredValue = enteredText.length();
		if (maxLengthOfTextField == lengthOfenteredValue) {
			return true;
		} else {
			return false;
		}
	}

	public void clickOnLink(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			String lnkText) throws Exception {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			if (driver.findElement(By.xpath(".//a[contains(text(),'" + lnkText + "')]")).isDisplayed()) {
				WebElement lnkToClick = driver.findElement(By.xpath(".//a[contains(text(),'" + lnkText + "')]"));
				lnkToClick.click();
				Thread.sleep(2000);
				test.pass("Successfully clicked on link " + lnkText + "",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"VerifyClickedLinkPassed", test, rowNoGbl, date1)).build());
				extent.flush();
			}
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void clickOnButton(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			String buttonName) throws Exception {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			WebElement btnToClick = driver
					.findElement(By.xpath(".//div/button[contains(text(),'" + buttonName + "')]"));
			if (btnToClick.isDisplayed()) {
				btnToClick.click();
				Thread.sleep(2000);
				System.out.println("Button clicked");
				test.pass("Successfully clicken on button " + buttonName + "",
						MediaEntityBuilder.createScreenCaptureFromPath(
								objCreateScreenshot.createScreenshot(driver, "ClickOnbutton", test, rowNoGbl, date1))
								.build());

			} else {
				test.fail("Button does not exist: " + buttonName + "", MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyButtonFailed", test, rowNoGbl, date1))
						.build());
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void VerifyLinkExist(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			String lnkText) throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement lnkToVerify = driver.findElement(By.xpath(".//li/a[contains(text(),'" + lnkText + "')]"));
			if (lnkToVerify.isDisplayed()) {
				test.pass("Successfully clicked on link " + lnkText + "",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"VerifiedLinkDisplayed", test, rowNoGbl, date1)).build());
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("Link does not exist: ", MediaEntityBuilder.createScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1))
					.build());
			// Close the Browser
			// driver.quit();
			// test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void VerifyButtonExist(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String btnText) throws Exception {
		try {
			Thread.sleep(2000);
			// Find the element to clicked
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement btnToVerify = driver.findElement(By.xpath(".//div/button[contains(text(),'" + btnText + "')]"));
			if (btnToVerify.isDisplayed()) {
				System.out.println("Button verified");
				test.pass("Successfully Verified button " + btnText + "",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"VerifiedLinkDisplayed", test, rowNoGbl, date1)).build());

			} else {
				test.fail("Button does not exist: " + btnText + "",
						MediaEntityBuilder.createScreenCaptureFromPath(
								objCreateScreenshot.createScreenshot(driver, "VerifyListItem", test, rowNoGbl, date1))
								.build());
			}
			extent.flush();
		}

		catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("Button does not exist: " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			// driver.quit();
			// test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void VerifyAvailableActions(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String actionName) throws IOException {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			List<WebElement> records;
			int i = 0, size;
			records = driver.findElements(By.xpath("//div[@role='gridcell']"));
			size = records.size();
			if (size <= 0)
				test.fail("No record to verify " + actionName + " action",
						MediaEntityBuilder.createScreenCaptureFromPath(
								objCreateScreenshot.createScreenshot(driver, "VerifyListItem", test, rowNoGbl, date1))
								.build());
			else {
				while (i < size) {
					WebElement element = records.get(i);
					if (element.isDisplayed()) {
						element.click();
						break;
					} else
						i++;
				}
				System.out.println("record clicked");
				WebElement CurrentRow = driver.switchTo().activeElement();
				Actions action = new Actions(driver);
				action.contextClick(CurrentRow).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ENTER).build().perform();
				Thread.sleep(2000);
				WebElement actionItem = driver.findElement(By.xpath(".//span[contains(text(),'" + actionName + "')]"));
				String value = actionItem.getText();
				if (value.equalsIgnoreCase(actionName)) {
					System.out.println("Verified :" + actionName);
					test.pass("Successfully verified action option " + value + "",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyListItem", test, rowNoGbl, date1)).build());
				} else {
					test.fail("Action does not exist: " + value + "", MediaEntityBuilder.createScreenCaptureFromPath(
							objCreateScreenshot.createScreenshot(driver, "VerifyListItem", test, rowNoGbl, date1))
							.build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("Action does not exist: " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			// driver.quit();
			// test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void scrollToRight(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1) {
		List<WebElement> list = driver.findElements(By.xpath("//span[@role='columnheader']"));
		int length = list.size();
		for (int i = 0; i < length; i++) {
			driver.findElement(By.xpath("//ag-grid-angular/div/div[2]/div[1]/div[3]/div[2]")).click();
			driver.findElement(By.tagName("body")).sendKeys(Keys.ARROW_RIGHT);
		}
	}

	public void scrollToRightForFileVault(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1) {
		List<WebElement> list = driver.findElements(By.xpath("//span[@role='columnheader']"));
		int length = list.size();
		for (int i = 0; i < length + 5; i++) {
			driver.findElement(By.xpath("//ag-grid-angular/div/div[2]/div[1]/div[3]/div[2]")).click();
			driver.findElement(By.tagName("body")).sendKeys(Keys.ARROW_RIGHT);
		}
	}

	public void scrollToLeft(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1) {
		List<WebElement> list = driver.findElements(By.xpath("//span[@role='columnheader']"));
		int length = list.size();
		for (int i = 0; i < length; i++) {
			driver.findElement(By.xpath("//ag-grid-angular/div/div[2]/div[1]/div[3]/div[2]")).click();
			driver.findElement(By.tagName("body")).sendKeys(Keys.ARROW_LEFT);
		}
	}

	public void SelectToggleColumn(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String columnName) throws IOException {
		try {
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//i[@class='fa fa-bars']")).click();
			driver.findElement(By.xpath("//span[contains(text(),'Tool Panel')]")).click();
			driver.findElement(By.xpath("//input[@placeholder='Filter...']")).clear();
			driver.findElement(By.xpath("//input[@placeholder='Filter...']")).sendKeys(columnName);
			driver.findElement(By.xpath("//span[contains(text(),'" + columnName
					+ "')]/preceding-sibling::span[@class='ag-column-select-checkbox']")).click();
			driver.findElement(By.xpath("//i[@class='fa fa-bars']")).click();
			driver.findElement(By.xpath("//span[contains(text(),'Tool Panel')]")).click();

			scrollToRight(driver, test, extent, rowNoGbl, date1);

			WebElement element = driver
					.findElement(By.xpath("//span[@role='columnheader'][contains(text(),'" + columnName + "')]"));
			if (element.isDisplayed())
				test.pass("Toggle column " + columnName + " selected successfully",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"VerifyToggleColumnselectedPassed", test, rowNoGbl, date1)).build());
			else
				test.fail("Toggle column " + columnName + " not selected",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"VerifyToggleColumnselectedFailed", test, rowNoGbl, date1)).build());

			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("Action does not exist: " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void VerifyAvailableActionsSubList(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String actionName) throws Exception {
		try {
			Thread.sleep(4000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			List<WebElement> records;
			int i = 0;
			records = driver.findElements(By.xpath("//div[@role='gridcell']"));
			int size = records.size();
			while (i < size) {
				WebElement element = records.get(i);
				if (element.isDisplayed()) {
					element.click();
					break;
				} else
					i++;
			}

			System.out.println("record clicked");
			WebElement CurrentRow = driver.switchTo().activeElement();
			Actions action = new Actions(driver);
			action.contextClick(CurrentRow).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(2000);
			WebElement actionItem = driver.findElement(By.xpath(".//span[contains(text(),'" + actionName + "')]"));
			String value = actionItem.getText();
			if (value.equalsIgnoreCase(actionName)) {
				System.out.println("Verified Action :" + actionName);
				test.pass("Successfully verified action option " + value + "",
						MediaEntityBuilder.createScreenCaptureFromPath(
								objCreateScreenshot.createScreenshot(driver, "VerifyListItem", test, rowNoGbl, date1))
								.build());
			} else {
				test.fail("Action does not exist: " + value);
				test.addScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("Action does not exist: " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyScreenName(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			String screenName) throws IOException {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement screenNameToCheck = driver
					.findElement(By.xpath(".//div/h1[contains(text(),'" + screenName + "')]"));
			if (screenNameToCheck.isDisplayed()) {
				System.out.println("Screen name is displayed");
				test.pass("Screen Name " + screenName + "is present", MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyScreenNamePassed", test, rowNoGbl, date1))
						.build());

			} else {
				test.fail("Screen Name " + screenName + "is not present",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"VerifyScreenNameFailed", test, rowNoGbl, date1)).build());
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			// driver.quit();
			// test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyPopUpName(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			String popUpName) throws IOException {
		try {
			// Find the element to clicked
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement screenNameToCheck = driver.findElement(By.xpath(".//div[contains(text(),'" + popUpName + "')]"));
			if (screenNameToCheck.isDisplayed()) {
				System.out.println("Pop up name is displayed");
				test.pass("Pop up Name " + popUpName + " is present", MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyPopupNamePassed", test, rowNoGbl, date1))
						.build());
			} else {
				test.fail("Pop up Name " + popUpName + " is not present",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"VerifyPopIpNameFailed", test, rowNoGbl, date1)).build());
			}
			extent.flush();
		}

		catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			// driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyNumberOfRecords(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String columName) throws IOException {
		try {
			Thread.sleep(4000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			try {
				if (driver.findElement(By.xpath("//i[@class='fa fa-exclamation-circle']")).isDisplayed()) {
					System.out.println("No search result found.");
					test.fail("Error Message:No search result found.",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyRecordsFoundFailed", test, rowNoGbl, date1)).build());
				}
				extent.flush();
			} catch (Exception e) {
				// Finding records value in Number of records found text
				String actualValue = "Number of Entries found: ";
				WebElement eleNumberofRecordsfound = driver
						.findElement(By.xpath("//div[@class='search-result-text desc']"));
				String valueOfRecordsFound = eleNumberofRecordsfound.getText();
				String recordsFoundArray[] = valueOfRecordsFound.split(" ", ':');

				int lenth = recordsFoundArray.length;
				String toSplit = recordsFoundArray[lenth - 6];
				String arrayToSplit[] = toSplit.split("\n");
				String value = arrayToSplit[1];
				String expectedValue = value + " " + recordsFoundArray[lenth - 5] + " " + recordsFoundArray[lenth - 4]
						+ " " + recordsFoundArray[lenth - 3] + " ";
				if (expectedValue.equals(actualValue)) {
					System.out.println("Number of Entries found text is displayed on screen");
					test.pass("Number of Entries found: " + recordsFoundArray[lenth - 1] + "",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyRecordsFoundTextPassed", test, rowNoGbl, date1)).build());
				} else {
					System.out.println("Number of Entries found text is not displayed on screen");
					test.fail("Number of Entries found is not displayed on screen",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyRecordsFoundTextFailed", test, rowNoGbl, date1)).build());
				}
				extent.flush();
			}
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			// driver.quit();
			// test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}

	}

	public void verifyDataInTable(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String columName) throws IOException {
		try {
			String columnValue = null;
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find out the records get loaded in table
			List<WebElement> recordsLoaded = driver.findElements(By.xpath(".//div[@col-id='" + columName + "']"));
			int sizeOfRecordsLoaded = recordsLoaded.size() - 1;
			if (sizeOfRecordsLoaded > 0) {
				List<WebElement> columnElement = driver
						.findElements(By.xpath(".//div[@col-id='" + columName + "'][@role='gridcell']"));

				for (WebElement iterator : columnElement) {
					columnValue = iterator.getText();
					if (!columnValue.isEmpty()) {
						break;
					}
				}
				if (!columnValue.isEmpty()) {
					System.out.println("Data is loaded in table" + columnValue);
					test.pass("Data is loaded in table",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyDataLoadedPassed", test, rowNoGbl, date1)).build());
				} else {
					// System.out.println("Data is not loaded in table");
					test.fail("Data is not loaded in table",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyDataLoadedFailed", test, rowNoGbl, date1)).build());
				}

			} else {
				System.out.println("No records loaded in table");
				test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyRecordsCountFailed", test, rowNoGbl, date1))
						.build());
			}
			extent.flush();
		}

		catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			// driver.quit();
			// test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void clickOnAction(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			String actionName) throws IOException, InterruptedException {
		try {
			// Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			List<WebElement> records;
			int i = 0, size;
			records = driver.findElements(By.xpath("//div[@role='gridcell']"));
			size = records.size();
			if (size <= 0)
				test.fail("No record to verify " + actionName + " action",
						MediaEntityBuilder.createScreenCaptureFromPath(
								objCreateScreenshot.createScreenshot(driver, "VerifyListItem", test, rowNoGbl, date1))
								.build());
			else {
				while (i < size) {
					WebElement element = records.get(i);
					if (element.isDisplayed()) {
						element.click();
						break;
					} else
						i++;
				}
				WebElement CurrentRow = driver.switchTo().activeElement();
				Actions action = new Actions(driver);
				action.contextClick(CurrentRow).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ENTER).build().perform();
				Thread.sleep(2000);
				WebElement viewOpen = driver.findElement(By.xpath(".//span[contains(text(),'" + actionName
						+ "')]")); /* This will select menu after right click */
				viewOpen.click();
				Thread.sleep(1000);
				System.out.println("Successfully clicked on action");
				test.pass("Successfully clicked on " + actionName + "Action",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"VerifyClickActionPassed", test, rowNoGbl, date1)).build());
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyLeftMenu(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			String leftMenuName) throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement menuToCheck = driver.findElement(By.xpath("//a[contains(text(),'" + leftMenuName + "')]"));
			if (menuToCheck.isDisplayed()) {
				System.out.println("Successfully verified left menu " + leftMenuName + "");
				test.pass("Successfully verified left menu " + leftMenuName + "",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"VerifyDataLoadedPassed", test, rowNoGbl, date1)).build());
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			// driver.quit();
			// test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkCalculations(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String fieldName) throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement fieldToCheck;
			boolean flag = false;
			String valueOfField = null;
			// String maxDataPoints="900000", dataPoints="10677",
			// maxReportingEntities="1000",reportingEntities="20";
			fieldToCheck = driver.findElement(By.xpath("//label[text()='" + fieldName + "']/following-sibling::input"));
			valueOfField = fieldToCheck.getAttribute("value");
			switch (fieldName) {
			case "Max. # Data Points":
				if (!valueOfField.isEmpty())
					flag = true;
			case "# Data Points":
				if (!valueOfField.isEmpty())
					flag = true;
			case "Max. # Reporting Entities":
				if (!valueOfField.isEmpty())
					flag = true;
			case "# Reporting Entities":
				if (!valueOfField.isEmpty())
					flag = true;
			}

			if (flag == true) {
				System.out.println(" calculations values are present for " + fieldName + " field");
				test.pass("calculations values are present for " + fieldName + " field",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"VerifyCheckCalculationsPassed", test, rowNoGbl, date1)).build());
			} else {
				// System.out.println("Data is not loaded in table");
				test.fail(" calculations values are not present for " + fieldName + " field",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"VerifyCheckCalculationsFailed", test, rowNoGbl, date1)).build());

			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			// driver.quit();
			// test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void searchForRecordsWithFilterValue(WebDriver driver, ExtentTest test, ExtentReports extent,
			String rowNoGbl, String date1) throws IOException, InterruptedException {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement versionrecord;
			String value = null;
			List<WebElement> recordsLoaded = driver.findElements(By.xpath(".//div[@role='gridcell']"));
			int sizeOfRecordsLoaded = recordsLoaded.size();
			if (sizeOfRecordsLoaded > 0) {
				versionrecord = recordsLoaded.get(0);
				versionrecord.click();
				System.out.println("record clicked");
				value = versionrecord.getText();
			} else {
				System.out.println("No records loaded in table");
				test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyRecordsCountFailed", test, rowNoGbl, date1))
						.build());
			}
			WebElement fieldToCheck;
			fieldToCheck = driver.findElement(objPageObjsProRead.getLocator("objSearchDC"));
			if (fieldToCheck.isDisplayed()) {
				fieldToCheck.sendKeys(value);

				System.out.println(" Records searched for " + value + "");
				test.pass(" Records searched for " + value + "",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"VerifyCheckCalculationsPassed", test, rowNoGbl, date1)).build());
			}

			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			// driver.quit();
			// test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyAddNewVersionAction(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String actionToCheck, String columnName) throws IOException, InterruptedException {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement versionrecord, CurrentRow, actionItem;
			Actions action;
			// Find out action for record in table
			List<WebElement> recordsLoaded = driver
					.findElements(By.xpath(".//div[@col-id='" + columnName + "'][@role='gridcell']"));
			int sizeOfRecordsLoaded = recordsLoaded.size();
			if (sizeOfRecordsLoaded > 0) {
				versionrecord = recordsLoaded.get(0);
				versionrecord.click();
				System.out.println("record clicked");
				CurrentRow = driver.switchTo().activeElement();
				action = new Actions(driver);
				action.contextClick(CurrentRow).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ENTER).build().perform();
				Thread.sleep(2000);
				actionItem = driver.findElement(By.xpath(".//span[contains(text(),'" + actionToCheck + "')]"));

				if (actionItem.isDisplayed()) {
					System.out.println("Verified :" + actionToCheck);
					test.pass("Action " + actionToCheck + " is available for latest version record",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					test.fail("Action does not exist: " + actionToCheck);
					test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException",
							test, rowNoGbl, date1));
				}

				for (int i = 1; i < sizeOfRecordsLoaded; i++) {
					versionrecord = recordsLoaded.get(i);
					versionrecord.click();
					System.out.println("record clicked");
					CurrentRow = driver.switchTo().activeElement();
					action = new Actions(driver);
					action.contextClick(CurrentRow).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(2000);
					actionItem = driver.findElement(By.xpath(".//span[contains(text(),'" + actionToCheck + "')]"));

					if (!actionItem.isSelected()) {
						System.out.println("Action: " + actionToCheck + " not exists for previous version");
						test.pass("Action " + actionToCheck + " is not available for previous version record",
								MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot
										.createScreenshot(driver, "VerifyActionItem", test, rowNoGbl, date1)).build());
					} else {
						test.fail("Action: " + actionToCheck + " exists for previous version");
						test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"NoSuchElementException", test, rowNoGbl, date1));
					}
				}
			} else {
				System.out.println("No records loaded in table");
				test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyRecordsCountFailed", test, rowNoGbl, date1))
						.build());
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			// driver.quit();
			// test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkCheckbox(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			String permissionName) throws IOException, InterruptedException {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement element = driver
					.findElement(By.xpath("//label[text()='" + permissionName + "']/preceding-sibling::input"));
			if (element.isSelected()) {
				System.out.println("Permission: " + permissionName + "  is available to user ");
				test.pass("Element " + permissionName + " clicked",
						MediaEntityBuilder.createScreenCaptureFromPath(
								objCreateScreenshot.createScreenshot(driver, "VerifyActionItem", test, rowNoGbl, date1))
								.build());
			} else {
				test.fail("Permission: " + permissionName + "  is not available to user ",
						MediaEntityBuilder.createScreenCaptureFromPath(
								objCreateScreenshot.createScreenshot(driver, "VerifyActionItem", test, rowNoGbl, date1))
								.build());
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkPermission(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			String permissionName, boolean flag) throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked

			WebElement element = driver
					.findElement(By.xpath("//label[text()='" + permissionName + "']/preceding-sibling::input"));
			if (flag == true) {
				if (element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out.println(
							"Permission: " + permissionName + "  is not assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			if (flag == false) {
				if (!element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is not assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {

					System.out
							.println("Permission: " + permissionName + "  is assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkPermissionForSubmissionList(WebDriver driver, ExtentTest test, ExtentReports extent,
			String rowNoGbl, String date1, String permissionName, boolean flag)
			throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked

			WebElement element = driver.findElement(By.xpath(
					"//*[@id='0']//tree-root/tree-viewport/div/div/tree-node-collection/div/tree-node/div/tree-node-children/div/tree-node-collection/div/tree-node[1]//label[text()='"
							+ permissionName + "']/preceding-sibling::input"));
			if (flag == true) {
				if (element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out.println(
							"Permission: " + permissionName + "  is not assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			if (flag == false) {
				if (!element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is not assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {

					System.out
							.println("Permission: " + permissionName + "  is assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkPermissionForFileVault(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String permissionName, boolean flag) throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement element = driver.findElement(By.xpath(
					"//*[@id='0']//tree-viewport/div/div/tree-node-collection/div/tree-node/div/tree-node-children/div/tree-node-collection/div/tree-node[2]//label[text()='"
							+ permissionName + "']/preceding-sibling::input"));
			if (flag == true) {
				if (element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out.println(
							"Permission: " + permissionName + "  is not assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			if (flag == false) {
				if (!element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is not assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out
							.println("Permission: " + permissionName + "  is assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkPermissionForModules(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String permissionName, boolean flag) throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement element = driver.findElement(By.xpath(
					"//*[@id='1']//tree-root/tree-viewport/div/div/tree-node-collection/div/tree-node/div/tree-node-children/div/tree-node-collection/div/tree-node[4]//label[text()='"
							+ permissionName + "']/preceding-sibling::input"));
			if (flag == true) {
				if (element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out.println(
							"Permission: " + permissionName + "  is not assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			if (flag == false) {
				if (!element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is not assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out
							.println("Permission: " + permissionName + "  is assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkPermissionForDataPoints(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String permissionName, boolean flag) throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement element = driver.findElement(By.xpath(
					"//*[@id='1']//tree-root/tree-viewport/div/div/tree-node-collection/div/tree-node/div/tree-node-children/div/tree-node-collection/div/tree-node[5]//label[text()='"
							+ permissionName + "']/preceding-sibling::input"));
			if (flag == true) {
				if (element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out.println(
							"Permission: " + permissionName + "  is not assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			if (flag == false) {
				if (!element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is not assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out
							.println("Permission: " + permissionName + "  is assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkPermissionForEntity(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String permissionName, boolean flag) throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement element = driver.findElement(By.xpath(
					"//*[@id='1']//tree-root/tree-viewport/div/div/tree-node-collection/div/tree-node/div/tree-node-children/div/tree-node-collection/div/tree-node[6]//label[text()='"
							+ permissionName + "']/preceding-sibling::input"));
			if (flag == true) {
				if (element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out.println(
							"Permission: " + permissionName + "  is not assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			if (flag == false) {
				if (!element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is not assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out
							.println("Permission: " + permissionName + "  is assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkPermissionForEntityGroup(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String permissionName, boolean flag) throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement element = driver.findElement(By.xpath(
					"//*[@id='1']//tree-root/tree-viewport/div/div/tree-node-collection/div/tree-node/div/tree-node-children/div/tree-node-collection/div/tree-node[8]//label[text()='"
							+ permissionName + "']/preceding-sibling::input"));
			if (flag == true) {
				if (element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out.println(
							"Permission: " + permissionName + "  is not assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			if (flag == false) {
				if (!element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is not assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out
							.println("Permission: " + permissionName + "  is assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkPermissionForDataDictionary(WebDriver driver, ExtentTest test, ExtentReports extent,
			String rowNoGbl, String date1, String permissionName, boolean flag)
			throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement element = driver.findElement(By.xpath(
					"//*[@id='2']//tree-root/tree-viewport/div/div/tree-node-collection/div/tree-node//label[text()='"
							+ permissionName + "']/preceding-sibling::input"));
			if (flag == true) {
				if (element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out.println(
							"Permission: " + permissionName + "  is not assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			if (flag == false) {
				if (!element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is not assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out
							.println("Permission: " + permissionName + "  is assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkPermissionForReportingCycle(WebDriver driver, ExtentTest test, ExtentReports extent,
			String rowNoGbl, String date1, String permissionName, boolean flag)
			throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement element = driver.findElement(By.xpath(
					"//*[@id='1']//tree-root/tree-viewport/div/div/tree-node-collection/div/tree-node/div/tree-node-children/div/tree-node-collection/div/tree-node[9]//label[text()='"
							+ permissionName + "']/preceding-sibling::input"));
			if (flag == true) {
				if (element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out.println(
							"Permission: " + permissionName + "  is not assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			if (flag == false) {
				if (!element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is not assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out
							.println("Permission: " + permissionName + "  is assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkPermissionForObligationPerGroup(WebDriver driver, ExtentTest test, ExtentReports extent,
			String rowNoGbl, String date1, String permissionName, boolean flag)
			throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement element = driver.findElement(By.xpath(
					"//*[@id='1']//tree-root/tree-viewport/div/div/tree-node-collection/div/tree-node/div/tree-node-children/div/tree-node-collection/div/tree-node[10]//label[text()='"
							+ permissionName + "']/preceding-sibling::input"));
			if (flag == true) {
				if (element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out.println(
							"Permission: " + permissionName + "  is not assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			if (flag == false) {
				if (!element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is not assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out
							.println("Permission: " + permissionName + "  is assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkPermissionForObligationPerEntity(WebDriver driver, ExtentTest test, ExtentReports extent,
			String rowNoGbl, String date1, String permissionName, boolean flag)
			throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement element = driver.findElement(By.xpath(
					"//*[@id='1']//tree-root/tree-viewport/div/div/tree-node-collection/div/tree-node/div/tree-node-children/div/tree-node-collection/div/tree-node[11]//label[text()='"
							+ permissionName + "']/preceding-sibling::input"));
			if (flag == true) {
				if (element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out.println(
							"Permission: " + permissionName + "  is not assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			if (flag == false) {
				if (!element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is not assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out
							.println("Permission: " + permissionName + "  is assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkPermissionForValidations(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String permissionName, boolean flag) throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement element = driver.findElement(By.xpath(
					"//*[@id='1']//tree-root/tree-viewport/div/div/tree-node-collection/div/tree-node/div/tree-node-children/div/tree-node-collection/div/tree-node[12]//label[text()='"
							+ permissionName + "']/preceding-sibling::input"));
			if (flag == true) {
				if (element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out.println(
							"Permission: " + permissionName + "  is not assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			if (flag == false) {
				if (!element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is not assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out
							.println("Permission: " + permissionName + "  is assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkPermissionForDiscExport(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String permissionName, boolean flag) throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement element = driver.findElement(By.xpath(
					"//*[@id='1']//tree-root/tree-viewport/div/div/tree-node-collection/div/tree-node/div/tree-node-children/div/tree-node-collection/div/tree-node[13]//label[text()='"
							+ permissionName + "']/preceding-sibling::input"));
			if (flag == true) {
				if (element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out.println(
							"Permission: " + permissionName + "  is not assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			if (flag == false) {
				if (!element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is not assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out
							.println("Permission: " + permissionName + "  is assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkPermissionForManageUser(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String permissionName, boolean flag) throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement element = driver.findElement(By.xpath(
					"//*[@id='3']//tree-root/tree-viewport/div/div/tree-node-collection/div/tree-node/div/tree-node-children/div/tree-node-collection/div/tree-node[1]//label[text()='"
							+ permissionName + "']/preceding-sibling::input"));
			if (flag == true) {
				if (element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out.println(
							"Permission: " + permissionName + "  is not assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			if (flag == false) {
				if (!element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is not assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out
							.println("Permission: " + permissionName + "  is assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkPermissionForRoles(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String permissionName, boolean flag) throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement element = driver.findElement(By.xpath(
					"//*[@id='3']//tree-root/tree-viewport/div/div/tree-node-collection/div/tree-node/div/tree-node-children/div/tree-node-collection/div/tree-node[2]//label[text()='"
							+ permissionName + "']/preceding-sibling::input"));
			if (flag == true) {
				if (element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out.println(
							"Permission: " + permissionName + "  is not assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			if (flag == false) {
				if (!element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is not assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out
							.println("Permission: " + permissionName + "  is assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkPermissionForCustomAttribute(WebDriver driver, ExtentTest test, ExtentReports extent,
			String rowNoGbl, String date1, String permissionName, boolean flag)
			throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement element = driver.findElement(By.xpath(
					"//*[@id='1']//tree-root/tree-viewport/div/div/tree-node-collection/div/tree-node/div/tree-node-children/div/tree-node-collection/div/tree-node[7]//label[text()='"
							+ permissionName + "']/preceding-sibling::input"));
			if (flag == true) {
				if (element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out.println(
							"Permission: " + permissionName + "  is not assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			if (flag == false) {
				if (!element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is not assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {
					System.out
							.println("Permission: " + permissionName + "  is assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkPermissionForMetadata(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String permissionName, boolean flag) throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			// Find the element to clicked
			WebElement element = driver.findElement(By.xpath(
					"//*[@id='1']//tree-viewport/div/div/tree-node-collection/div/tree-node/div/tree-node-children/div/tree-node-collection/div/tree-node[3]//label[text()='"
							+ permissionName + "']/preceding-sibling::input"));

			if (flag == true) {
				if (element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {

					System.out.println(
							"Permission: " + permissionName + "  is not assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			if (flag == false) {
				if (!element.isSelected()) {
					System.out.println("Permission: " + permissionName + "  is not assigned to user as per matrix ");
					test.pass("Permission: " + permissionName + "  is not assigned to user as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				} else {

					System.out
							.println("Permission: " + permissionName + "  is assigned to user and not as per matrix ");
					test.fail("Permission: " + permissionName + "  is not as per matrix ",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyActionItem", test, rowNoGbl, date1)).build());
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void clickOnElement(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			String elemenyToClick) throws IOException, InterruptedException {
		try {
			Thread.sleep(2000);
			// Find the element to clicked
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = driver
					.findElement(By.xpath("//*[@id='permissionlist']//h2[contains(text(),'" + elemenyToClick + "')]"));
			if (element.isDisplayed()) {
				element.click();
				System.out.println("Section: " + elemenyToClick + "  exists ");
				test.pass("Element " + elemenyToClick + " clicked",
						MediaEntityBuilder.createScreenCaptureFromPath(
								objCreateScreenshot.createScreenshot(driver, "VerifyActionItem", test, rowNoGbl, date1))
								.build());
			} else {
				test.fail("Element " + elemenyToClick + " not available",
						MediaEntityBuilder.createScreenCaptureFromPath(
								objCreateScreenshot.createScreenshot(driver, "VerifyActionItem", test, rowNoGbl, date1))
								.build());
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void VerifyNewRecordInTheList(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String columnName, String recordValue) throws Exception {
		try {
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			List<WebElement> records;
			int i = 0;
			records = driver.findElements(By.xpath(".//div[@col-id='" + columnName + "'][@role='gridcell']"));
			int size = records.size();
			if (size > 0) {
				while (i < size) {
					WebElement element = records.get(i);
					if (element.isDisplayed()) {
						String name = element.getText();
						if (name.equalsIgnoreCase(recordValue)) {
							System.out.println("New record added to the list");
							test.pass("New record added to the list: " + name + "",
									MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot
											.createScreenshot(driver, "VerifyNewRecord", test, rowNoGbl, date1))
											.build());
						} else {
							System.out.println("New record not added to the list");
							test.fail("New record not added to the list " + name + "",
									MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot
											.createScreenshot(driver, "VerifyNewRecord", test, rowNoGbl, date1))
											.build());
						}
						break;
					} else {
						i++;
					}
				}
			} else {
				System.out.println("No records loaded in table");
				test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyRecordsCountFailed", test, rowNoGbl, date1))
						.build());
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("Record not added " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void selectCheckbox(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			By xpath, String operation) throws Exception {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = driver.findElement(xpath);
			if (operation.equalsIgnoreCase("Y")) {
				if (element.isDisplayed()) {
					element.click();
					test.log(Status.PASS, "Checkbox selected");
				} else {
					test.log(Status.FAIL, "Checkbox not found on the page");
				}
			} else {
				test.log(Status.INFO, "Checkbox was not required to select");
			}
			extent.flush();
		}

		catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("Checkbox not selected " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void ClickOnCheckbox(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			String Value) throws Exception {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = driver
					.findElement(By.xpath("//label/input[@formcontrolname='" + Value + "']/following-sibling::span"));

			if (element.isDisplayed()) {
				element.click();
				test.log(Status.PASS, "Checkbox selected");
			}

			extent.flush();
		}

		catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("Checkbox not selected " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void selectRadioButton(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, By xpath) throws Exception {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = driver.findElement(xpath);
			element.click();
			test.log(Status.PASS, "Radio button selected");
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("Checkbox not selected " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void selectSupportMultiValueCheckbox(WebDriver driver, ExtentTest test, ExtentReports extent,
			String rowNoGbl, String date1, By xpath, String operation) throws Exception {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = driver.findElement(xpath);
			if (operation.equalsIgnoreCase("Y")) {
				if (element.isDisplayed()) {
					driver.findElement(By.xpath("//input[@formcontrolname='addCustomAttributeMultiSelect']")).click();
					test.log(Status.PASS, "Checkbox selected");
				} else {
					test.log(Status.FAIL, "Checkbox not found on the page");
				}
			} else {
				test.log(Status.INFO, "Checkbox was not required to select");
			}
			extent.flush();
		}

		catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("Checkbox not selected " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void selectFirstListItem(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, By xpath, String listItem) throws Exception {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Select element = new Select(driver.findElement((xpath)));
			element.selectByIndex(1);

			test.log(Status.PASS, "List item selected");
			extent.flush();
		}

		catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("item not available " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void selectLastItemFromListItem(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, By xpath) throws Exception {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Select element = new Select(driver.findElement((xpath)));
			List<WebElement> items = element.getOptions();
			int size = items.size();
			element.selectByIndex(size - 1);
			extent.flush();
		}

		catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("item not available " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void selectListItem(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			By xpath, String listItem) throws Exception {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(xpath);
			WebElement element = driver.findElement(By.xpath("//option[contains(text(),'" + listItem + "')]"));
			element.click();
			test.log(Status.PASS, "List item selected");
			extent.flush();
		}

		catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("item not available " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void searchForRecordsForFilterValue(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String value) throws IOException, InterruptedException {
		try {
			Thread.sleep(2000);
			WebElement fieldToCheck;
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			fieldToCheck = driver.findElement(objPageReadDict.getLocator("objSearchByName"));
			if (fieldToCheck.isDisplayed()) {
				fieldToCheck.clear();
				fieldToCheck.sendKeys(value);
			}
			Thread.sleep(4000);
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			// driver.quit();
			// test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void SearchDataCollection(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String DCName) {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement DataColl = driver.findElement(objPageObjsProRead.getLocator("objSearchDC"));
			DataColl.sendKeys(DCName);
			Thread.sleep(4000);
			extent.flush();
		} catch (Exception e) {
			System.out.println("Error while searching Data collection: " + e);
		}
	}

	public void clickOnMenu(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			String lnkText) throws Exception {
		try {
			Thread.sleep(3000);
			// Find the element to clicked
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (driver.findElement(By.xpath(".//a[contains(text(),'" + lnkText + "')]")).isDisplayed()) {
				WebElement lnkToClick = driver.findElement(By.xpath(".//a[text()='" + lnkText + "']"));
				lnkToClick.click();
				test.pass("Successfully clicken on link " + lnkText + "",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"VerifyClickedLinkPassed", test, rowNoGbl, date1)).build());
				extent.flush();
			}
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			driver.quit();
			test.pass("Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void selectItemInMultiselectList(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, By xpath, String listItem) throws Exception {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = driver.findElement((xpath));
			element.click();
			String[] ArrSplit = listItem.split(",");
			int length = ArrSplit.length;
			for (int i = 0; i < length; i++) {
				String listValue = ArrSplit[i].toLowerCase().trim();
				WebElement checkboxToClick = driver
						.findElement(By.xpath("//li/label[text()='" + listValue + "']/preceding-sibling::input"));
				if (!checkboxToClick.isSelected()) {
					driver.findElement(By.xpath("//li/label[text()='" + listValue + "']")).click();
					test.log(Status.PASS, "List item selected");
				} else {
					test.pass("Checkbox selected");
				}
			}
			extent.flush();
		}

		catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("item not available " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void searchRecordForModules(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String value) throws IOException, InterruptedException {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement fieldToCheck;
			fieldToCheck = driver.findElement(objPageReadModules.getLocator("objSearchCode"));
			if (fieldToCheck.isDisplayed()) {
				fieldToCheck.clear();
				fieldToCheck.sendKeys(value);
				Thread.sleep(2000);
			}

			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			// driver.quit();

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void searchRecordForValidationRule(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String value) throws IOException, InterruptedException {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement fieldToCheck;
			fieldToCheck = driver.findElement(objPageReadDefinition.getLocator("objRuleIDSearch"));
			if (fieldToCheck.isDisplayed()) {
				fieldToCheck.clear();
				fieldToCheck.sendKeys(value);
				Thread.sleep(2000);
			}

			extent.flush();
		} catch (Exception e) {

		}
	}

	public void searchRecordForModulesInSubmissionList(WebDriver driver, ExtentTest test, ExtentReports extent,
			String rowNoGbl, String date1, String value) throws IOException, InterruptedException {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement fieldToCheck;
			fieldToCheck = driver.findElement(objPageReadFileSub.getLocator("objSearchModule"));
			if (fieldToCheck.isDisplayed()) {
				fieldToCheck.clear();
				fieldToCheck.sendKeys(value);
			}

			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			// driver.quit();

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void searchRecordForCycleInSubmissionList(WebDriver driver, ExtentTest test, ExtentReports extent,
			String rowNoGbl, String date1, String value) throws IOException, InterruptedException {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement fieldToCheck;
			fieldToCheck = driver.findElement(objPageReadFileSub.getLocator("objSearchCycle"));
			if (fieldToCheck.isDisplayed()) {
				fieldToCheck.clear();
				fieldToCheck.sendKeys(value);
			}

			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			// driver.quit();

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void checkStatus(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			String colName) throws IOException, InterruptedException {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			scrollToRight(driver, test, extent, rowNoGbl, date1);
			WebElement element = driver
					.findElement(By.xpath("//div[@col-id='" + colName + "']//i[@class='fa fa-check-circle']"));
			if (element.isDisplayed()) {
				System.out.println(" Status is green for " + colName + "");
				test.pass(" Status is green for " + colName + "", MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyCheckStatusPassed", test, rowNoGbl, date1))
						.build());
			} else {
				System.out.println(" Status is not green for " + colName + "");
				test.fail(" Status is not green for " + colName + "", MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyRecordsCountFailed", test, rowNoGbl, date1))
						.build());
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void captureCycleName(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			By xpath, String workbookName, String colName) throws IOException, InterruptedException {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String value = null;
			String selectedCycleName = null;
			WebElement element = driver.findElement(xpath);
			if (element.isDisplayed()) {
				value = element.getText();
				String valu1 = value.trim();
				String[] splitArray = valu1.split(",|\n|\t");
				int length = splitArray.length;
				String val1 = splitArray[length - 2];
				selectedCycleName = val1.trim();
				String Path = "src/test/java/TestData/" + workbookName;
				ExcelUtils.setCellValueUsingColName(Path, colName, rowNoGbl, selectedCycleName);
				System.out.println(" Cycle name is " + selectedCycleName + "");
				test.pass(" Cycle name is " + selectedCycleName + "",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"VerifyCaptureCycleNamePassed", test, rowNoGbl, date1)).build());
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void VerifyNoRecordInTheList(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1) throws Exception {
		try {
			Thread.sleep(4000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			List<WebElement> records;
			records = driver.findElements(By.xpath(".//div[@role='gridcell']"));
			int size = records.size();

			if (size == 0) {
				System.out.println("No record in the list");
				test.pass("No record in the list", MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyNewRecordPassed", test, rowNoGbl, date1))
						.build());
			} else {
				System.out.println("Record is in the list");
				test.fail("Record is in the list", MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyNewRecordFailed", test, rowNoGbl, date1))
						.build());
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("Record not added " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void VerifyFileStatus(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			String columnName, String fileStatus) throws Exception {
		try {
			Thread.sleep(4000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			List<WebElement> records;
			int i = 0;
			scrollToRightForFileVault(driver, test, extent, rowNoGbl, date1);
			records = driver.findElements(By.xpath(".//div[@col-id='" + columnName + "'][@role='gridcell']"));
			int size = records.size();
			if (size > 0) {
				while (i < size) {
					WebElement element = records.get(i);
					if (element.isDisplayed()) {
						String name = element.getText();
						if (name.equalsIgnoreCase(fileStatus)) {
							System.out.println("File status is correct");
							test.pass("File status is correct: " + name + "",
									MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot
											.createScreenshot(driver, "VerifyNewRecord", test, rowNoGbl, date1))
											.build());
						} else {
							System.out.println("File status is not correct");
							test.fail("File status is not correct",
									MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot
											.createScreenshot(driver, "VerifyNewRecord", test, rowNoGbl, date1))
											.build());
						}
						break;
					} else {
						i++;
					}
				}
			} else {
				System.out.println("No records loaded in table");
				test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyRecordsCountFailed", test, rowNoGbl, date1))
						.build());
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("Record not added " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void VerifySubmissionStatus(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String columnName, String fileStatus) throws Exception {
		try {
			Thread.sleep(4000);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			List<WebElement> records;
			int i = 0;
			scrollToRight(driver, test, extent, rowNoGbl, date1);
			records = driver.findElements(By.xpath(".//div[@col-id='" + columnName + "'][@role='gridcell']"));
			int size = records.size();
			if (size > 0) {
				while (i < size) {
					WebElement element = driver.findElement(objPageObjsProRead.getLocator("objFileUploadSuccessImage"));
					if (element.isDisplayed()) {
						String name = element.getAttribute("title").toString();
						if (name.equalsIgnoreCase(fileStatus)) {
							System.out.println("File status is correct");
							test.pass("File status is correct: " + name + "",
									MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot
											.createScreenshot(driver, "VerifyNewRecord", test, rowNoGbl, date1))
											.build());
						} else {
							System.out.println("File status is not correct");
							test.fail("File status is not correct",
									MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot
											.createScreenshot(driver, "VerifyNewRecord", test, rowNoGbl, date1))
											.build());
						}
						break;
					} else {
						i++;
					}
				}
			} else {
				System.out.println("No records loaded in table");
				test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyRecordsCountFailed", test, rowNoGbl, date1))
						.build());
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("Record not added " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyUploadFileDialog(WebDriver driver, ExtentTest test, String strFilePath, ExtentReports extent,
			String rowNoGbl, String date1, String strSuccessMsg) {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (driver.findElement(objPageObjsProRead.getLocator("objFileUploadSuccessImage")).isDisplayed()) {

				String strActMsg = driver.findElement(objPageObjsProRead.getLocator("objSuccessMsg")).getText();

				if (strSuccessMsg.equals(strActMsg.trim())) {
					System.out.println("Success msg verified");
					// Add the screenshot to the Reporting steps with a hyperlink
					test.pass("Success Message is displayed as :" + strSuccessMsg,
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyMsgDisplayed", test, rowNoGbl, date1)).build());
				}

				else {
					System.out.println("incorrect msg in positive scenario");
					test.fail("Incorrect Message is displayed :" + strActMsg,
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyInvalidMsgDisplayed", test, rowNoGbl, date1)).build());

					// Add the screenshot to the Reporting steps with a hyperlink
					test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
							"VerifyInvalidMsgDisplayed", test, rowNoGbl, date1));
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyLinkOnPopUp(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String linkText) {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement linkToClick = driver.findElement(By.xpath("//div/label/a[contains(text(),'" + linkText + "')]"));
			if (linkToClick.isDisplayed()) {
				System.out.println("Link is present on pop up");
				test.pass("Link is present on pop up :" + linkText, MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyLinkDisplayed", test, rowNoGbl, date1))
						.build());
				linkToClick.click();
				test.pass("Click on link :" + linkText, MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyLinkClicked", test, rowNoGbl, date1))
						.build());
				Thread.sleep(25000);
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void ClickLinkOnPopUp(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			String linkText) {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement linkToClick = driver.findElement(By.xpath("//div//a[contains(text(),'" + linkText + "')]"));
			if (linkToClick.isDisplayed()) {
				System.out.println("Link is present on pop up");
				test.pass("Link is present on pop up :" + linkText, MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyLinkDisplayed", test, rowNoGbl, date1))
						.build());
				linkToClick.click();
				test.pass("Click on link :" + linkText, MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyLinkClicked", test, rowNoGbl, date1))
						.build());
				Thread.sleep(75000);
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifySuccessMessageOnScreen(WebDriver driver, ExtentTest test, String strFilePath,
			ExtentReports extent, String rowNoGbl, String date1, String strSuccessMsg) {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (driver.findElement(objPageObjsProRead.getLocator("objFileUploadSuccessImage")).isDisplayed()) {

				String strActMsg = driver.findElement(objPageObjsProRead.getLocator("objUpdateSuccessMsg")).getText();

				if (strSuccessMsg.equals(strActMsg.trim())) {
					System.out.println("Success msg verified");
					// Add the screenshot to the Reporting steps with a hyperlink
					test.pass("Success Message is displayed as :" + strSuccessMsg,
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyMsgDisplayed", test, rowNoGbl, date1)).build());
				}

				else {
					System.out.println("incorrect msg in positive scenario");
					test.fail("Incorrect Message is displayed :" + strActMsg,
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyInvalidMsgDisplayed", test, rowNoGbl, date1)).build());

					// Add the screenshot to the Reporting steps with a hyperlink
					test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
							"VerifyInvalidMsgDisplayed", test, rowNoGbl, date1));
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyErrorMessage(WebDriver driver, ExtentTest test, String strFilePath, ExtentReports extent,
			String rowNoGbl, String date1, String strExpTxt) {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (driver.findElement(objPageObjsProRead.getLocator("objFailImage")).isDisplayed()) {

				String strActMsg = driver.findElement(objPageObjsProRead.getLocator("objFailureMsg")).getText();

				if (strExpTxt.contains(strActMsg.trim())) {
					// Add the screenshot to the Reporting steps with a hyperlink
					System.out.println("error validated, passed!");
					test.pass("Error Message is displayed as :" + strExpTxt,
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyMsgDisplayed", test, rowNoGbl, date1)).build());
				}

				else {
					System.out.println("incorrect msg displayed");
					test.fail("Incorrect Message is displayed :" + strActMsg,
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyInvalidMsgDisplayed", test, rowNoGbl, date1)).build());

					// Add the screenshot to the Reporting steps with a hyperlink
					test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
							"VerifyInvalidMsgDisplayed", test, rowNoGbl, date1));
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyErrorText(WebDriver driver, ExtentTest test, String strFilePath, ExtentReports extent,
			String rowNoGbl, String date1, String strErrorMsg) {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Actions action = new Actions(driver);
			action.sendKeys(Keys.TAB).build().perform();
			if (driver.findElement(objPageObjsProRead.getLocator("objFailImage")).isDisplayed()) {
				String strActMsg = driver.findElement(objPageObjsProRead.getLocator("objFailureText")).getText();

				if (strErrorMsg.contains(strActMsg.trim())) {
					// Add the screenshot to the Reporting steps with a hyperlink
					System.out.println("error validated, passed!");
					test.pass("Error Message is displayed as :" + strErrorMsg,
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyMsgDisplayed", test, rowNoGbl, date1)).build());
				}

				else {
					System.out.println("incorrect msg displayed");
					test.fail("Incorrect Message is displayed :" + strActMsg,
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyInvalidMsgDisplayed", test, rowNoGbl, date1)).build());

					// Add the screenshot to the Reporting steps with a hyperlink
					test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
							"VerifyInvalidMsgDisplayed", test, rowNoGbl, date1));
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyErrorForIncorrectCSV(WebDriver driver, ExtentTest test, String strFilePath, ExtentReports extent,
			String rowNoGbl, String date1, String strErrorMsg) {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (driver.findElement(By.xpath("//div[@col-id='validationErrorCode'][@role='gridcell']")).isDisplayed()) {

				String strActMsg = driver
						.findElement(By.xpath("//div[@col-id='validationErrorCode'][@role='gridcell']")).getText();

				if (strErrorMsg.contains(strActMsg.trim())) {
					// Add the screenshot to the Reporting steps with a hyperlink
					System.out.println("error validated, passed!");
					test.pass("Error Message is displayed as :" + strErrorMsg,
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyMsgDisplayed", test, rowNoGbl, date1)).build());
				}

				else {
					System.out.println("incorrect msg displayed");
					test.fail("Incorrect Message is displayed :" + strActMsg,
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"VerifyInvalidMsgDisplayed", test, rowNoGbl, date1)).build());

					// Add the screenshot to the Reporting steps with a hyperlink
					test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
							"VerifyInvalidMsgDisplayed", test, rowNoGbl, date1));
				}
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void setAttributeValueInCSVFile(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String attributeCode) throws Exception {
		try {
			File inputFile = new File("src/test/resources/FilesToUpload/validentity1.csv");

			CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
			List<String[]> csvBody = reader.readAll();
			// get CSV row column and replace with by using row and column
			String[] strArray = csvBody.get(0);
			for (int j = 0; j < strArray.length; j++) {
				if (strArray[j].equalsIgnoreCase("ATTR_CODE")) { // String to be replaced
					for (int k = 1; k < csvBody.size(); k++) {
						csvBody.get(k)[j] = attributeCode; // Target replacement
					}
				}
			}
			reader.close();

			// Write to CSV file which is open
			CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');
			writer.writeAll(csvBody);
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setFileCountInExcel(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String workBookName, String sheetName, String rowNo, String ColName) throws Exception {
		try {
			Thread.sleep(2000);
			List<WebElement> records;
			records = driver.findElements(By.xpath(".//div[@col-id='submissionStatus'][@role='gridcell']"));
			int size = records.size();

			FileInputStream fis = new FileInputStream("src/test/java/TestData/" + workBookName);

			ExcelWBook = new XSSFWorkbook(fis);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			int col_num = -1;
			Row = ExcelWSheet.getRow(0);
			for (int j = 0; j < Row.getLastCellNum(); j++) {
				if (Row.getCell(j).getStringCellValue().trim().equals(ColName))
					col_num = j;
			}
			Row = ExcelWSheet.getRow(Integer.parseInt(rowNo));
			XSSFCell cell = Row.getCell(col_num);
			cell.setCellValue("");
			cell.setCellValue(size);
			FileOutputStream fos = new FileOutputStream("src/test/java/TestData/" + workBookName);
			ExcelWBook.write(fos);
			fos.close();
			test.pass("File count saved in sheet :" + sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getFileCountValue(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String workBookName, String ColName) {
		String value = null;
		try {
			FileInputStream fis = new FileInputStream("src/test/java/TestData/" + workBookName);

			ExcelWBook = new XSSFWorkbook(fis);
			ExcelWSheet = ExcelWBook.getSheetAt(0);
			int lastRow = ExcelWSheet.getLastRowNum();
			XSSFRow Row1 = ExcelWSheet.getRow(0);
			XSSFCell cell;

			int col_ruleIDIndex = getColumnIndex(driver, test, extent, rowNoGbl, date1, ColName, Row1);
			for (int i = 1; i <= lastRow; i++) {
				Row1 = ExcelWSheet.getRow(i);
				cell = Row1.getCell(col_ruleIDIndex);
				value = cell.getStringCellValue();
				System.out.println("Collection code get saved");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public void setDataCollectionCodeInFile(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String dataCollectionCode, String workBookName, String sheetName, String rowNo,
			String ColName) throws Exception {
		try {
			FileInputStream fis = new FileInputStream("src/test/java/TestData/" + workBookName);

			ExcelWBook = new XSSFWorkbook(fis);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			int lastRow = ExcelWSheet.getLastRowNum();
			XSSFRow Row1 = ExcelWSheet.getRow(0);
			XSSFCell cell;
			int col_ruleIDIndex = getColumnIndex(driver, test, extent, rowNoGbl, date1, ColName, Row1);
			for (int i = 1; i <= lastRow; i++) {
				Row1 = ExcelWSheet.getRow(i);
				cell = Row1.getCell(col_ruleIDIndex);
				cell.setCellValue("");
				cell.setCellValue(dataCollectionCode);
				FileOutputStream fos = new FileOutputStream("src/test/java/TestData/" + workBookName);
				ExcelWBook.write(fos);
				fos.close();
				System.out.println("Collection code get saved");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setDataCollectionCodeInExcel(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String dataCollectionCode, String workBookName, String ColName) {
		try {
			FileInputStream fis = new FileInputStream("src/test/java/TestData/" + workBookName);

			ExcelWBook = new XSSFWorkbook(fis);
			ExcelWSheet = ExcelWBook.getSheetAt(0);
			int lastRow = ExcelWSheet.getLastRowNum();
			XSSFRow Row1 = ExcelWSheet.getRow(0);
			XSSFCell cell;
			int col_ruleIDIndex = getColumnIndex(driver, test, extent, rowNoGbl, date1, ColName, Row1);
			for (int i = 1; i <= lastRow; i++) {
				Row1 = ExcelWSheet.getRow(i);
				cell = Row1.getCell(col_ruleIDIndex);
				cell.setCellValue("");
				cell.setCellValue(dataCollectionCode);
				FileOutputStream fos = new FileOutputStream("src/test/java/TestData/" + workBookName);
				ExcelWBook.write(fos);
				fos.close();
				System.out.println("Collection code get saved");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int getColumnIndex(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl, String date1,
			String colName, XSSFRow Row) {
		// Getting column index
		int index = -1;
		for (int i = 0; i <= Row.getLastCellNum(); i++) {
			String col = ExcelWSheet.getRow(0).getCell(i).toString().trim();
			if (col.equals(colName)) {
				index = ExcelWSheet.getRow(0).getCell(i).getColumnIndex();
				break;
			}
		}
		return index;
	}

	public void setCollectionUniqueIdentifierValueInCSV(WebDriver driver, ExtentTest test, ExtentReports extent,
			String rowNoGbl, String date1, String collectionUniqueIdentifier, String fileName) throws Exception {
		try {
			File inputFile = new File("src/test/resources/FilesToUpload/ReportingEntityUpload/" + fileName);

			CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
			List<String[]> csvBody = reader.readAll();
			// get CSV row column and replace with by using row and column
			String[] strArray = csvBody.get(0);
			for (int j = 0; j < strArray.length; j++) {
				if (strArray[j].equalsIgnoreCase("COLLECTIONUNIQUEIDENTIFIER")) { // String to be replaced
					for (int k = 1; k < csvBody.size(); k++) {
						csvBody.get(k)[j] = collectionUniqueIdentifier; // Target replacement
					}
				}
			}
			reader.close();

			// Write to CSV file which is open
			CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');
			writer.writeAll(csvBody);
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setModuleCodeValueInCSV(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String collectionUniqueIdentifier, String fileName) throws Exception {
		try {
			File inputFile = new File("src/test/resources/FilesToUpload/DataPointImport/" + fileName);

			CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
			List<String[]> csvBody = reader.readAll();
			// get CSV row column and replace with by using row and column
			String[] strArray = csvBody.get(0);
			for (int j = 0; j < strArray.length; j++) {
				if (strArray[j].equalsIgnoreCase("Module Code")) { // String to be replaced
					for (int k = 1; k < csvBody.size(); k++) {
						csvBody.get(k)[j] = collectionUniqueIdentifier; // Target replacement
					}
				}
			}
			reader.close();

			// Write to CSV file which is open
			CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');
			writer.writeAll(csvBody);
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void verifyCheckboxIsDisabled(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String value) {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = driver
					.findElement(By.xpath("//label[text()='" + value + "']/preceding-sibling::input"));
			if (!element.isEnabled()) {
				System.out.println("Checkbox is disabled");
				test.pass("Checkbox is disabled :" + value);
			} else {
				System.out.println("Checkbox is not disabled");
				test.fail("Checkbox is not disabled :" + value, MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyMsgDisplayed", test, rowNoGbl, date1))
						.build());
			}

			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyButtonIsDisabled(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String value) {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = driver.findElement(By.xpath("//button[contains(text(),'" + value + "')]"));
			if (!element.isEnabled()) {
				System.out.println("Button is disabled");
				test.pass("Button is disabled :" + value, MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyMsgDisplayed", test, rowNoGbl, date1))
						.build());
			} else {
				System.out.println("Button is not disabled");
				test.fail("Button is not disabled :" + value, MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyMsgDisplayed", test, rowNoGbl, date1))
						.build());
			}

			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyCheckboxIsNotSelected(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String value) {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = driver
					.findElement(By.xpath("//label[text()='" + value + "']/preceding-sibling::input"));
			if (!element.isSelected()) {
				System.out.println("Checkbox is not selected");
				test.pass("Checkbox is not selected :" + value, MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyMsgDisplayed", test, rowNoGbl, date1))
						.build());
			} else {
				System.out.println("Checkbox is selected");
				test.fail("Checkbox is selected:" + value, MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyMsgDisplayed", test, rowNoGbl, date1))
						.build());
			}
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyCheckboxIsEnabled(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String value) {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = driver.findElement(By.xpath(
					"//label[text()='" + value + "']/preceding-sibling::input[@formcontrolname='" + value + "']"));
			if (element.isEnabled()) {
				System.out.println("Checkbox is enabled");
				test.pass("Checkbox is enabled :" + value);
			} else {
				System.out.println("Checkbox is not enabled");
				test.fail("Checkbox is not enabled :" + value);
			}

			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyValueOnScreen(WebDriver driver, ExtentTest test, ExtentReports extent, String rowNoGbl,
			String date1, String value, String element) {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement fieldToCheck = driver
					.findElement(By.xpath("//div/label[contains(text(),'" + element + "')]/following-sibling::input"));
			String valueOfField = fieldToCheck.getAttribute("value");
			if (valueOfField.equals(value)) {
				System.out.println("Value is correct");
				test.pass("Value is as per entered value :" + value, MediaEntityBuilder.createScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "VerifyMsgDisplayed", test, rowNoGbl, date1))
						.build());
			}

			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void searchRecordForProcessStatusInStatus(WebDriver driver, ExtentTest test, ExtentReports extent,
			String rowNoGbl, String date1, String value) throws IOException, InterruptedException {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement fieldToCheck;
			fieldToCheck = driver.findElement(objPageReadDefinition.getLocator("objDISCProcessStatusSrch"));
			if (fieldToCheck.isDisplayed()) {
				fieldToCheck.clear();
				fieldToCheck.sendKeys(value);
			}

			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			test.addScreenCaptureFromPath(
					objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));

			// Close the Browser
			// driver.quit();

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}
}
