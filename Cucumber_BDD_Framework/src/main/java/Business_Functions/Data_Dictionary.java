package Business_Functions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utility.PropertyReader;
import junit.framework.TestCase;

public class DataDictionary extends TestCase{
	PropertyReader objPageReadDict = new PropertyReader("src/test/java/Page_Objects/Dictionaries.properties");
	Common_Business_Functions objCBF = new Common_Business_Functions();
	public void NavigateToDataDictionary(WebDriver driver,ExtentTest test,String rowNoGbl, String date1,ExtentReports extent,String lnkText) throws Exception
	{
		objCBF.clickOnLink(driver, test, extent, rowNoGbl, date1, lnkText);
	}
	public static void main(String[] args) {
	}

}
