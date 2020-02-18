
package StepDefinition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


import Business_Functions.Common_Business_Functions;
import Business_Functions.DataDictionary;
import Business_Functions.Data_Definition;
import Business_Functions.File_Submission;
import Business_Functions.RecentChanges_CommonFunctions;
import Utility.DBUtil;
import Utility.ExcelUtils;
import Utility.PropertyReader;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;

public class stepDefinition extends TestCase {

	// Initialisation of the Objects and Variables
	WebDriver driver;
	ExcelUtils objExcelUtils = null;
	ExcelUtils objExcelUtilsLogin = null; // testdata file object for login
	DBUtil objDBUtil = null;

	PropertyReader objProRead = new PropertyReader("config.properties");
	PropertyReader objPageObjsProRead = new PropertyReader("src/test/java/Page_Objects/Common_Page_Objects.properties");
	PropertyReader objPageReadFileSub = new PropertyReader("src/test/java/Page_Objects/File_Submission.properties");
	PropertyReader objPageReadDataDict = new PropertyReader("src/test/java/Page_Objects/Dictionaries.properties");
	PropertyReader objPageReadDefiniton = new PropertyReader("src/test/java/Page_Objects/Data_Definition.properties");
	PropertyReader objPageReadModules = new PropertyReader("src/test/java/Page_Objects/Modules.properties");
	PropertyReader objPageReadValidationRules = new PropertyReader(
			"src/test/java/Page_Objects/Business_validation_rule.properties");
	PropertyReader objPageReadDISCExport = new PropertyReader("src/test/java/Page_Objects/DISC.properties");

	Common_Business_Functions objCBF = new Common_Business_Functions();
	File_Submission objFileSub = new File_Submission();
	Data_Definition objEntityGroup = new Data_Definition();
	Data_Definition objDataDef = new Data_Definition();
	Data_Definition objBusinessValRule = new Data_Definition();
	DataDictionary objDataDict = new DataDictionary();
	static String sheet = null;
	static String currentDir = System.getProperty("user.dir");

	// Initialisation for Extent Reports
	ExtentReports extent = null;
	ExtentHtmlReporter htmlReporter = null;
	ExtentTest test = null;
	String userName = null, password = null, strFilePath = null, strExpErrMsg = null, NoOfValidFile = null,
			NoofInvalidFile = null, EntityGroupName = null, EntityGroupDesc = null, ReportingCycleName = null,
			StartDate = null, EndDate = null, Description = null;
	int NoOfFiles = 0;
	ExtentTest scenario = null;

	// Initialisation for Dates
	DateFormat dateFormat;
	static String date1 = null;
	static String rowNoGbl = null;
	Date date;

	// Implementation of Given Feature step
	@Given("^Open Casper Application in Web Browser \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void open_Casper_Application_in_Web_Browser(String workbookName, String sheetName, String rowNo)
			throws Throwable {
		try {

			System.out.println("Opening Casper Application in Web Browser");
			// Assign the date format
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			// Get current date time with Date()
			date = new Date();

			// Now format the date
			date1 = dateFormat.format(date);

			rowNoGbl = rowNo;

			// Initialise the Extent Report object
			extent = new ExtentReports();
			// Initialise the Extent Html Reporter object
			htmlReporter = new ExtentHtmlReporter(
					"Reports/TestCases/" + sheetName + "_" + date1 + "_Itr(" + rowNo + ").html");
			// Attach the Html Reporter object to the Extent Report object
			extent.attachReporter(htmlReporter);
			// Create a test object for the Extent report object
			// test = extent.createTest("Demo Sample Test Name Iteration - " +
			// rowNo);
			test = extent.createTest("Test Report for Iteration - " + rowNo);
			// Duplicating the Sheet Name for further usage
			sheet = sheetName;
			// Get the URL and Browser from the config.properties
			String strURL = objProRead.getData("URL");
			String browserType = objProRead.getData("Browser");

			// Get the URL and Browser from system property for command line
			// String strURL = System.getProperty("URL");
			// String browserType = System.getProperty("Browser");

			// Created object for all excel data file
			String strTestDataFilePathDict = objProRead.getData("TestDataFilePath_DataDict");
			String strTestDataFilePathUA = objProRead.getData("TestDataFilePath_UserAssign");
			String strTestDataFilePathFV = objProRead.getData("TestDataFilePath_FileVault");
			String strTestDataFilePathLogin = objProRead.getData("TestDataFilePath_Login");
			String strTestDataFilePathSub = objProRead.getData("TestDataFilePath_Submissions");
			String strTestDataFilePath = objProRead.getData("TestDataFilePath");
			String strTestDataFilePathBVR = objProRead.getData("TestDataFilePath_BusinessValidationRules");
			String strTestDataFilePathBVRFileNames = objProRead
					.getData("TestDataFilePath_BusinessValidationRules_FileName");
			String strTestDataFilePathModules = objProRead.getData("TestDataFilePath_Modules");
			String strTestDataFilePathModuleFileNames = objProRead.getData("TestDataFilePath_Modules_FileName");
			String strTestDataFilePathDISC = objProRead.getData("TestDataFilePath_DISC_FileName");
			// System.out.println(" TID_RECEIVEDFILE =
			// "+objDBUtil.verifyRecordInDB("metacasper.tbl_cspr_receivedfile","TID_RECEIVEDFILE"
			// ,"28400"));
			// Initialise the excel object for the Test Data sheet
			// objExcelUtilsLogin = new
			// ExcelUtils(strTestDataFilePathLogin,"Login");

			if (workbookName.equalsIgnoreCase("Submissions")) {
				objExcelUtils = new ExcelUtils(strTestDataFilePathSub, sheet);
			}
			if (workbookName.equalsIgnoreCase("Definitions")) {
				objExcelUtils = new ExcelUtils(strTestDataFilePath, sheet);
				System.out.println("Created object");
			}
			if (workbookName.equalsIgnoreCase("SanityTestData")) {
				objExcelUtils = new ExcelUtils(strTestDataFilePathLogin, sheet);
				System.out.println("Created object");
			}
			if (workbookName.equalsIgnoreCase("DataDictionary")) {
				objExcelUtils = new ExcelUtils(strTestDataFilePathDict, sheet);
				System.out.println("Created object");
			}
			if (workbookName.equalsIgnoreCase("BusinessValidationRule")) {
				objExcelUtils = new ExcelUtils(strTestDataFilePathBVR, sheet);
			}
			if (workbookName.equalsIgnoreCase("BVRFileNames")) {
				objExcelUtils = new ExcelUtils(strTestDataFilePathBVRFileNames, sheet);
			}
			if (workbookName.equalsIgnoreCase("Modules")) {
				objExcelUtils = new ExcelUtils(strTestDataFilePathModules, sheet);
			}
			if (workbookName.equalsIgnoreCase("ModuleFileNames")) {
				objExcelUtils = new ExcelUtils(strTestDataFilePathModuleFileNames, sheet);
			}
			if (workbookName.equalsIgnoreCase("DISCTesting")) {
				objExcelUtils = new ExcelUtils(strTestDataFilePathDISC, sheet);
			}
			// Get the Username and Password values from Test Data CASPERLogin
			// Sheet
			userName = objExcelUtils.getCellValueUsingColName("UserName", Integer.parseInt(rowNo));
			password = objExcelUtils.getCellValueUsingColName("Password", Integer.parseInt(rowNo));

			// Open the WebBrowser as per the input in config.properties
			driver = objCBF.getDriver(driver, browserType);

			// Login to Casper
			objCBF.loginCasper(driver, strURL, userName, password, test, rowNoGbl, date1, extent);

		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Login failed, Please check screenshot for more detail");
		}
	}

	// Implementation of When Feature step
	@When("^I Upload the Incorrect File$")
	public void i_Upload_the_Incorrect_File() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			strFilePath = objExcelUtils.getCellValueUsingColName("FilePath", Integer.parseInt(rowNoGbl));

			// Upload a File to Casper
			objFileSub.uploadZipFile(driver, rowNoGbl, test, strFilePath);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : I Upload the Incorrect File");
		}

	}

	// Implementation of Then Feature step
	@SuppressWarnings("deprecation")
	@Then("^Error should be displayed$")
	public void error_should_be_displayed() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			strExpErrMsg = objExcelUtils.getCellValueUsingColName("ErrorMessage", Integer.parseInt(rowNoGbl));
			// Verify the error message displayed after upload
			objFileSub.verifyUploadFileErrorMessageDialog(driver, test, strFilePath, extent, rowNoGbl, date1,
					strExpErrMsg);
			// The zip file has already been processed
			// The zip file is empty
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Error should be displayed");
		}
	}

	@When("^I Upload a File$")
	public void i_Upload_a_File() throws Throwable {
		try {
			strFilePath = objExcelUtils.getCellValueUsingColName("FilePath", Integer.parseInt(rowNoGbl));

			// Upload a File to Casper
			// objFileSub.uploadFileDragNDrop(driver,rowNoGbl,test,strFilePath);
			objFileSub.uploadZipFile(driver, rowNoGbl, test, strFilePath);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : I Upload a File");
		}
	}

	@Then("^Verify File Upload$")
	public void Verify_File_Upload() throws Throwable {
		try {
			String strScnType = objExcelUtils.getCellValueUsingColName("ScenarioType", Integer.parseInt(rowNoGbl));
			if (strScnType.equals("Positive")) {
				// Verify the success message displayed after upload
				objFileSub.verifyUploadFileSuccessUploadText(driver, test, strFilePath, extent, rowNoGbl, date1);
			} else if (strScnType.equals("Negative")) {
				strExpErrMsg = objExcelUtils.getCellValueUsingColName("ErrorMessage", Integer.parseInt(rowNoGbl));
				// Verify the error message displayed after upload
				objFileSub.verifyUploadFileErrorMessageDialog(driver, test, strFilePath, extent, rowNoGbl, date1,
						strExpErrMsg);
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify File Upload");
		}
	}

	@Then("^Verify File is Rejected when File Type is Missing$")
	public void Verify_File_is_Rejected_when_File_Type_Missing() throws Throwable {
		try {
			String strScnType = objExcelUtils.getCellValueUsingColName("ScenarioType", Integer.parseInt(rowNoGbl));
			if (strScnType.equals("Positive")) {
				// Verify the success message displayed after upload
				objFileSub.verifyUploadFileSuccessUploadText(driver, test, strFilePath, extent, rowNoGbl, date1);
			} else if (strScnType.equals("Negative")) {
				strExpErrMsg = objExcelUtils.getCellValueUsingColName("ErrorMessage", Integer.parseInt(rowNoGbl));
				// Verify the error message displayed after upload
				objFileSub.verifyUploadFileTypeMissing(driver, test, strFilePath, extent, rowNoGbl, date1,
						strExpErrMsg);
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify File is Rejected when File Type is Missing");
		}
	}

	@Then("^Verify File is Rejected when Reference Date is Missing$")
	public void Verify_File_is_Rejected_when_Reference_Date_Missing() throws Throwable {
		try {
			String strScnType = objExcelUtils.getCellValueUsingColName("ScenarioType", Integer.parseInt(rowNoGbl));
			if (strScnType.equals("Positive")) {
				// Verify the success message displayed after upload
				objFileSub.verifyUploadFileSuccessUploadText(driver, test, strFilePath, extent, rowNoGbl, date1);
			} else if (strScnType.equals("Negative")) {
				strExpErrMsg = objExcelUtils.getCellValueUsingColName("ErrorMessage", Integer.parseInt(rowNoGbl));
				// Verify the error message displayed after upload
				objFileSub.verifyUploadReferenceDateMissing(driver, test, strFilePath, extent, rowNoGbl, date1,
						strExpErrMsg);
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify File is Rejected when Reference Date is Missing");
		}
	}

	@Then("^Verify File is Rejected when Data Collection and Reporting Cycle is Missing$")
	public void Verify_File_is_Rejected_when_Data_Collection_and_Reporting_Cycle_is_Missing() throws Throwable {
		try {
			String strScnType = objExcelUtils.getCellValueUsingColName("ScenarioType", Integer.parseInt(rowNoGbl));
			if (strScnType.equals("Positive")) {
				// Verify the success message displayed after upload
				objFileSub.verifyUploadFileSuccessUploadText(driver, test, strFilePath, extent, rowNoGbl, date1);
			} else if (strScnType.equals("Negative")) {
				strExpErrMsg = objExcelUtils.getCellValueUsingColName("ErrorMessage", Integer.parseInt(rowNoGbl));
				// Verify the error message displayed after upload
				objFileSub.verifyReportingCycleMissing(driver, test, strFilePath, extent, rowNoGbl, date1, objDBUtil);
				objFileSub.verifyDataCollectionMissing(driver, test, strFilePath, extent, rowNoGbl, date1, objDBUtil);
				objFileSub.closeBrowserAndReport(driver, test, extent, rowNoGbl, date1);
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println(
					"Step Failed : Verify File is Rejected when Data Collection and Reporting Cycle is Missing");
		}
	}

	@Then("^Verify File is Accepted when Data Collection is Present$")
	public void Verify_File_is_Accepted_when_Data_Collection_is_Present() throws Throwable {
		try {
			String strScnType = objExcelUtils.getCellValueUsingColName("ScenarioType", Integer.parseInt(rowNoGbl));
			if (strScnType.equals("Positive")) {
				// Verify the success message displayed after upload
				objFileSub.verifyDataCollectionPresent(driver, test, strFilePath, extent, rowNoGbl, date1, objDBUtil);
				objFileSub.closeBrowserAndReport(driver, test, extent, rowNoGbl, date1);
			} else if (strScnType.equals("Negative")) {
				strExpErrMsg = objExcelUtils.getCellValueUsingColName("ErrorMessage", Integer.parseInt(rowNoGbl));
				// Verify the error message displayed after upload
				objFileSub.verifyDataCollectionPresent(driver, test, strFilePath, extent, rowNoGbl, date1, objDBUtil);
				objFileSub.closeBrowserAndReport(driver, test, extent, rowNoGbl, date1);
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify File is Accepted when Data Collection is Present");
		}
	}

	@When("^I Select a File$")
	public void i_Select_a_File() throws Throwable {
		try {
			strFilePath = objExcelUtils.getCellValueUsingColName("FilePath", Integer.parseInt(rowNoGbl));

			// Upload a File to Casper
			// objFileSub.uploadFileDragNDrop(driver,rowNoGbl,test,strFilePath);
			objFileSub.selectFile(driver, rowNoGbl, test, strFilePath);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : I Select a File");

		}
	}

	@Then("^Verify File Selected on Upload Page$")
	public void Verify_File_Selected_on_Upload_Page() throws Throwable {
		try {
			String strScnType = objExcelUtils.getCellValueUsingColName("ScenarioType", Integer.parseInt(rowNoGbl));
			String strExpFileName = objExcelUtils.getCellValueUsingColName("FileName", Integer.parseInt(rowNoGbl));
			if (strScnType.equals("Positive")) {
				// Verify the success message displayed after upload
				objFileSub.verifySelectFileSuccessUploadPage(driver, test, strFilePath, extent, rowNoGbl, date1,
						strExpFileName);
			} else if (strScnType.equals("Negative")) {
				strExpErrMsg = objExcelUtils.getCellValueUsingColName("ErrorMessage", Integer.parseInt(rowNoGbl));
				// Verify the error message displayed after upload
				objFileSub.verifyUploadFileErrorMessageDialog(driver, test, strFilePath, extent, rowNoGbl, date1,
						strExpErrMsg);
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify File Selected on Upload Page");
		}
	}

	// Implementation of when Feature step for sequence number
	@When("^Upload the file one to Ninety Nine \"([^\"]*)\"$")
	public void upload_the_file_one_to_Ninety_Nine(String rowNo) throws Throwable {
		try {
			strFilePath = objExcelUtils.getCellValueUsingColName("FilePath", Integer.parseInt(rowNoGbl));

			// Upload a File to Casper
			objFileSub.uploadZipFile(driver, rowNo, test, strFilePath);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Upload the file one to Ninety Nine.*");
		}

	}

	// Implementation of Then Feature step for sequence number

	@Then("^File Upload should be successful for valid sequence$")
	public void file_Upload_should_be_successful_for_valid_sequence() throws Throwable {
		try {
			// Verify the error message displayed after upload
			objFileSub.verifyUploadSequenceCheck(driver, test, strFilePath, extent, rowNoGbl, date1, "");
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : File Upload should be successful for valid sequence");
		}
	}

	/*
	 * @Then("^File Upload should be Successful$") public void
	 * file_Upload_should_be_Successful() throws Throwable { try { //Verify the
	 * error message displayed after upload
	 * objFileSub.verifyUploadFileSuccess(driver,test,strFilePath,extent,
	 * rowNoGbl,date1); } catch (Exception e) {
	 * System.out.println("In catch block " + e ); } }
	 */

	@Then("^File Upload should be Successful$")
	public void file_Upload_should_be_Successful() throws Throwable {
		try {
			// Verify the error message displayed after upload
			objFileSub.verifyUploadFileSuccessGreenTick(driver, test, strFilePath, extent, rowNoGbl, date1);
			// The zip file has already been processed
			// The zip file is empty
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : File Upload should be Successful");
		}
	}

	// Implementation of When Feature step for valid and invalid file upload
	@When("^Upload the valid and invalid file in zip file \"([^\"]*)\"$")
	public void upload_the_valid_and_invalid_file_in_zip_file(String rowNo) throws Throwable {
		try {
			strFilePath = objExcelUtils.getCellValueUsingColName("FilePath", Integer.parseInt(rowNoGbl));

			// Upload a File to Casper
			objFileSub.uploadZipFile(driver, rowNo, test, strFilePath);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Upload the valid and invalid file in zip file.*");
		}
	}

	// Implementation of Then Feature step for valid and invalid file upload
	@Then("^File is accepted or rejected$")
	public void file_is_accepted_or_rejected() throws Throwable {
		try {

			NoOfValidFile = objExcelUtils.getCellValueUsingColName("NoOfValidFile", Integer.parseInt(rowNoGbl));
			NoofInvalidFile = objExcelUtils.getCellValueUsingColName("NoOfInvalidFile", Integer.parseInt(rowNoGbl));
			// Verify valid file and invalid file upload with relevent message
			objFileSub.verifyValidandInvalidFileIpload(driver, test, strFilePath, extent, rowNoGbl, date1,
					NoOfValidFile, NoofInvalidFile);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : File is accepted or rejected");
		}
	}

	@When("^A file is uploaded \"([^\"]*)\"$")
	public void a_file_is_uploaded(String rowNo) throws Throwable {
		try {
			strFilePath = objExcelUtils.getCellValueUsingColName("FilePath", Integer.parseInt(rowNoGbl));

			// Upload a File to Casper
			objFileSub.uploadZipFile(driver, rowNo, test, strFilePath);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : A file is uploaded.*");
		}
	}

	@Then("^Verify the maximum length of filename components$")
	public void verify_the_maximum_length_of_filename_components() throws Throwable {
		try {

			// Verify each filename component length and error message
			objFileSub.verifyFileComponentsLength(driver, test, strFilePath, extent, rowNoGbl, date1);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify the maximum length of filename components");
		}
	}

	@Then("^Mandatory filename components identify same submission$")
	public void mandatory_filename_components_identify_same_submission() throws Throwable {
		try {

			// Verify each filename component length and error message
			objFileSub.verifyMandatoryFileNameComponentsSameSubmission(driver, test, strFilePath, extent, rowNoGbl,
					date1);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Mandatory filename components identify same submission");
		}
	}

	@Then("^Multiple File Upload should be Successful$")
	public void multiple_File_Upload_should_be_Successful() throws Throwable {
		try {
			// Verify the error message displayed after upload
			NoOfFiles = objExcelUtils.getIntCellValueUsingColName("NoOfFiles", Integer.parseInt(rowNoGbl));
			objFileSub.verifyUploadPassMultipleFile(driver, test, strFilePath, extent, rowNoGbl, date1, NoOfFiles);

		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Multiple File Upload should be Successful");
		}

	}

	@Then("^Verify the Reporting Cycle as Started$")
	public void verify_the_Reporting_Cycle_as_Started() throws Throwable {
		try {
			strFilePath = objExcelUtils.getCellValueUsingColName("FilePath", Integer.parseInt(rowNoGbl));

			// Upload a File to Casper
			objFileSub.verifyReportingCycleStatusAsStarted(driver, test, strFilePath, extent, rowNoGbl, date1,
					objDBUtil);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify the Reporting Cycle as Started");
		}
	}

	@Then("^Verify the Reporting Cycle as Closed$")
	public void verify_the_Reporting_Cycle_as_Closed() throws Throwable {
		try {
			strFilePath = objExcelUtils.getCellValueUsingColName("FilePath", Integer.parseInt(rowNoGbl));

			// Upload a File to Casper
			objFileSub.verifyReportingCycleStatusAsClosed(driver, test, strFilePath, extent, rowNoGbl, date1,
					objDBUtil);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify the Reporting Cycle as Closed");
		}
	}

	@Then("^Verify the Reporting Cycle as In Preparation$")
	public void verify_the_Reporting_Cycle_as_In_Preparation() throws Throwable {
		try {
			strFilePath = objExcelUtils.getCellValueUsingColName("FilePath", Integer.parseInt(rowNoGbl));

			// Upload a File to Casper
			objFileSub.verifyReportingCycleStatusAsInPrep(driver, test, strFilePath, extent, rowNoGbl, date1,
					objDBUtil);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify the Reporting Cycle as In Preparation");
		}
	}

	@Then("^Verify the Filetype and Reporting Cycle$")
	public void verify_the_Filetype_and_Reporting_Cycle() throws Throwable {
		try {
			strFilePath = objExcelUtils.getCellValueUsingColName("FilePath", Integer.parseInt(rowNoGbl));

			// Upload a File to Casper
			objFileSub.verifyFileTypeAndReportingCycle(driver, test, strFilePath, extent, rowNoGbl, date1, objDBUtil);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify the Filetype and Reporting Cycle");
		}
	}

	@Then("^Verify the Filetype and Reporting Cycle As In Preparation$")
	public void verify_the_Filetype_and_Reporting_Cycle_As_In_Preparation() throws Throwable {

		try {
			strFilePath = objExcelUtils.getCellValueUsingColName("FilePath", Integer.parseInt(rowNoGbl));

			// Upload a File to Casper
			objFileSub.verifyFileTypeAndReportingCycleAsInPrep(driver, test, strFilePath, extent, rowNoGbl, date1,
					objDBUtil);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify the Filetype and Reporting Cycle As In Preparation");
		}
	}

	@Then("^Verify the File Status and Reporting Cycle As Closed$")
	public void verify_the_File_Status_and_Reporting_Cycle_As_Closed() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		try {
			strFilePath = objExcelUtils.getCellValueUsingColName("FilePath", Integer.parseInt(rowNoGbl));

			// Upload a File to Casper
			objFileSub.verifyReportingCycleStatusClosed(driver, test, strFilePath, extent, rowNoGbl, date1, objDBUtil);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify the File Status and Reporting Cycle As Closed");
		}
	}

	@When("^Add Entity Group$")
	public void add_Entity_Group() throws Throwable {
		try {
			objEntityGroup.EntityGroup(driver, test, extent);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Add Entity Group");
		}
	}

	@Then("^Validate Entity Groups$")
	public void validate_Entity_Groups() throws Throwable {
		try {
			String strExpErrorMsg = objExcelUtils.getCellValueUsingColName("ErrorMessage", Integer.parseInt(rowNoGbl));
			EntityGroupName = objExcelUtils.getCellValueUsingColName("EntityGroupName", Integer.parseInt(rowNoGbl));
			EntityGroupDesc = objExcelUtils.getCellValueUsingColName("EntityGroupDesc", Integer.parseInt(rowNoGbl));
			objEntityGroup.addEntityGroup(driver, test, extent, rowNoGbl, date1, EntityGroupName, EntityGroupDesc,
					strExpErrorMsg, objDBUtil);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Validate Entity Groups");
		}
	}

	@When("^Add Duplicate Entity Group$")
	public void add_Duplicate_Entity_Group() throws Throwable {
		try {
			objEntityGroup.EntityGroup(driver, test, extent);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Add Duplicate Entity Group");
		}
	}

	@Then("^Validate Duplicate Entity Groups$")
	public void validate_Duplicate_Entity_Groups() throws Throwable {
		try {
			String strExpErrorMsg = objExcelUtils.getCellValueUsingColName("ErrorMessage", Integer.parseInt(rowNoGbl));
			EntityGroupName = objExcelUtils.getCellValueUsingColName("EntityGroupName", Integer.parseInt(rowNoGbl));
			EntityGroupDesc = objExcelUtils.getCellValueUsingColName("EntityGroupDesc", Integer.parseInt(rowNoGbl));
			objEntityGroup.duplicateEntityGroup(driver, test, extent, rowNoGbl, date1, EntityGroupName, EntityGroupDesc,
					strExpErrorMsg, objDBUtil);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Validate Duplicate Entity Groups");
		}

	}

	@When("^Edit Entity Group$")
	public void edit_Entity_Group() throws Throwable {
		try {
			objEntityGroup.EntityGroup(driver, test, extent);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Edit Entity Group");
		}
	}

	@Then("^Validate Entity Group update$")
	public void validate_Entity_Group_update() throws Throwable {
		try {
			String strExpErrorMsg = objExcelUtils.getCellValueUsingColName("ErrorMessage", Integer.parseInt(rowNoGbl));
			EntityGroupDesc = objExcelUtils.getCellValueUsingColName("EntityGroupDesc", Integer.parseInt(rowNoGbl));
			objEntityGroup.editEntityGroup(driver, test, extent, rowNoGbl, date1, EntityGroupName, EntityGroupDesc,
					strExpErrorMsg, objDBUtil);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Validate Entity Group update");
		}
	}

	@When("^Delete Entity Group$")
	public void delete_Entity_Group() throws Throwable {
		try {
			objEntityGroup.EntityGroup(driver, test, extent);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Delete Entity Group");
		}
	}

	@Then("^Validate Entity Group deletion$")
	public void validate_Entity_Group_deletion() throws Throwable {
		try {
			String strExpErrorMsg = objExcelUtils.getCellValueUsingColName("ErrorMessage", Integer.parseInt(rowNoGbl));
			objEntityGroup.deleteEntityGroup(driver, test, extent, rowNoGbl, date1, strExpErrorMsg, objDBUtil);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Validate Entity Group deletion");
		}
	}

	@When("^Adding a Reporting Cycle$")
	public void adding_a_Reporting_Cycle() throws Throwable {
		try {
			objDataDef.ReportingCycle(driver, test, extent);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Adding a Reporting Cycle");
		}
	}

	@Then("^Verify the Added Successfully Message displayed after adding a reporting cycle$")
	public void verify_the_Added_Successfully_Message_displayed_after_adding_a_reporting_cycle() throws Throwable {
		try {

			String ReportingCycleName = objExcelUtils.getCellValueUsingColName("ReportingCycleName",
					Integer.parseInt(rowNoGbl));
			String StartDate = objExcelUtils.getCellValueUsingColName("StartDate", Integer.parseInt(rowNoGbl));
			String EndDate = objExcelUtils.getCellValueUsingColName("EndDate", Integer.parseInt(rowNoGbl));
			String Description = objExcelUtils.getCellValueUsingColName("Description", Integer.parseInt(rowNoGbl));
			String strExpErrMsg = objExcelUtils.getCellValueUsingColName("ExpErrMsg", Integer.parseInt(rowNoGbl));
			// Adding a Reporting Cycle
			objDataDef.verifyReportingCycleAdd(driver, test, strFilePath, ReportingCycleName, StartDate, EndDate,
					Description, extent, rowNoGbl, date1, strExpErrMsg);

		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println(
					"Step Failed : Verify the Added Successfully Message displayed after adding a reporting cycle");
			throw new PendingException();
		}
	}

	@When("^Adding an Existing Reporting Cycle$")
	public void adding_An_Existing_Reporting_Cycle() throws Throwable {
		try {
			objDataDef.ReportingCycle(driver, test, extent);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Adding an Existing Reporting Cycle");
		}
	}

	@Then("^Verify Add Message displayed$")
	public void verify_Add_Message_displayed() throws Throwable {
		try {

			String ReportingCycleName = objExcelUtils.getCellValueUsingColName("ReportingCycleName",
					Integer.parseInt(rowNoGbl));
			String StartDate = objExcelUtils.getCellValueUsingColName("StartDate", Integer.parseInt(rowNoGbl));
			String EndDate = objExcelUtils.getCellValueUsingColName("EndDate", Integer.parseInt(rowNoGbl));
			String Description = objExcelUtils.getCellValueUsingColName("Description", Integer.parseInt(rowNoGbl));
			String strExpErrMsg = objExcelUtils.getCellValueUsingColName("ExpErrMsg", Integer.parseInt(rowNoGbl));
			// Adding a Reporting Cycle
			objDataDef.verifyReportingCycleDuplicate(driver, test, strFilePath, objDBUtil, ReportingCycleName,
					StartDate, EndDate, Description, extent, rowNoGbl, date1, strExpErrMsg);

		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify Add Message displayed");
			throw new PendingException();
		}
	}

	@When("^Editing a Reporting Cycle$")
	public void editing_a_Reporting_Cycle() throws Throwable {
		try {
			objDataDef.ReportingCycle(driver, test, extent);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Editing a Reporting Cycle");
		}
	}

	@Then("^Verify Edit Message displayed$")
	public void verify_Edit_Message_displayed() throws Throwable {
		try {
			String ReportingCycleName = objExcelUtils.getCellValueUsingColName("ReportingCycleName",
					Integer.parseInt(rowNoGbl));
			String StartDate = objExcelUtils.getCellValueUsingColName("StartDate", Integer.parseInt(rowNoGbl));
			String EndDate = objExcelUtils.getCellValueUsingColName("EndDate", Integer.parseInt(rowNoGbl));
			String Description = objExcelUtils.getCellValueUsingColName("Description", Integer.parseInt(rowNoGbl));
			String strExpErrMsg = objExcelUtils.getCellValueUsingColName("ExpErrMsg", Integer.parseInt(rowNoGbl));
			// Adding a Reporting Cycle
			objDataDef.verifyReportingCycleEdit(driver, test, strFilePath, objDBUtil, ReportingCycleName, StartDate,
					EndDate, Description, extent, rowNoGbl, date1, strExpErrMsg);

		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify Edit Message displayed");
			throw new PendingException();
		}
	}

	// Zip file upload
	@When("^Upload a Zip File$")
	public void upload_a_Zip_File() throws Throwable {
		try {
			strFilePath = objExcelUtils.getCellValueUsingColName("FilePath", Integer.parseInt(rowNoGbl));
			String strDCName = objExcelUtils.getCellValueUsingColName("DCName", Integer.parseInt(rowNoGbl));

			objFileSub.navigateSubmissionList(driver, rowNoGbl, test, strDCName);
			// Upload a File to Casper
			objFileSub.uploadZipFile(driver, rowNoGbl, test, strFilePath);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Upload a Zip File");
		}
	}

	@Then("^Validate success message$")
	public void validate_success_message() throws Throwable {
		try {

			// strFilePath = objExcelUtils.getCellValueUsingColName("FilePath",
			// Integer.parseInt(rowNoGbl));

			String strExpMsg = objExcelUtils.getCellValueUsingColName("ExpectedMessage", Integer.parseInt(rowNoGbl));
			String ScenarioType = objExcelUtils.getCellValueUsingColName("ScenarioType", Integer.parseInt(rowNoGbl));
			Thread.sleep(10000);
			objFileSub.verifyUploadFileDialog(driver, test, strFilePath, extent, rowNoGbl, date1, strExpMsg,
					ScenarioType);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Validate success message");
		}
	}

	@Then("^Verify success message$")
	public void verify_success_message() throws Throwable {
		try {

			// strFilePath = objExcelUtils.getCellValueUsingColName("FilePath",
			// Integer.parseInt(rowNoGbl));

			String strExpMsg = objExcelUtils.getCellValueUsingColName("ExpectedMessage", Integer.parseInt(rowNoGbl));
			Thread.sleep(1000);
			objCBF.verifyUploadFileDialog(driver, test, strFilePath, extent, rowNoGbl, date1, strExpMsg);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify success message");
		}
	}

	@Then("^Verify and click on \"([^\"]*)\" link on pop up$")
	public void verify_and_click_on_link_on_pop_up(String linkText) throws Throwable {
		try {
			objCBF.verifyLinkOnPopUp(driver, test, extent, rowNoGbl, date1, linkText);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_and_click_on_link_on_pop_up");
		}
	}

	@Then("^Click on \"([^\"]*)\" link on pop up$")
	public void click_on_link_on_pop_up(String linkText) throws Throwable {
		try {
			objCBF.ClickLinkOnPopUp(driver, test, extent, rowNoGbl, date1, linkText);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : click_on_link_on_pop_up");
		}
	}

	@When("^Navigate to Entity group$")
	public void navigate_to_Entity_group() throws Throwable {
		try {
			String StrDataCollName = objExcelUtils.getCellValueUsingColName("DCName", Integer.parseInt(rowNoGbl));
			objDataDef.NavigateToEntityGroups(driver, test, extent, StrDataCollName);
		} catch (IOException e) {
			System.out.println("In catch block navigate entity");
			System.out.println("Step Failed : Navigate to Entity group");
		}

	}

	@Then("^Add and Validate Entity Groups$")
	public void add_and_Validate_Entity_Groups() throws Throwable {
		String strEntityGrpName = objExcelUtils.getCellValueUsingColName("EntityGroupName", Integer.parseInt(rowNoGbl));
		String strEntityGrpDesc = objExcelUtils.getCellValueUsingColName("EntityGroupDescription",
				Integer.parseInt(rowNoGbl));
		String strExpMessage = objExcelUtils.getCellValueUsingColName("ExpectedMessage", Integer.parseInt(rowNoGbl));
		try {
			objDataDef.addEntityGroup(driver, test, extent, rowNoGbl, date1, strEntityGrpName, strEntityGrpDesc,
					strExpMessage, objDBUtil);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Add and Validate Entity Groups");
		}

	}

	@Then("^Edit and Verify Entity Groups$")
	public void edit_and_Verify_Entity_Groups() throws Throwable {
		String strEntityGrpName = objExcelUtils.getCellValueUsingColName("EntityGroupName", Integer.parseInt(rowNoGbl));
		String strEntityGrpDesc = objExcelUtils.getCellValueUsingColName("EntityGroupDescription",
				Integer.parseInt(rowNoGbl));
		String strExpMessage = objExcelUtils.getCellValueUsingColName("ExpectedMessage", Integer.parseInt(rowNoGbl));

		try {
			objDataDef.editEntityGroup(driver, test, extent, rowNoGbl, date1, strEntityGrpName, strEntityGrpDesc,
					strExpMessage, objDBUtil);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Edit and Verify Entity Groups");
		}

	}

	@After
	public void tearDown() throws InterruptedException {
		System.out.println("Closing The Browser");

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try {
			driver.findElement(By.xpath("//button[contains(text(),'Cancel')]")).click();
			Thread.sleep(1000);
			System.out.println("Clicked on Cancel button");
		} catch (Exception Ex) {
			System.out.println("Cancel button not displayed");
		}
		try {
			driver.findElement(By.xpath("//button[contains(text(),'Close')]")).click();
			Thread.sleep(1000);
			System.out.println("Clicked on Close button");

		} catch (Exception Ex) {
			System.out.println("Close button not displayed");
		} finally {
			try {
				RecentChanges_CommonFunctions.finallyBlock_TearDown(driver);
			} catch (Exception Ex) {
				System.out.println("Logout link not displayed closing the browser to end the test");
				Ex.printStackTrace();

				driver.quit();
			}
			driver.quit();
			System.out.println("===========INDIVIDUAL TEST EXECUTION ENDED=========");
		}
	}

	@Then("^Verify Max length$")
	public void verify_Max_length() throws Throwable {

		objEntityGroup.verifyMaxLength(driver, "wertyhgnyhuhuytrfdrs", "objAddEntityGroupName");
	}

	@Then("^Verify tabs on home screen \"([^\"]*)\"$")
	public void verify_tabs_on_home_screen(String Tab1) throws Throwable {
		try {
			objCBF.VerifyLinkExist(driver, test, extent, rowNoGbl, date1, Tab1);
			System.out.println(Tab1 + "tab verified");
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify tabs on home screen " + Tab1);
		}
	}

	@When("^Click on \"([^\"]*)\" tab$")
	public void click_on_menu(String menu) throws Throwable {
		try {
			objCBF.clickOnLink(driver, test, extent, rowNoGbl, date1, menu);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : click_on_menu");
		}
	}

	@Then("^Verify \"([^\"]*)\" button exists$")
	public void verify_button_exists(String btnName) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);
			objCBF.VerifyButtonExist(driver, test, extent, rowNoGbl, date1, btnName);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_button_exists");
		}
	}

	@Then("^Verify \"([^\"]*)\" Action for table record$")
	public void verify_Action_for_table_record(String actionName) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			objCBF.VerifyAvailableActions(driver, test, extent, rowNoGbl, date1, actionName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_Action_for_table_record");
		}
	}

	@Then("^Verify the \"([^\"]*)\" screen displayed$")
	public void screen_displayed(String screenName) throws Throwable {
		try {
			objCBF.verifyScreenName(driver, test, extent, rowNoGbl, date1, screenName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : screen_displayed");
		}
	}

	@Then("^Verify record count in table using column \"([^\"]*)\"$")
	public void verify_the_record_count(String colName) throws Throwable {
		try {
			objCBF.verifyNumberOfRecords(driver, test, extent, rowNoGbl, date1, colName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_the_record_count");
		}
	}

	@Then("^Verify data loaded in table for column \"([^\"]*)\"$")
	public void verify_data_loaded_in_table_for_column(String colName) throws Throwable {
		try {
			objCBF.verifyDataInTable(driver, test, extent, rowNoGbl, date1, colName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_data_loaded_in_table_for_column");
		}
	}

	@When("^Click on \"([^\"]*)\" action$")
	public void click_on_action(String actionName) throws Throwable {
		try {
			objCBF.clickOnAction(driver, test, extent, rowNoGbl, date1, actionName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : click_on_action");
		}
	}

	@When("^Click on \"([^\"]*)\" button$")
	public void click_on_button(String buttonName) throws Throwable {
		try {
			objCBF.clickOnButton(driver, test, extent, rowNoGbl, date1, buttonName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : click_on_button");
		}
	}

	@Then("^Verify left menu \"([^\"]*)\" get displayed$")
	public void left_menu_get_displayed(String leftMenuName) throws Throwable {
		try {
			objCBF.verifyLeftMenu(driver, test, extent, rowNoGbl, date1, leftMenuName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : left_menu_get_displayed");
		}
	}

	@Then("^Verify \"([^\"]*)\" Action for Submission List table record$")
	public void verify_Action_for_Submission_List_table_record(String actionName) throws Throwable {
		try {
			objCBF.VerifyAvailableActionsSubList(driver, test, extent, rowNoGbl, date1, actionName);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_Action_for_Submission_List_table_record");
		}
	}

	@Then("^Verify the \"([^\"]*)\" pop up displayed$")
	public void verify_pop_up_displayed(String popUpName) throws Throwable {
		try {
			objCBF.verifyPopUpName(driver, test, extent, rowNoGbl, date1, popUpName);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_pop_up_displayed");
		}
	}

	@Then("^Check Calculations for \"([^\"]*)\" field$")
	public void check_Calculations_for(String fieldName) throws Throwable {
		try {
			objCBF.checkCalculations(driver, test, extent, rowNoGbl, date1, fieldName);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : check_Calculations_for");
		}
	}

	@When("^Search record with filter value$")
	public void search_record_with_filter_value() throws Throwable {
		try {
			objCBF.searchForRecordsWithFilterValue(driver, test, extent, rowNoGbl, date1);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : search_record_with_filter_value");
		}
	}

	@Then("^Verify module \"([^\"]*)\" action for \"([^\"]*)\"$")
	public void verify_module_Add_New_Version_action(String action, String columnName) throws Throwable {
		try {
			objCBF.verifyAddNewVersionAction(driver, test, extent, rowNoGbl, date1, action, columnName);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_module_Add_New_Version_action");
		}
	}

	@When("^Click on \"([^\"]*)\" element$")
	public void click_on_element(String elementToClick) throws Throwable {
		try {
			objCBF.clickOnElement(driver, test, extent, rowNoGbl, date1, elementToClick);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : click_on_element");
		}
	}

	@Then("^Verify \"([^\"]*)\" checkbox is checked$")
	public void verify_checkbox_is_checked(String permissionName) throws Throwable {
		try {
			objCBF.checkCheckbox(driver, test, extent, rowNoGbl, date1, permissionName);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_checkbox_is_checked");
		}
	}

	@When("^Navigate to \"([^\"]*)\" screen$")
	public void navigate_to_screen(String lnkText) throws Throwable {
		try {
			objDataDict.NavigateToDataDictionary(driver, test, rowNoGbl, date1, extent, lnkText);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : navigate_to_screen");
		}
	}

	@When("^Enter Data Dictionary details \"([^\"]*)\"$")
	public void enter_Data_Dictionary_details(String workbookName) throws Throwable {
		try {
			String colName = "DD_Name";
			// String DDName = objCBF.generateRandomString(rowNoGbl,
			// workbookName, colName);
			String DDName = objCBF.generateSixDigitRandomNumber(rowNoGbl, workbookName, colName);
			String DDOwner = ExcelUtils.getCellValueUsingColName("DD_Owner", Integer.parseInt(rowNoGbl));
			String DDDescription = ExcelUtils.getCellValueUsingColName("DD_Description", Integer.parseInt(rowNoGbl));

			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDataDict.getLocator("objName"), DDName);
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDataDict.getLocator("objOwner"),
					DDOwner);
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDataDict.getLocator("objDesc"),
					DDDescription);

		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_Data_Dictionary_details");
		}

	}

	@When("^Edit Data Dictionary details$")
	public void edit_Data_Dictionary_details() throws Throwable {
		try {
			String DDOwner = ExcelUtils.getCellValueUsingColName("DD_Owner_Edit", Integer.parseInt(rowNoGbl));
			String DDDescription = ExcelUtils.getCellValueUsingColName("DD_Description_Edit",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDataDict.getLocator("objEditOwner"),
					DDOwner);
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDataDict.getLocator("objEditDesc"),
					DDDescription);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : edit_Data_Dictionary_details");
		}
	}

	@Then("^Verify new entry created in Data Dictionary List \"([^\"]*)\"$")
	public void verify_new_entry_created_in_data_dictionary_List(String columnName) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			if (columnName.equals("name")) {
				String DDName = ExcelUtils.getCellValueUsingColName("DD_Name", Integer.parseInt(rowNoGbl));
				objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, columnName, DDName);
			} else if (columnName.equals("owner")) {
				String DDOwner = ExcelUtils.getCellValueUsingColName("DD_Owner_Edit", Integer.parseInt(rowNoGbl));
				objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, columnName, DDOwner);

			} else if (columnName.equals("description")) {
				String DDDescription = ExcelUtils.getCellValueUsingColName("DD_Description_Edit",
						Integer.parseInt(rowNoGbl));
				objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, columnName, DDDescription);
			} else {
				test.log(Status.FAIL, "No column name given");
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_new_entry_created_in_data_dictionary_List");
		}
	}

	@When("^Search record for filter value \"([^\"]*)\"$")
	public void search_record_for_filter_value(String colName) throws Throwable {
		try {
			String DDName = ExcelUtils.getCellValueUsingColName(colName, Integer.parseInt(rowNoGbl));
			objCBF.searchForRecordsForFilterValue(driver, test, extent, rowNoGbl, date1, DDName);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : search_record_for_filter_value");
		}
	}

	@When("^Search record for import entity$")
	public void search_record_for_import_entity() throws Throwable {
		try {
			String recordName = ExcelUtils.getCellValueUsingColName("COLLECTIONUNIQUEIDENTIFIER",
					Integer.parseInt(rowNoGbl));
			objCBF.searchForRecordsForFilterValue(driver, test, extent, rowNoGbl, date1, recordName);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : search_record_for_import_entity");
		}
	}

	@When("^Enter DataCollection details \"([^\"]*)\"$")
	public void enter_DataCollection_details(String workbookName) throws Throwable {
		try {

			String colName = "DC_Code";
			// String RandomString = objCBF.generateRandomString(rowNoGbl,
			// workbookName, colName);
			String RandomString = objCBF.generateSixDigitRandomNumber(rowNoGbl, workbookName, colName);
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCCode"),
					RandomString);
			// String RandomName= objCBF.generateRandomString(rowNoGbl,
			// workbookName);
			String DCName = objExcelUtils.getCellValueUsingColName("DC_Name", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCName"),
					DCName);
			String DCDescription = objExcelUtils.getCellValueUsingColName("DC_Description", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objAddDCDescription"), DCDescription);
			String DCOwner = objExcelUtils.getCellValueUsingColName("DC_Owner", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCOwner"),
					DCOwner);
			String DCValidFrom = objExcelUtils.getDateCellValueUsingColName("DC_ValidFrom", Integer.parseInt(rowNoGbl));
			// int DCValidFrom =
			// objExcelUtils.getIntCellValueUsingColName("DC_ValidFrom",
			// Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objAddDCValidFrom"), DCValidFrom);
			String DCValidTo = objExcelUtils.getDateCellValueUsingColName("DC_ValidTo", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCValidTo"),
					DCValidTo);
			String DCIsCntryRel = objExcelUtils.getCellValueUsingColName("DC_IsCountryRelevant",
					Integer.parseInt(rowNoGbl));
			WebElement ele1 = driver.findElement(By.xpath("//select[@formcontrolname='addAccessRestriction']"));
			Select s = new Select(ele1);
			s.selectByVisibleText("Country");
			objCBF.selectCheckbox(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objAddDCIsCountryRelevantChkbox"), DCIsCntryRel);
			String DCPrimaryDDOwner = objExcelUtils.getCellValueUsingColName("DC_PrimaryDataDictionary",
					Integer.parseInt(rowNoGbl));
			objCBF.selectFirstListItem(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objAddDCPrimaryDCOwner"), DCPrimaryDDOwner);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : search_record_for_import_entity");
		}
	}

	@When("^Enter DataCollection details E2E \"([^\"]*)\"$")
	public void enter_DataCollection_details_E2E(String workbookName) throws Throwable {
		try {

			String colName = "DC_Code";
			// String RandomString = objCBF.generateRandomString(rowNoGbl,
			// workbookName, colName);
			String RandomString = objCBF.generateSixDigitRandomNumber(rowNoGbl, workbookName, colName);
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCCode"),
					RandomString);
			// String RandomName= objCBF.generateRandomString(rowNoGbl,
			// workbookName);
			String DCName = objExcelUtils.getCellValueUsingColName("DC_Name", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCName"),
					DCName);
			String DCDescription = objExcelUtils.getCellValueUsingColName("DC_Description", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objAddDCDescription"), DCDescription);
			String DCOwner = objExcelUtils.getCellValueUsingColName("DC_Owner", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCOwner"),
					DCOwner);
			String DCValidFrom = objExcelUtils.getDateCellValueUsingColName("DC_ValidFrom", Integer.parseInt(rowNoGbl));
			// int DCValidFrom =
			// objExcelUtils.getIntCellValueUsingColName("DC_ValidFrom",
			// Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objAddDCValidFrom"), DCValidFrom);
			String DCValidTo = objExcelUtils.getDateCellValueUsingColName("DC_ValidTo", Integer.parseInt(rowNoGbl));
			
			WebElement ele1 = driver.findElement(By.xpath("//select[@formcontrolname='addAccessRestriction']"));
			Select s = new Select(ele1);
			s.selectByVisibleText("Country");
				
			
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCValidTo"),
					DCValidTo);
			String DCIsCntryRel = objExcelUtils.getCellValueUsingColName("DC_IsCountryRelevant",
					Integer.parseInt(rowNoGbl));
			objCBF.selectCheckbox(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objAddDCIsCountryRelevantChkbox"), DCIsCntryRel);
			String DCPrimaryDDOwner = objExcelUtils.getCellValueUsingColName("DD_Name", Integer.parseInt(rowNoGbl));
			objCBF.selectListItem(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objAddDCPrimaryDCOwner"), DCPrimaryDDOwner);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_DataCollection_details_E2E");
		}
	}

	@Then("^Verify new entry created in DataCollection List \"([^\"]*)\"$")
	public void verify_new_entry_created_in_DataCollection_List(String ColName) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String colName = "DC_Code";
			String codeName = objExcelUtils.getCellValueUsingColName(colName, Integer.parseInt(rowNoGbl));
			objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName, codeName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_new_entry_created_in_DataCollection_List");
		}
	}

	@When("^Edit DataCollection details \"([^\"]*)\"$")
	public void edit_DataCollection_details(String workbookName) throws Throwable {
		try {
			String DCName = objExcelUtils.getCellValueUsingColName("DC_Name_Edit", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCName"),
					DCName);
			String DCDescription = objExcelUtils.getCellValueUsingColName("DC_Description_Edit",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objAddDCDescription"), DCDescription);
			String DCOwner = objExcelUtils.getCellValueUsingColName("DC_Owner_Edit", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCOwner"),
					DCOwner);
			String DCValidFrom = objExcelUtils.getDateCellValueUsingColName("DC_ValidFrom_Edit",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objAddDCValidFrom"), DCValidFrom);
			String DCValidTo = objExcelUtils.getDateCellValueUsingColName("DC_ValidTo_Edit",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCValidTo"),
					DCValidTo);
			String DCIsCntryRel = objExcelUtils.getCellValueUsingColName("DC_IsCountryRelevant_Edit",
					Integer.parseInt(rowNoGbl));
			objCBF.selectCheckbox(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objAddDCIsCountryRelevantChkbox"), DCIsCntryRel);
			String DCPrimaryDDOwner = objExcelUtils.getCellValueUsingColName("DC_PrimaryDataDictionary_Edit",
					Integer.parseInt(rowNoGbl));
			objCBF.selectFirstListItem(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objAddDCPrimaryDCOwner"), DCPrimaryDDOwner);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : edit_DataCollection_details");
		}
	}

	@When("^Enter Entities Details \"([^\"]*)\"$")
	public void enter_Entities_Details(String workbookName) throws Throwable {
		String ENT_CollUniqueID = "ENT_CollUniqueID";
		String RandomString = objCBF.generateRandomString(rowNoGbl, workbookName, ENT_CollUniqueID);
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objRepEntityColUniqueId"), RandomString);
		String ReportingEntityName = objExcelUtils.getCellValueUsingColName("ENT_NAME", Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objRepEntityName"),
				ReportingEntityName);
		String ReportingEntityType = objExcelUtils.getCellValueUsingColName("ENT_EntityType",
				Integer.parseInt(rowNoGbl));
		objCBF.selectFirstListItem(driver, test, extent, rowNoGbl, date1,
				objPageReadDefiniton.getLocator("objRepEntityType"), ReportingEntityType);
		String StartDate = objExcelUtils.getCellValueUsingColName("ENT_StartDate", Integer.parseInt(rowNoGbl));
		// int DCValidFrom =
		// objExcelUtils.getIntCellValueUsingColName("DC_ValidFrom",
		// Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objRepEntityStartDate"), StartDate);
		String EndDate = objExcelUtils.getCellValueUsingColName("ENT_EndDate", Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objRepEntityEndDate"),
				EndDate);
		String attrVal1 = objExcelUtils.getCellValueUsingColName("ATTR1_Value", Integer.parseInt(rowNoGbl));
		String repCode1 = objExcelUtils.getCellValueUsingColName("ATTR1_IsRepCode", Integer.parseInt(rowNoGbl));
		String validFrom1 = objExcelUtils.getDateCellValueUsingColName("ATTR1_ValidFrom", Integer.parseInt(rowNoGbl));
		String validTo1 = objExcelUtils.getDateCellValueUsingColName("ATTR1_ValidTo", Integer.parseInt(rowNoGbl));
		objDataDef.EnterAttributeDetails(driver, ReportingEntityType, test, extent, StartDate, "1", attrVal1, repCode1,
				validFrom1, validTo1);
		String attrVal2 = objExcelUtils.getCellValueUsingColName("ATTR2_Value", Integer.parseInt(rowNoGbl));
		String repCode2 = objExcelUtils.getCellValueUsingColName("ATTR2_IsRepCode", Integer.parseInt(rowNoGbl));
		String validFrom2 = objExcelUtils.getDateCellValueUsingColName("ATTR2_ValidFrom", Integer.parseInt(rowNoGbl));
		String validTo2 = objExcelUtils.getDateCellValueUsingColName("ATTR2_ValidTo", Integer.parseInt(rowNoGbl));
		objDataDef.EnterAttributeDetails(driver, ReportingEntityType, test, extent, StartDate, "3", attrVal2, repCode2,
				validFrom2, validTo2);
	}

	@When("^Enter Entity Details \"([^\"]*)\"$")
	public void enter_Entity_Details(String workbookName) throws Throwable {
		String ENT_CollUniqueID = "ENT_CollUniqueID";
		String RandomString = objCBF.generateRandomString(rowNoGbl, workbookName, ENT_CollUniqueID);
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objRepEntityColUniqueId"), RandomString);
		String ReportingEntityName = objExcelUtils.getCellValueUsingColName("ENT_NAME", Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objRepEntityName"),
				ReportingEntityName);
		String ReportingEntityType = objExcelUtils.getCellValueUsingColName("ENT_EntityType",
				Integer.parseInt(rowNoGbl));
		objCBF.selectFirstListItem(driver, test, extent, rowNoGbl, date1,
				objPageReadDefiniton.getLocator("objRepEntityType"), ReportingEntityType);
		String StartDate = objExcelUtils.getCellValueUsingColName("ENT_StartDate", Integer.parseInt(rowNoGbl));
		// int DCValidFrom =
		// objExcelUtils.getIntCellValueUsingColName("DC_ValidFrom",
		// Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objRepEntityStartDate"), StartDate);
		String EndDate = objExcelUtils.getCellValueUsingColName("ENT_EndDate", Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objRepEntityEndDate"),
				EndDate);
		String attrVal1 = objExcelUtils.getCellValueUsingColName("ATTR1_Value", Integer.parseInt(rowNoGbl));
		String repCode1 = objExcelUtils.getCellValueUsingColName("ATTR1_IsRepCode", Integer.parseInt(rowNoGbl));
		String validFrom1 = objExcelUtils.getDateCellValueUsingColName("ATTR1_ValidFrom", Integer.parseInt(rowNoGbl));
		String validTo1 = objExcelUtils.getDateCellValueUsingColName("ATTR1_ValidTo", Integer.parseInt(rowNoGbl));
		objDataDef.EnterAttributeDetails(driver, ReportingEntityType, test, extent, StartDate, "1", attrVal1, repCode1,
				validFrom1, validTo1);
	}

	@When("^Enter Entities Details for E2E \"([^\"]*)\"$")
	public void enter_Entities_Details_E2E(String workbookName) throws Throwable {
		String ENT_CollUniqueID = "ENT_CollUniqueID";
		String RandomString = objCBF.generateRandomString(rowNoGbl, workbookName, ENT_CollUniqueID);
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objRepEntityColUniqueId"), RandomString);
		String ReportingEntityName = objExcelUtils.getCellValueUsingColName("ENT_NAME", Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objRepEntityName"),
				ReportingEntityName);
		String ReportingEntityType = objExcelUtils.getCellValueUsingColName("ENT_EntityType",
				Integer.parseInt(rowNoGbl));
		objCBF.selectFirstListItem(driver, test, extent, rowNoGbl, date1,
				objPageReadDefiniton.getLocator("objRepEntityType"), ReportingEntityType);
		String StartDate = objExcelUtils.getCellValueUsingColName("ENT_StartDate", Integer.parseInt(rowNoGbl));
		// int DCValidFrom =
		// objExcelUtils.getIntCellValueUsingColName("DC_ValidFrom",
		// Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objRepEntityStartDate"), StartDate);
		String EndDate = objExcelUtils.getCellValueUsingColName("ENT_EndDate", Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objRepEntityEndDate"),
				EndDate);
		String attrVal1 = objExcelUtils.getCellValueUsingColName("ATTR1_Value", Integer.parseInt(rowNoGbl));
		String repCode1 = objExcelUtils.getCellValueUsingColName("ATTR1_IsRepCode", Integer.parseInt(rowNoGbl));
		String validFrom1 = objExcelUtils.getDateCellValueUsingColName("ATTR1_ValidFrom", Integer.parseInt(rowNoGbl));
		String validTo1 = objExcelUtils.getDateCellValueUsingColName("ATTR1_ValidTo", Integer.parseInt(rowNoGbl));
		objDataDef.EnterAttributeDetails(driver, ReportingEntityType, test, extent, StartDate, "1", attrVal1, repCode1,
				validFrom1, validTo1);
		String attrVal2 = objExcelUtils.getCellValueUsingColName("ATTR2_Value", Integer.parseInt(rowNoGbl));
		String repCode2 = objExcelUtils.getCellValueUsingColName("ATTR2_IsRepCode", Integer.parseInt(rowNoGbl));
		String validFrom2 = objExcelUtils.getDateCellValueUsingColName("ATTR2_ValidFrom", Integer.parseInt(rowNoGbl));
		String validTo2 = objExcelUtils.getDateCellValueUsingColName("ATTR2_ValidTo", Integer.parseInt(rowNoGbl));
		objDataDef.EnterAttributeDetails(driver, ReportingEntityType, test, extent, StartDate, "2", attrVal2, repCode2,
				validFrom2, validTo2);
	}

	@Then("^Verify new entry created in Entities List \"([^\"]*)\"$")
	public void verify_new_entry_created_in_Entities_List(String ColName) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String ENT_CollUniqueID = "ENT_CollUniqueID";
			String ColUniqueID = objExcelUtils.getCellValueUsingColName(ENT_CollUniqueID, Integer.parseInt(rowNoGbl));
			objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName, ColUniqueID);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_new_entry_created_in_Entities_List");
		}
	}

	@Then("^Verify new entry created in Entities List via CSV Upload \"([^\"]*)\"$")
	public void verify_new_entry_created_in_Entities_List_via_CSV_Upload(String ColName) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String ColUniqueID = objExcelUtils.getCellValueUsingColName("COLLECTIONUNIQUEIDENTIFIER",
					Integer.parseInt(rowNoGbl));
			objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName, ColUniqueID);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_new_entry_created_in_Entities_List_via_CSV_Upload");
		}
	}

	@When("^Edit Entities details \"([^\"]*)\"$")
	public void edit_Entities_details(String workbookName) throws Throwable {
		String ReportingEntityName = objExcelUtils.getCellValueUsingColName("ENT_NAME_Edit",
				Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objRepEntityName"),
				ReportingEntityName);
		String ReportingEntityType = objExcelUtils.getCellValueUsingColName("ENT_EntityType_Edit",
				Integer.parseInt(rowNoGbl));
		objCBF.selectFirstListItem(driver, test, extent, rowNoGbl, date1,
				objPageReadDefiniton.getLocator("objRepEntityType"), ReportingEntityType);
		String StartDate = objExcelUtils.getDateCellValueUsingColName("ENT_StartDate_Edit", Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objRepEntityStartDate"), StartDate);
		String EndDate = objExcelUtils.getDateCellValueUsingColName("ENT_EndDate_Edit", Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objRepEntityEndDate"),
				EndDate);
		String attrVal1 = objExcelUtils.getCellValueUsingColName("ATTR1_Value_Edit", Integer.parseInt(rowNoGbl));
		String repCode1 = objExcelUtils.getCellValueUsingColName("ATTR1_IsRepCode_Edit", Integer.parseInt(rowNoGbl));
		String validFrom1 = objExcelUtils.getDateCellValueUsingColName("ATTR1_ValidFrom_Edit",
				Integer.parseInt(rowNoGbl));
		String validTo1 = objExcelUtils.getDateCellValueUsingColName("ATTR1_ValidTo_Edit", Integer.parseInt(rowNoGbl));
		objDataDef.EnterAttributeDetails(driver, rowNoGbl, test, extent, date1, "1", attrVal1, repCode1, validFrom1,
				validTo1);
		String attrVal2 = objExcelUtils.getCellValueUsingColName("ATTR2_Value_Edit", Integer.parseInt(rowNoGbl));
		String repCode2 = objExcelUtils.getCellValueUsingColName("ATTR2_IsRepCode_Edit", Integer.parseInt(rowNoGbl));
		String validFrom2 = objExcelUtils.getDateCellValueUsingColName("ATTR2_ValidFrom_Edit",
				Integer.parseInt(rowNoGbl));
		String validTo2 = objExcelUtils.getDateCellValueUsingColName("ATTR2_ValidTo_Edit", Integer.parseInt(rowNoGbl));
		objDataDef.EnterAttributeDetails(driver, rowNoGbl, test, extent, date1, "3", attrVal2, repCode2, validFrom2,
				validTo2);
	}

	@When("^Search DataCollection in List \"([^\"]*)\"$")
	public void search_datacollection_in_list(String ExcelColName) throws Throwable {
		try {
			String DCName = objExcelUtils.getCellValueUsingColName(ExcelColName, Integer.parseInt(rowNoGbl));
			objCBF.SearchDataCollection(driver, test, extent, rowNoGbl, date1, DCName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : search_datacollection_in_list");
		}
	}

	@When("^Enter Attribute Details \"([^\"]*)\"$")
	public void enter_Attributes_Details(String workbookName) throws Throwable {
		String attributeCode = "ATTR_Code";
		String RandomString = objCBF.generateRandomString(rowNoGbl, workbookName, attributeCode);
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objCstmEntityAttrCode"), RandomString);
		String AttributeDesc = objExcelUtils.getCellValueUsingColName("ATTR_Description", Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objCstmEntityAttrDesc"), AttributeDesc);
		String DataType = objExcelUtils.getCellValueUsingColName("ATTR_DataType", Integer.parseInt(rowNoGbl));
		objCBF.selectFirstListItem(driver, test, extent, rowNoGbl, date1,
				objPageReadDefiniton.getLocator("objCtsmEntityAttrDataType"), DataType);
		String IsIdentifier = objExcelUtils.getCellValueUsingColName("ATTR_IsIdentifier", Integer.parseInt(rowNoGbl));
		objCBF.selectCheckbox(driver, test, extent, rowNoGbl, date1,
				objPageReadDefiniton.getLocator("objCstmEntityAttrIsIdChkbox"), IsIdentifier);
		if (DataType.equalsIgnoreCase("List Box")) {
			String Entries = objExcelUtils.getCellValueUsingColName("ListBox_Entries", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objCstmEntityAttrEntries"), Entries);
			String MultiValues = objExcelUtils.getCellValueUsingColName("ListBox_MultiValues",
					Integer.parseInt(rowNoGbl));
			objCBF.selectSupportMultiValueCheckbox(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objCstmEntityAttrSuuportMultiVal"), MultiValues);
		}
	}

	@When("^Enter Attribute Details for Attribute1$")
	public void enter_Attributes_Details_for_attribute1() throws Throwable {
		try {
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objCstmEntityAttrCode"), "LEI0123");
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objCstmEntityAttrDesc"),
					"Attribute created for import entity testing");
			objCBF.selectFirstListItem(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objCtsmEntityAttrDataType"), "String");
			objCBF.selectCheckbox(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objCstmEntityAttrIsIdChkbox"), "Y");

		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Enter Attribute Details for Attribute1");
		}
	}

	@When("^Enter Attribute Details for Attribute2$")
	public void enter_Attributes_Details_for_attribute2() throws Throwable {
		try {
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objCstmEntityAttrCode"), "ISIDNO");
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objCstmEntityAttrDesc"),
					"Attribute created for import entity testing");
			objCBF.selectFirstListItem(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objCtsmEntityAttrDataType"), "String");
			objCBF.selectCheckbox(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objCstmEntityAttrIsIdChkbox"), "N");
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Enter Attribute Details for Attribute2");
		}
	}

	@Then("^Search for file and verify status in import history$")
	public void search_for_file_and_verify_status() throws Throwable {
		try {
			String colName = "status";
			String fileName = objExcelUtils.getCellValueUsingColName("CSV-File_To_Upload", Integer.parseInt(rowNoGbl));
			String fileStatus = objExcelUtils.getCellValueUsingColName("File_Status", Integer.parseInt(rowNoGbl));
			objDataDef.SearchFileInImportHistory(driver, test, extent, rowNoGbl, date1, fileName);
			objCBF.VerifyFileStatus(driver, test, extent, rowNoGbl, date1, colName, fileStatus);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Search for file and verify status in import history");
		}
	}

	@Then("^Verify the result for reporting entity imported file$")
	public void verify_the_result_for_reporting_entity_imported_file() throws Throwable {
		try {
			String attributeCode = objExcelUtils.getCellValueUsingColName("Attribute_code", Integer.parseInt(rowNoGbl));
			String error_code = objExcelUtils.getCellValueUsingColName("Error_code", Integer.parseInt(rowNoGbl));
			String error_message = objExcelUtils.getCellValueUsingColName("Expected_ErrorMessage",
					Integer.parseInt(rowNoGbl));
			objDataDef.verifyErrCodeInImportHisPage(driver, test, extent, rowNoGbl, date1, error_code);
			objDataDef.verifyErrMsgInImportHisForRepEntity(driver, test, extent, rowNoGbl, date1, error_message,
					attributeCode);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify the result for reporting entity imported file");
		}
	}

	@Then("^Verify the result of datapoint imported file$")
	public void verify_the_result_for_datapoint_imported_file() throws Throwable {
		try {
			String error_code = objExcelUtils.getCellValueUsingColName("Error_code", Integer.parseInt(rowNoGbl));
			String error_message = objExcelUtils.getCellValueUsingColName("Expected_ErrorMessage",
					Integer.parseInt(rowNoGbl));
			objDataDef.verifyErrCodeInImportHisPage(driver, test, extent, rowNoGbl, date1, error_code);
			objDataDef.verifyErrMsgInImportHisForDataPoint(driver, test, extent, rowNoGbl, date1, error_message);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify the result of datapoint imported file");
		}
	}

	@Then("^Wait for few minutes$")
	public void wait_for_few_minutes() throws Throwable {
		try {
			Thread.sleep(180000);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Wait for few minutes");
		}
	}

	@When("^Enter second Attribute Details \"([^\"]*)\"$")
	public void enter_second_Attributes_Details(String workbookName) throws Throwable {
		String attributeCode = "ATTR_Code";
		String RandomString = objCBF.generateRandomString(rowNoGbl, workbookName, attributeCode);
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objCstmEntityAttrCode"), RandomString);
		String AttributeDesc = objExcelUtils.getCellValueUsingColName("ATTR_Description", Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objCstmEntityAttrDesc"), AttributeDesc);
		String DataType = objExcelUtils.getCellValueUsingColName("ATTR2_DataType", Integer.parseInt(rowNoGbl));
		objCBF.selectListItem(driver, test, extent, rowNoGbl, date1,
				objPageReadDefiniton.getLocator("objCtsmEntityAttrDataType"), DataType);
		String IsIdentifier = objExcelUtils.getCellValueUsingColName("ATTR_IsIdentifier", Integer.parseInt(rowNoGbl));
		objCBF.selectCheckbox(driver, test, extent, rowNoGbl, date1,
				objPageReadDefiniton.getLocator("objCstmEntityAttrIsIdChkbox"), IsIdentifier);
		if (DataType.equalsIgnoreCase("List Box")) {
			String Entries = objExcelUtils.getCellValueUsingColName("ListBox_Entries", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objCstmEntityAttrEntries"), Entries);
			String MultiValues = objExcelUtils.getCellValueUsingColName("ListBox_MultiValues",
					Integer.parseInt(rowNoGbl));
			// objCBF.selectSupportMultiValueCheckbox(driver, test, extent,
			// rowNoGbl, date1,
			// objPageReadDefiniton.getLocator("objCstmEntityAttrSuuportMultiVal"),
			// MultiValues);
		}
	}

	@When("^Edit Attribute Details \"([^\"]*)\"$")
	public void edit_Attribute_details(String workbookName) throws Throwable {
		String AttributeDesc = objExcelUtils.getCellValueUsingColName("ATTR_Description_Edit",
				Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objCstmEntityAttrDesc"), AttributeDesc);
		String DataType = objExcelUtils.getCellValueUsingColName("ATTR_DataType_Edit", Integer.parseInt(rowNoGbl));
		if (DataType.equalsIgnoreCase("List Box")) {
			String Entries = objExcelUtils.getCellValueUsingColName("ListBox_Entries_Edit", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objCstmEntityAttrEntries"), Entries);
			String MultiValues = objExcelUtils.getCellValueUsingColName("ListBox_MultiValues_Edit",
					Integer.parseInt(rowNoGbl));
			objCBF.selectSupportMultiValueCheckbox(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objCstmEntityAttrSuuportMultiVal"), MultiValues);
		}
	}

	@Then("^Verify new entry created in Attributes List \"([^\"]*)\"$")
	public void verify_new_entry_created_in_Attributes_List(String ColName) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String attributeCode = "ATTR_Code";
			String AttrCode = objExcelUtils.getCellValueUsingColName(attributeCode, Integer.parseInt(rowNoGbl));
			objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName, AttrCode);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_new_entry_created_in_Attributes_List");
		}
	}

	@When("^Click on \"([^\"]*)\" left menu$")
	public void click_on_left_menu(String menu) throws Throwable {
		try {
			objCBF.clickOnMenu(driver, test, extent, rowNoGbl, date1, menu);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : click_on_left_menu");
		}
	}

	@When("^Enter Modules details \"([^\"]*)\"$")
	public void enter_Modules_details(String workbookName) throws Throwable {
		try {
			String colName = "MD_Code";
			String MDName = objExcelUtils.getCellValueUsingColName("MD_NAME", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objName"), MDName);
			String RandomString = objCBF.generateRandomString(rowNoGbl, workbookName, colName);
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objCode"),
					RandomString);
			String MDValidFrom = objExcelUtils.getDateCellValueUsingColName("MD_ValidFrom", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objValidFrom"),
					MDValidFrom);
			String MDValidTo = objExcelUtils.getDateCellValueUsingColName("MD_ValidTo", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objValidTo"),
					MDValidTo);
			String MDExtension = objExcelUtils.getCellValueUsingColName("MD_Extensions", Integer.parseInt(rowNoGbl));
			objCBF.selectItemInMultiselectList(driver, test, extent, rowNoGbl, date1,
					objPageReadModules.getLocator("objExtensions"), MDExtension);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_Modules_details");
		}
	}

	@When("^Enter Modules details$")
	public void enter_Modules_details() throws Throwable {
		try {
			String MD_Code = objExcelUtils.getCellValueUsingColName("MD_Code", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objCode"), MD_Code);
			String MDName = objExcelUtils.getCellValueUsingColName("MD_NAME", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objName"), MDName);
			String MDValidFrom = objExcelUtils.getDateCellValueUsingColName("MD_ValidFrom", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objValidFrom"),
					MDValidFrom);
			String MDValidTo = objExcelUtils.getDateCellValueUsingColName("MD_ValidTo", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objValidTo"),
					MDValidTo);
			String MDExtension = objExcelUtils.getCellValueUsingColName("MD_Extensions", Integer.parseInt(rowNoGbl));
			objCBF.selectItemInMultiselectList(driver, test, extent, rowNoGbl, date1,
					objPageReadModules.getLocator("objExtensions"), MDExtension);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_Modules_details");
		}
	}

	@When("^Edit Modules details$")
	public void edir_Modules_details() throws Throwable {
		try {
			String MDName = objExcelUtils.getCellValueUsingColName("MD_NAME_Edit", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objName"), MDName);
			String MDValidFrom = objExcelUtils.getDateCellValueUsingColName("MD_ValidFrom_Edit",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objValidFrom"),
					MDValidFrom);
			String MDValidTo = objExcelUtils.getDateCellValueUsingColName("MD_ValidTo_Edit",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objValidTo"),
					MDValidTo);
			String MDExtension = objExcelUtils.getCellValueUsingColName("MD_Extensions_Edit",
					Integer.parseInt(rowNoGbl));
			objCBF.selectItemInMultiselectList(driver, test, extent, rowNoGbl, date1,
					objPageReadModules.getLocator("objExtensions"), MDExtension);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : edir_Modules_details");
		}
	}

	@When("^Search record for module$")
	public void search_record_for_module() throws Throwable {
		try {
			String colName = "MD_Code";
			String MDCode = ExcelUtils.getCellValueUsingColName(colName, Integer.parseInt(rowNoGbl));
			objCBF.searchRecordForModules(driver, test, extent, rowNoGbl, date1, MDCode);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : search_record_for_module");
		}
	}

	@When("^Search record for module name$")
	public void search_record_for_module_name() throws Throwable {
		try {
			String colName = "MD_Code";
			String MDCode = ExcelUtils.getCellValueUsingColName(colName, Integer.parseInt(rowNoGbl));
			objCBF.searchRecordForModulesInSubmissionList(driver, test, extent, rowNoGbl, date1, MDCode);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : search_record_for_module_name");
		}
	}

	@When("^Search record for cycle name$")
	public void search_record_for_cycle_name() throws Throwable {
		try {
			String colName = "Cycle_Name";
			String MDCode = ExcelUtils.getCellValueUsingColName(colName, Integer.parseInt(rowNoGbl));
			objCBF.searchRecordForCycleInSubmissionList(driver, test, extent, rowNoGbl, date1, MDCode);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : search_record_for_cycle_name");
		}
	}

	@Then("^Verify new entry created in Modules List \"([^\"]*)\"$")
	public void verify_new_entry_created_in_Modules_List(String ColName) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			if (ColName.equals("moduleCode")) {
				String codeName = objExcelUtils.getCellValueUsingColName("MD_Code", Integer.parseInt(rowNoGbl));
				objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName, codeName);
			} else if (ColName.equals("moduleName")) {
				String MDName = objExcelUtils.getCellValueUsingColName("MD_NAME_Edit", Integer.parseInt(rowNoGbl));
				objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName, MDName);

			} else if (ColName.equals("version")) {
				int MDVersion = objExcelUtils.getIntCellValueUsingColName("MD_Version", Integer.parseInt(rowNoGbl));
				objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName,
						Integer.toString(MDVersion));

			} else if (ColName.equals("module")) {
				String codeName = objExcelUtils.getCellValueUsingColName("MD_Code", Integer.parseInt(rowNoGbl));
				objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName, codeName);
			} else {
				test.log(Status.FAIL, "No column name given");
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_new_entry_created_in_Modules_List");
		}
	}

	@When("^Enter Add New Version details$")
	public void enter_Add_New_Version_details() throws Throwable {
		try {
			String MDValidFrom = objExcelUtils.getDateCellValueUsingColName("MD_ValidFrom_AddVersion",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objValidFrom"),
					MDValidFrom);
			String MDValidTo = objExcelUtils.getDateCellValueUsingColName("MD_ValidTo_AddVersion",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objValidTo"),
					MDValidTo);
			String MDExtension = objExcelUtils.getCellValueUsingColName("MD_Extensions_AddVersion",
					Integer.parseInt(rowNoGbl));
			objCBF.selectItemInMultiselectList(driver, test, extent, rowNoGbl, date1,
					objPageReadModules.getLocator("objExtensions"), MDExtension);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_Add_New_Version_details");
		}
	}

	@When("^Upload a template$")
	public void upload_a_template() throws Throwable {
		try {
			String strFilePath1 = objExcelUtils.getCellValueUsingColName("MD_Template1", Integer.parseInt(rowNoGbl));
			String strFilePath2 = objExcelUtils.getCellValueUsingColName("MD_Template2", Integer.parseInt(rowNoGbl));
			String strFilePath3 = objExcelUtils.getCellValueUsingColName("MD_Template3", Integer.parseInt(rowNoGbl));
			// Upload a File to Casper
			objDataDef.uploadTemplate(driver, rowNoGbl, test, strFilePath1,
					objPageReadModules.getLocator("objOriginalTemplate"));
			objDataDef.uploadTemplate(driver, rowNoGbl, test, strFilePath2,
					objPageReadModules.getLocator("objDPITemplate"));
			objDataDef.uploadTemplate(driver, rowNoGbl, test, strFilePath3,
					objPageReadModules.getLocator("objlabelTemplate"));
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : upload_a_template");
		}
	}

	@When("^Upload business rules$")
	public void upload_business_rules() throws Throwable {
		try {
			String strFilePath1 = objExcelUtils.getCellValueUsingColName("BusinessRule_fileName",
					Integer.parseInt(rowNoGbl));
			// Upload a File to Casper
			objDataDef.uploadValidationRule(driver, rowNoGbl, test, strFilePath1,
					objPageReadValidationRules.getLocator("objUploadFile"));
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : upload_business_rules");
		}
	}

	@Then("^Check the status \"([^\"]*)\"$")
	public void check_the_status(String colName) throws Throwable {
		try {
			Thread.sleep(2000);
			objCBF.checkStatus(driver, test, extent, date1, rowNoGbl, colName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : check_the_status");
		}
	}

	@When("^Upload entity CSV File$")
	public void upload_entity_CSV_File() throws Throwable {
		try {
			String FilePath = objExcelUtils.getCellValueUsingColName("CSV-File_To_Upload", Integer.parseInt(rowNoGbl));
			objDataDef.uploadEntity(driver, rowNoGbl, test, FilePath,
					objPageReadDefiniton.getLocator("objBrowseFileBtn"));
		} catch (IOException e) {
			System.out.println("in the block: " + e);
			System.out.println("Step Failed : upload_entity_CSV_File");
		}
	}

	@When("^Upload data point CSV File$")
	public void upload_data_point_CSV_File() throws Throwable {
		try {
			String FilePath = objExcelUtils.getCellValueUsingColName("CSV-File_To_Upload", Integer.parseInt(rowNoGbl));
			objDataDef.uploadDataPoint(driver, rowNoGbl, test, FilePath,
					objPageReadDefiniton.getLocator("objBrowseFileBtn"));
		} catch (IOException e) {
			System.out.println("in the block: " + e);
			System.out.println("Step Failed : upload_data_point_CSV_File");
		}
	}

	@When("^Enter EntityGroup Details \"([^\"]*)\"$")
	public void enter_EntityGroup_Details(String workbookName) throws Throwable {
		String colName = "EG_Name";
		String entityGrpName = objCBF.generateRandomString(rowNoGbl, workbookName, colName);
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objAddEntityGroupName"), entityGrpName);
		String entityGrpDesc = objExcelUtils.getCellValueUsingColName("EG_Description", Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objAddEntityGroupDesc"), entityGrpDesc);
	}

	@When("^Enter EntityGroup Details$")
	public void enter_EntityGroup_Details() throws Throwable {
		String entityGrpName = objExcelUtils.getCellValueUsingColName("EG_Name", Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objAddEntityGroupName"), entityGrpName);
		String entityGrpDesc = objExcelUtils.getCellValueUsingColName("EG_Description", Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objAddEntityGroupDesc"), entityGrpDesc);
	}

	@When("^Edit EntityGroup Details$")
	public void edit_EntityGroup_Details() throws Throwable {
		String entityGrpDesc = objExcelUtils.getCellValueUsingColName("EG_Description_Edit",
				Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objEditEntityGroupDesc"), entityGrpDesc);
	}

	@When("^Edit Assigned Entities$")
	public void edit_Assigned_Entities() throws Throwable {
		String validFrom = objExcelUtils.getDateCellValueUsingColName("EG_ValidFrom", Integer.parseInt(rowNoGbl));
		String validTo = objExcelUtils.getDateCellValueUsingColName("EG_ValidTo", Integer.parseInt(rowNoGbl));
		objDataDef.EditAssignedEntities(driver, rowNoGbl, test, extent, date1, "1", validFrom, validTo);
	}

	@Then("^Verify new entry created in EntityGroup List \"([^\"]*)\"$")
	public void verify_new_entry_created_in_EntityGroup_List(String ColName) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String entityGrpName = objExcelUtils.getCellValueUsingColName("EG_Name", Integer.parseInt(rowNoGbl));
			objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName, entityGrpName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_new_entry_created_in_EntityGroup_List");
		}
	}

	@Then("^Verify new entry in AssignedEntityGroup List \"([^\"]*)\"$")
	public void verify_new_entry_in_AssignedEntityGroup_List(String ColName) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String entities = "1";
			objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName, entities);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_new_entry_in_AssignedEntityGroup_List");
		}
	}

	@When("^Enter Cycles Details \"([^\"]*)\"$")
	public void enter_Cycles_Details(String workbookName) throws Throwable {
		try {
			String colName = "CY_Name";
			String cycleName = objCBF.generateRandomString(rowNoGbl, workbookName, colName);
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliCycleAddName"), cycleName);
			// String
			// startDate=objExcelUtils.getDateCellValueUsingColName("CY_ValidFrom",
			// Integer.parseInt(rowNoGbl));
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate localDate = LocalDate.now();
			String startDate = dtf.format(localDate).toString();
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliCycleStartDate"), startDate);
			String endDate = objExcelUtils.getDateCellValueUsingColName("CY_ValidTo", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliCycleEndDate"), endDate);
			String description = objExcelUtils.getCellValueUsingColName("CY_Description", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliCycleDescription"), description);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_Cycles_Details");
		}
	}

	@When("^Edit Cycles details$")
	public void edit_Cycles_details() throws Throwable {
		try {
			String endDate = objExcelUtils.getDateCellValueUsingColName("CY_ValidTo_Edit", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliCycleEditEndDate"), endDate);
			String description = objExcelUtils.getCellValueUsingColName("CY_Description_Edit",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliCycleEditDescription"), description);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : edit_Cycles_details");
		}
	}

	@Then("^Verify new entry created in Cycles List \"([^\"]*)\"$")
	public void verify_new_entry_created_in_cycles_List(String ColName) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			if (ColName.equals("name")) {
				String cycleName = objExcelUtils.getCellValueUsingColName("CY_Name", Integer.parseInt(rowNoGbl));
				objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName, cycleName);
			} else if (ColName.equals("description")) {
				String description = objExcelUtils.getCellValueUsingColName("CY_Description_Edit",
						Integer.parseInt(rowNoGbl));
				objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName, description);

			} else {
				test.log(Status.FAIL, "No column name given");
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_new_entry_created_in_cycles_List");
		}
	}

	@When("^Enter Obligations per group Details \"([^\"]*)\"$")
	public void enter_Obligations_per_group_Details(String workbookName) throws Throwable {
		try {
			// String
			// referenceDate=objExcelUtils.getDateCellValueUsingColName("RO_ReferenceDate",
			// Integer.parseInt(rowNoGbl));
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate localDate = LocalDate.now();
			String referenceDate = dtf.format(localDate).toString();
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliPerGrpReferenceDate"), referenceDate);
			String reportingCycle = objExcelUtils.getCellValueUsingColName("RO_RepCycle", Integer.parseInt(rowNoGbl));
			objCBF.selectLastItemFromListItem(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objObliPerGrpReportigCycle"));
			objCBF.captureCycleName(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objObliPerGrpReportigCycle"), workbookName, "RO_RepCycle");
			String entityGroup = objExcelUtils.getCellValueUsingColName("RO_EntityGroup", Integer.parseInt(rowNoGbl));
			objCBF.selectLastItemFromListItem(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objObliPerGrpEntityGroup"));
			// String
			// remittanceDate=objExcelUtils.getDateCellValueUsingColName("RO_ReferenceDate",
			// Integer.parseInt(rowNoGbl));;
			String remittanceDate = dtf.format(localDate).toString();
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliPerGrpRemittanceDate"), remittanceDate);
			objCBF.selectCheckbox(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objModuleSelectionCheckbox"), "Y");
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_Obligations_per_group_Details");
		}
	}

	@When("^Search record for Cycle name value$")
	public void search_record_for_CycleName() throws Throwable {
		try {
			String CycleName = ExcelUtils.getCellValueUsingColName("RO_RepCycle", Integer.parseInt(rowNoGbl));
			objCBF.searchForRecordsForFilterValue(driver, test, extent, rowNoGbl, date1, CycleName);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : search_record_for_CycleName");
		}
	}

	@Then("^Verify new entry created in Obligations per group List \"([^\"]*)\"$")
	public void verify_new_entry_created_in_Obligations_per_group_List(String ColName) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate localDate = LocalDate.now();

			if (ColName.equals("referenceDate")) {
				String referenceDate = dtf.format(localDate).toString();
				// String
				// referenceDate=objExcelUtils.getDateCellValueUsingColName("RO_ReferenceDate",
				// Integer.parseInt(rowNoGbl));
				objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName, referenceDate);
			} else if (ColName.equals("remittanceDate")) {
				String remittanceDate = dtf.format(localDate).toString();
				// String
				// remittanceDate=objExcelUtils.getDateCellValueUsingColName("RO_RemittanceDate_Edit",
				// Integer.parseInt(rowNoGbl));
				objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName, remittanceDate);

			} else {
				test.log(Status.FAIL, "No column name given");
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_new_entry_created_in_Obligations_per_group_List");
		}
	}

	@When("^Edit Obligations per group details$")
	public void edit_Obligations_per_group_details() throws Throwable {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate localDate = LocalDate.now();
			String remittanceDate = dtf.format(localDate).toString();
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliPerGrpEditRemittanceDate"), remittanceDate);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : edit_Obligations_per_group_details");
		}
	}

	@Then("^Verify no entry in table list$")
	public void verify_no_entry_in_table_list() throws Throwable {
		try {
			objCBF.VerifyNoRecordInTheList(driver, test, extent, rowNoGbl, date1);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_no_entry_in_table_list");
		}
	}

	@When("^Upload a Zip File in submission list \"([^\"]*)\"$")
	public void upload_a_Zip_File_submission_list(String workbookName) throws Throwable {
		try {
			String colName = "Sub_FilePath";
			String zipColName = "Sub_ZipFilePath";
			String fileType = objExcelUtils.getCellValueUsingColName("Sub_FileType", Integer.parseInt(rowNoGbl));
			String dataCollName = objExcelUtils.getCellValueUsingColName("DC_Code", Integer.parseInt(rowNoGbl));
			String reportingCycleName = objExcelUtils.getCellValueUsingColName("CY_Name", Integer.parseInt(rowNoGbl));
			String moduleName = objExcelUtils.getCellValueUsingColName("MD_Code", Integer.parseInt(rowNoGbl));
			String reportingCode = objExcelUtils.getCellValueUsingColName("ATTR2_Value", Integer.parseInt(rowNoGbl));
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate localDate = LocalDate.now();
			String referenceDate = dtf.format(localDate).toString();
			String[] array = referenceDate.split("/");
			String newDate = array[2] + array[1] + array[0];
			String name = fileType + dataCollName + "_" + reportingCycleName + "_" + moduleName + "_" + reportingCode
					+ "_" + newDate + "_1";
			String newName = fileType + dataCollName + "_" + reportingCycleName + "_" + moduleName + "_" + reportingCode
					+ "_" + newDate + "_1" + ".xlsx";

			// Changing the file name
			String fileNameTemp = objExcelUtils.getCellValueUsingColName("Sub_FilePath", Integer.parseInt(rowNoGbl));
			String folderPath = "src/test/resources/FilesToUpload/ZipFileUpload/";
			File f1 = new File(folderPath);
			String foldPath = f1.getAbsolutePath() + "\\";
			String filePath = foldPath + fileNameTemp;

			File file = new File(filePath);
			String fileName = file.getParentFile() + "\\" + newName;
			file.renameTo(new File(fileName));

			// Writting file name in excel
			// String value=folderPath +newName;
			String value = newName;
			String Path = "src/test/java/TestData/" + workbookName;
			ExcelUtils.setCellValueUsingColName(Path, colName, rowNoGbl, value);

			// convert excel file into zip file
			FileOutputStream fos = new FileOutputStream(foldPath + name + ".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);
			ZipEntry ze = new ZipEntry(newName);
			zos.putNextEntry(ze);
			FileInputStream in = new FileInputStream(foldPath + newName);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = in.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}
			in.close();
			zos.closeEntry();
			zos.close();
			// Writting zip file name in excel
			// String zipFileName=folderPath+name+".zip";
			String zipFileName = name + ".zip";
			String PathForZipFile = "src/test/java/TestData/" + workbookName;
			ExcelUtils.setCellValueUsingColName(PathForZipFile, zipColName, rowNoGbl, zipFileName);

			filePath = objExcelUtils.getCellValueUsingColName("Sub_ZipFilePath", Integer.parseInt(rowNoGbl));
			objFileSub.uploadZipFile(driver, rowNoGbl, test, filePath);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : upload_a_Zip_File_submission_list");
		}
	}

	@When("^Filter for status \"([^\"]*)\"$")
	public void filter_for_status(String fileStatus) throws Throwable {
		try {
			objFileSub.FilterForStatusInFileVault(driver, test, extent, fileStatus, fileStatus, fileStatus);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : filter_for_status");
		}
	}

	@Then("^Save the completed status file count in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void save_the_completed_status_file_count_in(String workbookName, String sheetName, String rowNo)
			throws Throwable {
		try {
			String colName = "CompletedStatus_FileCount";
			objCBF.setFileCountInExcel(driver, test, extent, rowNoGbl, date1, workbookName, sheetName, rowNo, colName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : save_the_completed_status_file_count_in");
		}
	}

	@When("Upload individual file in submission list$")
	public void upload_individual_file_in_submission_list_for_column() throws Throwable {
		try {
			String filePath = objExcelUtils.getCellValueUsingColName("Sub_FilePath", Integer.parseInt(rowNoGbl));
			objFileSub.uploadIndividualFile(driver, rowNoGbl, test, filePath);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Upload individual file in submission list");
		}
	}

	@When("Upload invalid individual file in submission list$")
	public void upload_invalid_individual_file_in_submission_list_for_column() throws Throwable {
		try {
			String filePath = objExcelUtils.getCellValueUsingColName("Sub_invalidFilePath", Integer.parseInt(rowNoGbl));
			objFileSub.uploadIndividualFile(driver, rowNoGbl, test, filePath);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : upload_invalid_individual_file_in_submission_list_for_column");
		}
	}

	@Then("^Verify file status in submission list for column \"([^\"]*)\"$")
	public void verify_file_status_in_submission_list_for_column(String ColName) throws Throwable {
		try {
			String fileStatus = objExcelUtils.getCellValueUsingColName("Sub_FileStatus", Integer.parseInt(rowNoGbl));
			objCBF.VerifySubmissionStatus(driver, test, extent, rowNoGbl, date1, ColName, fileStatus);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_file_status_in_submission_list_for_column");
		}
	}

	@Then("^Verify the child records for submission \"([^\"]*)\"$")
	public void verify_the_child_records_for_submission(String ColName) throws Throwable {
		try {
			objFileSub.verifyChildRecords(driver, test, extent, rowNoGbl, date1, ColName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_the_child_records_for_submission");
		}
	}

	@Then("^Verify count of files is correct for submission \"([^\"]*)\"$")
	public void verify_count_of_files_correct_for_submission(String ColName) throws Throwable {
		try {
			int countInFileVault = objExcelUtils.getIntCellValueUsingColName("CompletedStatus_FileCount",
					Integer.parseInt(rowNoGbl));
			objFileSub.CheckCountOfFilesInBrackets(driver, test, extent, rowNoGbl, date1, ColName, countInFileVault);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_count_of_files_correct_for_submission");
		}
	}

	@Then("^Verify Module filters in submission list$")
	public void verify_module_filter_in_submission_list() throws Throwable {
		try {
			String moduleName = objExcelUtils.getCellValueUsingColName("Module_Name", Integer.parseInt(rowNoGbl));
			objFileSub.verifyModuleFilterInSubmissionList(driver, test, extent, rowNoGbl, date1, moduleName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_module_filter_in_submission_list");
		}
	}

	@Then("^Verify Reception date filters in submission list \"([^\"]*)\"$")
	public void verify_reception_date_filter_in_submission_list(String workbookName) throws Throwable {
		try {
			String colName = "Reception_Date";
			objFileSub.captureReceptionDate(driver, test, extent, rowNoGbl, date1, workbookName, colName);
			String receptionDate = objExcelUtils.getCellValueUsingColName("Reception_Date", Integer.parseInt(rowNoGbl));
			objFileSub.verifyReceptionDateFilterInSubmissionList(driver, test, extent, rowNoGbl, date1, receptionDate);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_reception_date_filter_in_submission_list");
		}
	}

	@Then("^Verify File name filter in file vault \"([^\"]*)\"$")
	public void verify_file_name_filter_in_file_vault(String workbookName) throws Throwable {
		try {
			String colName = "File_Name";
			objFileSub.captureFileName(driver, test, extent, rowNoGbl, date1, workbookName, colName);
			String fileName = objExcelUtils.getCellValueUsingColName("File_Name", Integer.parseInt(rowNoGbl));
			objFileSub.verifyFileNameInFileVault(driver, test, extent, rowNoGbl, date1, fileName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_file_name_filter_in_file_vault");
		}
	}

	@Then("^Verify Reception date filters in file vault \"([^\"]*)\"$")
	public void verify_reception_date_filter_in_file_vault(String workbookName) throws Throwable {
		try {
			String receptionDate = objExcelUtils.getCellValueUsingColName("Reception_Date", Integer.parseInt(rowNoGbl));
			objFileSub.verifyReceptionDateFilterInFileVault(driver, test, extent, rowNoGbl, date1, receptionDate);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_reception_date_filter_in_file_vault");
		}
	}

	@When("^Select record in submission list$")
	public void select_record_in_submission_list() throws Throwable {
		try {
			objFileSub.selectRecordInSubmissionList(driver, test, extent, rowNoGbl, date1);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : select_record_in_submission_list ");
		}
	}

	@Then("^Verify file gets downloaded$")
	public void verify_file_gets_downloaded() throws Throwable {
		try {
			String datacollectionCode = objExcelUtils.getCellValueUsingColName("DataCollection_Code",
					Integer.parseInt(rowNoGbl));
			String userName = System.getProperty("user.name");
			objFileSub.verifyFileIsDownloaded(driver, test, extent, rowNoGbl, date1, datacollectionCode, userName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_file_gets_downloaded");
		}
	}

	@When("^Select toggle column \"([^\"]*)\"$")
	public void select_toggle_column(String columnName) throws Throwable {
		try {
			objCBF.SelectToggleColumn(driver, test, extent, rowNoGbl, date1, columnName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : select_toggle_column");
		}
	}

	@Then("^Verify Latest version is on top \"([^\"]*)\"$")
	public void verify_latest_version_is_on_top(String ColName) throws Throwable {
		try {
			objFileSub.verifyLatestVersionOfFile(driver, test, extent, rowNoGbl, date1, ColName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_latest_version_is_on_top");
		}
	}

	@Then("^Verify new entry created in Submission List \"([^\"]*)\"$")
	public void verify_new_entry_created_in_Submission_List(String ColName) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String cycleName = objExcelUtils.getCellValueUsingColName("Cycle_Name", Integer.parseInt(rowNoGbl));
			String newCycleName = cycleName + "(0)";
			objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName, newCycleName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_new_entry_created_in_Submission_List");
		}
	}

	@Then("^Verify file status in file vault list for column \"([^\"]*)\"$")
	public void verify_file_status_in_file_vault_list_for_column(String ColName) throws Throwable {
		try {
			String fileStatus = objExcelUtils.getCellValueUsingColName("FileVault_Status", Integer.parseInt(rowNoGbl));
			objCBF.VerifyFileStatus(driver, test, extent, rowNoGbl, date1, ColName, fileStatus);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_file_status_in_file_vault_list_for_column");
		}
	}

	@When("^Save Attribute code for import entities$")
	public void save_Attribute_code_for_import_entities() throws Throwable {
		try {
			String attributeCode = objExcelUtils.getCellValueUsingColName("ATTR_Code", Integer.parseInt(rowNoGbl));
			objCBF.setAttributeValueInCSVFile(driver, test, extent, rowNoGbl, date1, attributeCode);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : save_Attribute_code_for_import_entities");
		}
	}

	@When("^Save Data collection code in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void save_Data_collection_code(String workbookName, String sheetName, String rowNo) throws Throwable {
		try {
			String colName = "DataCollection_Code";
			String dataCollectionCode = objExcelUtils.getCellValueUsingColName("DC_Code", Integer.parseInt(rowNoGbl));
			objCBF.setDataCollectionCodeInFile(driver, test, extent, rowNoGbl, date1, dataCollectionCode, workbookName,
					sheetName, rowNo, colName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : save_Data_collection_code");
		}
	}

	@When("^Save module code in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void save_module_code(String workbookName, String sheetName, String rowNo) throws Throwable {
		try {
			String colName = "Module_Name";
			String dataCollectionCode = objExcelUtils.getCellValueUsingColName("MD_Code", Integer.parseInt(rowNoGbl));
			objCBF.setDataCollectionCodeInFile(driver, test, extent, rowNoGbl, date1, dataCollectionCode, workbookName,
					sheetName, rowNo, colName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : save_module_code");
		}
	}

	@When("^Save cycle name in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void save_cycle_name(String workbookName, String sheetName, String rowNo) throws Throwable {
		try {
			String colName = "Cycle_Name";
			String cycleName = objExcelUtils.getCellValueUsingColName("RO_RepCycle", Integer.parseInt(rowNoGbl));
			objCBF.setDataCollectionCodeInFile(driver, test, extent, rowNoGbl, date1, cycleName, workbookName,
					sheetName, rowNo, colName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : save_cycle_name");
		}
	}

	@When("^Save Data collection code in \"([^\"]*)\"$")
	public void save_Data_collection_code_in(String workbookName) throws Throwable {
		try {
			String colName = "DataCollection_Code";
			String dataCollectionCode = objExcelUtils.getCellValueUsingColName("DC_Code", Integer.parseInt(rowNoGbl));
			// String dataCollectionCode="BVR15";
			objCBF.setDataCollectionCodeInExcel(driver, test, extent, rowNoGbl, date1, dataCollectionCode, workbookName,
					colName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : save_Data_collection_code_in");
		}
	}

	@Then("^Pick and change module code in CSV file$")
	public void pick_and_change_module_code_in_CSV_file() throws Throwable {
		try {
			String errorCode = objExcelUtils.getCellValueUsingColName("Error_code", Integer.parseInt(rowNoGbl));
			if (!errorCode.equals("DP007") && !errorCode.equals("DP024")) {
				List<WebElement> listOfCUI = driver
						.findElements(By.xpath("//div[@role='gridcell'][@col-id='moduleCode']"));
				String moduleCode = listOfCUI.get(0).getText();
				String fileName = objExcelUtils.getCellValueUsingColName("CSV-File_To_Upload",
						Integer.parseInt(rowNoGbl));
				objCBF.setModuleCodeValueInCSV(driver, test, extent, rowNoGbl, date1, moduleCode, fileName);
			} else
				System.out.println("abc");
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : pick_and_change_module_code_in_CSV_file");
		}
	}

	@Then("^Pick and change collection unique identifier in CSV \"([^\"]*)\"$")
	public void pick_and_change_the_collection_unique_identifier_in_CSV(String rowNo) throws Throwable {
		try {
			if (rowNo.equals("12")) {
				List<WebElement> listOfCUI = driver
						.findElements(By.xpath("//div[@role='gridcell'][@col-id='collectionUniqueId']"));
				String COLLECTIONUNIQUEIDENTIFIER = listOfCUI.get(0).getText();
				String fileName = objExcelUtils.getCellValueUsingColName("CSV-File_To_Upload",
						Integer.parseInt(rowNoGbl));
				objCBF.setCollectionUniqueIdentifierValueInCSV(driver, test, extent, rowNoGbl, date1,
						COLLECTIONUNIQUEIDENTIFIER, fileName);
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : pick_and_change_the_collection_unique_identifier_in_CSV");
		}
	}

	@When("^Change the COLLECTIONUNIQUEIDENTIFIER in CSV file \"([^\"]*)\"$")
	public void change_the_COLLECTIONUNIQUEIDENTIFIER_in_CSV_file(String workbookName) throws Throwable {
		try {
			String fileName = "validentity1.csv";
			String COLLECTIONUNIQUEIDENTIFIER = objCBF.generateRandomString(rowNoGbl, workbookName,
					"COLLECTIONUNIQUEIDENTIFIER");
			objCBF.setCollectionUniqueIdentifierValueInCSV(driver, test, extent, rowNoGbl, date1,
					COLLECTIONUNIQUEIDENTIFIER, fileName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : change_the_COLLECTIONUNIQUEIDENTIFIER_in_CSV_file");
		}
	}

	@When("^Enter duplicate Data Dictionary details$")
	public void enter_duplicate_Data_Dictionary_details() throws Throwable {
		try {
			String DDName = ExcelUtils.getCellValueUsingColName("DD_Name", Integer.parseInt(rowNoGbl));
			String DDOwner = ExcelUtils.getCellValueUsingColName("DD_Owner_Edit", Integer.parseInt(rowNoGbl));
			String DDDescription = ExcelUtils.getCellValueUsingColName("DD_Description", Integer.parseInt(rowNoGbl));

			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDataDict.getLocator("objName"), DDName);
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDataDict.getLocator("objOwner"),
					DDOwner);
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDataDict.getLocator("objDesc"),
					DDDescription);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_duplicate_Data_Dictionary_details");
		}
	}

	@Then("^Verify error message for entry exists$")
	public void verify_error_message_for_entry_exist() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String strExpMsg = objExcelUtils.getCellValueUsingColName("ErrorMessage_EntryExist",
					Integer.parseInt(rowNoGbl));
			Thread.sleep(1000);
			objCBF.verifyErrorMessage(driver, test, strFilePath, extent, rowNoGbl, date1, strExpMsg);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_error_message_for_entry_exist");
		}
	}

	@When("^Enter invalid date in DataCollection$")
	public void enter_invalid_date_in_Data_Collection() throws Throwable {
		try {
			String DCName = objExcelUtils.getCellValueUsingColName("DC_Name", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCName"),
					DCName);
			String DCDescription = objExcelUtils.getCellValueUsingColName("DC_Description", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objAddDCDescription"), DCDescription);
			String DCOwner = objExcelUtils.getCellValueUsingColName("DC_Owner", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCOwner"),
					DCOwner);
			String DCValidFrom = objExcelUtils.getDateCellValueUsingColName("DC_ValidFrom", Integer.parseInt(rowNoGbl));
			// int DCValidFrom =
			// objExcelUtils.getIntCellValueUsingColName("DC_ValidFrom",
			// Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objAddDCValidFrom"), DCValidFrom);
			String DCValidTo = objExcelUtils.getDateCellValueUsingColName("DC_ValidTo_Negative",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCValidTo"),
					DCValidTo);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_invalid_date_in_Data_Collection");
		}
	}

	@Then("^Verify error message for invalid date format$")
	public void verify_error_message_for_invalid_date_format() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String strExpTxt = objExcelUtils.getCellValueUsingColName("ErrorMessage_InvalidDate",
					Integer.parseInt(rowNoGbl));
			Thread.sleep(1000);
			objCBF.verifyErrorText(driver, test, strFilePath, extent, rowNoGbl, date1, strExpTxt);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_error_message_for_invalid_date_format");
		}
	}

	@When("^Enter invalid value for max entites$")
	public void enter_invalid_value_for_max_entites() throws Throwable {
		try {
			String DCCode = objExcelUtils.getCellValueUsingColName("DC_Code", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCCode"),
					DCCode);
			String DCName = objExcelUtils.getCellValueUsingColName("DC_Name", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCName"),
					DCName);
			String DCDescription = objExcelUtils.getCellValueUsingColName("DC_Description", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objAddDCDescription"), DCDescription);
			String DCOwner = objExcelUtils.getCellValueUsingColName("DC_Owner", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCOwner"),
					DCOwner);
			String DCValidFrom = objExcelUtils.getDateCellValueUsingColName("DC_ValidFrom", Integer.parseInt(rowNoGbl));
			// int DCValidFrom =
			// objExcelUtils.getIntCellValueUsingColName("DC_ValidFrom",
			// Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objAddDCValidFrom"), DCValidFrom);
			String DCValidTo = objExcelUtils.getDateCellValueUsingColName("DC_ValidTo", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objAddDCValidTo"),
					DCValidTo);
			String DCIsCntryRel = objExcelUtils.getCellValueUsingColName("DC_IsCountryRelevant",
					Integer.parseInt(rowNoGbl));
			objCBF.selectCheckbox(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objAddDCIsCountryRelevantChkbox"), DCIsCntryRel);
			String DCPrimaryDDOwner = objExcelUtils.getCellValueUsingColName("DC_PrimaryDataDictionary",
					Integer.parseInt(rowNoGbl));
			objCBF.selectFirstListItem(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objAddDCPrimaryDCOwner"), DCPrimaryDDOwner);
			int DCDataPoints = objExcelUtils.getIntCellValueUsingColName("DC_Max#DataPoints",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objAddDCDataPointDetails"), Integer.toString(DCDataPoints));
			int DCRepEntities = objExcelUtils.getIntCellValueUsingColName("DC_Max#Entities_Negative",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objAddDCMaxRepEntities"), Integer.toString(DCRepEntities));
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_invalid_value_for_max_entites");
		}
	}

	@Then("^Verify error message for invalid value$")
	public void verify_error_message_for_invalid_value() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String strExpTxt = objExcelUtils.getCellValueUsingColName("ErrorMessage_MaxValue",
					Integer.parseInt(rowNoGbl));
			Thread.sleep(1000);
			objCBF.verifyErrorText(driver, test, strFilePath, extent, rowNoGbl, date1, strExpTxt);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_error_message_for_invalid_value");
		}
	}

	@Then("^Verify error message for empty value$")
	public void verify_error_message_for_empty_value() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			// Get current date time with Date()
			date = new Date();
			// Now format the date
			date1 = dateFormat.format(date);
			String strExpTxt = objExcelUtils.getCellValueUsingColName("ErrorMessage_MandatoryField",
					Integer.parseInt(rowNoGbl));
			Thread.sleep(1000);
			objCBF.verifyErrorText(driver, test, strFilePath, extent, rowNoGbl, date1, strExpTxt);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_error_message_for_empty_value");
		}
	}

	@When("^Enter empty date in Modules$")
	public void enter_empty_date_in_Modules() throws Throwable {
		try {
			String MDName = objExcelUtils.getCellValueUsingColName("MD_NAME", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objName"), MDName);
			String MDCode = objExcelUtils.getCellValueUsingColName("MD_Code", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objCode"), MDCode);
			String MDValidFrom = objExcelUtils.getDateCellValueUsingColName("MD_ValidFrom_Negative",
					Integer.parseInt(rowNoGbl));
			objCBF.removeValue(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objValidFrom"));

			// driver.findElement(objPageReadModules.getLocator("objValidFrom")).sendKeys(Keys.ENTER);
			String MDValidTo = objExcelUtils.getDateCellValueUsingColName("MD_ValidTo", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objValidTo"),
					MDValidTo);
			Actions action = new Actions(driver);
			driver.findElement(By.xpath("//input[@formcontrolname='moduleName']")).click();
			action.sendKeys(Keys.TAB).build().perform();
			Thread.sleep(500);
			action.sendKeys(Keys.TAB).build().perform();
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_empty_date_in_Modules");
		}
	}

	@When("^Enter invalid date in Modules$")
	public void enter_invalid_date_in_Modules() throws Throwable {
		try {
			String MDName = objExcelUtils.getCellValueUsingColName("MD_NAME_Edit", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objName"), MDName);
			String MDValidFrom = objExcelUtils.getDateCellValueUsingColName("MD_ValidFrom_Edit",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objValidFrom"),
					MDValidFrom);
			String MDValidTo = objExcelUtils.getDateCellValueUsingColName("MD_ValidTo_Edit_Negative",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objValidTo"),
					MDValidTo);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_invalid_date_in_Modules");
		}
	}

	@When("^Enter past date in Add new version$")
	public void enter_past_date_in_Add_new_verion() throws Throwable {
		try {
			String MDValidFrom = objExcelUtils.getDateCellValueUsingColName("MD_ValidFrom_AddVersion",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objValidFrom"),
					MDValidFrom);
			String MDValidTo = objExcelUtils.getDateCellValueUsingColName("MD_ValidTo_AddVersion_Negative",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadModules.getLocator("objValidTo"),
					MDValidTo);
			String MDExtension = objExcelUtils.getCellValueUsingColName("MD_Extensions_AddVersion",
					Integer.parseInt(rowNoGbl));
			objCBF.selectItemInMultiselectList(driver, test, extent, rowNoGbl, date1,
					objPageReadModules.getLocator("objExtensions"), MDExtension);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_past_date_in_Add_new_verion");
		}
	}

	@Then("^Verify error message for date limit$")
	public void verify_error_message_for_date_limit() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String strExpTxt = objExcelUtils.getCellValueUsingColName("ErrorMessage_FromDateLimit",
					Integer.parseInt(rowNoGbl));
			Thread.sleep(1000);
			objCBF.verifyErrorText(driver, test, strFilePath, extent, rowNoGbl, date1, strExpTxt);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_error_message_for_date_limit");
		}
	}

	@When("^Enter empty description in custom attributes$")
	public void enter_empty_description_in_custom_attributes() throws Throwable {
		try {
			String attributeCode = objExcelUtils.getCellValueUsingColName("ATTR_Code", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objCstmEntityAttrCode"), attributeCode);
			String AttributeDesc = objExcelUtils.getCellValueUsingColName("ATTR_Description_Negative",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objCstmEntityAttrDesc"), AttributeDesc);
			String DataType = objExcelUtils.getCellValueUsingColName("ATTR_DataType", Integer.parseInt(rowNoGbl));
			objCBF.selectFirstListItem(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objCtsmEntityAttrDataType"), DataType);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_empty_description_in_custom_attributes");
		}
	}

	@When("^Enter empty entries in custom attributes$")
	public void enter_empty_entries_in_custom_attributes() throws Throwable {
		try {
			String AttributeDesc = objExcelUtils.getCellValueUsingColName("ATTR_Description_Edit",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objCstmEntityAttrDesc"), AttributeDesc);
			String DataType = objExcelUtils.getCellValueUsingColName("ATTR_DataType_Negative",
					Integer.parseInt(rowNoGbl));
			if (DataType.equalsIgnoreCase("List Box")) {
				// objCBF.removeValue(driver, test, date1, rowNoGbl, extent,
				// objPageReadDefiniton.getLocator("objCstmEditEntityAttrEntries"));
				for (int i = 0; i < 6; i++) {
					driver.findElement(By.xpath("//div/textarea[@formcontrolname='editCustomAttributeList']"))
							.sendKeys(Keys.BACK_SPACE);
					Thread.sleep(200);
				}
			}
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_empty_entries_in_custom_attributes");
		}
	}

	@When("^Enter invalid start date in entities$")
	public void enter_invalid_start_date_in_entities() throws Throwable {
		try {
			String ENT_CollUniqueID = objExcelUtils.getCellValueUsingColName("ENT_CollUniqueID",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objRepEntityColUniqueId"), ENT_CollUniqueID);
			String ReportingEntityName = objExcelUtils.getCellValueUsingColName("ENT_NAME", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objRepEntityName"),
					ReportingEntityName);
			String ReportingEntityType = objExcelUtils.getCellValueUsingColName("ENT_EntityType",
					Integer.parseInt(rowNoGbl));
			objCBF.selectFirstListItem(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objRepEntityType"), ReportingEntityType);
			String StartDate = objExcelUtils.getDateCellValueUsingColName("ENT_StartDate_Negative",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objRepEntityStartDate"), StartDate);
			String EndDate = objExcelUtils.getDateCellValueUsingColName("ENT_EndDate", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objRepEntityEndDate"), EndDate);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_invalid_start_date_in_entities");
		}
	}

	@When("^Enter start date after end date in entities$")
	public void enter_start_date_after_end_date_in_entities() throws Throwable {
		try {
			String ReportingEntityName = objExcelUtils.getCellValueUsingColName("ENT_NAME", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDefiniton.getLocator("objRepEntityName"),
					ReportingEntityName);
			String ReportingEntityType = objExcelUtils.getCellValueUsingColName("ENT_EntityType_Edit",
					Integer.parseInt(rowNoGbl));
			objCBF.selectFirstListItem(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objRepEntityType"), ReportingEntityType);
			String StartDate = objExcelUtils.getDateCellValueUsingColName("ENT_StartDate_Edit",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objRepEntityStartDate"), StartDate);
			String EndDate = objExcelUtils.getDateCellValueUsingColName("ENT_EndDate_Edit_Negative",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objRepEntityEndDate"), EndDate);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_start_date_after_end_date_in_entities");
		}
	}

	@When("^Enter valid from date after valid to date in entities$")
	public void enter_validFrom_date_after_ValidTo_date_in_entities() throws Throwable {
		try {
			String ReportingEntityType = objExcelUtils.getCellValueUsingColName("ENT_EntityType_Edit",
					Integer.parseInt(rowNoGbl));
			String attrVal1 = objExcelUtils.getCellValueUsingColName("ATTR1_Value", Integer.parseInt(rowNoGbl));
			String repCode1 = objExcelUtils.getCellValueUsingColName("ATTR1_IsRepCode", Integer.parseInt(rowNoGbl));
			String validFrom1 = objExcelUtils.getDateCellValueUsingColName("ATTR1_ValidFrom",
					Integer.parseInt(rowNoGbl));
			String validTo1 = objExcelUtils.getDateCellValueUsingColName("ATTR1_ValidTo_Negative",
					Integer.parseInt(rowNoGbl));
			objDataDef.EnterAttributeDetails(driver, ReportingEntityType, test, extent, StartDate, "1", attrVal1,
					repCode1, validFrom1, validTo1);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_validFrom_date_after_ValidTo_date_in_entities");
		}
	}

	@Then("^Verify error message for start date$")
	public void verify_error_message_for_start_date() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String strExpTxt = objExcelUtils.getCellValueUsingColName("ErrorMessage_StartDateLimit",
					Integer.parseInt(rowNoGbl));
			Thread.sleep(1000);
			objCBF.verifyErrorText(driver, test, strFilePath, extent, rowNoGbl, date1, strExpTxt);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_error_message_for_start_date");
		}
	}

	@Then("^Verify error message for valid from date$")
	public void verify_error_message_for_valid_from_date() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String strExpTxt = objExcelUtils.getCellValueUsingColName("ErrorMessage_ValidFromDateLimit",
					Integer.parseInt(rowNoGbl));
			Thread.sleep(1000);
			objCBF.verifyErrorText(driver, test, strFilePath, extent, rowNoGbl, date1, strExpTxt);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_error_message_for_valid_from_date");
		}
	}

	@When("^Upload incorrect headers CSV File$")
	public void upload_incorrect_headers_CSV_File() throws Throwable {
		try {
			String FilePath = objExcelUtils.getCellValueUsingColName("CSV-File_To_Upload_Negative",
					Integer.parseInt(rowNoGbl));
			objDataDef.uploadTemplate(driver, rowNoGbl, test, FilePath,
					objPageReadDefiniton.getLocator("objBrowseFileBtn"));
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : upload_incorrect_headers_CSV_File");
		}
	}

	@Then("^Verify error message for incorrect headers$")
	public void verify_error_message_for_incorrect_headers() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String strExpTxt = objExcelUtils.getCellValueUsingColName("ErrorMessage_IncorrectHeaders",
					Integer.parseInt(rowNoGbl));
			Thread.sleep(1000);
			objCBF.verifyErrorForIncorrectCSV(driver, test, strFilePath, extent, rowNoGbl, date1, strExpTxt);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_error_message_for_incorrect_headers");
		}
	}

	@When("^Enter duplicate Entity group details$")
	public void enter_duplicate_Entity_group_details() throws Throwable {
		String colName = objExcelUtils.getCellValueUsingColName("EG_Name", Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objAddEntityGroupName"), colName);
		String entityGrpDesc = objExcelUtils.getCellValueUsingColName("EG_Description", Integer.parseInt(rowNoGbl));
		objCBF.enterData(driver, test, date1, rowNoGbl, extent,
				objPageReadDefiniton.getLocator("objAddEntityGroupDesc"), entityGrpDesc);
	}

	@Then("^Verify error message for entity exists$")
	public void verify_error_message_for_entity_exists() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String strExpMsg = objExcelUtils.getCellValueUsingColName("ErrorMessage_EntityExists",
					Integer.parseInt(rowNoGbl));
			Thread.sleep(1000);
			objCBF.verifyErrorMessage(driver, test, strFilePath, extent, rowNoGbl, date1, strExpMsg);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_error_message_for_entity_exists");
		}
	}

	@When("^Enter invalid valid to date$")
	public void enter_invalid_valid_to_date() throws Throwable {
		String validFrom = objExcelUtils.getDateCellValueUsingColName("EG_ValidFrom", Integer.parseInt(rowNoGbl));
		String validTo = objExcelUtils.getDateCellValueUsingColName("EG_ValidTo_Negative", Integer.parseInt(rowNoGbl));
		objDataDef.EditAssignedEntitiesNegative(driver, rowNoGbl, test, extent, date1, "1", validFrom, validTo);
	}

	@When("^Enter empty start date in Cycles$")
	public void enter_empty_start_date_in_Cycles() throws Throwable {
		try {
			String cycleName = objExcelUtils.getDateCellValueUsingColName("CY_Name", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliCycleAddName"), cycleName);
			String startDate = objExcelUtils.getDateCellValueUsingColName("CY_ValidFrom_Negative",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliCycleStartDate"), startDate);
			String endDate = objExcelUtils.getDateCellValueUsingColName("CY_ValidTo", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliCycleEndDate"), endDate);
			String description = objExcelUtils.getCellValueUsingColName("CY_Description", Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliCycleDescription"), description);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_empty_start_date_in_Cycles");
		}
	}

	@When("^Enter past end date in Cycles$")
	public void enter_past_end_date_in_Cycles() throws Throwable {
		try {
			String endDate = objExcelUtils.getDateCellValueUsingColName("CY_ValidTo_Negative",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliCycleEditEndDate"), endDate);
			String description = objExcelUtils.getCellValueUsingColName("CY_Description_Edit",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliCycleEditDescription"), description);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_past_end_date_in_Cycles");
		}
	}

	@Then("^Verify error message for past end date$")
	public void verify_error_message_for_past_end_date() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String strExpMsg = objExcelUtils.getCellValueUsingColName("ErrorMessage_PastDate",
					Integer.parseInt(rowNoGbl));
			Thread.sleep(1000);
			objCBF.verifyErrorText(driver, test, strFilePath, extent, rowNoGbl, date1, strExpMsg);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_error_message_for_past_end_date");
		}
	}

	@When("^Enter outside cycle remittance date$")
	public void enter_outside_cycle_remittance_date() throws Throwable {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate localDate = LocalDate.now();
			String referenceDate = dtf.format(localDate).toString();
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliPerGrpReferenceDate"), referenceDate);
			String reportingCycle = objExcelUtils.getCellValueUsingColName("RO_RepCycle", Integer.parseInt(rowNoGbl));
			objCBF.selectLastItemFromListItem(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objObliPerGrpReportigCycle"));
			String entityGroup = objExcelUtils.getCellValueUsingColName("RO_EntityGroup", Integer.parseInt(rowNoGbl));
			objCBF.selectLastItemFromListItem(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objObliPerGrpEntityGroup"));
			String remittanceDate = objExcelUtils.getDateCellValueUsingColName("RO_RemittanceDate_Negative",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliPerGrpRemittanceDate"), remittanceDate);
			objCBF.selectCheckbox(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objModuleSelectionCheckbox"), "Y");
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_outside_cycle_remittance_date");
		}
	}

	@Then("^Verify error message for outside cycle remittance date$")
	public void verify_error_message_for_outside_cycle_remittance_date() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String strExpMsg = objExcelUtils.getCellValueUsingColName("ErrorMessage_RemittanceDateLimit",
					Integer.parseInt(rowNoGbl));
			Thread.sleep(1000);
			objCBF.verifyErrorMessage(driver, test, strFilePath, extent, rowNoGbl, date1, strExpMsg);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_error_message_for_outside_cycle_remittance_date");
		}
	}

	@When("^Enter invalid remittance offset$")
	public void enter_invalid_remittance_offset() throws Throwable {
		try {
			objCBF.selectRadioButton(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objObliPerGrpEditoffsetRadioBtn"));
			String remittanceOffset = objExcelUtils.getCellValueUsingColName("RO_RemittanceOffset_Negative",
					Integer.parseInt(rowNoGbl));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent,
					objPageReadDefiniton.getLocator("objObliPerGrpEditRemittanceoffset"), remittanceOffset);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_invalid_remittance_offset");
		}
	}

	@Then("^Verify error message for cycle remittance offset$")
	public void verify_error_message_for_cycle_remittance_offset() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String strExpMsg = objExcelUtils.getCellValueUsingColName("ErrorMessage_RemittanceOffsetLimit",
					Integer.parseInt(rowNoGbl));
			Thread.sleep(1000);
			objCBF.verifyErrorText(driver, test, strFilePath, extent, rowNoGbl, date1, strExpMsg);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_error_message_for_cycle_remittance_offset");
		}
	}

	@Then("^Verify \"([^\"]*)\" checkbox is disabled$")
	public void verify_checbox_is_disabled(String value) throws Throwable {
		try {
			objCBF.verifyCheckboxIsDisabled(driver, test, extent, rowNoGbl, date1, value);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_checbox_is_disabled");
		}
	}

	@Then("^Verify \"([^\"]*)\" checkbox is not selected$")
	public void verify_checbox_is_not_selected(String value) throws Throwable {
		try {
			objCBF.verifyCheckboxIsNotSelected(driver, test, extent, rowNoGbl, date1, value);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_checbox_is_not_selected");
		}
	}

	@Then("^Verify \"([^\"]*)\" button is disabled$")
	public void verify_button_is_disabled(String value) throws Throwable {
		try {
			objCBF.verifyButtonIsDisabled(driver, test, extent, rowNoGbl, date1, value);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_button_is_disabled");
		}
	}

	@Then("^Verify \"([^\"]*)\" checkbox is enabled$")
	public void verify_checkbox_is_enabled(String value) throws Throwable {
		try {
			objCBF.verifyCheckboxIsEnabled(driver, test, extent, rowNoGbl, date1, value);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_checkbox_is_enabled");
		}
	}

	@When("^Enter Edit DISC export settings details$")
	public void enter_Edit_DISC_export_settings_details() throws Throwable {
		try {
			String DISC_Target = objExcelUtils.getCellValueUsingColName("DISC_Target", Integer.parseInt(rowNoGbl));
			String Target_Path = objExcelUtils.getCellValueUsingColName("Target_Path", Integer.parseInt(rowNoGbl));
			String Interval = objExcelUtils.getCellValueUsingColName("Interval", Integer.parseInt(rowNoGbl));

			objCBF.CheckCheckbox(driver, test, extent, rowNoGbl, date1, objPageReadDISCExport.getLocator("objActive"));
			objCBF.selectItemFromList(driver, test, extent, rowNoGbl, date1,
					objPageReadDISCExport.getLocator("objDiscTarget"), DISC_Target);
			objCBF.CheckCheckbox(driver, test, extent, rowNoGbl, date1,
					objPageReadDISCExport.getLocator("objDataMerge"));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDISCExport.getLocator("objTargetPath"),
					Target_Path);
			objCBF.selectItemFromList(driver, test, extent, rowNoGbl, date1,
					objPageReadDISCExport.getLocator("objInterval"), Interval);
			String SheduleTime = objExcelUtils.getCellValueUsingColName("DISC_SheduleTime", Integer.parseInt(rowNoGbl));
			objCBF.selectListItem(driver, test, extent, rowNoGbl, date1,
					objPageReadDefiniton.getLocator("objDISCSheduleTime"), SheduleTime);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : enter_Edit_DISC_export_settings_details");
		}
	}

	@Then("^Verify updates successfully message$")
	public void verify_updates_successfully_message() throws Throwable {
		try {
			String strExpMsg = objExcelUtils.getCellValueUsingColName("DISC_UpdateSuccessfully_ExpectedMessage",
					Integer.parseInt(rowNoGbl));
			Thread.sleep(1000);
			objCBF.verifySuccessMessageOnScreen(driver, test, strFilePath, extent, rowNoGbl, date1, strExpMsg);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_updates_successfully_message");
		}
	}

	@Then("^Verify export triggered success message$")
	public void verify_export_triggered_success_message() throws Throwable {
		try {
			String strExpMsg = objExcelUtils.getCellValueUsingColName("DISC_ExportTriggeredSuccess_ExpectedMessage",
					Integer.parseInt(rowNoGbl));
			Thread.sleep(2000);
			objCBF.verifySuccessMessageOnScreen(driver, test, strFilePath, extent, rowNoGbl, date1, strExpMsg);
			Thread.sleep(30000);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_export_triggered_success_message");
		}
	}

	@Then("^Verify value on DISC screen for \"([^\"]*)\"$")
	public void verify_Verify_value_on_DISC_screen_for(String value) throws Throwable {
		try {
			String labName = objExcelUtils.getCellValueUsingColName("DISC_DataLabName", Integer.parseInt(rowNoGbl));
			String interval = objExcelUtils.getCellValueUsingColName("DISC_Interval", Integer.parseInt(rowNoGbl));
			String SheduleTime = objExcelUtils.getCellValueUsingColName("DISC_SheduleTime", Integer.parseInt(rowNoGbl));
			if (value.equals("Target Data Lab for scheduled export")) {
				objCBF.verifyValueOnScreen(driver, test, extent, rowNoGbl, date1, labName, value);
			} else if (value.equals("Interval")) {
				objCBF.verifyValueOnScreen(driver, test, extent, rowNoGbl, date1, interval, value);
			} else if (value.equals("Fulfilled Obligations until")) {
				objCBF.verifyValueOnScreen(driver, test, extent, rowNoGbl, date1, SheduleTime, value);
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_Verify_value_on_DISC_screen_for");
		}
	}

	@When("^Search record for DISC process status \"([^\"]*)\"$")
	public void search_record_for_DISC_process_status(String value) throws Throwable {
		try {
			objCBF.searchRecordForProcessStatusInStatus(driver, test, extent, rowNoGbl, date1, value);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : search_record_for_DISC_process_status");
		}
	}

	@Then("^Verify new entry created in DISC List \"([^\"]*)\"$")
	public void verify_new_entry_created_in_DISC_List(String ColName) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			if (ColName.equals("processStatus")) {
				String status = objExcelUtils.getCellValueUsingColName("DISC_TransmissionStatus",
						Integer.parseInt(rowNoGbl));
				objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName, status);
			} else if (ColName.equals("triggeredBy")) {
				String userName = objExcelUtils.getCellValueUsingColName("UserName", Integer.parseInt(rowNoGbl));
				objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName, userName);
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_new_entry_created_in_DISC_List");
		}
	}

	@Then("^Verify new entry created in DISC List for cancelled \"([^\"]*)\"$")
	public void verify_new_entry_created_in_DISC_List_for_cancelled(String ColName) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			if (ColName.equals("processStatus")) {
				String status = objExcelUtils.getCellValueUsingColName("DISC_CancelledStatus",
						Integer.parseInt(rowNoGbl));
				objCBF.VerifyNewRecordInTheList(driver, test, extent, rowNoGbl, date1, ColName, status);
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_new_entry_created_in_DISC_List_for_cancelled");
		}
	}

	@When("^Search record for DISC triggered by$")
	public void search_record_for_DISC_triggered_by() throws Throwable {
		try {
			String userName = ExcelUtils.getCellValueUsingColName("UserName", Integer.parseInt(rowNoGbl));
			objCBF.searchForRecordsForFilterValue(driver, test, extent, rowNoGbl, date1, userName);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : search_record_for_DISC_triggered_by");
		}
	}

	@Then("^Verify export cancelled success message$")
	public void verify_export_cancelled_success_message() throws Throwable {
		try {
			String strExpMsg = objExcelUtils.getCellValueUsingColName("DISC_ExportCancelledSuccess_ExpectedMessage",
					Integer.parseInt(rowNoGbl));
			Thread.sleep(1000);
			objCBF.verifySuccessMessageOnScreen(driver, test, strFilePath, extent, rowNoGbl, date1, strExpMsg);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_export_cancelled_success_message");
		}
	}

	@Then("^Verify submission \"([^\"]*)\" permission$")
	public void verify_submission_permission(String permissionName) throws Throwable {
		try {
			if (permissionName.equals("Submission")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Submission",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermission(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Submission List")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_SubmissionList",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForSubmissionList(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Sub_View", Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForSubmissionList(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Upload")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Sub_ZipFileUpload",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForSubmissionList(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Download")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Sub_Download",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForSubmissionList(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_submission_permission");
		}
	}

	@Then("^Verify file vault \"([^\"]*)\" permission$")
	public void verify_file_vault_permission(String permissionName) throws Throwable {
		try {
			if (permissionName.equals("File Vault")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_FileVault",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForFileVault(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_FileVault_View",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForFileVault(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View Validation Result")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_FileVault_ViewValidationResult",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForFileVault(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Download")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_FileVault_Download",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForFileVault(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_file_vault_permission");
		}
	}

	@Then("^Verify definitions \"([^\"]*)\" permission$")
	public void verify_definitions_permission(String permissionName) throws Throwable {
		try {
			if (permissionName.equals("Definitions")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Definations",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermission(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Add Data Collection")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Def_AddDataCollection",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermission(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_definitions_permission");
		}
	}

	@Then("^Verify metadata \"([^\"]*)\" permission$")
	public void verify_metadata_permission(String permissionName) throws Throwable {
		try {
			if (permissionName.equals("Metadata")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Metadata", Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForMetadata(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Metadata_View",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForMetadata(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Edit")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Metadata_Edit",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForMetadata(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Import Metadata Result")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_ImportMetadataResult",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForMetadata(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View Import Metadata Result")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_ViewImportMetadataResult",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForMetadata(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View Results")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Metadata_ViewResults",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForMetadata(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Cancel")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Metadata_Cancel",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForMetadata(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_metadata_permission");
		}
	}

	@Then("^Verify module \"([^\"]*)\" permission$")
	public void verify_module_permission(String permissionName) throws Throwable {
		try {
			if (permissionName.equals("Module")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Modules", Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForModules(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Modules_View",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForModules(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Add")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Modules_Add",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForModules(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Edit")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Modules_Edit",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForModules(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Add New Version")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Modules_AddNewVersion",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForModules(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Upload Template")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Modules_UploadTemplete",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForModules(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_module_permission");
		}
	}

	@Then("^Verify data points \"([^\"]*)\" permission$")
	public void verify_data_points_permission(String permissionName) throws Throwable {
		try {
			if (permissionName.equals("Data Points")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_DataPoints",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForDataPoints(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_DataPoints_View",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForDataPoints(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Edit")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_DataPoints_Edit",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForDataPoints(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Upload")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_DataPoints_UploadDataPoint",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForDataPoints(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_data_points_permission");
		}
	}

	@Then("^Verify entity \"([^\"]*)\" permission$")
	public void verify_entity_permission(String permissionName) throws Throwable {
		try {
			if (permissionName.equals("Entity")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Entity", Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForEntity(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Entity_View",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForEntity(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Add")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Entity_Add",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForEntity(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Assign Custom Attributes")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Entity_AssignCustomAttribute",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForEntity(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Edit")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Entity_Edit",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForEntity(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Import Entities")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Entity_ImportEntities",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForEntity(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Export")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Entity_Export",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForEntity(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View Entity Attributes")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Entity_ViewEntityAttributes",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForEntity(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Delete")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Entity_Delete",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForEntity(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_entity_permission");
		}
	}

	@Then("^Verify custom attribute \"([^\"]*)\" permission$")
	public void verify_custom_attribute_permission(String permissionName) throws Throwable {
		try {
			if (permissionName.equals("Custom Attributes ")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_CustomAttributes",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForCustomAttribute(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_CustomAttributes_View",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForCustomAttribute(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Add")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_CustomAttributes_Add",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForCustomAttribute(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Edit")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_CustomAttributes_Edit",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForCustomAttribute(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Delete")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_CustomAttributes_Delete",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForCustomAttribute(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_custom_attribute_permission");
		}
	}

	@Then("^Verify entity group \"([^\"]*)\" permission$")
	public void verify_entity_group_permission(String permissionName) throws Throwable {
		try {
			if (permissionName.equals("Entity Group")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_EntityGroup",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForEntityGroup(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_EntityGroup_View",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForEntityGroup(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Add")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_EntityGroup_Add",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForEntityGroup(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Edit")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_EntityGroup_Edit",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForEntityGroup(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Delete")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_EntityGroup_Delete",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForEntityGroup(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View EG Assignment")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_EntityGroup_ViewEGAssignment",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForEntityGroup(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Edit  EG Assignment")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_EntityGroup_EditEGAssignment",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForEntityGroup(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_entity_group_permission");
		}
	}

	@Then("^Verify data dictionary \"([^\"]*)\" permission$")
	public void verify_data_dictionary_permission(String permissionName) throws Throwable {
		try {
			if (permissionName.equals("Dictionaries")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Dictionaries",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForDataDictionary(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Add Data Dictionary")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Dictionary_Add",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForDataDictionary(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Delete Data Dictionary")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Dictionary_Delete",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForDataDictionary(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Dictionary Metadata")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Dictionary_Metadata",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForDataDictionary(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Dictionary_Metadata_View",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForDataDictionary(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Edit")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Dictionary_Metadata_Edit",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForDataDictionary(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_data_dictionary_permission");
		}
	}

	@Then("^Verify reporting cycle \"([^\"]*)\" permission$")
	public void verify_reporting_cycle_permission(String permissionName) throws Throwable {
		try {
			if (permissionName.equals("Reporting Cycle")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_ReportingCycle",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForReportingCycle(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_ReportingCycle_View",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForReportingCycle(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Add")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_ReportingCycle_Add",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForReportingCycle(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Edit")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_ReportingCycle_Edit",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForReportingCycle(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Delete")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_ReportingCycle_Delete",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForReportingCycle(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_reporting_cycle_permission");
		}
	}

	@Then("^Verify obligation per group \"([^\"]*)\" permission$")
	public void verify_obligation_per_group_permission(String permissionName) throws Throwable {
		try {
			if (permissionName.equals("Obligation per Group")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_ObligationPerGrp",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForObligationPerGroup(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_ObligationPerGrp_View",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForObligationPerGroup(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Add")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_ObligationPerGrp_Add",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForObligationPerGroup(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Edit")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_ObligationPerGrp_Edit",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForObligationPerGroup(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Delete")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_ObligationPerGrp_Delete",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForObligationPerGroup(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_obligation_per_group_permission");
		}
	}

	@Then("^Verify obligation per entity \"([^\"]*)\" permission$")
	public void verify_obligation_per_entity_permission(String permissionName) throws Throwable {
		try {
			if (permissionName.equals("Obligation per Entity")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_ObligationPerEntity",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForObligationPerEntity(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_ObligationPerEntity_View",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForObligationPerEntity(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_obligation_per_entity_permission");
		}
	}

	@Then("^Verify validations \"([^\"]*)\" permission$")
	public void verify_validations_permission(String permissionName) throws Throwable {
		try {
			if (permissionName.equals("Validations")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Validations",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForValidations(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Validations_View",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForValidations(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Edit")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Validations_Edit",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForValidations(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Enable")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Validations_Enable",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForValidations(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Disable")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Validations_Disable",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForValidations(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Delete")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Validations_Delete",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForValidations(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Add")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Validations_Add",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForValidations(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Import Validation")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Validations_ImportValidation",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForValidations(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Export Validation")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Validations_ExportValidation",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForValidations(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			}

		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_validations_permission");
		}
	}

	@Then("^Verify disc export \"([^\"]*)\" permission$")
	public void verify_disc_export_permission(String permissionName) throws Throwable {
		try {
			if (permissionName.equals("Disc Export")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_DiscExport",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForDiscExport(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_DiscExport_View",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForDiscExport(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Edit")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_DiscExport_Edit",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForDiscExport(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Export")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_DiscExport_Export",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForDiscExport(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Cancel")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_DiscExport_Cancel",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForDiscExport(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_disc_export_permission");
		}
	}

	@Then("^Verify manage user \"([^\"]*)\" permission$")
	public void verify_user_and_roles_permission(String permissionName) throws Throwable {
		try {
			if (permissionName.equals("Users & Roles")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_UserRoles",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermission(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Manage User Assignments to data collections")) {
				String flag = objExcelUtils.getCellValueUsingColName(
						"Permission_ManageUserAssignmentstodatacollections", Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForManageUser(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_UserRoles_View",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForManageUser(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Assign")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_UserRoles_Assign",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForManageUser(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Un-Assign")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_UserRoles_UnAssign",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForManageUser(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_user_and_roles_permission");
		}
	}

	@Then("^Verify roles \"([^\"]*)\" permission$")
	public void verify_roles_permission(String permissionName) throws Throwable {
		try {
			if (permissionName.equals("Roles")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Roles", Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForRoles(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("View")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Roles_View",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForRoles(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			} else if (permissionName.equals("Edit")) {
				String flag = objExcelUtils.getCellValueUsingColName("Permission_Roles_Edit",
						Integer.parseInt(rowNoGbl));
				objCBF.checkPermissionForRoles(driver, test, extent, rowNoGbl, date1, permissionName,
						Boolean.parseBoolean(flag));
			}
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_roles_permission");
		}
	}

	@When("^Click on user role$")
	public void click_on_user_role() throws Throwable {
		try {
			String user = objExcelUtils.getCellValueUsingColName("RoleName", Integer.parseInt(rowNoGbl));
			objCBF.clickOnLink(driver, test, extent, rowNoGbl, date1, user);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : click_on_user_role");
		}
	}

	@When("^Compare the results of uploaded rules \"([^\"]*)\"$")
	public void compare_the_results_of_uploaded_rules(String workbookName) throws Throwable {
		try {
			String UploadedFileName = objExcelUtils.getCellValueUsingColName("BusinessRule_fileName",
					Integer.parseInt(rowNoGbl));
			String FileToCompareName = objExcelUtils.getCellValueUsingColName("FileToCompare",
					Integer.parseInt(rowNoGbl));
			objBusinessValRule.compareValidationRuleResults(driver, test, extent, rowNoGbl, date1, UploadedFileName,
					FileToCompareName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : compare_the_results_of_uploaded_rules");
		}

	}

	@Then("^Edit DISC export settings$")
	public void Edit_DISC_export_settings() throws Throwable {
		try {
			String DISC_Target = objExcelUtils.getCellValueUsingColName("DISC_Target", Integer.parseInt(rowNoGbl));
			String Target_Path = objExcelUtils.getCellValueUsingColName("Target_Path", Integer.parseInt(rowNoGbl));
			String Interval = objExcelUtils.getCellValueUsingColName("Interval", Integer.parseInt(rowNoGbl));

			objCBF.CheckCheckbox(driver, test, extent, rowNoGbl, date1, objPageReadDISCExport.getLocator("objActive"));
			objCBF.selectItemFromList(driver, test, extent, rowNoGbl, date1,
					objPageReadDISCExport.getLocator("objDiscTarget"), DISC_Target);
			objCBF.CheckCheckbox(driver, test, extent, rowNoGbl, date1,
					objPageReadDISCExport.getLocator("objDataMerge"));
			objCBF.enterData(driver, test, date1, rowNoGbl, extent, objPageReadDISCExport.getLocator("objTargetPath"),
					Target_Path);
			objCBF.selectItemFromList(driver, test, extent, rowNoGbl, date1,
					objPageReadDISCExport.getLocator("objInterval"), Interval);

		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Edit_DISC_export_settings");
		}
	}

	@Then("^Verify the \"([^\"]*)\" Screen Name$")
	public void ScreenDisplayed(String screenName) throws Throwable {
		try {
			objCBF.Verify_Screen_Name(driver, test, extent, rowNoGbl, date1, screenName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : ScreenDisplayed");
		}
	}

	@Given("^Launch Casper Application with Requestly$")
	public void LaunchCasper() throws Throwable {
		try {
			String strURL = objProRead.getData("URL");
			String browserType = objProRead.getData("Browser");
			driver = objCBF.getDriver(driver, browserType);
			// Create driver for FF browser and open a URL
			driver.get(strURL);
			// Log the action performed in the extent report
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : LaunchCasper");
		}
	}

	@When("^Upload RA template$")
	public void upload_RA_template() throws Throwable {
		try {

			File obj = new File("src/test/resources/FilesToUpload/STE09_Liquidity_t1_RA_template.xlsx");
			String Path = obj.getAbsolutePath();

			driver.findElement(By.xpath("//input[@class='upload ng-untouched ng-pristine ng-valid']")).sendKeys(Path);

		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : upload_RA_template");
		}
	}

	@Then("^Click on close button$")
	public void Click_Close() throws Throwable {
		try {

			driver.findElement(By.xpath("//button[@type='button'][@class='btn btn-secondary']")).click();
			// Thread.sleep(1000);

		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Click_Close");
		}
	}

	@When("^Filter record for DISC process status Cancelled$")
	public void Filter_record_for_DISC_process_status_Cancelled() throws Throwable {
		try {
			objCBF.searchRecordForProcessStatus(driver, test, extent, rowNoGbl, date1);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Filter_record_for_DISC_process_status_Cancelled");
		}
	}

	@Then("^Verify \"([^\"]*)\" action item not present$")
	public void Verify_Action_Item_Not_Present(String actionName) throws Throwable {
		try {
			objCBF.VerifyActionItemNotVisible(driver, test, extent, rowNoGbl, date1, actionName);
			System.out.println(actionName + " Action is not present");
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify_Action_Item_Not_Present");
		}
	}

	@Then("^Verify tab not present on home screen \"([^\"]*)\"$")
	public void verify_tabs_not_present_on_home_screen(String Tab1) throws Throwable {
		try {
			objCBF.VerifyLinkNotExist(driver, test, extent, rowNoGbl, date1, Tab1);
			System.out.println(Tab1 + " tab not present on home page");
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_tabs_not_present_on_home_screen");
		}
	}

	@Then("^Search for User \"([^\"]*)\" in User Assignment$")
	public void Search_for_User_in_User_Assignment(String UserName) throws Throwable {
		try {
			String UName = objExcelUtils.getCellValueUsingColName(UserName, Integer.parseInt(rowNoGbl));
			objCBF.SearchForUserInUserAssignment(driver, test, extent, rowNoGbl, date1, UName);
			System.out.println(UserName + " is successfully searched");
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Search_for_User_in_User_Assignment");
		}
	}

	@Then("^Verify \"([^\"]*)\" button exists \"([^\"]*)\"$")
	public void verify_button_exists_WithTCID(String btnName, String TCID) throws Throwable {
		try {
			objCBF.VerifyButtonExistWithTCID(driver, test, extent, rowNoGbl, date1, btnName, TCID);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_button_exists_WithTCID");
		}
	}

	@Then("^Verify \"([^\"]*)\" Tempering error$")
	public void verify_URL_Tempering_Error(String URL) throws Throwable {
		try {
			String URL1 = objExcelUtils.getCellValueUsingColName(URL, Integer.parseInt(rowNoGbl));
			objCBF.VerifyURLTempering(driver, test, extent, rowNoGbl, date1, URL1);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_URL_Tempering_Error");
		}
	}

	@Then("^Add \"([^\"]*)\" and \"([^\"]*)\"$")
	public void test_case_description(String TCDescription, String UserNme) {
		try {
			String User = objExcelUtils.getCellValueUsingColName(UserNme, Integer.parseInt(rowNoGbl));
			String Description = objExcelUtils.getCellValueUsingColName(TCDescription, Integer.parseInt(rowNoGbl));
			String strURL = objProRead.getData("URL");
			String browserType = objProRead.getData("Browser");

			objCBF.testcasedescription(driver, test, extent, rowNoGbl, date1, User, Description, strURL, browserType);

		} catch (Exception e) {
			System.out.println("In catch block " + e);
		}

	}

	@Then("^Verify \"([^\"]*)\" action is disabled$")
	public void verify_action_disabled(String actionName) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			objCBF.verifyActionDisabled(driver, test, extent, rowNoGbl, date1, actionName);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_action_disabled");
		}
	}

	@Then("^Search record with Cycle name filter$")
	public void search_record_with_Cycle_name_filter() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);
			String colName = "Cycle";
			String Cycle = ExcelUtils.getCellValueUsingColName(colName, Integer.parseInt(rowNoGbl));
			objCBF.searchRecordWithCycleNameFilter(driver, test, extent, rowNoGbl, date1, Cycle);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : search_record_with_Cycle_name_filter");
		}
	}

	@Then("^Verify Expand-Collapse link \"([^\"]*)\"$")
	public void verify_Expand_Collapse_Link(String linkText) throws Throwable {
		try {
			objCBF.VerifyExpandCollapseLink(driver, test, extent, rowNoGbl, date1, linkText);
			System.out.println(linkText + "tab verified");
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_Expand_Collapse_Link " + linkText);
		}
	}

	@Then("^Select Discussion Topic record$")
	public void Select_Discussion_Topic_Record() throws Throwable {
		try {
			objCBF.selectRecordInTable(driver, test, extent, rowNoGbl, date1);
			System.out.println("Record Selected");
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Select_Discussion_Topic_Record ");
		}
	}

	@Then("^Verify \"([^\"]*)\" Check box exist$")
	public void verify_Check_Box_Existance(String checkBoxName) throws Throwable {
		try {
			objCBF.verifyCheckBoxExistance(driver, test, extent, rowNoGbl, date1, checkBoxName);
			System.out.println("Check Box Exist");
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_Check_Box_Existance ");
		}
	}

	@Then("^Verify \"([^\"]*)\" Edit box exist$")
	public void verify_Edit_Box_Existance(String editBoxName) throws Throwable {
		try {
			objCBF.verifyEditBoxExistance(driver, test, extent, rowNoGbl, date1, editBoxName);
			System.out.println("Edit Box Exist");
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_Edit_Box_Existance ");
		}
	}

	@Then("^Scroll down \"([^\"]*)\"$")
	public void scroll_Down(int numOfPixels) throws Throwable {
		try {
			objCBF.scrollDown(driver, test, extent, rowNoGbl, date1, numOfPixels);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : scroll_Down ");
		}
	}

	@Then("^Scroll Up \"([^\"]*)\"$")
	public void scroll_Up(int numOfPixels) throws Throwable {
		try {
			objCBF.scrollUp(driver, test, extent, rowNoGbl, date1, numOfPixels);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : scroll_Up ");
		}
	}

	@Then("^Verify Comment section \"([^\"]*)\" Action$")
	public void verify_Comment_Section_Action(String actionName) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			objCBF.verifyCommentSectionAction(driver, test, extent, rowNoGbl, date1, actionName);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_Comment_Section_Action");
		}
	}

	@Then("^Filter Module Record with Status \"([^\"]*)\"$")
	public void Filter_Module_record_With_status(String value) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			objCBF.filterModuleRecordWithStatus(driver, test, extent, rowNoGbl, date1, value);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Filter_Module_record_With_status");
		}
	}

	@Then("^Verify action \"([^\"]*)\" is disabled$")
	public void verify_Action_is_Disabled(String value) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			objCBF.verifyActionItemIsDisabled(driver, test, extent, rowNoGbl, date1, value);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_Action_is_Disabled");
		}
	}

	@Then("^Verify error message for Module Delete$")
	public void verify_error_message_for_Module_Delete() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String strExpMsg = objExcelUtils.getCellValueUsingColName("ErrorMessage_EntryExist",
					Integer.parseInt(rowNoGbl));
			Thread.sleep(1000);
			objCBF.verifyErrorMessage(driver, test, strFilePath, extent, rowNoGbl, date1, strExpMsg);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_error_message_for_Module_Delete");
		}
	}

	@Then("^Verify Module Delete success message$")
	public void verify_Module_Delete_success_message() throws Throwable {
		try {
			String strExpMsg = objExcelUtils.getCellValueUsingColName("Expected_Success_Message",
					Integer.parseInt(rowNoGbl));
			Thread.sleep(1000);
			objCBF.verifySuccessMessageOnScreen(driver, test, strFilePath, extent, rowNoGbl, date1, strExpMsg);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_Module_Delete_success_message");
		}
	}

	@Then("^Add validation rule with Severity \"([^\"]*)\"$")
	public void add_validation_rule(String strSeverity) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			objCBF.addValidationRule(driver, test, extent, rowNoGbl, date1, strSeverity);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : add_validation_rule");
		}
	}

	@Then("^Verify \"([^\"]*)\" Message on screen$")
	public void verify_Message_On_Screen(String strMessage) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			objCBF.verifyMessageOnScreen(driver, test, extent, rowNoGbl, date1, strMessage);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_Message_On_Screen");
		}
	}

	@Then("^Filter User DC Assignment by Assignment value \"([^\"]*)\"$")
	public void Filter_User_Assignment_By(String Assignment) throws Throwable {
		try {
			System.out.println("Filter applied for Assignment value : " + Assignment);
			objCBF.FilterUserDCAssignmentByAssignmentValue(driver, test, extent, rowNoGbl, date1, Assignment);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Filter_User_Assignment_By");
		}
	}

	@Then("^Delete created Validation Rule$")
	public void Delete_created_Validation_Rule() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			String strRuleId = objExcelUtils.getCellValueUsingColName("Rule_Id", Integer.parseInt(rowNoGbl));
			objCBF.deleteCreatedValidationRule(driver, test, extent, rowNoGbl, date1, strRuleId);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Delete_created_Validation_Rule");
		}
	}

	@Then("^Select all module status in status column$")
	public void Select_all_module_status_in_status_column() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			objCBF.selectAllModuleStatus(driver, test, extent, rowNoGbl, date1);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Select_all_module_status_in_status_column");
		}
	}

	@Then("^Wait for \"([^\"]*)\" Seconds$")
	public void Wait_For_Seconds(int seconds) throws Throwable {
		try {
			Thread.sleep(seconds);

		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : WaitForSeconds");
		}
	}
	
	@Then("^Verify new record added in the table$")
	public void Verify_new_record_added_in_the_table() throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			objCBF.VerifyNewRecordAddedInTheTable(driver, test, extent, rowNoGbl, date1);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Verify_new_record_added_in_the_table");
		}
	}
	@Then("^Verify and click on \"([^\"]*)\" pop up link$")
	public void verify_and_click_on_pop_up_link(String linkText) throws Throwable {
		try {
			objCBF.clickOnPopupLink(driver, test, extent, rowNoGbl, date1, linkText);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : verify_and_click_on_pop_up_link");
		}
	}
	@Then("^Add validation rule with Severity in E2E test \"([^\"]*)\"$")
	public void add_validation_rule_with_severity_in_E2E_test(String strSeverity) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			objCBF.addValidationRuleE2ETest(driver, test, extent, rowNoGbl, date1, strSeverity);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : add_validation_rule_with_severity_in_E2E_test");
		}
	}
	@Then("^Add Access Group \"([^\"]*)\"$")
	public void add_access_group(String wbname) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			objCBF.addAccessGroup(driver, test, extent, rowNoGbl, date1, wbname);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : add_access_group");
		}
	}
	
	@When("^Search data collection in Access group \"([^\"]*)\"$")
	public void search_data_collection_in_Access_group(String colName) throws Throwable {
		try {
			String DDName = ExcelUtils.getCellValueUsingColName(colName, Integer.parseInt(rowNoGbl));
			objCBF.searchDataCollectionAccessGroup(driver, test, extent, rowNoGbl, date1, DDName);
			;
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : search_data_collection_in_Access_group");
		}
	}

	@Then("^Edit assigned Entities \"([^\"]*)\" and \"([^\"]*)\"$")
	public void Edit_Assigned_Entities(String entity1, String entity2) throws Throwable {
		try {
//			String DDName = ExcelUtils.getCellValueUsingColName(colName, Integer.parseInt(rowNoGbl));
			objCBF.editAssignedEntities(driver, test, extent, rowNoGbl, date1, entity1, entity2);
		} catch (Exception e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : Edit_Assigned_Entities");
		}
	}
	
	@Then("^Edit Access Group \"([^\"]*)\"$")
	public void edit_access_group(String wbname) throws Throwable {
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
			date = new Date();
			date1 = dateFormat.format(date);

			objCBF.editAccessGroup(driver, test, extent, rowNoGbl, date1, wbname);
		} catch (IOException e) {
			System.out.println("In catch block " + e);
			System.out.println("Step Failed : edit_access_group");
		}
	}
}
