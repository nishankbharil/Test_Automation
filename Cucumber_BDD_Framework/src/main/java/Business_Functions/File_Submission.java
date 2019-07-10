package Business_Functions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Utility.DBUtil;
import Utility.ExcelUtils;
import Utility.PropertyReader;
import Utility.Screenshots;
import junit.framework.Assert;
import junit.framework.TestCase;

public class File_Submission extends TestCase{

	PropertyReader objPageObjsProRead = new PropertyReader("src/test/java/Page_Objects/Common_Page_Objects.properties");
	PropertyReader objPageRead = new PropertyReader("src/test/java/Page_Objects/File_Submission.properties");
	Screenshots objCreateScreenshot = new Screenshots();
	Common_Business_Functions objCBF= new Common_Business_Functions();
	public void uploadZipFile(WebDriver driver,String rowNo,ExtentTest test,String strFilePath) throws Throwable {
		try {
			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(objPageRead.getLocator("objZipfileUploadBtn")).click();
			test.log(Status.INFO, "Clicked Zip Upload button on the page");
			Thread.sleep(2000);
			File obj = new File("src/test/resources/FilesToUpload/ZipFileUpload/"+strFilePath);
			String Path=obj.getAbsolutePath();
			driver.findElement(By.xpath("//input[@name='myFile']")).sendKeys(Path);
			Thread.sleep(2000);
			test.log(Status.INFO, "Selected the file to be uploaded and hit Enter");

			driver.findElement(objPageRead.getLocator("objUploadBtn")).click();
			Thread.sleep(1000);
			test.log(Status.INFO, "Clicked Upload Button");

		} catch (Exception e) {
			System.out.println("In the catch block");
		}
	}
	public void uploadIndividualFile(WebDriver driver,String rowNo,ExtentTest test,String strFilePath) throws Throwable {
		try {
			Thread.sleep(2000);
			File obj = new File("src/test/resources/FilesToUpload/IndividualFileUpload/"+strFilePath);
			String Path=obj.getAbsolutePath();
			driver.findElement(By.xpath("//input[@name='myFile']")).sendKeys(Path);
			Thread.sleep(2000);
			test.log(Status.INFO, "Selected the file to be uploaded and hit Enter");

			driver.findElement(objPageRead.getLocator("objUploadBtn")).click();
			Thread.sleep(1000);
			test.log(Status.INFO, "Clicked Upload Button");

		} catch (Exception e) {
			System.out.println("In the catch block");
		}
	}
	public void FilterForStatusInFileVault(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1,String fileStatus) throws Exception
	{
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		objCBF.scrollToRightForFileVault(driver, test, extent, rowNoGbl, date1);
		driver.findElement(objPageRead.getLocator("objProcessingStatusFilter")).click();
		driver.findElement(By.xpath("//i[@title='"+fileStatus+"']/preceding-sibling::input")).click();
		test.pass("Filter selected for processing status", MediaEntityBuilder
				.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "FilterSelected", test,rowNoGbl, date1)).build());
		
	}
	public void captureReceptionDate(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1,String workbookName,String colName) throws IOException
	{try{
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String value=null;String receptionDate=null;
		WebElement element= driver.findElement(By.xpath("//div[@role='gridcell'][@col-id='receptionDate']"));
		if(element.isDisplayed())
		{
			 value= element.getText();
			 String valu1=value.trim();
			 receptionDate=valu1;
			 String Path="src/test/java/TestData/"+workbookName;
				ExcelUtils.setCellValueUsingColName(Path,colName, rowNoGbl, receptionDate);
			 System.out.println(" Reception date is " +receptionDate+ "");
				test.pass(" Reception date is " +receptionDate+ "", MediaEntityBuilder
				.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyCaptureReception datePassed", test,rowNoGbl, date1)).build());
		}
		extent.flush();
	}catch(Exception e)	{
		System.out.println("In catch block");
		// Add the screenshot to the Reporting steps with a hyperlink
		test.fail("NoSuchElementException : " + e.getMessage());
		test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
	
		// Calling flush writes everything to the Extent Report
		extent.flush();

		fail("NoSuchElementException");
	}
		
	}
	public void captureFileName(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1,String workbookName,String colName) throws IOException
	{try{
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String value=null;String receptionDate=null;
		WebElement element= driver.findElement(By.xpath("//div[@role='gridcell'][@col-id='fileName']"));
		if(element.isDisplayed())
		{
			 value= element.getText();
			 String valu1=value.trim();
			 receptionDate=valu1;
			 String Path="src/test/java/TestData/"+workbookName;
				ExcelUtils.setCellValueUsingColName(Path,colName, rowNoGbl, receptionDate);
			 System.out.println(" Reception date is " +receptionDate+ "");
				test.pass(" Reception date is " +receptionDate+ "", MediaEntityBuilder
				.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyCaptureReception datePassed", test,rowNoGbl, date1)).build());
		}
		extent.flush();
	}catch(Exception e)	{
		System.out.println("In catch block");
		// Add the screenshot to the Reporting steps with a hyperlink
		test.fail("NoSuchElementException : " + e.getMessage());
		test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
	
		// Calling flush writes everything to the Extent Report
		extent.flush();

		fail("NoSuchElementException");
		}		
	}
	private boolean isFileDownloaded_Ext(String dirPath, String ext){
		boolean flag=false;
	    File dir = new File("C:\\Users\\Xa_bodkhes\\Downloads");
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        flag = false;
	    }
	    
	    for (int i = 1; i < files.length; i++) {
	    	if(files[i].getName().contains(ext)) {
	    		flag=true;
	    	}
	    }
	    return flag;
	}
	public void verifyFileIsDownloaded(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1,String dataCollectionCode,String userName) throws IOException
	{
		try{
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Runtime.getRuntime().exec("wscript src/test/resources/VBScript/DownloadFileCheck.vbs " + userName+"|"+ dataCollectionCode);
		test.pass("File downloaded verified", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyFileDownloadedPassed", test,rowNoGbl, date1)).build());
		//System.out.println("Downloaded file:" + userName+"|"+ dataCollectionCode);
		
		Runtime runtime = Runtime.getRuntime();
		Process proc = runtime.exec("wscript src/test/resources/VBScript/DownloadFileCheck.vbs " + userName+"|"+ dataCollectionCode);
		System.out.println ("zip file downloaded successfully for"+dataCollectionCode);
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		System.out.println (stdInput.readLine());

		}catch(Exception e)	{
		System.out.println("In catch block");
		// Add the screenshot to the Reporting steps with a hyperlink
		test.fail("NoSuchElementException : " + e.getMessage());
		test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
	
		// Calling flush writes everything to the Extent Report
		extent.flush();

		fail("NoSuchElementException");
		}		
	}
	public void verifyModuleFilterInSubmissionList(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1,String ModuleName) throws Exception
	{
		try
		{
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(objPageRead.getLocator("objModuleFilterButton")).click();
		Select filters= new Select(driver.findElement(By.xpath("//select[@id='filterType']")));
		List<WebElement> totalFilters= filters.getOptions();
		int size= totalFilters.size();
		for(int i=0;i<=size-1;i++)
		{
			filters.selectByIndex(i);
			driver.findElement(By.xpath("//input[@type='text']")).clear();
			driver.findElement(By.xpath("//input[@type='text']")).sendKeys(ModuleName);
			Thread.sleep(1000);
			
			if(totalFilters.get(i).getText().equals("Equals"))
			{
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='module'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords>0)
					test.pass("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyClickedLinkPassed", test,rowNoGbl, date1)).build());
					
				else
					   test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterFailed", test,rowNoGbl, date1)).build());
			}
			if(totalFilters.get(i).getText().equals("Not equal"))
			{
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='module'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords<1)
					test.pass("No Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterPaased", test,rowNoGbl, date1)).build());
				else
					   test.fail("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterFailed", test,rowNoGbl, date1)).build());
			}
			if(totalFilters.get(i).getText().equals("Starts with"))
			{
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='module'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords>0)
					test.pass("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterPaased", test,rowNoGbl, date1)).build());
				else
					   test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterFailed", test,rowNoGbl, date1)).build());
			}
			if(totalFilters.get(i).getText().equals("Ends with"))
			{
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='module'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords>0)
					test.pass("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterPaased", test,rowNoGbl, date1)).build());
				else
					   test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterFailed", test,rowNoGbl, date1)).build());
			}
			if(totalFilters.get(i).getText().equals("Contains"))
			{
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='module'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords>0)
					test.pass("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterPaased", test,rowNoGbl, date1)).build());
				else
					   test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterFailed", test,rowNoGbl, date1)).build());
			}
			if(totalFilters.get(i).getText().equals("Not contains"))
			{
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='module'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords<1)
					test.pass("No Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterPaased", test,rowNoGbl, date1)).build());
				else
					   test.fail("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterFailed", test,rowNoGbl, date1)).build());
			}
		}
		extent.flush();
	}
	catch(Exception e) {
		System.out.println("In catch block");
		// Add the screenshot to the Reporting steps with a hyperlink
		test.fail("Record not added " + e.getMessage());
		test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));

		// Calling flush writes everything to the Extent Report
		extent.flush();

		fail("NoSuchElementException");
	}
	}
	public void verifyReceptionDateFilterInSubmissionList(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1,String ReceptionDate) throws Exception
	{
		try
		{
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(objPageRead.getLocator("objReceptionDateFilterButton")).click();
		Select filters= new Select(driver.findElement(By.xpath("//div/select")));
		List<WebElement> totalFilters= filters.getOptions();
		int size= totalFilters.size();
		for(int i=0;i<=size-1;i++)
		{
			filters.selectByIndex(i);	
			String[] date=ReceptionDate.trim().split("/|:| ");
			String dd=date[0];
		    String mm=date[1];
		    String yyyy=date[2];
		    String hh= date[3];
		    String min= date[4];
		    String ss=  date[5];
			if(totalFilters.get(i).getText().equals("Equals"))
			{
				driver.findElement(objPageRead.getLocator("objDD")).sendKeys(dd);
				driver.findElement(objPageRead.getLocator("objMM")).sendKeys(mm);
				driver.findElement(objPageRead.getLocator("objYYYY")).sendKeys(yyyy);
				Thread.sleep(1000);
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='receptionDate'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords>0)
					test.pass("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyReceptionDateFilterPaased", test,rowNoGbl, date1)).build());
					
				else
					   test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyReceptionDateFilterFailed", test,rowNoGbl, date1)).build());
			}
			if(totalFilters.get(i).getText().equals("Less than"))
			{
				driver.findElement(objPageRead.getLocator("objDD")).sendKeys(dd);
				driver.findElement(objPageRead.getLocator("objMM")).sendKeys(mm);
				driver.findElement(objPageRead.getLocator("objYYYY")).sendKeys(yyyy);
				driver.findElement(objPageRead.getLocator("objHH")).sendKeys(hh);
				driver.findElement(objPageRead.getLocator("objMIN")).sendKeys(min);
				driver.findElement(objPageRead.getLocator("objSS")).sendKeys(ss);
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='receptionDate'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords<1)
					test.pass("No Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyReceptionDateFilterPaased", test,rowNoGbl, date1)).build());
				else
					   test.fail("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyReceptionDateFilterFailed", test,rowNoGbl, date1)).build());
			}
			if(totalFilters.get(i).getText().equals("Greater than"))
			{
				driver.findElement(objPageRead.getLocator("objDD")).sendKeys(dd);
				driver.findElement(objPageRead.getLocator("objMM")).sendKeys(mm);
				driver.findElement(objPageRead.getLocator("objYYYY")).sendKeys(yyyy);
				driver.findElement(objPageRead.getLocator("objHH")).sendKeys(hh);
				driver.findElement(objPageRead.getLocator("objMIN")).sendKeys(min);
				driver.findElement(objPageRead.getLocator("objSS")).sendKeys(ss);
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='receptionDate'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords>0)
					test.pass("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyReceptionDateFilterPaased", test,rowNoGbl, date1)).build());
				else
					   test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyReceptionDateFilterFailed", test,rowNoGbl, date1)).build());
			}
			if(totalFilters.get(i).getText().equals("In range"))
			{
				driver.findElement(objPageRead.getLocator("objDD")).sendKeys(dd);
				driver.findElement(objPageRead.getLocator("objMM")).sendKeys(mm);
				driver.findElement(objPageRead.getLocator("objYYYY")).sendKeys(yyyy);
				driver.findElement(objPageRead.getLocator("objHH")).sendKeys(hh);
				driver.findElement(objPageRead.getLocator("objMIN")).sendKeys(min);
				driver.findElement(objPageRead.getLocator("objSS")).sendKeys(ss);
				int newSS= Integer.parseInt(ss)+1;
				driver.findElement(objPageRead.getLocator("objDDInRangesecond")).sendKeys(dd);
				driver.findElement(objPageRead.getLocator("objMMInRangesecond")).sendKeys(mm);
				driver.findElement(objPageRead.getLocator("objYYYYInRangesecond")).sendKeys(yyyy);
				driver.findElement(objPageRead.getLocator("objHHInRangesecond")).sendKeys(hh);
				driver.findElement(objPageRead.getLocator("objMINInRangesecond")).sendKeys(min);
				driver.findElement(objPageRead.getLocator("objSSInRangesecond")).sendKeys(Integer.toString(newSS));
				driver.findElement(objPageRead.getLocator("objSSInRangesecond")).sendKeys(Keys.TAB);
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='receptionDate'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords>0)
					test.pass("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyReceptionDateFilterPaased", test,rowNoGbl, date1)).build());
				else
					   test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyReceptionDateFilterFailed", test,rowNoGbl, date1)).build());
			}			
		}
		extent.flush();
	}
	catch(Exception e) {
		System.out.println("In catch block");
		// Add the screenshot to the Reporting steps with a hyperlink
		test.fail("Record not added " + e.getMessage());
		test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));

		// Calling flush writes everything to the Extent Report
		extent.flush();

		fail("NoSuchElementException");
	}
	}
	public void verifyFileNameInFileVault(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1,String FileName) throws Exception
	{
		try
		{
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(objPageRead.getLocator("objFileNameFilterButtonFileVault")).click();
		Select filters= new Select(driver.findElement(By.xpath("//select[@id='filterType']")));
		List<WebElement> totalFilters= filters.getOptions();
		int size= totalFilters.size();
		for(int i=0;i<=size-1;i++)
		{
			filters.selectByIndex(i);
			driver.findElement(By.xpath("//input[@type='text']")).clear();
			driver.findElement(By.xpath("//input[@type='text']")).sendKeys(FileName);
			Thread.sleep(1000);
			
			if(totalFilters.get(i).getText().equals("Equals"))
			{
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='module'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords>0)
					test.pass("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyClickedLinkPassed", test,rowNoGbl, date1)).build());
					
				else
					   test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterFailed", test,rowNoGbl, date1)).build());
			}
			if(totalFilters.get(i).getText().equals("Not equal"))
			{
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='module'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords>0)
					test.pass("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyClickedLinkPassed", test,rowNoGbl, date1)).build());
					
				else
					   test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterFailed", test,rowNoGbl, date1)).build());
			}
			if(totalFilters.get(i).getText().equals("Starts with"))
			{
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='module'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords>0)
					test.pass("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterPaased", test,rowNoGbl, date1)).build());
				else
					   test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterFailed", test,rowNoGbl, date1)).build());
			}
			if(totalFilters.get(i).getText().equals("Ends with"))
			{
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='module'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords>0)
					test.pass("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterPaased", test,rowNoGbl, date1)).build());
				else
					   test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterFailed", test,rowNoGbl, date1)).build());
			}
			if(totalFilters.get(i).getText().equals("Contains"))
			{
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='module'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords>0)
					test.pass("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterPaased", test,rowNoGbl, date1)).build());
				else
					   test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterFailed", test,rowNoGbl, date1)).build());
			}
			if(totalFilters.get(i).getText().equals("Not contains"))
			{
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='module'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords>0)
					test.pass("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyClickedLinkPassed", test,rowNoGbl, date1)).build());
					
				else
					   test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyModuleFilterFailed", test,rowNoGbl, date1)).build());
			}
		}
		extent.flush();
	}
	catch(Exception e) {
		System.out.println("In catch block");
		// Add the screenshot to the Reporting steps with a hyperlink
		test.fail("Record not added " + e.getMessage());
		test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));

		// Calling flush writes everything to the Extent Report
		extent.flush();

		fail("NoSuchElementException");
	}
	}
	public void verifyReceptionDateFilterInFileVault(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1,String ReceptionDate) throws Exception
	{
		try
		{
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		objCBF.scrollToRight(driver, test, extent, rowNoGbl, date1);
		driver.findElement(objPageRead.getLocator("objReceptionDateFilterButtonFileVault")).click();
		Select filters= new Select(driver.findElement(By.xpath("//div/select")));
		List<WebElement> totalFilters= filters.getOptions();
		int size= totalFilters.size();
		for(int i=0;i<=size-1;i++)
		{
			filters.selectByIndex(i);	
			String[] date=ReceptionDate.trim().split("/|:| ");
			String dd=date[0];
		    String mm=date[1];
		    String yyyy=date[2];
		    String hh= date[3];
		    String min= date[4];
		    String ss=  date[5];
			if(totalFilters.get(i).getText().equals("Equals"))
			{
				driver.findElement(objPageRead.getLocator("objDD")).sendKeys(dd);
				driver.findElement(objPageRead.getLocator("objMM")).sendKeys(mm);
				driver.findElement(objPageRead.getLocator("objYYYY")).sendKeys(yyyy);
				Thread.sleep(1000);
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='receptionDate'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords>0)
					test.pass("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyReceptionDateFilterPaased", test,rowNoGbl, date1)).build());
					
				else
					   test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyReceptionDateFilterFailed", test,rowNoGbl, date1)).build());
			}
			if(totalFilters.get(i).getText().equals("Less than"))
			{
				driver.findElement(objPageRead.getLocator("objDD")).sendKeys(dd);
				driver.findElement(objPageRead.getLocator("objMM")).sendKeys(mm);
				driver.findElement(objPageRead.getLocator("objYYYY")).sendKeys(yyyy);
				driver.findElement(objPageRead.getLocator("objHH")).sendKeys(hh);
				driver.findElement(objPageRead.getLocator("objMIN")).sendKeys(min);
				driver.findElement(objPageRead.getLocator("objSS")).sendKeys(ss);
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='receptionDate'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords>0)
					test.pass("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyReceptionDateFilterPaased", test,rowNoGbl, date1)).build());
					
				else
					   test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyReceptionDateFilterFailed", test,rowNoGbl, date1)).build());
			}
			if(totalFilters.get(i).getText().equals("Greater than"))
			{
				driver.findElement(objPageRead.getLocator("objDD")).sendKeys(dd);
				driver.findElement(objPageRead.getLocator("objMM")).sendKeys(mm);
				driver.findElement(objPageRead.getLocator("objYYYY")).sendKeys(yyyy);
				driver.findElement(objPageRead.getLocator("objHH")).sendKeys(hh);
				driver.findElement(objPageRead.getLocator("objMIN")).sendKeys(min);
				driver.findElement(objPageRead.getLocator("objSS")).sendKeys(ss);
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='receptionDate'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords>0)
					test.pass("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyReceptionDateFilterPaased", test,rowNoGbl, date1)).build());
				else
					   test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyReceptionDateFilterFailed", test,rowNoGbl, date1)).build());
			}
			if(totalFilters.get(i).getText().equals("In range"))
			{
				driver.findElement(objPageRead.getLocator("objDD")).sendKeys(dd);
				driver.findElement(objPageRead.getLocator("objMM")).sendKeys(mm);
				driver.findElement(objPageRead.getLocator("objYYYY")).sendKeys(yyyy);
				driver.findElement(objPageRead.getLocator("objHH")).sendKeys(hh);
				driver.findElement(objPageRead.getLocator("objMIN")).sendKeys(min);
				driver.findElement(objPageRead.getLocator("objSS")).sendKeys(ss);
				int newSS= Integer.parseInt(ss)+1;
				driver.findElement(objPageRead.getLocator("objDDInRangesecond")).sendKeys(dd);
				driver.findElement(objPageRead.getLocator("objMMInRangesecond")).sendKeys(mm);
				driver.findElement(objPageRead.getLocator("objYYYYInRangesecond")).sendKeys(yyyy);
				driver.findElement(objPageRead.getLocator("objHHInRangesecond")).sendKeys(hh);
				driver.findElement(objPageRead.getLocator("objMINInRangesecond")).sendKeys(min);
				driver.findElement(objPageRead.getLocator("objSSInRangesecond")).sendKeys(Integer.toString(newSS));
				driver.findElement(objPageRead.getLocator("objSSInRangesecond")).sendKeys(Keys.TAB);
				List<WebElement> records =driver.findElements(By.xpath(".//div[@col-id='receptionDate'][@role='gridcell']"));
				int sizeofrecords= records.size();
				if(sizeofrecords>0)
					test.pass("Records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyReceptionDateFilterPaased", test,rowNoGbl, date1)).build());
				else
					   test.fail("No records loaded in table", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyReceptionDateFilterFailed", test,rowNoGbl, date1)).build());
			}			
		}
		extent.flush();
	}
	catch(Exception e) {
		System.out.println("In catch block");
		// Add the screenshot to the Reporting steps with a hyperlink
		test.fail("Record not added " + e.getMessage());
		test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));

		// Calling flush writes everything to the Extent Report
		extent.flush();

		fail("NoSuchElementException");
	}
	}
	public void selectRecordInSubmissionList(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1) throws IOException
	{
		try
		{
			Thread.sleep(2000);	
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[@role='gridcell'][@col-id='0']/span/span[1]/i[2]")).click();
			extent.flush();
	}
	catch(Exception e) {
		System.out.println("In catch block");
		// Add the screenshot to the Reporting steps with a hyperlink
		test.fail("Record not added " + e.getMessage());
		test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));

		// Calling flush writes everything to the Extent Report
		extent.flush();

		fail("NoSuchElementException");
	}
	}
	public void CheckCountOfFilesInBrackets(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1, String ColName,int countInFileVault) throws IOException
	{
		try
		{
			Thread.sleep(3000);	
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			List<WebElement> records ;
			String name=null;
			int i=0,childCount=0;
			records= driver.findElements(By.xpath(".//div[@col-id='"+ColName+"'][@role='gridcell']"));
			int size= records.size();
			if(size>0){				
					WebElement element = records.get(i);
					if(element.isDisplayed())
					{
						 name = element.getText();
					}
					String cycleName[]= name.split("");
					childCount= Integer.parseInt(cycleName[7]);
			}
		    else
			   {
				   System.out.println("No records loaded in table");
				   test.fail("No records loaded in table", MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyRecordsCountFailed", test,rowNoGbl, date1)).build());
			   }
			if(childCount>0)
			{
				 if(childCount==(countInFileVault-1))
				 {
					 test.pass("Count in brackets correctly gets displayed", MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyChildRecordPassed", test,rowNoGbl, date1)).build());
				 }
				 else
				 {
					 test.fail("Count in brackets incorrectly gets displayed",MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyChildRecordFailed", test,rowNoGbl, date1)).build());
				 }
			}
			extent.flush();
	}
	catch(Exception e) {
		System.out.println("In catch block");
		// Add the screenshot to the Reporting steps with a hyperlink
		test.fail("Record not added " + e.getMessage());
		test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));

		// Calling flush writes everything to the Extent Report
		extent.flush();

		fail("NoSuchElementException");
	}
	}
	public void verifyChildRecords(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1, String ColName) throws IOException
	{
		try
		{
			Thread.sleep(3000);	
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			List<WebElement> records ;
			String name=null;
			int i=0,childCount=0;
			records= driver.findElements(By.xpath(".//div[@col-id='"+ColName+"'][@role='gridcell']"));
			int size= records.size();
			if(size>0){				
					WebElement element = records.get(i);
					if(element.isDisplayed())
					{
						 name = element.getText();
					}
					String cycleName[]= name.split("");
					childCount= Integer.parseInt(cycleName[7]);
			}
		    else
			   {
				   System.out.println("No records loaded in table");
				   test.fail("No records loaded in table", MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyRecordsCountFailed", test,rowNoGbl, date1)).build());
			   }
			if(childCount>0)
			{
				 driver.findElement(objPageRead.getLocator("objChildDownArrow")).click();
				 Thread.sleep(2000);
				 List<WebElement> newRecords=driver.findElements(By.xpath(".//div[@col-id='"+ColName+"'][@role='gridcell']"));
				 int sizeOfNewRecords= newRecords.size();
				 if(childCount==(sizeOfNewRecords-1))
				 {
					 test.pass("Child record count correctly gets displayed", MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyChildRecordPassed", test,rowNoGbl, date1)).build());
				 }
				 else
				 {
					 test.fail("Child record count incorrectly gets displayed",MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyChildRecordFailed", test,rowNoGbl, date1)).build());
				 }
			}
			extent.flush();
	}
	catch(Exception e) {
		System.out.println("In catch block");
		// Add the screenshot to the Reporting steps with a hyperlink
		test.fail("Record not added " + e.getMessage());
		test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));

		// Calling flush writes everything to the Extent Report
		extent.flush();

		fail("NoSuchElementException");
	}

	}
	public void navigateSubmissionList(WebDriver driver,String rowNo,ExtentTest test, String strDCName) throws Throwable {
		try {

			Actions action = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(objPageRead.getLocator("objSubmissionTab")).click();
			Thread.sleep(12000);
			
			//Search for desired data collection
			WebElement DataColl= driver.findElement(objPageRead.getLocator("objSearchDC"));
			DataColl.sendKeys(strDCName);
			Thread.sleep(2000);
						
			List<WebElement> Matches = driver.findElements(objPageRead.getLocator("objGridCodeCol")); 
			
			// Now iterate through them and check for our desired match
			for (WebElement anElement : Matches) {
				System.out.println("column value: "+anElement.getText());
			    if (anElement.getText().equals(strDCName)) {
			    	System.out.println("data col matches");
			    	anElement.click();
			    	break;
			    }
			}
		
							
			WebElement CurrentRow = driver.switchTo().activeElement();
			
			action.contextClick(CurrentRow).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(2000);
			WebElement viewOpen = driver.findElement(objPageRead.getLocator("objSubView")); /*This will select menu after right click */
			viewOpen.click();
			Thread.sleep(1000);
			driver.findElement(objPageRead.getLocator("objSearchBtn")).click();
			Thread.sleep(3000);

		} catch (IOException e) {
			System.out.println("In the catch block");
		}
	}
	public void verifyLatestVersionOfFile(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1,String columnName) throws IOException
	{
		try {
			Thread.sleep(1000);	
			 boolean flag=false;
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        objCBF.scrollToLeft(driver,test,extent,rowNoGbl,date1);
	        driver.findElement(objPageRead.getLocator("objChildDownArrow")).click();
	        objCBF.scrollToRight(driver,test,extent,rowNoGbl,date1);
	        List<WebElement> records ;
			records= driver.findElements(By.xpath(".//div[@col-id='"+columnName+"'][@role='gridcell']"));
			int size= records.size();		   
			for(int i=0;i<size-1;i++)
			{
				int topValue= Integer.parseInt(records.get(size-1).getText());
				if(Integer.parseInt(records.get(i).getText())<topValue)
				flag=true;
			}			
			if(flag==true)
				test.pass("Latest version of file is on top", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyLatestVersionPassed", test,rowNoGbl, date1)).build());
			else
			   test.fail("Latest version of file is not on top", MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyLatestVersionFailed", test,rowNoGbl, date1)).build());
			extent.flush();
			} catch (Exception e) {
				System.out.println("In catch block");
				// Add the screenshot to the Reporting steps with a hyperlink
				test.fail("Action does not exist: " + e.getMessage());
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));

				// Close the Browser
				driver.quit();
				test.pass("Closed the Browser");

				// Calling flush writes everything to the Extent Report
				extent.flush();

				fail("NoSuchElementException");
			}
	}
	public void selectFile(WebDriver driver,String rowNo,ExtentTest test,String strFilePath) throws Throwable {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//System.out.println("strFilePath = "+strFilePath);
			Thread.sleep(1000);
			driver.findElement(objPageObjsProRead.getLocator("objTools")).click();
			test.log(Status.INFO, "Clicked Tools in the Menu");
			Thread.sleep(1000);
			driver.findElement(objPageObjsProRead.getLocator("objFileUpload")).click();
			test.log(Status.INFO, "Clicked File Upload option in the Menu");
			Thread.sleep(1000);
			driver.findElement(objPageObjsProRead.getLocator("objBrowse")).click();
			test.log(Status.INFO, "Clicked Browse Button");
			Thread.sleep(1000);			

			//VBScript code to do actions on the Upload File Windows Dialog box
			Runtime.getRuntime().exec("wscript src/test/resources/VBScript/upload.vbs " + strFilePath);
			Thread.sleep(1000);
			test.log(Status.INFO, "Entered the file to be uploaded and hit Enter");			

		} catch (IOException e) {
			System.out.println("In the catch block");
		}
	}
	
	public void verifyUploadFileDialog(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1, String strSuccessMsg, String ScenarioType) throws InterruptedException
	{
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try {
			if (ScenarioType.equalsIgnoreCase("Positive")) {
				if (driver.findElement(objPageRead.getLocator("objFileUploadInfoImage")).isDisplayed()) {

					String strActMsg = driver.findElement(objPageRead.getLocator("objInfoMsg")).getText();
					if (strSuccessMsg.equals(strActMsg.trim())){
						System.out.println("Success msg verified");
						// Add the screenshot to the Reporting steps with a hyperlink
						test.pass("Success Message is displayed as the valid zip file has been uploaded",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyMsgDisplayed", test,rowNoGbl, date1))
								.build());				
					}
	
					else {				
						System.out.println("incorrect msg in positive scenario");
						test.fail("Incorrect Message is displayed",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyInvalidMsgDisplayed", test,rowNoGbl, date1))
								.build());				
	
						// Add the screenshot to the Reporting steps with a hyperlink
						test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyInvalidMsgDisplayed", test,rowNoGbl, date1));
					}
				}
			
			}
			else
			{
				if(ScenarioType.equalsIgnoreCase("Negative")) {
					if (driver.findElement(objPageRead.getLocator("objFileUploadFailImage")).isDisplayed()) {
						
						String strActMsg = driver.findElement(objPageRead.getLocator("objFailureMsg")).getText();
						System.out.println("actual msg: "+ strActMsg);
						System.out.println("Expected msg: "+ strSuccessMsg);
						if (strSuccessMsg.contains(strActMsg.trim()))
						{ 
							// Add the screenshot to the Reporting steps with a hyperlink
							System.out.println("error validated, passed!");
							test.pass("Error Message is displayed as the invalid zip file has been uploaded",
									MediaEntityBuilder
									.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyMsgDisplayed", test,rowNoGbl, date1))
									.build());				
						}
	
						else {				
							System.out.println("incorrect msg displayed");
							test.fail("Incorrect Message is displayed",
									MediaEntityBuilder
									.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyInvalidMsgDisplayed", test,rowNoGbl, date1))
									.build());				
	
							// Add the screenshot to the Reporting steps with a hyperlink
							test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyInvalidMsgDisplayed", test,rowNoGbl, date1));
						}
					}
				}
				else {
					System.out.println("Else part, test failed");
					test.fail("Expected message not displayed",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyMsgNotDisplayed", test,rowNoGbl, date1))
							.build());				

					// Add the screenshot to the Reporting steps with a hyperlink
					test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyMsgNotDisplayed", test,rowNoGbl, date1));
				}
			}
			
				
			// Close the Browser
			//driver.close();
			//driver.quit();
			//test.log(Status.INFO, "Closed the Browser");

			// calling flush writes everything to the Extent Report
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			//driver.close();
			//driver.quit();
			//test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyUploadFileErrorDialog(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1)
	{
		try {
			if (driver.findElement(objPageObjsProRead.getLocator("objError")).isDisplayed()) {
				// Add the screenshot to the Reporting steps with a hyperlink
				test.pass("Verified Error Message is displayed as the incorrect file as been uploaded",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorDisplayed", test,rowNoGbl, date1))
						.build());				
				driver.findElement(objPageObjsProRead.getLocator("objErrorOkButton")).click();
			} else {				
				// Add the screenshot to the Reporting steps with a hyperlink
				test.fail("Verified Error Message is not displayed as the correct file as been uploaded",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorNotDisplayed", test,rowNoGbl, date1))
						.build());				

				// Add the screenshot to the Reporting steps with a hyperlink
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorNotDisplayed", test,rowNoGbl, date1));

				// Close the Browser
				driver.quit();
				test.log(Status.INFO, "Closed the Browser");

				// calling flush writes everything to the Extent Report
				extent.flush();
				fail("Verified Error Message is not displayed as the correct file as been uploaded");
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// calling flush writes everything to the Extent Report
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyUploadFileErrorMessageDialog(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1,String strExpErrorMsg)
	{
		try {
			String strActErrorMsg = driver.findElement(objPageObjsProRead.getLocator("objError")).getText();
			//System.out.println("strActErrorMsg = " + strActErrorMsg);
			//System.out.println("strExpErrorMsg = " + strExpErrorMsg);
			if (strActErrorMsg.equals(strExpErrorMsg)) {
				// Add the screenshot to the Reporting steps with a hyperlink
				test.pass("Verified Error Message displayed is correct as the incorrect file as been uploaded",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorDisplayed", test,rowNoGbl, date1))
						.build());				
				driver.findElement(objPageObjsProRead.getLocator("objErrorOkButton")).click();
			} else {				
				// Add the screenshot to the Reporting steps with a hyperlink
				test.fail("Verified Error Message displayed is not proper",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorNotDisplayed", test,rowNoGbl, date1))
						.build());				

				// Add the screenshot to the Reporting steps with a hyperlink
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorNotDisplayed", test,rowNoGbl, date1));

				// Close the Browser
				driver.quit();
				test.log(Status.INFO, "Closed the Browser");

				// calling flush writes everything to the Extent Report
				extent.flush();
				fail("Verified Error Message is not displayed as the correct file as been uploaded");
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// calling flush writes everything to the Extent Report
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifySelectFileSuccessUploadPage(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1,String strExpFileName)
	{
		try {
			String strActFileName = driver.findElement(objPageObjsProRead.getLocator("objFileName")).getText();
			//System.out.println("strActFileName = " + strActFileName);
			//System.out.println("strExpFileName = " + strExpFileName);
			if (strActFileName.equals(strExpFileName)) {
				// Add the screenshot to the Reporting steps with a hyperlink
				test.pass("Verified File Name is Selected and Displayed",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyFileNameDisplayed", test,rowNoGbl, date1))
						.build());				
			} else {				
				// Add the screenshot to the Reporting steps with a hyperlink
				test.fail("Verified File Name is not Selected and not Displayed",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyFileNameNotDisplayed", test,rowNoGbl, date1))
						.build());

				// Close the Browser
				driver.quit();
				test.log(Status.INFO, "Closed the Browser");

				// calling flush writes everything to the Extent Report
				extent.flush();
				fail("Verified File Name is not Selected and not Displayed");
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// calling flush writes everything to the Extent Report
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyUploadFileSuccessGreenTick(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1)
	{
		try {

			if (driver.findElement(objPageObjsProRead.getLocator("objFileUploadSuccessImage")).isDisplayed()) {
				// Add the screenshot to the Reporting steps with a hyperlink
				test.pass("Verified Success Image(green tick mark)is displayed in the File status as the correct file has been uploaded",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifySuccessGreenTickDisplayed", test,rowNoGbl, date1))
						.build());				

			} else {				
				// Add the screenshot to the Reporting steps with a hyperlink
				test.fail("Verified Success Image(green tick mark)is not displayed in the File status as the incorrect file has been uploaded",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifySuccessGreenTickNotDisplayed", test,rowNoGbl, date1))
						.build());				

				// Add the screenshot to the Reporting steps with a hyperlink
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoGreenTickDisplayed", test,rowNoGbl, date1));

				// Close the Browser
				driver.quit();
				test.log(Status.INFO, "Closed the Browser");

				// calling flush writes everything to the Extent Report
				extent.flush();
				fail("Verified Success Image(green tick mark)is not displayed in the File status as the incorrect file has been uploaded");
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// calling flush writes everything to the Extent Report
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyUploadFileSuccessUploadText(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1)
	{
		try {

			if (driver.findElement(objPageObjsProRead.getLocator("objUploadResultsText")).isDisplayed()) {
				// Add the screenshot to the Reporting steps with a hyperlink
				test.pass("Verified File Upload is Success as the valid Zip file has been uploaded",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyUploadResultsTextDisplayed", test,rowNoGbl, date1))
						.build());				

			} else {				
				// Add the screenshot to the Reporting steps with a hyperlink
				test.fail("Verified File Upload is Fail as the invalid Zip file has been uploaded",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyUploadResultsTextNotDisplayed", test,rowNoGbl, date1))
						.build());				

				// Close the Browser
				driver.quit();
				test.log(Status.INFO, "Closed the Browser");

				// calling flush writes everything to the Extent Report
				extent.flush();
				fail("Verified File Upload is Fail as the invalid Zip file has been uploaded");
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// calling flush writes everything to the Extent Report
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyUploadFileTypeMissing(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1,String strExpErrorMsg)
	{
		try {

			List <WebElement> fileNameList = driver.findElements(objPageObjsProRead.getLocator("objTableFileName")); 
			int i = 0;
			String strErrorTDID = null,strUIFileName,strExpErrorMsg1,strExpErrorMsg2,strErrorMsgText = null,strErrorTextLocator,strActErrorMsgText = null;
			strExpErrorMsg1 ="The filename ";
			strExpErrorMsg2 =" does not conform with the CASPER file naming structure (ReportType_DataCollection_Module_Identifier_RefDate_SeqNumber_(Service_).FileExtension)";
			By strTDPlus;
			Thread.sleep(2000);
			boolean blnFileType=false;
			for(WebElement  objTDEle:fileNameList)
			{
				Thread.sleep(2000);
				//strTDPlus = "(.//i[@class='fa fa-plus-circle'])[1]"; 
				strUIFileName = objTDEle.getText() ;
				strExpErrorMsg = strExpErrorMsg1 + strUIFileName + strExpErrorMsg2;
				//System.out.println("objTDEle.getText() = " + objTDEle.getText());
				driver.findElement(objPageObjsProRead.getLocator("objPlusIcon")).click();
				strErrorTDID = "(.//div[@class='errorFileDataTxt'])[" + (i+1) + "]";
				strActErrorMsgText=driver.findElement(By.xpath(strErrorTDID)).getText();
				String[] strFileNameSeperated = strUIFileName.split("\\.");
				String[] strFileName = strFileNameSeperated [0].split("\\_");
				String strFileType = strFileName[0];
				if(strFileType.equals("C") || strFileType.equals("A") || strFileType.equals("TC") || strFileType.equals("D") || strFileType.equals("TD") || strFileType.equals("TA") )
				{
					blnFileType = true;                                                      
				}
				else
				{
					blnFileType = false;
				}
				i++;  
			}

			if (blnFileType= false)
			{ 
				if(strExpErrorMsg.equals(strActErrorMsgText))
				{
					//System.out.println("Passed : File is rejected since the sequence number is not in between 1-99");
					test.pass("Passed : Verified File is rejected since the File Type is not C or T or D or TC or TD or TA and Verified the Error Message",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyValidFileType", test,rowNoGbl, date1))
							.build());
				}else
				{
					//System.out.println("Failed : Incorrect error message displayed");
					// Add the screenshot to the Reporting steps with a hyperlink
					test.fail("Verified File is not rejected since the File Type is C or T or D or TC or TD or TA and Verified the Error Message",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyNotValidFileType", test,rowNoGbl, date1))
							.build());
				}                                          
			}
			else
			{
				test.fail("Verified File is not rejected since the File Type is C or T or D or TC or TD or TA",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyNotValidFileType", test,rowNoGbl, date1))
						.build());
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// calling flush writes everything to the Extent Report
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyUploadReferenceDateMissing(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1,String strExpErrorMsg)
	{
		try {

			List <WebElement> fileNameList = driver.findElements(objPageObjsProRead.getLocator("objTableFileName")); 
			int i = 0;
			String strErrorTDID = null,strUIFileName,strExpErrorMsg1,strExpErrorMsg2,strErrorMsgText = null,strErrorTextLocator,strActErrorMsgText = null;
			strExpErrorMsg1 ="The filename ";
			strExpErrorMsg2 =" does not conform with the CASPER file naming structure (ReportType_DataCollection_Module_Identifier_RefDate_SeqNumber_(Service_).FileExtension)";
			By strTDPlus;
			Thread.sleep(2000);
			boolean blnFileType=false;
			for(WebElement  objTDEle:fileNameList)
			{
				Thread.sleep(2000);
				//strTDPlus = "(.//i[@class='fa fa-plus-circle'])[1]"; 
				strUIFileName = objTDEle.getText() ;
				strExpErrorMsg = strExpErrorMsg1 + strUIFileName + strExpErrorMsg2;
				//System.out.println("objTDEle.getText() = " + objTDEle.getText());
				driver.findElement(objPageObjsProRead.getLocator("objPlusIcon")).click();
				strErrorTDID = "(.//div[@class='errorFileDataTxt'])[" + (i+1) + "]";
				strActErrorMsgText=driver.findElement(By.xpath(strErrorTDID)).getText();
				String[] strFileNameSeperated = strUIFileName.split("\\.");
				String[] strFileName = strFileNameSeperated [0].split("\\_");
				String strRefDate = strFileName[5];                      

				blnFileType = verifyInput(strRefDate);                      
				//System.out.println("blnFileType = " + blnFileType);
				i++;  
			}

			if (blnFileType==false)
			{ 
				if(strExpErrorMsg.equals(strActErrorMsgText))
				{
					//System.out.println("Passed : File is rejected since the sequence number is not in between 1-99");
					test.pass("Passed : Verified File is rejected since the Reference Date is missing or format is incorrect and Verified the Error Message",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyReferenceDate", test,rowNoGbl, date1))
							.build());
				}else
				{
					//System.out.println("Failed : Incorrect error message displayed");
					// Add the screenshot to the Reporting steps with a hyperlink
					test.fail("Verified File is not rejected since the Reference Date is not missing or format is correct and Verified the Error Message",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyFileReferenceDate", test,rowNoGbl, date1))
							.build());
				}                                          
			}
			else if (blnFileType= true)
			{
				test.fail("Verified File is not rejected since the Reference Date is not missing or format correct and Verified the Error Message",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyNotValidFileType", test,rowNoGbl, date1))
						.build());
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// calling flush writes everything to the Extent Report
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}



	public void verifyReportingCycleMissing(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1,DBUtil objDBUtil)
	{
		try {

			Thread.sleep(2000);
			List <WebElement> fileNameList = driver.findElements(By.xpath(".//td[@class='tableFileNameTxt']")); 
			int i = 0;
			String strTDID = null,strTDPlus,strFileName,strExpErrorMsg,strErrorMsgText,ReportingCycleStatus = null,strExpErrorMsg1,strExpErrorMsg2;
			Thread.sleep(2000);
			for(WebElement  objTDEle:fileNameList)
			{
				Thread.sleep(2000);
				strTDID = "errorFileDataTxt"+i;                       
				strTDPlus = "(.//i[@class='fa fa-plus-circle'])[1]";    
				strFileName = objTDEle.getText() ;                                     
				if(driver.findElement(By.xpath(strTDPlus)).isDisplayed())
				{
					driver.findElement(By.xpath(strTDPlus)).click();
				}
				strErrorMsgText =driver.findElement(By.id(strTDID)).getText();
				String[] strFileNameSeperated = strFileName.split("\\.");
				String[] strFileComponents = strFileNameSeperated[0].split("\\_");
				strExpErrorMsg1 ="The filename ";
				strExpErrorMsg2 =" does not conform with the CASPER file naming structure (ReportType_DataCollection_Module_Identifier_RefDate_SeqNumber_(Service_).FileExtension)";
				strExpErrorMsg = strExpErrorMsg1 + strFileName + strExpErrorMsg2;

				ReportingCycleStatus = objDBUtil.verifyTableColumnValueNotInDB("TBL_CSPR_REPORTINGCYCLE","NAME",strFileComponents[2]);
				System.out.println("ReportingCycleStatus = " + ReportingCycleStatus);
				if(ReportingCycleStatus.equals("0"))
				{
					if(strExpErrorMsg.equals(strErrorMsgText))
					{
						//System.out.println("Passed : File is rejected since the sequence number is not in between 1-99");
						test.pass("Passed : Verified File is rejected since the Reporting Cycle is missing or format is incorrect and Verified the Error Message",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyValidFileType", test,rowNoGbl, date1))
								.build());
					}else
					{
						//System.out.println("Failed : Incorrect error message displayed");
						// Add the screenshot to the Reporting steps with a hyperlink
						test.fail("Verified File is not rejected since the Reporting Cycle is not missing or format is correct and Verified the Error Message",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyNotValidFileType", test,rowNoGbl, date1))
								.build());
					}

				}

				i++;  
			}

			/*// Close the Browser
                driver.quit();
                test.log(Status.INFO, "Closed the Browser");

                // Calling flush writes everything to the Extent Report
                extent.flush();*/         
		}
		catch (Exception e) {
			e.printStackTrace();
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		} 
	}

	public void closeBrowserAndReport(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1)
	{
		try {

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();         
		}
		catch (Exception e) {
			e.printStackTrace();
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		} 
	}

	public void verifyDataCollectionMissing(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1,DBUtil objDBUtil)
	{
		try {
			Thread.sleep(2000);
			List <WebElement> fileNameList = driver.findElements(By.xpath(".//td[@class='tableFileNameTxt']")); 
			int i = 0;
			String strTDID = null,strTDMinus,strTDPlus,strFileName,strExpErrorMsg,strErrorMsgText,ReportingCycleStatus,strExpErrorMsg1,strExpErrorMsg2;
			Thread.sleep(2000);
			for(WebElement  objTDEle:fileNameList)
			{
				Thread.sleep(2000);
				strTDID = "errorFileDataTxt"+i;  
				strTDMinus = "(.//*[@class='fa fa-plus-circle fa-minus-circle'])[1]";
				strTDPlus = "(.//i[@class='fa fa-plus-circle'])[1]";    //System.out.println("strTDPlus = " + strTDPlus);
				verifyWebElementAndClick(driver, strTDMinus);
				strFileName = objTDEle.getText() ;                                      //System.out.println("objTDEle.getText() = " + objTDEle.getText());
				verifyWebElementAndClick(driver, strTDPlus);
				strErrorMsgText =driver.findElement(By.id(strTDID)).getText();
				String[] strFileNameSeperated = strFileName.split("\\.");
				String[] strFileComponents = strFileNameSeperated[0].split("\\_");                      
				strExpErrorMsg1 ="The filename ";
				strExpErrorMsg2 =" does not conform with the CASPER file naming structure (ReportType_DataCollection_Module_Identifier_RefDate_SeqNumber_(Service_).FileExtension)";
				strExpErrorMsg = strExpErrorMsg1 + strFileName + strExpErrorMsg2;

				ReportingCycleStatus = objDBUtil.verifyTableColumnValueNotInDB("TBL_CSPR_REPORTINGCYCLE","NAME",strFileComponents[1]);
				System.out.println("ReportingCycleStatus = " + ReportingCycleStatus);                      
				if(ReportingCycleStatus.equals("0"))
				{
					if(strExpErrorMsg.equals(strErrorMsgText))
					{
						//System.out.println("Passed : File is rejected since the sequence number is not in between 1-99");
						test.pass("Passed : Verified File is rejected since the Data Collection is missing or format is incorrect and Verified the Error Message",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyValidFileType", test,rowNoGbl, date1))
								.build());
					}else
					{
						//System.out.println("Failed : Incorrect error message displayed");
						// Add the screenshot to the Reporting steps with a hyperlink
						test.fail("Verified File is not rejected since the Data Collection is not missing or format is correct and Verified the Error Message",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyNotValidFileType", test,rowNoGbl, date1))
								.build());
						fail("Verified File is not rejected since the Data Collection is not missing or format is correct and Verified the Error Message");
					}                       
				}                     
				i++;  
			}                         
		}
		catch (Exception e) {
			e.printStackTrace();
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		} 
	}

	public void verifyDataCollectionPresent(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1,DBUtil objDBUtil)
	{
		try {
			Thread.sleep(2000);
			List <WebElement> fileNameList = driver.findElements(By.xpath(".//td[@class='tableFileNameTxt']")); 
			int i = 0;
			String strTDID = null,strTDMinus,strTDPlus,strFileName,strExpErrorMsg,strErrorMsgText,strExpErrorMsg1,strExpErrorMsg2;
			boolean ReportingCycleStatus;
			Thread.sleep(2000);
			for(WebElement  objTDEle:fileNameList)
			{
				Thread.sleep(2000);
				strFileName = objTDEle.getText();
				String[] strFileNameSeperated = strFileName.split("\\.");
				String[] strFileComponents = strFileNameSeperated[0].split("\\_");

				ReportingCycleStatus = objDBUtil.verifyTableColumnValueInDB("TBL_CSPR_DATACOLLECTION","CODE",strFileComponents[1]);
				//System.out.println("ReportingCycleStatus = " + ReportingCycleStatus);                      
				if(ReportingCycleStatus)
				{
					if(driver.findElement(objPageObjsProRead.getLocator("objFileUploadSuccessImage")).isDisplayed())
					{
						//System.out.println("Passed : File is rejected since the sequence number is not in between 1-99");
						test.pass("Passed : Verified File is accepted since the Data Collection is present or format is correct and Verified the Green Tick",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyValidFileType", test,rowNoGbl, date1))
								.build());
					}else
					{
						//System.out.println("Failed : Incorrect error message displayed");
						// Add the screenshot to the Reporting steps with a hyperlink
						test.fail("Verified File is not accepted since the Data Collection is not present or format is incorrect and Verified the Green Tick",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyNotValidFileType", test,rowNoGbl, date1))
								.build());
						fail("Verified File is not accepted since the Data Collection is not present or format is incorrect and Verified the Green Tick");
					}                       
				}                     
				i++;  
			}                         
		}
		catch (Exception e) {
			e.printStackTrace();
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		} 
	}

	public void uploadFileDragNDrop(WebDriver driver,String rowNo,ExtentTest test,String strFilePath) throws Throwable {
		try {
			Thread.sleep(5000);
			driver.findElement(objPageObjsProRead.getLocator("objTools")).click();
			test.log(Status.INFO, "Clicked Tools in the Menu");
			Thread.sleep(1000);
			driver.findElement(objPageObjsProRead.getLocator("objFileUpload")).click();
			test.log(Status.INFO, "Clicked File Upload option in the Menu");
			Thread.sleep(1000);
			//driver.findElement(objPageObjsProRead.getLocator("objBrowse")).click();
			test.log(Status.INFO, "Clicked Browse Button");
			Thread.sleep(1000);

			// locate the drop area
			WebElement droparea = driver.findElement(By.xpath(".//*[@id='holder']"));

			Point point = droparea.getLocation();
			int xcord = point.getX();
			System.out.println("Position of the webelement from left side is "+xcord +" pixels");
			int ycord = point.getY();
			System.out.println("Position of the webelement from top side is "+ycord +" pixels");

			// drop the file
			DropFile(new File(strFilePath), droparea, 300, 3000);

			//VBScript code to do actions on the Upload File Windows Dialog box
			Runtime.getRuntime().exec("wscript src/test/resources/VBScript/upload.vbs " + strFilePath);
			Thread.sleep(10000);
			test.log(Status.INFO, "Entered the file to be uploaded and hit Enter");

			driver.findElement(objPageObjsProRead.getLocator("objUpload")).click();
			Thread.sleep(20000);
			test.log(Status.INFO, "Clicked Upload Button");

		} catch (IOException e) {
			System.out.println("In the catch block");
		}
	}

	public void verifyUploadSequenceCheck(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1,String strExpErrorMsg)
	{
		try {

			List <WebElement> fileNameList = driver.findElements(objPageObjsProRead.getLocator("objFileName")); 
			int i = 0;
			String strTDID = null,strTDPlus,strFileName,StrExpErrorMsg,strErrorMsgText,strErrorTextLocator;
			Thread.sleep(2000);
			for(WebElement  objTDEle:fileNameList)
			{
				Thread.sleep(2000);
				strTDPlus = "(.//i[@class='fa fa-plus-circle'])[1]"; 
				//strTDPlus =objPageObjsProRead.getLocator("objPlusIcon");
				strFileName = objTDEle.getText() ;							//System.out.println("objTDEle.getText() = " + objTDEle.getText());
				driver.findElement(By.xpath(strTDPlus)).click();
				strTDID = ".//div[@id='errorFileDataTxt" + (i) + "']";
				strErrorMsgText=driver.findElement(By.xpath(strTDID)).getText();
				String[] strFileNameSeperated = strFileName.split("\\.");
				String[] strFileSequence = strFileNameSeperated [0].split("\\_");
				if(Integer.parseInt(strFileSequence[6])<1 || Integer.parseInt(strFileSequence[6])>99)
				{
					StrExpErrorMsg ="The minimum value of a sequential number is 01, the maximum value is 99";
					if (strErrorMsgText.contains(StrExpErrorMsg))
					{ 
						System.out.println("Passed : File is rejected since the sequence number is not in between 1-99");
						test.pass("Passed : File is rejected since the sequence number is not in between 1-99",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorDisplayed", test,rowNoGbl, date1))
								.build());                    
					}
					else
					{
						System.out.println("Failed : Incorrect error message displayed");
						// Add the screenshot to the Reporting steps with a hyperlink
						test.fail("Failed : Incorrect error message displayed",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "Incorrect error message", test,rowNoGbl, date1))
								.build());                    

						// Add the screenshot to the Reporting steps with a hyperlink
						test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "Incorrect error message", test,rowNoGbl, date1));
					}
				}
				else
				{
					System.out.println("File is accepted");
					test.pass("Passed : File is accepted",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "ValidfileUploaded", test,rowNoGbl, date1))
							.build()); 
				}
				i++;	
			}
			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// calling flush writes everything to the Extent Report
			extent.flush();
		} 
		catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}


	public void verifyValidandInvalidFileIpload(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1,String NoOfValidFile, String NoofInvalidFile)
	{
		try {
			driver.findElement(objPageObjsProRead.getLocator("objShowAccepted")).click();
			List <WebElement> validFileNameList = driver.findElements(objPageObjsProRead.getLocator("objValidUpload"));
			int i = 0;
			int valiFileCount = driver.findElements(objPageObjsProRead.getLocator("objValidUpload")).size();

			if(valiFileCount==Integer.parseInt(NoOfValidFile)){
				System.out.println("Number of valid files uploaded and No number valid files accepted are same");
				System.out.println("Number of Green Ticks = " + valiFileCount);
				test.pass("Passed : All valid files uploaded successfully",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "Valid files upload successful", test,rowNoGbl, date1))
						.build());
			}
			else
			{
				System.out.println("Number of valid files uploaded and No number valid files accepted are NOT same");
				test.fail("Failed : Not all valid files uploaded are accepted",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "All Valid files upload NOT successful", test,rowNoGbl, date1))
						.build());  
			}

			driver.findElement(objPageObjsProRead.getLocator("objShowRejected")).click();
			List <WebElement> invalidFileNameList = driver.findElements(objPageObjsProRead.getLocator("objInvalidUpload"));
			int j = 0;
			int invalidFileCount = driver.findElements(objPageObjsProRead.getLocator("objInvalidUpload")).size();

			if(invalidFileCount==Integer.parseInt(NoOfValidFile)){
				System.out.println("Number of Invalid files uploaded and No number Invalid files Rejected are same");
				System.out.println("Number of Green Ticks = " + invalidFileCount);
				test.pass("Passed : All valid files uploaded successfully",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "Invalid files are Rejected successfully", test,rowNoGbl, date1))
						.build());
			}
			else 
			{
				System.out.println("Number of Invalid files uploaded and No number Invalid files Rejected are NOT same");
				test.fail("Failed : Not all valid files uploaded are accepted",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "All Invalid files are not Rejected", test,rowNoGbl, date1))
						.build());  
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// calling flush writes everything to the Extent Report
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}


	public void verifyFileComponentsLength(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1)
	{
		try {

			List <WebElement> fileNameList = driver.findElements(objPageObjsProRead.getLocator("objFileName")); 
			int i = 0, fileTypeLen=0, dataCollLen =0, reportingCycleLen=0, moduleLen=0,identifierLen=0,refDateLen=0, seqNumLen=0,serviceLen=0,entityTypeLen=0;
			String strTDID = null,strTDPlus,strFileName,StrExpErrorMsg,strErrorMsgText,strErrorTextLocator;
			Thread.sleep(2000);
			for(WebElement  objTDEle:fileNameList)
			{
				Thread.sleep(2000);
				strTDPlus = "(.//i[@class='fa fa-plus-circle'])[1]"; 
				strFileName = objTDEle.getText() ;							
				driver.findElement(By.xpath(strTDPlus)).click();				
				strTDID = ".//div[@id='errorFileDataTxt" + (i) + "']";
				//System.out.println("strTDID = " + strTDID);
				strErrorMsgText=driver.findElement(By.xpath(strTDID)).getText();
				String[] strFileNameSeperated = strFileName.split("\\.");
				String[] strFileComponent = strFileNameSeperated [0].split("\\_");
				fileTypeLen=strFileComponent[0].length();
				dataCollLen=strFileComponent[1].length();
				reportingCycleLen=strFileComponent[2].length();
				moduleLen=strFileComponent[3].length();
				identifierLen=strFileComponent[4].length();
				refDateLen=strFileComponent[5].length();
				seqNumLen=strFileComponent[6].length();
				serviceLen=strFileComponent[7].length();
				entityTypeLen=strFileComponent[8].length();
				//The maximum length of the following filename components was exceeded: 
				//[File Type, Data Collection, Reporting Cycle, Module, Identifier, Reference Date, Version Number]
				if(fileTypeLen > 2 || dataCollLen > 6 || reportingCycleLen > 6 || moduleLen > 15 || identifierLen > 20 || refDateLen > 6 || seqNumLen > 2)
					//|| serviceLen > 10 || entityTypeLen > 2)
				{
					StrExpErrorMsg ="The maximum length of the following filename components was exceeded";
					if (strErrorMsgText.contains(StrExpErrorMsg))
					{ 
						if(fileTypeLen > 2 && strErrorMsgText.contains("File Type"))
						{
							System.out.println("Passed : File was rejected since the maximum length of the Filetype exceeds the specified length");
							test.pass("Passed : File was rejected as expeted and error message verified",
									MediaEntityBuilder
									.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorDisplayed", test,rowNoGbl, date1))
									.build()); 
						}
						if(dataCollLen > 6 && strErrorMsgText.contains("Data Collection"))
						{
							System.out.println("Passed : File was rejected since the maximum length of the Data collection exceeds the specified length");
							test.pass("Passed : File was rejected as expeted and error message verified",
									MediaEntityBuilder
									.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorDisplayed", test,rowNoGbl, date1))
									.build()); 
						}
						if(reportingCycleLen > 6 && strErrorMsgText.contains("Reporting Cycle"))
						{
							System.out.println("Passed : File was rejected since the maximum length of the Reporting Cycle exceeds the specified length");
							test.pass("Passed : File was rejected as expeted and error message verified",
									MediaEntityBuilder
									.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorDisplayed", test,rowNoGbl, date1))
									.build()); 
						}
						if(moduleLen > 6 && strErrorMsgText.contains("Module"))
						{
							System.out.println("Passed : File was rejected since the maximum length of the Module exceeds the specified length");
							test.pass("Passed : File was rejected as expeted and error message verified",
									MediaEntityBuilder
									.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorDisplayed", test,rowNoGbl, date1))
									.build()); 
						}
						if(identifierLen > 6 && strErrorMsgText.contains("Identifier"))
						{
							System.out.println("Passed : File was rejected since the maximum length of the Identifier exceeds the specified length");
							test.pass("Passed : File was rejected as expeted and error message verified",
									MediaEntityBuilder
									.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorDisplayed", test,rowNoGbl, date1))
									.build()); 
						}
						if(refDateLen > 6 && strErrorMsgText.contains("Reference Date"))
						{
							System.out.println("Passed : File was rejected since the maximum length of the Reference Date exceeds the specified length");
							test.pass("Passed : File was rejected as expeted and error message verified",
									MediaEntityBuilder
									.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorDisplayed", test,rowNoGbl, date1))
									.build()); 
						}
						if(seqNumLen > 6 && strErrorMsgText.contains("Version Number"))
						{
							System.out.println("Passed : File was rejected since the maximum length of the Version Number exceeds the specified length");
							test.pass("Passed : File was rejected as expeted and error message verified",
									MediaEntityBuilder
									.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorDisplayed", test,rowNoGbl, date1))
									.build()); 
						}
					}
					else
					{
						System.out.println("Failed : Incorrect error message displayed");
						// Add the screenshot to the Reporting steps with a hyperlink
						test.fail("Failed : Incorrect error message displayed",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorNotDisplayed", test,rowNoGbl, date1))
								.build());                    

						// Add the screenshot to the Reporting steps with a hyperlink
						test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoErrorMessage", test,rowNoGbl, date1));
					}
				}
				else
				{
					System.out.println("File is accepted since the filename components length is within the range specified");
					test.pass("Passed : File is accepted since no issue with filename component length",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorDisplayed", test,rowNoGbl, date1))
							.build()); 
				}
				i++;	
			}
			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// calling flush writes everything to the Extent Report
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyMandatoryFileNameComponentsSameSubmission(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1)
	{
		try {
			driver.findElement(objPageObjsProRead.getLocator("objShowRejected")).click();
			List <WebElement> invalidFileNameList = driver.findElements(objPageObjsProRead.getLocator("objInvalidUpload"));
			int i = 0;
			String strTDID = null,strTDPlus,strFileName,StrExpErrorMsg,strErrorMsgText,strErrorTextLocator;
			int invalidFileCount = driver.findElements(objPageObjsProRead.getLocator("objInvalidUpload")).size();
			for(WebElement  objTDEle:invalidFileNameList)
			{
				strTDPlus = "(.//i[@class='fa fa-plus-circle'])[1]"; 
				strFileName = objTDEle.getText() ;							
				driver.findElement(By.xpath(strTDPlus)).click();				
				strTDID = ".//div[@id='errorFileDataTxt" + (i) + "']";
				strErrorMsgText=driver.findElement(By.xpath(strTDID)).getText();
				StrExpErrorMsg ="For resubmissions the sequential number needs to be higher than the sequential number used in previous versions. The sequence number you have specified has already been used";
				if(strErrorMsgText.contains(StrExpErrorMsg))
				{
					System.out.println("A file with same mandatory filename components has been submitted, Correct error message is displayed");
					test.pass("A file with same mandatory filename components has been submitted. Error message verified.",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "InvalidFileUpload. ErrorVerified", test,rowNoGbl, date1))
							.build());
				}
				else 
				{
					System.out.println("File is rejected But no correct expected error message");
					test.fail("File is rejected But no correct expected error message",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "InvalidFileUpload. IncorrectError", test,rowNoGbl, date1))
							.build());  
				}

			}
			driver.findElement(objPageObjsProRead.getLocator("objShowAccepted")).click();
			List <WebElement> validFileNameList = driver.findElements(objPageObjsProRead.getLocator("objValidUpload"));
			int validFileCount = driver.findElements(objPageObjsProRead.getLocator("objValidUpload")).size();
			for(WebElement  objTDEle:validFileNameList)
			{
				System.out.println("A file with different mandatory filename components has been submitted, File Submission successful");
				test.pass("A file with same mandatory filename components has been submitted. Error message verified.",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "Valid File Upload", test,rowNoGbl, date1))
						.build());

			}
			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// calling flush writes everything to the Extent Report
			extent.flush();

			i++;	

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// calling flush writes everything to the Extent Report
			extent.flush();

		}
		catch (Exception e) 
		{
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}

	public void verifyUploadPassMultipleFile(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1, int NoOfFiles)
	{
		try {

			Thread.sleep(2000);
			List <WebElement> fileNameList = driver.findElements(objPageObjsProRead.getLocator("objMultipleUpload"));
			int i = 0;
			int fileCount = driver.findElements(objPageObjsProRead.getLocator("objMultipleUpload")).size();

			if(fileCount==NoOfFiles){
				System.out.println("count is matching");
				System.out.println("Number of Green Ticks = " + fileCount);
				test.pass("Passed : Multiple files uploaded successfully",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "Multiple Files Upload successful", test,rowNoGbl, date1))
						.build());
			}
			else
			{
				System.out.println("count is not matching");
				test.fail("Failed : Incorrect error message displayed",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "Incorrect error message", test,rowNoGbl, date1))
						.build());  
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// calling flush writes everything to the Extent Report
			extent.flush();
		} catch (Exception e) {
			System.out.println("In catch block");
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		}
	}           

	public void verifyReportingCycleStatusAsStarted(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1,DBUtil objDBUtil)
	{
		try {

			Thread.sleep(2000);
			List <WebElement> fileNameList = driver.findElements(By.xpath(".//td[@class='tableFileNameTxt']")); 
			int i = 0;
			String strTDID = null,strTDPlus,strFileName,StrExpErrorMsg,strErrorMsgText,ReportingCycleStatus;
			Thread.sleep(2000);
			for(WebElement  objTDEle:fileNameList)
			{
				Thread.sleep(2000);
				//strTDID = "errorFileDataTxt"+i;                       
				strTDPlus = "(.//i[@class='fa fa-plus-circle'])[1]";    //System.out.println("strTDPlus = " + strTDPlus);
				strFileName = objTDEle.getText() ;                                      //System.out.println("objTDEle.getText() = " + objTDEle.getText());
				//driver.findElement(By.xpath(strTDPlus)).click();
				//strErrorMsgText =driver.findElement(By.id(strTDID)).getText();
				String[] strFileNameSeperated = strFileName.split("\\.");
				String[] strFileComponents = strFileNameSeperated[0].split("\\_");

				ReportingCycleStatus = objDBUtil.verifyReportingCycleInDB(strFileComponents[2]);
				System.out.println("ReportingCycleStatus = " + ReportingCycleStatus);
				if(ReportingCycleStatus.equals("Started"))
				{
					if(strFileComponents[0].equals("C") || strFileComponents[0].equals("D") ||strFileComponents[0].equals("A") ||
							strFileComponents[0].equals("TC") ||strFileComponents[0].equals("TA") ||strFileComponents[0].equals("TD") )
					{
						System.out.println("Passed : File is accepted since the Reporting Cycle is in Started");
						test.pass("Passed : File is Accepted ",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorDisplayed", test,rowNoGbl, date1))
								.build());
					}
					else
					{
						System.out.println("Failed : FileType is incorrect and therefore file is Rejected");
						// Add the screenshot to the Reporting steps with a hyperlink
						test.fail("Failed : Incorrect error message displayed",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "Incorrect error message", test,rowNoGbl, date1))
								.build());                    

						// Add the screenshot to the Reporting steps with a hyperlink
						test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "Incorrect error message", test,rowNoGbl, date1));
					}
				}


				i++;  
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();                    
		}
		catch (Exception e) {
			e.printStackTrace();
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

			fail("NoSuchElementException");
		} 


	}


	public void verifyReportingCycleStatusAsClosed(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1,DBUtil objDBUtil)
	{
		try {

			Thread.sleep(2000);
			List <WebElement> fileNameList = driver.findElements(By.xpath(".//td[@class='tableFileNameTxt']")); 
			int i = 0;
			String strTDID = null,strTDPlus,strFileName,StrExpErrorMsg,strErrorMsgText,ReportingCycleStatus;
			Thread.sleep(2000);
			for(WebElement  objTDEle:fileNameList)
			{
				Thread.sleep(2000);
				strTDID = "errorFileDataTxt"+i;                       
				strTDPlus = "(.//i[@class='fa fa-plus-circle'])[1]";    //System.out.println("strTDPlus = " + strTDPlus);
				strFileName = objTDEle.getText() ;                                      //System.out.println("objTDEle.getText() = " + objTDEle.getText());
				driver.findElement(By.xpath(strTDPlus)).click();
				strErrorMsgText =driver.findElement(By.id(strTDID)).getText();
				String[] strFileNameSeperated = strFileName.split("\\.");
				String[] strFileComponents = strFileNameSeperated[0].split("\\_");
				StrExpErrorMsg ="The referenced reporting cycle is in status Closed. A file submission is not allowed and the file is therefore rejected";
				ReportingCycleStatus = objDBUtil.verifyReportingCycleInDB1(strFileComponents[2]);
				System.out.println("ReportingCycleStatus = " + ReportingCycleStatus);
				if(ReportingCycleStatus.equals("Closed") && strErrorMsgText.contains(StrExpErrorMsg))
				{
					if(strFileComponents[0].equals("TC") ||strFileComponents[0].equals("TA") ||strFileComponents[0].equals("TD") )
					{
						System.out.println("Passed : File is Rejected since the Reporting Cycle is Closed");
						test.pass("Passed : File is Rejected ",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorDisplayed", test,rowNoGbl, date1))
								.build());
					}
					else
					{
						System.out.println("File is valid and is accepted");
						// Add the screenshot to the Reporting steps with a hyperlink
						test.fail("Failed : Incorrect error message displayed",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "Incorrect error message", test,rowNoGbl, date1))
								.build());                    

						// Add the screenshot to the Reporting steps with a hyperlink
						test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "Incorrect error message", test,rowNoGbl, date1));
					}
				}
			}



			i++;  
			//}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();                    
		}
		catch (Exception e) {
			e.printStackTrace();
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	public void verifyReportingCycleStatusAsInPrep(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1,DBUtil objDBUtil)

	{
		try {

			Thread.sleep(2000);
			List <WebElement> fileNameList = driver.findElements(By.xpath(".//td[@class='tableFileNameTxt']")); 
			int i = 0;
			String strTDID = null,strTDPlus,strFileName,StrExpErrorMsg,strErrorMsgText,ReportingCycleStatus;
			Thread.sleep(2000);
			for(WebElement  objTDEle:fileNameList)
			{
				Thread.sleep(2000);
				//strTDID = "errorFileDataTxt"+i;                       
				strTDPlus = "(.//i[@class='fa fa-plus-circle'])[1]";    //System.out.println("strTDPlus = " + strTDPlus);
				strFileName = objTDEle.getText() ;                                      //System.out.println("objTDEle.getText() = " + objTDEle.getText());
				//driver.findElement(By.xpath(strTDPlus)).click();
				//strErrorMsgText =driver.findElement(By.id(strTDID)).getText();
				String[] strFileNameSeperated = strFileName.split("\\.");
				String[] strFileComponents = strFileNameSeperated[0].split("\\_");

				ReportingCycleStatus = objDBUtil.verifyReportingCycleInDB2(strFileComponents[2]);
				System.out.println("ReportingCycleStatus = " + ReportingCycleStatus);
				if(ReportingCycleStatus.equals("In Preparation"))
				{
					if(strFileComponents[0].equals("TC") ||strFileComponents[0].equals("TA") ||strFileComponents[0].equals("TD") )
					{
						System.out.println("Passed : Test File is accepted since the Reporting Cycle is in Preparation");
						test.pass("Passed : File is Accepted ",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "ValidFileUpload", test,rowNoGbl, date1))
								.build());
					}
					else
					{
						System.out.println("Failed : FileType is incorrect and therefore file is Rejected");
						// Add the screenshot to the Reporting steps with a hyperlink
						test.fail("Failed : Incorrect error message displayed",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "Incorrect error message", test,rowNoGbl, date1))
								.build());                    

						// Add the screenshot to the Reporting steps with a hyperlink
						test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "Incorrect error message", test,rowNoGbl, date1));
					}
				}
			}




			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

		} 
		catch (Exception e) {
			e.printStackTrace();
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}              

	}


	public void verifyFileTypeAndReportingCycle(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1,DBUtil objDBUtil)

	{
		try {

			Thread.sleep(2000);
			List <WebElement> fileNameList = driver.findElements(By.xpath(".//td[@class='tableFileNameTxt']")); 
			int i = 0;
			String strTDID = null,strTDPlus,strFileName,StrExpErrorMsg,strErrorMsgText,ReportingCycleStatus;
			Thread.sleep(2000);
			for(WebElement  objTDEle:fileNameList)
			{
				Thread.sleep(2000);
				//strTDID = "errorFileDataTxt"+i;                       
				strTDPlus = "(.//i[@class='fa fa-plus-circle'])[1]";    //System.out.println("strTDPlus = " + strTDPlus);
				strFileName = objTDEle.getText() ;                                      //System.out.println("objTDEle.getText() = " + objTDEle.getText());
				//driver.findElement(By.xpath(strTDPlus)).click();
				//strErrorMsgText =driver.findElement(By.id(strTDID)).getText();
				String[] strFileNameSeperated = strFileName.split("\\.");
				String[] strFileComponents = strFileNameSeperated[0].split("\\_");

				ReportingCycleStatus = objDBUtil.verifyReportingCycleInDB(strFileComponents[2]);
				System.out.println("ReportingCycleStatus = " + ReportingCycleStatus);
				if(ReportingCycleStatus.equals("Started"))   
				{
					if(strFileComponents[0].equals("C") ||strFileComponents[0].equals("D") ||strFileComponents[0].equals("A") )
					{
						System.out.println("Passed : Test File is accepted since the Reporting Cycle is Started");
						test.pass("Passed : File is Accepted ",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "Valid file Upload", test,rowNoGbl, date1))
								.build());
					}
					else
					{
						System.out.println("Failed : FileType is incorrect and therefore file is Rejected");
						// Add the screenshot to the Reporting steps with a hyperlink
						test.fail("Failed : FileType is incorrect and therefore file is Rejected",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "FileType is incorrect", test,rowNoGbl, date1))
								.build());                    

						// Add the screenshot to the Reporting steps with a hyperlink
						test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "FileType is incorrect", test,rowNoGbl, date1));
					}
				}
			}


			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

		} 
		catch (Exception e) {
			e.printStackTrace();
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}              

	}

	public void verifyFileTypeAndReportingCycleAsInPrep(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1,DBUtil objDBUtil)

	{
		try {

			Thread.sleep(2000);
			List <WebElement> fileNameList = driver.findElements(By.xpath(".//td[@class='tableFileNameTxt']")); 
			int i = 0;
			String strTDID = null,strTDPlus,strFileName,StrExpErrorMsg,strErrorMsgText,ReportingCycleStatus;
			Thread.sleep(2000);
			for(WebElement  objTDEle:fileNameList)
			{
				Thread.sleep(2000);
				//strTDID = "errorFileDataTxt"+i;                       
				strTDPlus = "(.//i[@class='fa fa-plus-circle'])[1]";    //System.out.println("strTDPlus = " + strTDPlus);
				strFileName = objTDEle.getText() ;                                      //System.out.println("objTDEle.getText() = " + objTDEle.getText());
				//driver.findElement(By.xpath(strTDPlus)).click();
				//strErrorMsgText =driver.findElement(By.id(strTDID)).getText();
				String[] strFileNameSeperated = strFileName.split("\\.");
				String[] strFileComponents = strFileNameSeperated[0].split("\\_");

				ReportingCycleStatus = objDBUtil.verifyReportingCycleInDB2(strFileComponents[2]);
				System.out.println("ReportingCycleStatus = " + ReportingCycleStatus);
				if(ReportingCycleStatus.equals("In Preparation"))
				{
					if(strFileComponents[0].equals("TC") ||strFileComponents[0].equals("TA") ||strFileComponents[0].equals("TD") )
					{
						System.out.println("Passed : Test File is accepted since the Reporting Cycle is in Preparation");
						test.pass("Passed : File is Accepted ",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "Valid file Uploaded", test,rowNoGbl, date1))
								.build());
					}
					else
					{
						System.out.println("Failed : FileType is incorrect and therefore file is Rejected");
						// Add the screenshot to the Reporting steps with a hyperlink
						test.fail("Failed : FileType is incorrect",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "FileType is incorrect", test,rowNoGbl, date1))
								.build());                    

						// Add the screenshot to the Reporting steps with a hyperlink
						test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "FileType is incorrect", test,rowNoGbl, date1));
					}
				}
			}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

		} 
		catch (Exception e) {
			e.printStackTrace();
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}              

	}              

	public void verifyReportingCycleStatusClosed(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl, String date1,DBUtil objDBUtil)
	{
		try {

			Thread.sleep(2000);
			List <WebElement> fileNameList = driver.findElements(By.xpath(".//td[@class='tableFileNameTxt']")); 
			int i = 0;
			String strTDID = null,strTDPlus,strFileName,StrExpErrorMsg,strErrorMsgText,ReportingCycleStatus;
			Thread.sleep(2000);
			for(WebElement  objTDEle:fileNameList)
			{
				Thread.sleep(2000);
				strTDID = "errorFileDataTxt"+i;                       
				strTDPlus = "(.//i[@class='fa fa-plus-circle'])[1]";    //System.out.println("strTDPlus = " + strTDPlus);
				strFileName = objTDEle.getText() ;                                      //System.out.println("objTDEle.getText() = " + objTDEle.getText());
				driver.findElement(By.xpath(strTDPlus)).click();
				strErrorMsgText =driver.findElement(By.id(strTDID)).getText();
				String[] strFileNameSeperated = strFileName.split("\\.");
				String[] strFileComponents = strFileNameSeperated[0].split("\\_");
				StrExpErrorMsg ="The referenced reporting cycle is in status Closed. A file submission is not allowed and the file is therefore rejected";
				ReportingCycleStatus = objDBUtil.verifyReportingCycleInDB1(strFileComponents[2]);
				System.out.println("ReportingCycleStatus = " + ReportingCycleStatus);
				if(ReportingCycleStatus.equals("Closed") && strErrorMsgText.contains(StrExpErrorMsg))
				{
					if(strFileComponents[0].equals("TC") ||strFileComponents[0].equals("TA") ||strFileComponents[0].equals("TD") )
					{
						System.out.println("Passed : File is Rejected since the Reporting Cycle is Closed");
						test.pass("Passed : File is Rejected ",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorDisplayed", test,rowNoGbl, date1))
								.build());
					}
					else
					{
						System.out.println("File is accepted");
						// Add the screenshot to the Reporting steps with a hyperlink
						test.fail("File is accepted",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "Valid file upload", test,rowNoGbl, date1))
								.build());                    

						// Add the screenshot to the Reporting steps with a hyperlink
						test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "Valid file upload", test,rowNoGbl, date1));
					}
				}
			}
			i++;  
			//}

			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();                    
		}
		catch (Exception e) {
			e.printStackTrace();
			// Add the screenshot to the Reporting steps with a hyperlink
			test.fail("NoSuchElementException : " + e.getMessage());
			try {
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test,rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public static void DropFile(File filePath, WebElement target, int offsetX, int offsetY) {
		if(!filePath.exists())
			throw new WebDriverException("File not found: " + filePath.toString());

		WebDriver driver = ((RemoteWebElement)target).getWrappedDriver();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, 30);

		String JS_DROP_FILE =
				"var target = arguments[0]," +
						"    offsetX = arguments[1]," +
						"    offsetY = arguments[2]," +
						"    document = target.ownerDocument || document," +
						"    window = document.defaultView || window;" +
						"" +
						"var input = document.createElement('INPUT');" +
						"input.type = 'file';" +
						"input.style.display = 'none';" +
						"input.onchange = function () {" +
						"  var rect = target.getBoundingClientRect()," +
						"      x = rect.left + (offsetX || (rect.width >> 1))," +
						"      y = rect.top + (offsetY || (rect.height >> 1))," +
						"      dataTransfer = { files: this.files };" +
						"" +
						"  ['dragenter', 'dragover', 'drop'].forEach(function (name) {" +
						"    var evt = document.createEvent('MouseEvent');" +
						"    evt.initMouseEvent(name, !0, !0, window, 0, 0, 0, x, y, !1, !1, !1, !1, 0, null);" +
						"    evt.dataTransfer = dataTransfer;" +
						"    target.dispatchEvent(evt);" +
						"  });" +
						"" +
						"  setTimeout(function () { document.body.removeChild(input); }, 25);" +
						"};" +
						"document.body.appendChild(input);" +
						"return input;";

		WebElement input =  (WebElement)jse.executeScript(JS_DROP_FILE, target, offsetX, offsetY);
		input.sendKeys(filePath.getAbsoluteFile().toString());
		wait.until(ExpectedConditions.stalenessOf(input));
	}

	private static final java.text.SimpleDateFormat sdf = 
			new java.text.SimpleDateFormat("yyyyMMdd");

	public static boolean verifyInput(String strRefDate) 
	{
		if (strRefDate != null) {
			try {
				java.util.Date ret = sdf.parse(strRefDate.trim());

			} catch (ParseException e) {
				//e.printStackTrace();
				return false;		 
			}
		}
		return true;
	}

	public static boolean verifyWebElementAndClick(WebDriver driver,String strXPath) 
	{
		try
		{
			if(driver.findElement(By.xpath(strXPath)).isDisplayed())
			{
				driver.findElement(By.xpath(strXPath)).click();
			}
		}catch(Exception e)
		{
			//e.printStackTrace();
		}
		return true;
	}
	
	public void UploadZipFile(WebDriver driver,ExtentTest test,String strFilePath,ExtentReports extent,String rowNoGbl)throws Throwable {
		
		//Click on Browse button
		//driver.findElement(objPageObjsProRead.getLocator("")).Click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		test.log(Status.INFO, "Clicked on browse button");
		Runtime.getRuntime().exec("Wscript src/test/resources/VBScript.Upload.vbs" + strFilePath);
		test.log(Status.INFO, "Selected file to be uploaded");
		Thread.sleep(1000);
		//Click on upload button
		Actions action = new Actions(driver);
		//action.d
		//driver.findElement(objPageObjsProRead.getLocator("UploadButton")).Click();
		Thread.sleep(1000);
	}	
}
