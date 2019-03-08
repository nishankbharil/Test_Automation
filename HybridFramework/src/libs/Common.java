package libs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Common {

	private static WebDriver driver;

	public static void sleep(int millSec) {
		try {
			Thread.sleep(millSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void launchBrowser(String browserType, String baseUrl) {
		if (browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver();

		}
		if (browserType.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
			driver = new FirefoxDriver();

		}

		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	public static List<String> getTestData(String testName) {
		List<String> testData = new ArrayList<String>();
		try {
			XSSFWorkbook book = new XSSFWorkbook("TestData.xlsx");

			XSSFSheet sheet1 = book.getSheet("Sheet1");

			for (Row row : sheet1) {
				Cell cell0 = row.getCell(0);
				String cell0Data = cell0.getStringCellValue();
				if (cell0Data.equals(testName)) {
					for (Cell cell : row) {
						String data = cell.getStringCellValue();
						testData.add(data);
					}
					break;
				}
			}
			book.close();
			testData.remove(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return testData;
	}
	
	public static WebDriver getDriver()
	{
		return driver;
	}

}
