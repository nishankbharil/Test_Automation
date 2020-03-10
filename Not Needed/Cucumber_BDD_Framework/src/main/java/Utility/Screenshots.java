package Utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class Screenshots {
	
	static String currentDir = System.getProperty("user.dir");
	
	// Method to Capture the Screenshot
		public String createScreenshot(WebDriver driver, String fileName, ExtentTest eTest,String rowNoGbl, String date1) {
			String file = null;		

			// Generate screenshot as a file object
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				//Copy file object to designated location
				file = currentDir + "\\Reports\\Screenshots\\" + fileName + "_Itr(" + rowNoGbl + ")" + date1 + ".png";			
				FileUtils.copyFile(scrFile, new File(file));			
			} catch (IOException e) {
				System.out.println("Error while generating screenshot:\n" + e.toString());
			}
			return file;

		}

}
