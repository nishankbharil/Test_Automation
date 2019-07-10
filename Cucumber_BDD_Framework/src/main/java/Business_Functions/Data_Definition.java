package Business_Functions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//test
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Utility.DBUtil;
import Utility.ExcelUtils;
import Utility.PropertyReader;
import Utility.Screenshots;
import junit.framework.TestCase;

public class Data_Definition extends TestCase
{
	PropertyReader objPageObjsProRead = new PropertyReader("src/test/java/Page_Objects/Common_Page_Objects.properties");
	PropertyReader objPageRead = new PropertyReader("src/test/java/Page_Objects/Data_Definition.properties");
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	Screenshots objCreateScreenshot = new Screenshots();
	Common_Business_Functions objCBF = new Common_Business_Functions();
	private String rowNo;
	private String sheet;


	public void EntityGroup(WebDriver driver,ExtentTest test,ExtentReports extent) throws Throwable {
		try {

			Thread.sleep(1000);
			driver.findElement(objPageRead.getLocator("objDefinitionTab")).click();
			test.log(Status.INFO, "Clicked on Definition Tab");
			Thread.sleep(1000);
			driver.findElement(objPageRead.getLocator("objPopulationTab")).click();
			test.log(Status.INFO, "Clicked Population Tab");
			Thread.sleep(1000);
			driver.findElement(objPageRead.getLocator("objEntityGroups")).click();
			test.log(Status.INFO, "Clicked on Entity Groups");
			Thread.sleep(1000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ReportingCycle(WebDriver driver, ExtentTest test, ExtentReports extent) throws Throwable 
	{
		try {

			driver.findElement(objPageRead.getLocator("objDefinitionTab")).click();
			test.log(Status.INFO, "Clicked Definition in the Menu");
			Thread.sleep(1000);
			driver.findElement(objPageRead.getLocator("objObligationsTab")).click();
			test.log(Status.INFO, "Clicked Obligation in the Left Pane");
			Thread.sleep(1000);
			driver.findElement(objPageRead.getLocator("objCycles")).click();
			test.log(Status.INFO, "Clicked Cycles from the Sub Menu");
			Thread.sleep(1000);

		} catch (IOException e) {
			System.out.println("In the catch block");
		}
	}

	public void verifyReportingCycleAdd(WebDriver driver, ExtentTest test, String strFilePath,String ReportingCycleName,String StartDate,String EndDate, String Description,ExtentReports extent, String rowNoGbl, String date1, String strExpMsg) 
	{
		try {

			driver.findElement(objPageRead.getLocator("ObjAdd")).click();
			Thread.sleep(2000);
			driver.findElement(objPageRead.getLocator("objReportingCycleName")).sendKeys(ReportingCycleName);
			driver.findElement(objPageRead.getLocator("objStartDate")).sendKeys(StartDate);
			driver.findElement(objPageRead.getLocator("objEndDate")).sendKeys(EndDate);
			driver.findElement(objPageRead.getLocator("objDescription")).sendKeys(Description);
			driver.findElement(objPageRead.getLocator("objSave")).click();
			Thread.sleep(2000);
			String strActMsg = driver.findElement(objPageRead.getLocator("objSuccessMessage")).getText();

			if (strActMsg.equals(strExpMsg)) {
				// Add the screenshot to the Reporting steps with a hyperlink
				test.pass("Reporting Cycle Added Successfully",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"RC Added Successfully", test, rowNoGbl, date1)).build());
			} 
			else 
			{
				// Add the screenshot to the Reporting steps with a hyperlink
				test.fail("Reporting Cycle Is Not Added Successfully",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"RC Not Added", test, rowNoGbl, date1)).build());

				// Add the screenshot to the Reporting steps with a hyperlink
				test.addScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "NoErrorMessage", test, rowNoGbl, date1));

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
				test.addScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "NoSuchElementException", test, rowNoGbl, date1));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}	
		}

	}
	public void verifyReportingCycleDuplicate(WebDriver driver, ExtentTest test, String strFilePath,DBUtil objDBUtil,String ReportingCycleName,String StartDate,String EndDate, String Description,ExtentReports extent, String rowNoGbl, String date1, String strExpMsg) 
	{
		boolean reportingCycleNameFound;
		try {

			driver.findElement(objPageRead.getLocator("ObjAdd")).click();
			Thread.sleep(2000);
			driver.findElement(objPageRead.getLocator("objReportingCycleName")).sendKeys(ReportingCycleName);
			driver.findElement(objPageRead.getLocator("objStartDate")).sendKeys(StartDate);
			driver.findElement(objPageRead.getLocator("objEndDate")).sendKeys(EndDate);
			driver.findElement(objPageRead.getLocator("objDescription")).sendKeys(Description);
			driver.findElement(objPageRead.getLocator("objSave")).click();
			Thread.sleep(2000);
			String strActMsg = driver.findElement(objPageRead.getLocator("objDuplicateMessage")).getText();
			//String reportingCycleName = driver.findElement(objPageRead.getLocator("objReportingCycleName")).getText();
			if (strActMsg.equals(strExpMsg)) {
				// Add the screenshot to the Reporting steps with a hyperlink
				reportingCycleNameFound = objDBUtil.verifyTableColumnValueInDB("Metacasper.TBL_CSPR_REPORTINGCYCLE","NAME",ReportingCycleName);
				if(reportingCycleNameFound){
					test.pass("Reporting Cycle Name for this Data Collection is already existing",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"RC is Not Updated", test, rowNoGbl, date1)).build());
				}
				else
				{
					test.fail("Reporting Cycle Name for this Data Collection is already existing",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"RC Updated Successfully", test, rowNoGbl, date1)).build());
				}

			} 
			else 
			{
				// Add the screenshot to the Reporting steps with a hyperlink
				test.fail("Reporting Cycle Is Not Updated",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"RC Not Updated", test, rowNoGbl, date1)).build());

				// Add the screenshot to the Reporting steps with a hyperlink
				test.addScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "Reporting Cycle Name is already existing for this DC", test, rowNoGbl, date1));
			}
			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

		}

		catch (Exception e) {
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
		}
	}
	public void verifyReportingCycleEdit(WebDriver driver, ExtentTest test, String strFilePath,
			DBUtil objDBUtil,String ReportingCycleName,String StartDate,String EndDate, String Description,ExtentReports extent, String rowNoGbl, String date1, String strExpMsg) 
	{

		try {
			
			String FirstRowName=null, FirstRowDesc=null,FirstRowStatus=null,FirstRowStartDate=null,FirstRowEndDate=null;
			Actions action = new Actions(driver);
			driver.findElement(objPageRead.getLocator("objEditRCEditFirstRowName")).click();

			WebElement objRightClick = driver.findElement(objPageRead.getLocator("objEditRCEditFirstRowName"));
			action.contextClick(objRightClick).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
			WebElement editOpen = driver.findElement(objPageRead.getLocator("objRCEdit")); /*This will select menu after right click */
			editOpen.click();
			Thread.sleep(2000);

			boolean reportingCycleNameFound;
			Thread.sleep(2000);
			driver.findElement(objPageRead.getLocator("objEditStartDate")).sendKeys(StartDate);
			driver.findElement(objPageRead.getLocator("objEditEndDate")).sendKeys(EndDate);
			driver.findElement(objPageRead.getLocator("objEditDescription")).sendKeys(Description);
			driver.findElement(objPageRead.getLocator("objSave")).click();
			Thread.sleep(2000);
			driver.findElement(objPageRead.getLocator("objEditRCEditFirstRowName")).click();
			FirstRowName=driver.findElement(objPageRead.getLocator("objEditRCEditFirstRowName")).getText();
			FirstRowDesc=driver.findElement(objPageRead.getLocator("objEditRCEditFirstRowDesc")).getText();
			FirstRowStatus=driver.findElement(objPageRead.getLocator("objEditRCEditFirstRowStatus")).getText();
			FirstRowStartDate=driver.findElement(objPageRead.getLocator("objEditRCEditFirstRowStartDate")).getText();
			FirstRowEndDate=driver.findElement(objPageRead.getLocator("objEditRCEditFirstRowEndDate")).getText();

			Thread.sleep(2000);
			String strActMsg = driver.findElement(objPageRead.getLocator("objEditMessage")).getText();


			if (strActMsg.equals(strExpMsg)) {
				// Add the screenshot to the Reporting steps with a hyperlink

				//	String DBEntityGroup = objDBUtil.verifyColumnValueNotDeleted("Metacasper.TBL_CSPR_ENTITYGROUP","NAME","DESCRIPTION",EntityGroupName.toUpperCase(),EntityGroupDesc.toUpperCase(),"ISDELETE","N");

				reportingCycleNameFound = objDBUtil.verifyRCValuesInDB("Metacasper.TBL_CSPR_REPORTINGCYCLE","NAME","DESCRIPTION","STATUS","STARTDATE","ENDDATE",FirstRowName,FirstRowDesc,FirstRowStatus,FirstRowStartDate,FirstRowEndDate);
				if(reportingCycleNameFound){
					test.pass("Reporting Cycle Updated Successfully",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"RC Updated Successfully", test, rowNoGbl, date1)).build());

				}else{
					test.fail("Reporting Cycle Name Not Updated",
							MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
									"RC Not Updated", test, rowNoGbl, date1)).build());
				}
			} 
			else 
			{
				// Add the screenshot to the Reporting steps with a hyperlink
				test.fail("Reporting Cycle Is Not Added",
						MediaEntityBuilder.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver,
								"RC Not Updated", test, rowNoGbl, date1)).build());

				// Add the screenshot to the Reporting steps with a hyperlink
				test.addScreenCaptureFromPath(
						objCreateScreenshot.createScreenshot(driver, "NoErrorMessage", test, rowNoGbl, date1));


			}
			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// Calling flush writes everything to the Extent Report
			extent.flush();

		}

		catch (Exception e) {
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
		}

	}

	public void addEntityGroup(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1, String strEntityGrpName, String strEntityGrpDesc,String strExpMessage, DBUtil objDBUtil)
	{
		try {
			Thread.sleep(1000);
			//Generate random number and add to the name and description
			//int iteration = objCBF.generateRandomNumber();
			//strEntityGrpName = strEntityGrpName + iteration;
			strEntityGrpName = "EGName15";
			System.out.println("Generated EntityGroup Name: "+strEntityGrpName);
		//	strEntityGrpDesc = strEntityGrpDesc + iteration;
			System.out.println("Generated EntityGroup Name: "+strEntityGrpDesc);
			
			driver.findElement(objPageRead.getLocator("objEntityGroupAdd")).click();
			WebElement entityGrpName = driver.findElement(objPageRead.getLocator("objAddEntityGroupName"));
			WebElement entityGrpDesc = driver.findElement(objPageRead.getLocator("objAddEntityGroupDesc"));
			
			if (entityGrpName.isDisplayed()) {
				
				entityGrpName.click();
				entityGrpDesc.click();
				Thread.sleep(1000);
				if (driver.findElement(objPageRead.getLocator("objMandatoryFieldError")).isDisplayed()) {
					System.out.println("mandatory!!");
					test.pass("Fields are mandatory. Verified successfully",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyMessageDisplayed", test,rowNoGbl, date1))
							.build());
				}
				else {
					System.out.println("Not mandatory, verification failed!");
					test.fail("Fields are not mandatory, verification failed",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyMandatoryField", test,rowNoGbl, date1))
							.build());
				}
				if (driver.findElement(objPageRead.getLocator("objAddEntityGroupSave")).isEnabled()) {
					System.out.println("Mandatory field verificaton failed");
					test.fail("Entity Group fields are not mandatory",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyMandatoryField", test,rowNoGbl, date1))
							.build());
				}
				else {
					test.log(Status.INFO, "Mandatory fields verified");
				}
					
				entityGrpName.sendKeys(strEntityGrpName);
				test.log(Status.INFO, "Entered Entity Group Name");
			}
			else {
				test.fail("Entity Group Name field is not displayed",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyFieldDisplayed", test,rowNoGbl, date1))
						.build());	
			}
			
			if (entityGrpDesc.isDisplayed()) {
				entityGrpDesc.sendKeys(strEntityGrpDesc);
				test.log(Status.INFO, "Entered Entity Group Description");
			}
			else {
				test.fail("Entity Group Name field is not displayed",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyFieldDisplayed", test,rowNoGbl, date1))
						.build());	
			}
			
			driver.findElement(objPageRead.getLocator("objAddEntityGroupSave")).click();
			System.out.println("clicked on save");
			Thread.sleep(2000);
			
			String FirstRowName=null, FirstRowDesc=null;
			Thread.sleep(3000);
			//boolean result = driver.findElement(objPageRead.getLocator("objAddEntityGroupSuccessIcon")).isDisplayed();
			try {
				if(driver.findElement(objPageRead.getLocator("objAddEntityGroupSuccessIcon")).isDisplayed())
				{
					String strActErrorMsg = driver.findElement(objPageRead.getLocator("objAddEntityGroupSuccessMsg")).getText();
					if (strActErrorMsg.equalsIgnoreCase(strExpMessage)) {
						System.out.println("Verified success message");
						// Add the screenshot to the Reporting steps with a hyperlink
						test.pass("New Entity group Added Successfully and Message Verified",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyMessageDisplayed", test,rowNoGbl, date1))
								.build());	
						//Search for newly added group
						driver.findElement(objPageRead.getLocator("objSearchDC")).sendKeys(strEntityGrpName);
						Thread.sleep(2000);
						System.out.println("Search new added grp");
						driver.findElement(objPageRead.getLocator("objAddEntityGroupAddedFirstRowName")).click();
						FirstRowName=driver.findElement(objPageRead.getLocator("objAddEntityGroupAddedFirstRowName")).getText();
						System.out.println(FirstRowName);
						FirstRowDesc=driver.findElement(objPageRead.getLocator("objAddEntityGroupAddedFirstRowDesc")).getText();
						
						if (FirstRowName.equalsIgnoreCase(strEntityGrpName)&& FirstRowDesc.equalsIgnoreCase(strEntityGrpDesc))
						{	// Add the screenshot to the Reporting steps with a hyperlink
							System.out.println("verified new added record");
							test.pass("Entity group added is displayed in the list",
									MediaEntityBuilder
									.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyTopRow", test,rowNoGbl, date1))
									.build());		
						}

						else 
						{				
							// Add the screenshot to the Reporting steps with a hyperlink
							test.fail("Entity group added is NOT displayed in the list",
									MediaEntityBuilder
									.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyTopRow", test,rowNoGbl, date1))
									.build());				

							// Add the screenshot to the Reporting steps with a hyperlink
							test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyTopRow", test,rowNoGbl, date1));

						}
					
					String DBEntityGroup = objDBUtil.verifyColumnValueNotDeleted("Metacasper.TBL_CSPR_ENTITYGROUP","NAME","DESCRIPTION",strEntityGrpName,strEntityGrpDesc,"ISDELETE","N");
					System.out.println("inside DB block  --"+DBEntityGroup);
					test.log(Status.INFO, "Database query result: "+DBEntityGroup);
					if(Integer.parseInt(DBEntityGroup)==1)
					{
						
						test.pass("Entity Group and Entity Description stored in Database",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyDatabase", test,rowNoGbl, date1))
								.build());
					}	
					else 
					{	
						System.out.println("DB validation failure");
						// Add the screenshot to the Reporting steps with a hyperlink
						test.fail("Entity Group and Entity Description NOT stored in Database",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyDatabase", test,rowNoGbl, date1))
								.build());				

						// Add the screenshot to the Reporting steps with a hyperlink
						test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyDatabase", test,rowNoGbl, date1));
					} 
					// Close the Browser
					driver.quit();
					
					test.log(Status.INFO, "Closed the Browser");

					// calling flush writes everything to the Extent Report
					extent.flush();
					}
				}
				
			}
			catch(Exception e) {
									
					if (driver.findElement(objPageRead.getLocator("objDuplicateMessage")).isDisplayed()) {
						//test.log(Status.INFO, "Entity Group already exists.");
						System.out.println("Duplicate entity group name");
						test.fail("Message Displayed is not correct",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorNotDisplayed", test,rowNoGbl, date1))
								.build());				
	
						// Add the screenshot to the Reporting steps with a hyperlink
						test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorNotDisplayed", test,rowNoGbl, date1));
						//driver.close();
						//System.exit(0);
					}
					else { 
						// Add the screenshot to the Reporting steps with a hyperlink
						test.fail("Message Displayed is not correct",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorNotDisplayed", test,rowNoGbl, date1))
								.build());				
	
						// Add the screenshot to the Reporting steps with a hyperlink
						test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorNotDisplayed", test,rowNoGbl, date1));
					}	
				}
			

		} 
		catch(Exception e) {
			System.out.println("In main catch block "+e);
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
	public void duplicateEntityGroup(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1, String EntityGroupName, String EntityGroupDesc,String strExpErrorMsg,DBUtil objDBUtil)
	{
		try {
			Thread.sleep(1000);
			driver.findElement(objPageRead.getLocator("objEntityGroupAdd")).click();
			driver.findElement(objPageRead.getLocator("objAddEntityGroupName")).sendKeys(EntityGroupName);
			driver.findElement(objPageRead.getLocator("objAddEntityGroupDesc")).sendKeys(EntityGroupDesc);
			driver.findElement(objPageRead.getLocator("objAddEntityGroupSave")).click();
			String strActErrorMsg = driver.findElement(objPageRead.getLocator("objAddEntityGroupDuplicateRow")).getText();

			if (strActErrorMsg.equalsIgnoreCase(strExpErrorMsg))
			{	
				String DBEntityGroup = objDBUtil.verifyColumnValueNotDeleted("Metacasper.TBL_CSPR_ENTITYGROUP","NAME","DESCRIPTION",EntityGroupName.toUpperCase(),EntityGroupDesc.toUpperCase(),"ISDELETE","N");
				if(Integer.parseInt(DBEntityGroup)>=1)
				{
					// Add the screenshot to the Reporting steps with a hyperlink
					test.fail("Entity Group doesnt exists in Database, Entity group should be added",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyDatabase", test,rowNoGbl, date1))
							.build());				

					// Add the screenshot to the Reporting steps with a hyperlink
					test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyDatabase", test,rowNoGbl, date1));
				}	
				else 
				{	
					// Add the screenshot to the Reporting steps with a hyperlink
					test.pass("Duplicate Entity Group is Rejected and Message Verified",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorMessageOk", test,rowNoGbl, date1))
							.build());	
				}

			}
			else 
			{				
				// Add the screenshot to the Reporting steps with a hyperlink
				test.fail("Duplicate Entity group is rejected, Error Message not correct",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorNotDisplayed", test,rowNoGbl, date1))
						.build());				

				// Add the screenshot to the Reporting steps with a hyperlink
				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorNotDisplayed", test,rowNoGbl, date1));

			}


			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// calling flush writes everything to the Extent Report
			extent.flush();
		} 
		catch (Exception e) {
			System.out.println("In catch block "+e);
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

	public void editEntityGroup(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl,String date1, String strEntityGrpName, String strEntityGrpDesc,String strExpMessage,DBUtil objDBUtil)
	{
		try {

			String FirstRowName=null, FirstRowDesc=null;
			//int iteration = objCBF.generateRandomNumber(); //generate random number for description
			//strEntityGrpDesc = strEntityGrpDesc + iteration;
			System.out.println("EntityGroup Description: "+strEntityGrpDesc);
			
			driver.findElement(objPageRead.getLocator("objSearchDC")).sendKeys(strEntityGrpName);
			Thread.sleep(2000);
			System.out.println("Searched entity group to be modified");
			Actions action = new Actions(driver);
			driver.findElement(objPageRead.getLocator("objAddEntityGroupAddedFirstRowName")).click();

			WebElement objRightClick = driver.findElement(objPageRead.getLocator("objAddEntityGroupAddedFirstRowName"));
			action.contextClick(objRightClick).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ENTER).build().perform();
			WebElement editOpen = driver.findElement(objPageRead.getLocator("objEntityGroupEdit")); /*This will select menu after right click */
			editOpen.click();
			Thread.sleep(2000);
			driver.findElement(objPageRead.getLocator("objEditEntityGroupDesc")).clear();
			driver.findElement(objPageRead.getLocator("objEditEntityGroupDesc")).sendKeys(strEntityGrpDesc);
			driver.findElement(objPageRead.getLocator("objEditEntityGroupSave")).click();
			Thread.sleep(2000);
			String strActErrorMsg = driver.findElement(objPageRead.getLocator("objEditEntityGroupSuccessMsg")).getText();
			Thread.sleep(2000);
			driver.findElement(objPageRead.getLocator("objAddEntityGroupAddedFirstRowName")).click();
			FirstRowName=driver.findElement(objPageRead.getLocator("objAddEntityGroupAddedFirstRowName")).getText();
			FirstRowDesc=driver.findElement(objPageRead.getLocator("objAddEntityGroupAddedFirstRowDesc")).getText();

			if (strActErrorMsg.equalsIgnoreCase(strExpMessage))
			{	
				System.out.println("Updated");
				String DBEntityGroup = objDBUtil.verifyColumnValueNotDeleted("Metacasper.TBL_CSPR_ENTITYGROUP","NAME","DESCRIPTION",FirstRowName.toUpperCase(),FirstRowDesc.toUpperCase(),"ISDELETE","N");
				if(Integer.parseInt(DBEntityGroup)>=1)
				{
					test.fail("Entity Group doesnt exists in Database, Entity group should be updated",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyDatabase", test,rowNoGbl, date1))
							.build());				

					test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyDatabase", test,rowNoGbl, date1));
				}	
				else 
				{	
					test.pass("Entitygroup is Updated correctly and Message Verified",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorMessageOk", test,rowNoGbl, date1))
							.build());
				}
			}
			else 
			{				
				test.fail("Entity Group updated but Error Message not correct",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorNotDisplayed", test,rowNoGbl, date1))
						.build());				

				test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorNotDisplayed", test,rowNoGbl, date1));

			}


			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// calling flush writes everything to the Extent Report
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
	public void deleteEntityGroup(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1, String strExpErrorMsg,DBUtil objDBUtil)
	{
		try {

			String FirstRowName=null, FirstRowDesc=null,FirstRowEntities=null;
			Actions action = new Actions(driver);
			driver.findElement(objPageRead.getLocator("objAddEntityGroupAddedFirstRowName")).click();
			FirstRowName=driver.findElement(objPageRead.getLocator("objAddEntityGroupAddedFirstRowName")).getText();
			FirstRowDesc=driver.findElement(objPageRead.getLocator("objAddEntityGroupAddedFirstRowDesc")).getText();
			FirstRowEntities=driver.findElement(objPageRead.getLocator("objDeleteEntityGroupNoofEntities")).getText();

			if(FirstRowEntities.isEmpty())
			{
				WebElement objRightClick = driver.findElement(objPageRead.getLocator("objAddEntityGroupAddedFirstRowName"));
				action.contextClick(objRightClick).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
				WebElement deleteOpen = driver.findElement(objPageRead.getLocator("objEntityGroupDelete")); 
				deleteOpen.click();
				Thread.sleep(2000);

				String strActErrorMsg = driver.findElement(objPageRead.getLocator("objDeleteEntityGroupSuccessMsg")).getText();

				if (strActErrorMsg.equalsIgnoreCase(strExpErrorMsg))
				{	
					String DBEntityGroup = objDBUtil.verifyColumnValueNotDeleted("Metacasper.TBL_CSPR_ENTITYGROUP","NAME","DESCRIPTION",FirstRowName.toUpperCase(),FirstRowDesc.toUpperCase(),"ISDELETE","Y");
					if(Integer.parseInt(DBEntityGroup)>=1)
					{
						test.fail("Entity Group still exists in Database, Entity group and not deleted",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyDatabase", test,rowNoGbl, date1))
								.build());				

						test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyDatabase", test,rowNoGbl, date1));
					}	
					else 
					{	
						test.pass("Entitygroup is Deleted and Message Verified",
								MediaEntityBuilder
								.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorMessageOk", test,rowNoGbl, date1))
								.build());
					}
				}
				else 
				{				
					test.fail("Entity group deletion Error Message not correct",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorNotDisplayed", test,rowNoGbl, date1))
							.build());				

					test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorNotDisplayed", test,rowNoGbl, date1));

				}
			}
			else
			{
				test.pass("Entitygroup is Assigned with Entities and hence cannot be Deleted",
						MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorMessageOk", test,rowNoGbl, date1))
						.build());

				// Find another entity group good for deletion and delete it

				Boolean EntityForDelete = IsTestElementPresent(driver);

				if (EntityForDelete == true)
				{
					WebElement EntityForDeletion = driver.findElement(objPageRead.getLocator("objEntityForDelete"));
					EntityForDeletion.click();
					FirstRowName=driver.findElement(objPageRead.getLocator("objAddEntityGroupAddedFirstRowName")).getText();
					FirstRowDesc=driver.findElement(objPageRead.getLocator("objAddEntityGroupAddedFirstRowDesc")).getText();
					FirstRowEntities=driver.findElement(objPageRead.getLocator("objDeleteEntityGroupNoofEntities")).getText();
					WebElement objRightClick = driver.findElement(objPageRead.getLocator("objAddEntityGroupAddedFirstRowName"));
					action.contextClick(objRightClick).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
					WebElement deleteOpen = driver.findElement(objPageRead.getLocator("objEntityGroupDelete")); 
					deleteOpen.click();
					Thread.sleep(2000);

					String strActErrorMsg = driver.findElement(objPageRead.getLocator("objDeleteEntityGroupSuccessMsg")).getText();

					if (strActErrorMsg.equalsIgnoreCase(strExpErrorMsg))
					{	
						String DBEntityGroup = objDBUtil.verifyColumnValueNotDeleted("Metacasper.TBL_CSPR_ENTITYGROUP","NAME","DESCRIPTION",FirstRowName.toUpperCase(),FirstRowDesc.toUpperCase(),"ISDELETE","Y");
						if(Integer.parseInt(DBEntityGroup)>=1)
						{
							test.fail("Found another Entity Group. Entity Group still exists in Database, Entity group and not deleted",
									MediaEntityBuilder
									.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyDatabase", test,rowNoGbl, date1))
									.build());				

							test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyDatabase", test,rowNoGbl, date1));
						}	
						else 
						{	
							test.pass("Found another Entity Group. Entitygroup is Deleted and Message Verified",
									MediaEntityBuilder
									.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorMessageOk", test,rowNoGbl, date1))
									.build());
						}

					}
				}
				else
				{
					test.fail("There are not entity groups available for deletion. All are assigned with entities",
							MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyEntityFordeletion", test,rowNoGbl, date1))
							.build());				

					test.addScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyEntityFordeletion", test,rowNoGbl, date1));

				}
			}
			// Close the Browser
			driver.quit();
			test.log(Status.INFO, "Closed the Browser");

			// calling flush writes everything to the Extent Report
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

	public boolean IsTestElementPresent(WebDriver driver)
	{
		try
		{
			driver.findElement(objPageRead.getLocator("objEntityForDelete"));
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public void NavigateToEntityGroups(WebDriver driver,ExtentTest test,ExtentReports extent, String StrDataCollName)throws Throwable {
		
		try {
			driver.findElement(objPageRead.getLocator("objDefinitionTab")).click();
			test.log(Status.INFO, "Clicked on Definition Tab");
			Thread.sleep(7000);
			driver.findElement(objPageRead.getLocator("objSearchDC")).sendKeys(StrDataCollName);
			System.out.println("entered DC: "+StrDataCollName);
			test.log(Status.INFO, "Entered data collection name in search box");
			Thread.sleep(3000);
			
			List<WebElement> DCMatches = driver.findElements(objPageRead.getLocator("objGridCodeCol")); 
			
			// Now iterate through them and check for our desired match
			for (WebElement anElement : DCMatches) {
				System.out.println("column value: "+anElement.getText());
			    if (anElement.getText().equalsIgnoreCase(StrDataCollName)) {
			    	System.out.println("data collection matches");
			    	anElement.click();
			    	break;
			    }
			}
		
				Actions action = new Actions(driver);			
				WebElement CurrentRow = driver.switchTo().activeElement();
				action.contextClick(CurrentRow).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ENTER).build().perform();
				Thread.sleep(2000);
				WebElement viewOpen = driver.findElement(objPageRead.getLocator("objView")); /*This will select menu after right click */
				viewOpen.click();
				test.log(Status.INFO, "Selected view option for data collection");
				Thread.sleep(2000);
				driver.findElement(objPageRead.getLocator("objPopulationTab")).click();
				Thread.sleep(1000);
				test.log(Status.INFO, "Clicked on Population link");
				driver.findElement(objPageRead.getLocator("objEntityGroups")).click();
				test.log(Status.INFO, "Clicked on Entity groups link");
				Thread.sleep(1000);
			}
		catch(Exception e){
			System.out.println("in catch block");
			
		}
	}

	public boolean verifyMaxLength(WebDriver driver,String text,String fieldName) throws Exception {
		WebElement eleName = driver.findElement(objPageRead.getLocator(fieldName));
		String lengthValue = eleName.getAttribute("ng-reflect-maxlength");
		int maxLengthOfTextField = Integer.parseInt(lengthValue);
		eleName.sendKeys(text);
		String enteredText = eleName.getAttribute("value");
		int lengthOfenteredValue = enteredText.length();
		if(maxLengthOfTextField==lengthOfenteredValue){
			return true;
		}
		else {
			return false;
		}
	}
	public void uploadTemplate(WebDriver driver,String rowNo,ExtentTest test,String strFilePath,By browseBtn) throws Throwable {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			File obj = new File("src/test/resources/FilesToUpload/"+strFilePath);
			String Path=obj.getAbsolutePath();
			driver.findElement(browseBtn).sendKeys(Path);
			test.log(Status.INFO, "Clicked File Upload option in the Menu");
            //VBScript code to do actions on the Upload File Windows Dialog box
			//Runtime.getRuntime().exec("wscript src/test/resources/VBScript/upload.vbs " + strFilePath);
			Thread.sleep(2000);
			test.log(Status.INFO, "Selected the file to be uploaded and hit Enter");
			
		} catch (Exception e) {
			System.out.println("In the catch block");
		}
	}
	public void uploadEntity(WebDriver driver,String rowNo,ExtentTest test,String strFilePath,By browseBtn) throws Throwable {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			File obj = new File("src/test/resources/FilesToUpload/ReportingEntityUpload/"+strFilePath);
			String Path=obj.getAbsolutePath();
			driver.findElement(browseBtn).sendKeys(Path);
			test.log(Status.INFO, "Clicked File Upload option in the Menu");
            //VBScript code to do actions on the Upload File Windows Dialog box
			//Runtime.getRuntime().exec("wscript src/test/resources/VBScript/upload.vbs " + strFilePath);
			Thread.sleep(2000);
			test.log(Status.INFO, "Selected the file to be uploaded and hit Enter");
			
		} catch (Exception e) {
			System.out.println("In the catch block");
		}
	}
	public void uploadDataPoint(WebDriver driver,String rowNo,ExtentTest test,String strFilePath,By browseBtn) throws Throwable {
		try {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			File obj = new File("src/test/resources/FilesToUpload/DataPointImport/"+strFilePath);
			String Path=obj.getAbsolutePath();
			driver.findElement(browseBtn).sendKeys(Path);
			test.log(Status.INFO, "Clicked File Upload option in the Menu");
            //VBScript code to do actions on the Upload File Windows Dialog box
			//Runtime.getRuntime().exec("wscript src/test/resources/VBScript/upload.vbs " + strFilePath);
			Thread.sleep(2000);
			test.log(Status.INFO, "Selected the file to be uploaded and hit Enter");
			
		} catch (Exception e) {
			System.out.println("In the catch block");
		}
	}
	public void uploadValidationRule(WebDriver driver,String rowNo,ExtentTest test,String strFilePath,By browseBtn) throws Throwable {
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			File obj = new File("src/test/resources/FilesToUpload/Validation_Rules/"+strFilePath);
			String Path=obj.getAbsolutePath();
			driver.findElement(browseBtn).sendKeys(Path);
			test.log(Status.INFO, "Clicked File Upload option in the Menu");
            //VBScript code to do actions on the Upload File Windows Dialog box
			//Runtime.getRuntime().exec("wscript src/test/resources/VBScript/upload.vbs " + strFilePath);
			Thread.sleep(2000);
			test.log(Status.INFO, "Selected the file to be uploaded and hit Enter");
			
		} catch (Exception e) {
			System.out.println("In the catch block");
		}
	}
	public void EnterAttributeDetails(WebDriver driver,String rowNoGbl,ExtentTest test,ExtentReports extent,String date1,String tableRowNo, String attrVal, String repCode, String validFrom, String validTo) throws Exception{
		try {
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement elmattrValue;
			WebElement chkbox = driver.findElement(By.xpath("//*[@id='model-body']/table/tbody/tr["+tableRowNo+"]/td[1]/label/input"));
			Select elmisRepCode = new Select(driver.findElement(By.xpath("//*[@id='model-body']/table/tbody/tr["+tableRowNo+"]/td[4]/div/select")));
			WebElement elmvalidFrom = driver.findElement(By.xpath("//*[@id='model-body']/table/tbody/tr["+tableRowNo+"]/td[5]/div[1]/input"));
			WebElement elmvalidTo = driver.findElement(By.xpath("//*[@id='model-body']/table/tbody/tr["+tableRowNo+"]/td[6]/div[1]/input"));
			
			if(!chkbox.isSelected())
			{
				chkbox.click();
				WebElement element= driver.findElement(By.xpath("//*[@id='model-body']/table/tbody/tr["+tableRowNo+"]/td[3]/div"));
				WebElement child = element.findElement(By.xpath("./child::*"));
				String tagName= child.getTagName();
				if(tagName.equals("select"))
				{
					    Thread.sleep(1000);
						Select select =new Select(driver.findElement(By.xpath("//*[@id='model-body']/table/tbody/tr["+tableRowNo+"]/td[3]/div/select")));
						select.selectByIndex(1);
						elmisRepCode.selectByValue(repCode);
						elmvalidFrom.clear();
						elmvalidFrom.sendKeys(validFrom);
						elmvalidTo.clear();
						elmvalidTo.sendKeys(validTo);
						test.log(Status.INFO, "Attribute details has been entered");
						
				}
				else if(tagName.equals("input"))
				{
					        elmattrValue = driver.findElement(By.xpath("//*[@id='model-body']/table/tbody/tr["+tableRowNo+"]/td[3]/div/input"));
							Thread.sleep(1000);
							elmattrValue.clear();
							elmattrValue.sendKeys(attrVal);
							elmisRepCode.selectByValue(repCode);
							elmvalidFrom.clear();
							elmvalidFrom.sendKeys(validFrom);
							elmvalidTo.clear();
							elmvalidTo.sendKeys(validTo);
							test.log(Status.INFO, "Attribute details has been entered");
							
				}
			  }
			else 
			{
				test.log(Status.INFO, "Attribute is selected already");
				WebElement element= driver.findElement(By.xpath("//*[@id='model-body']/table/tbody/tr["+tableRowNo+"]/td[3]/div"));
				WebElement child = element.findElement(By.xpath("./child::*"));
				String tagName= child.getTagName();
				if(tagName.equals("select"))
				{
					    Thread.sleep(1000);
						Select select =new Select(driver.findElement(By.xpath("//*[@id='model-body']/table/tbody/tr["+tableRowNo+"]/td[3]/div/select")));
						select.selectByIndex(1);
						elmisRepCode.selectByValue(repCode);
						elmvalidFrom.clear();
						elmvalidFrom.sendKeys(validFrom);
						elmvalidTo.clear();
						elmvalidTo.sendKeys(validTo);
						test.log(Status.INFO, "Attribute details has been entered");
						
				}
				else if(tagName.equals("input"))
				{
					        elmattrValue = driver.findElement(By.xpath("//*[@id='model-body']/table/tbody/tr["+tableRowNo+"]/td[3]/div/input"));
							Thread.sleep(1000);
							elmattrValue.clear();
							elmattrValue.sendKeys(attrVal);
							elmisRepCode.selectByValue(repCode);
							elmvalidFrom.sendKeys(validFrom);
							elmvalidFrom.clear();
							elmvalidFrom.sendKeys(validFrom);
							elmvalidTo.clear();
							elmvalidTo.sendKeys(validTo);
							test.log(Status.INFO, "Attribute details has been entered");
							
				}
			}
			extent.flush();
		}	
		catch(Exception e) {
			System.out.println("In the catch block "+e);
			test.log(Status.INFO, e);
			extent.flush();
		}
	}
	
	public void EditAssignedEntities(WebDriver driver,String rowNoGbl,ExtentTest test,ExtentReports extent,String date1,String tableRowNo,String validFrom, String validTo ) throws Exception{
		try {
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement chkbox = driver.findElement(By.xpath("//div["+tableRowNo+"]/div/span/span/i[2]"));
			if(!chkbox.isSelected()) {
				chkbox.click();
				Thread.sleep(1000);
				WebElement elmvalidFrom = driver.findElement(By.xpath("//div["+tableRowNo+"]/div[@col-id='entityGroupVFDate']/app-datecell-renderer/form/div/input"));
				WebElement elmvalidTo = driver.findElement(By.xpath("//div["+tableRowNo+"]/div[@col-id='entityGroupVTDate']/app-datecell-renderer/form/div/input"));
				elmvalidFrom.clear();
				elmvalidFrom.sendKeys(validFrom);
				elmvalidTo.sendKeys(validTo);
				test.log(Status.INFO, "Details has been entered");
			}
			else {
				test.log(Status.INFO, "checkbox is selected already");
			}
			extent.flush();
		}
		catch(Exception e) {
			System.out.println("In the catch block "+e);
			test.log(Status.INFO, e);
			extent.flush();
		}
	}
	public void EditAssignedEntitiesNegative(WebDriver driver,String rowNoGbl,ExtentTest test,ExtentReports extent,String date1,String tableRowNo,String validFrom, String validTo ) throws Exception{
		try {
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement elmvalidFrom = driver.findElement(By.xpath("//div["+tableRowNo+"]/div[@col-id='entityGroupVFDate']/app-datecell-renderer/form/div/input"));
			WebElement elmvalidTo = driver.findElement(By.xpath("//div["+tableRowNo+"]/div[@col-id='entityGroupVTDate']/app-datecell-renderer/form/div/input"));
				elmvalidFrom.clear();
				elmvalidFrom.sendKeys(validFrom);
				elmvalidTo.clear();
				elmvalidTo.sendKeys(validTo);
				test.log(Status.INFO, "Details has been entered");
			
			extent.flush();
		}
		catch(Exception e) {
			System.out.println("In the catch block "+e);
			test.log(Status.INFO, e);
			extent.flush();
		}
	}
	public int getRowNumberWithCellValue(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl,By xpath,String cellValue) {
		int rowNo = 0;
		WebElement recordVal=null;
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		List<WebElement> element = driver.findElements(xpath);
		for(int i=0;i<=element.size();i++) {
			 recordVal = element.get(i);
			 String actualVal = recordVal.getText();
			 if(actualVal.equalsIgnoreCase(cellValue)) {
				 break;
			
			}
		}
		js.executeScript("arguments[0].cellIndex;",recordVal);
		return rowNo;
	}
	public void compareValidationRuleResults(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl,String date1,String fileName,String fileToCompareName) throws IOException
	{
		try{
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//Reading CSV input file 
        	String inputFilePath= "src/test/resources/FilesToUpload/Validation_Rules/"+fileName;
        	
            Reader reader = Files.newBufferedReader(Paths.get(inputFilePath));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim());
            
            //Storing all rule ID from CSV file into Hashmap
            HashMap <String,String> rules = new HashMap <String,String>();
            for (CSVRecord record : csvParser)
            {
            	String ruleID= record.get("RULEID");
            	String errorMessage= record.get("ERRORMESSAGE");
            	
            	rules.put(ruleID, errorMessage);
            }
            reader.close();
            
            //Read file to compare result
            FileInputStream fis = new FileInputStream("src/test/resources/FilesToUpload/Validation_Rules/"+fileToCompareName);
        	ExcelWBook = new XSSFWorkbook(fis);	
        	ExcelWSheet = ExcelWBook.getSheetAt(0);
        	int lastRow = ExcelWSheet.getLastRowNum();
        	Map<String, String> dataMap = new HashMap<String, String>();
        	String colNameRuleID="RULEID";
        	String colNameErrorMessage="ERRORMESSAGE";
        	String colNameResult="RESULT";
        	for(int i=1; i<=lastRow; i++){
        		XSSFRow Row = ExcelWSheet.getRow(0);
        		int col_ruleIDIndex = getColumnIndex(driver, test, extent, rowNoGbl, date1, colNameRuleID,Row);
    			int col_ErroMessageIndex=getColumnIndex(driver, test, extent, rowNoGbl, date1, colNameErrorMessage,Row);;
  
    			Row = ExcelWSheet.getRow(i);
    			XSSFCell ruleIdCell = Row.getCell(col_ruleIDIndex);
    			XSSFCell errorMsgCell = Row.getCell(col_ErroMessageIndex);
    			String key = ruleIdCell.getStringCellValue();
    			String value = errorMsgCell.getStringCellValue();   
        		   //Putting key & value in dataMap
        		   dataMap.put(key, value);
        	}
        	
        	//Compare the rule id from uploaded CSV file with rule id in file to compare result. 
        	
        	for(String ruleID :rules.keySet())
        	{	
                if(dataMap.containsKey(ruleID))
        		{
        			//If rule id is correct the compare the error message.
        			String errorMessageValue= dataMap.get(ruleID);
        			boolean result =checkErrorMessage(driver,test,extent,rowNoGbl, date1,ruleID,errorMessageValue);
        			XSSFRow Row1 = ExcelWSheet.getRow(0);
        			int col_ruleIDIndex = getColumnIndex(driver, test, extent, rowNoGbl, date1, colNameRuleID,Row1 );
        			int col_resultIndex=getColumnIndex(driver, test, extent, rowNoGbl, date1, colNameResult,Row1 );;
        			if(result==true)
        			{
        				for(int i=1;i<=ExcelWSheet.getLastRowNum();i++)
        				{
        					Row1 = ExcelWSheet.getRow(i);
        					XSSFCell ruleIdCell = Row1.getCell(col_ruleIDIndex);
        					String key = ruleIdCell.getStringCellValue();
        					if(key.equals(ruleID))
        					{
        						XSSFCell resultCell= Row1.getCell(col_resultIndex, Row.CREATE_NULL_AS_BLANK);
        						resultCell.setCellValue("");
        						resultCell.setCellValue("Pass");
        						FileOutputStream fileOut = new FileOutputStream("src/test/resources/FilesToUpload/Validation_Rules/"+fileToCompareName);
        						ExcelWBook.write(fileOut);
        					}        					
        				}
        			} 
        			else
        			{
        				for(int i=1;i<=ExcelWSheet.getLastRowNum();i++)
        				{
        					Row1 = ExcelWSheet.getRow(i);
        					XSSFCell ruleIdCell = Row1.getCell(col_ruleIDIndex);
        					String key = ruleIdCell.getStringCellValue();
        					if(key.equals(ruleID))
        					{
        						XSSFCell resultCell= Row1.getCell(col_resultIndex);
        						resultCell.setCellValue("");
        						resultCell.setCellValue("Fail");
        						FileOutputStream fileOut = new FileOutputStream("src/test/resources/FilesToUpload/Validation_Rules/"+fileToCompareName);
        						ExcelWBook.write(fileOut);
        					}
        				}
        			}
        		}
            }
	    }
		catch(Exception e)
		{
			
		}
	}
	
	public int getColumnIndex(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl,String date1,String colName, XSSFRow Row)
	{
		//Getting column index
		int index=-1;
		for(int i=0;i<=Row.getLastCellNum();i++)
		{
			String col= ExcelWSheet.getRow(0).getCell(i).toString().trim();
			if(col.equals(colName))
			{
		      index = 	ExcelWSheet.getRow(0).getCell(i).getColumnIndex();
			  break;
			}
		}
		return index;
	}
	public boolean checkErrorMessage(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl,String date1,String ruleID,String errorMessageValue) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		boolean result=false;
		//Search for rule id
		objCBF.searchRecordForValidationRule(driver,test,extent,rowNoGbl,ruleID);
		
		objCBF.clickOnAction(driver,test,extent,rowNoGbl,date1,"View");
		WebElement errorField= driver.findElement(By.xpath("//textarea[@formcontrolname='errorMessageDefinition']"));
		String errorText= errorField.getAttribute("value");
		//Checking the error message is correct or not
		if(errorText.equals(errorMessageValue))
		{
		   result=true;
		   objCBF.clickOnButton(driver, test, extent, rowNoGbl, date1, "Close");
		   test.pass("Error message matches as expected");
		   return result;
		}
		else
		{
			objCBF.clickOnButton(driver, test, extent, rowNoGbl, date1, "Close");
			test.fail("Error message not matches as expected");
			return result;
		}
		
	}
	public void SearchFileInImportHistory(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1,String fileName) {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement searchFile= driver.findElement(objPageRead.getLocator("objSearchFileImportHistory"));
			searchFile.sendKeys(fileName);
			Thread.sleep(2000);
			 test.pass("File gets searched");
			extent.flush();
		}
		catch(Exception e) {
			System.out.println("Error while searching file "+e);
		}
	}
	
	public void verifyErrCodeInImportHisPage(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1,String expectedErrorCode)
	{
		try {
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement searchCode= driver.findElement(By.xpath("//div[@role='gridcell'][@col-id='errorCode']"));
			String actualErrorCode=searchCode.getText();
			if(actualErrorCode.trim().equals(expectedErrorCode))
				test.pass("Correct error code get displyed:"+actualErrorCode +"", MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorCodePassed", test,rowNoGbl, date1)).build());				
			else
				test.fail("InCorrect error code get displyed:"+actualErrorCode +"", MediaEntityBuilder
					.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorCodeFailed", test,rowNoGbl, date1)).build());				
			
			extent.flush();
		}
		catch(Exception e) {
			System.out.println("Error while searching file "+e);
		}
	}
	
	public void verifyErrMsgInImportHisForRepEntity(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1,String expectedErrorMessage,String attributeCode)
	{
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement searchMessage= driver.findElement(By.xpath("//div[@role='gridcell'][@col-id='errorDesc']"));
			String actualErrorMessage=searchMessage.getText();
			
			if(expectedErrorMessage.contains("{attributeCode}"))
			{
				String updatedErrorMessage=expectedErrorMessage.replace("{attributeCode}", attributeCode);
				if(actualErrorMessage.trim().equals(updatedErrorMessage.trim()))
					test.pass("Correct error message get displyed:"+actualErrorMessage +"", MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorCodePassed", test,rowNoGbl, date1)).build());				
				else
					test.fail("InCorrect error message get displyed:"+actualErrorMessage +"", MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorCodeFailed", test,rowNoGbl, date1)).build());				
			}
			else if(expectedErrorMessage.contains("(Row {{lineNumber}} Column {{columnName}})"))
			{
				String updatedErrorMessage=expectedErrorMessage.replace("{{columnName}}", "ATTR_VALUE");
				String rowNo=driver.findElement(By.xpath("//div[@role='gridcell'][@col-id='row']")).getText();
				updatedErrorMessage=updatedErrorMessage.replace("{{lineNumber}}", rowNo);
				if(actualErrorMessage.trim().equals(updatedErrorMessage.trim()))
					test.pass("Correct error message get displyed:"+actualErrorMessage +"", MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorCodePassed", test,rowNoGbl, date1)).build());				
				else
					test.fail("InCorrect error message get displyed:"+actualErrorMessage +"", MediaEntityBuilder
						.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorCodeFailed", test,rowNoGbl, date1)).build());				
			}
			else
			{
				if(actualErrorMessage.trim().equals(expectedErrorMessage.trim()))
					test.pass("Correct error message get displyed:"+actualErrorMessage +"", MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorCodePassed", test,rowNoGbl, date1)).build());				
				else
					test.fail("InCorrect error message get displyed:"+actualErrorMessage +"", MediaEntityBuilder
					.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorCodeFailed", test,rowNoGbl, date1)).build());				
			}
			extent.flush();
		}
		catch(Exception e) {
			System.out.println("Error while searching file "+e);
		}
	}
	
	public void verifyErrMsgInImportHisForDataPoint(WebDriver driver,ExtentTest test,ExtentReports extent,String rowNoGbl, String date1,String expectedErrorMessage)
	{
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement searchMessage= driver.findElement(By.xpath("//div[@role='gridcell'][@col-id='errorDesc']"));
			String actualErrorMessage=searchMessage.getText();
				if(actualErrorMessage.trim().equals(expectedErrorMessage.trim()))
					test.pass("Correct error message get displyed:"+actualErrorMessage +"", MediaEntityBuilder
							.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorCodePassed", test,rowNoGbl, date1)).build());				
				else
					test.fail("InCorrect error message get displyed:"+actualErrorMessage +"", MediaEntityBuilder
					.createScreenCaptureFromPath(objCreateScreenshot.createScreenshot(driver, "VerifyErrorCodeFailed", test,rowNoGbl, date1)).build());				
			
			extent.flush();
		}
		catch(Exception e) {
			System.out.println("Error while searching file "+e);
		}
	}
}

