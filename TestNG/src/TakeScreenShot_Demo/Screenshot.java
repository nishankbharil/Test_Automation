package TakeScreenShot_Demo;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot 

{
	WebDriver driver;
	
	public void captureScreenShot(String fileName)
	{
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(file, new File())
	}
	
	
}
