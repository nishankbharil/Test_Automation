package Business_Functions;

public class Multi_Language {

}
/*


Scenario Outline:  0049.02  Allowed range for the sequential number (01-99) 
	Given Open Casper Application in Web Browser "Casper_UploadFile_016" "<RowNo>"
	When Upload the file one to Ninety Nine "<RowNo>"
	Then File Upload should be successful for valid sequence
Examples:
|RowNo|
|1|


Scenario Outline: 0010.24  Separate handling of single files with respect to errors
	Given Open Casper Application in Web Browser "Casper_UploadFile_004" "<RowNo>"
	When Upload the valid and invalid file in zip file "<RowNo>"
	Then File is accepted or rejected
Examples:
|RowNo|
|1|



Scenario Outline:  0048.15  Maximum lenght of each filename component
	Given Open Casper Application in Web Browser "Casper_UploadFile_013" "<RowNo>"
	When A file is uploaded "<RowNo>"
	Then Verify the maximum length of filename components
Examples:
|RowNo|
|1|

Scenario Outline:  0048.17  Mandatory filename components identify the same submission
	Given Open Casper Application in Web Browser "Casper_UploadFile_023" "<RowNo>"
	When A file is uploaded "<RowNo>"
	Then Mandatory filename components identify same submission
Examples:
|RowNo|
|1|


Scenario Outline:  0048.16  Allowed values or other restrictions
	Given Open Casper Application in Web Browser "Casper_UploadFile_024" "<RowNo>"
	When I Upload a File
	Then Verify File is Accepted when Data Collection is Present
Examples:
|RowNo|
|1|


Scenario Outline:  0010.21  No hierarchically organized zip-files (folders within folders)
               Given Open Casper Application in Web Browser "Casper_UploadFile_001" "<RowNo>"
               When I Upload a File
               Then Verify File Upload
Examples:
|RowNo|
|1|
|2|
|3|
|4|
|5|

Scenario Outline:  0010.22  Only one data collection per zip-file
               Given Open Casper Application in Web Browser "Casper_UploadFile_002" "<RowNo>"
               When I Upload a File
               Then Verify File Upload
Examples:
|RowNo|
|1|
|2|
|3|
|4|

Scenario Outline:  0010.25  Rejection of blank zip-files
               Given Open Casper Application in Web Browser "Casper_UploadFile_005" "<RowNo>"
               When I Upload a File
               Then Verify File Upload
Examples:
|RowNo|
|1|

Scenario Outline:  0010.12  Zip-file selection via dedicated button
               Given Open Casper Application in Web Browser "Casper_UploadFile_021" "<RowNo>"
               When I Select a File
               Then Verify File Selected on Upload Page
Examples:
|RowNo|
|1|

Scenario Outline:  0048.11  Mandatory filename components
               Given Open Casper Application in Web Browser "Casper_UploadFile_006" "<RowNo>"
               When I Upload a File
               Then Verify File is Rejected when File Type is Missing
Examples:
|RowNo|
|1|

Scenario Outline:  0048.11  Mandatory filename components
               Given Open Casper Application in Web Browser "Casper_UploadFile_007" "<RowNo>"
               When I Upload a File
               Then Verify File is Rejected when Data Collection and Reporting Cycle is Missing
Examples:
|RowNo|
|1|

Scenario Outline:  0048.11  Mandatory filename components
               Given Open Casper Application in Web Browser "Casper_UploadFile_008" "<RowNo>"
               When I Upload a File
               Then File Upload should be Successful
Examples:
|RowNo|
|1|

Scenario Outline:  0048.11  Mandatory filename components
               Given Open Casper Application in Web Browser "Casper_UploadFile_009" "<RowNo>"
               When I Upload a File
               Then Verify File is Rejected when Reference Date is Missing
Examples:
|RowNo|
|1|



Scenario Outline:  0050.01  File handling for reporting cycle status Started
               Given Open Casper Application in Web Browser "Casper_UploadFile_017" "<RowNo>"
               When Upload a File  
               Then Verify the Reporting Cycle as Started 
Examples:
|RowNo|
|1|

---

Scenario Outline:  0050.02  File handling for reporting cycle status Closed
               Given Open Casper Application in Web Browser "Casper_UploadFile_018" "<RowNo>"
               When Upload a File  
               Then Verify the Reporting Cycle as Closed
Examples:
|RowNo|
|1|


Scenario Outline:  0050.03  File handling for reporting cycle status In preparation
               Given Open Casper Application in Web Browser "Casper_UploadFile_019" "<RowNo>"
               When Upload a File  
               Then Verify the Reporting Cycle as In Preparation
Examples:
|RowNo|
|1|



Scenario Outline:  TC: 0048.16  Upload a File with file type as C or D or A with Reporting Cycle as Started
               Given Open Casper Application in Web Browser "Casper_UploadFile_014" "<RowNo>"
               When Upload a File  
               Then Verify the Filetype and Reporting Cycle
Examples:
|RowNo|
|1|



Scenario Outline:  TC: 0048.16  Upload a File with file type as TC or TD or TA with Reporting Cycle as In Preparation
               Given Open Casper Application in Web Browser "Casper_UploadFile_025" "<RowNo>"
               When Upload a File  
               Then Verify the Filetype and Reporting Cycle As In Preparation
Examples:
|RowNo|
|1|



Scenario Outline:  TC: 0048.16  Upload any File Type with Reporting Cycle as Closed
               Given Open Casper Application in Web Browser "Casper_UploadFile_026" "<RowNo>"
               When Upload a File  
               Then Verify the File Status and Reporting Cycle As Closed
Examples:
|RowNo|
|1|






===================================================================
@Upload

Feature: Upload File

Scenario Outline:  0049.02  Allowed range for the sequential number (01-99) 
	Given Open Casper Application in Web Browser "Casper_UploadFile_016" "<RowNo>"
	When Upload the file one to Ninety Nine "<RowNo>"
	Then File Upload should be successful for valid sequence
Examples:
|RowNo|
|1|

Scenario Outline: 0010.24  Separate handling of single files with respect to errors
	Given Open Casper Application in Web Browser "Casper_UploadFile_004" "<RowNo>"
	When Upload the valid and invalid file in zip file "<RowNo>"
	Then File is accepted or rejected
Examples:
|RowNo|
|1|

Scenario Outline:  0048.15  Maximum length of each filename component
	Given Open Casper Application in Web Browser "Casper_UploadFile_013" "<RowNo>"
	When A file is uploaded "<RowNo>"
	Then Verify the maximum length of filename components
Examples:
|RowNo|
|1|

Scenario Outline:  0048.17  Mandatory filename components identify the same submission
	Given Open Casper Application in Web Browser "Casper_UploadFile_023" "<RowNo>"
	When A file is uploaded "<RowNo>"
	Then Mandatory filename components identify same submission
Examples:
|RowNo|
|1|

Scenario Outline:  0048.16  Allowed values or other restrictions
	Given Open Casper Application in Web Browser "Casper_UploadFile_024" "<RowNo>"
	When I Upload a File
	Then Verify File is Accepted when Data Collection is Present
Examples:
|RowNo|
|1|

Scenario Outline:  0010.21  No hierarchically organised zip-files (folders within folders)
               Given Open Casper Application in Web Browser "Casper_UploadFile_001" "<RowNo>"
               When I Upload a File
               Then Verify File Upload
Examples:
|RowNo|
|1|
|2|
|3|
|4|
|5|

Scenario Outline:  0010.22  Only one data collection per zip-file
               Given Open Casper Application in Web Browser "Casper_UploadFile_002" "<RowNo>"
               When I Upload a File
               Then Verify File Upload
Examples:
|RowNo|
|1|
|2|
|3|
|4|

Scenario Outline:  0010.25  Rejection of blank zip-files
               Given Open Casper Application in Web Browser "Casper_UploadFile_005" "<RowNo>"
               When I Upload a File
               Then Verify File Upload
Examples:
|RowNo|
|1|

Scenario Outline:  0010.12  Zip-file selection via dedicated button
               Given Open Casper Application in Web Browser "Casper_UploadFile_021" "<RowNo>"
               When I Select a File
               Then Verify File Selected on Upload Page
Examples:
|RowNo|
|1|

Scenario Outline:  0048.11  Mandatory filename components
               Given Open Casper Application in Web Browser "Casper_UploadFile_006" "<RowNo>"
               When I Upload a File
               Then Verify File is Rejected when File Type is Missing
Examples:
|RowNo|
|1|

Scenario Outline:  0048.11  Mandatory filename components
               Given Open Casper Application in Web Browser "Casper_UploadFile_007" "<RowNo>"
               When I Upload a File
               Then Verify File is Rejected when Data Collection and Reporting Cycle is Missing
Examples:
|RowNo|
|1|

Scenario Outline:  0048.11  Mandatory filename components
               Given Open Casper Application in Web Browser "Casper_UploadFile_008" "<RowNo>"
               When I Upload a File
               Then File Upload should be Successful
Examples:
|RowNo|
|1|

Scenario Outline:  0048.11  Mandatory filename components
               Given Open Casper Application in Web Browser "Casper_UploadFile_009" "<RowNo>"
               When I Upload a File
               Then Verify File is Rejected when Reference Date is Missing
Examples:
|RowNo|
|1|

Scenario Outline:  0050.01  File handling for reporting cycle status Started
               Given Open Casper Application in Web Browser "Casper_UploadFile_017" "<RowNo>"
               When Upload a File  
               Then Verify the Reporting Cycle as Started 
Examples:
|RowNo|
|1|

Scenario Outline:  0050.02  File handling for reporting cycle status Closed
               Given Open Casper Application in Web Browser "Casper_UploadFile_018" "<RowNo>"
               When I Upload a File 
               Then Verify the Reporting Cycle as Closed
Examples:
|RowNo|
|1|

Scenario Outline:  0050.03  File handling for reporting cycle status In preparation
               Given Open Casper Application in Web Browser "Casper_UploadFile_019" "<RowNo>"
               When I Upload a File  
               Then Verify the Reporting Cycle as In Preparation
Examples:
|RowNo|
|1|

Scenario Outline:  TC: 0048.16  Upload a File with file type as C or D or A with Reporting Cycle as Started
               Given Open Casper Application in Web Browser "Casper_UploadFile_014" "<RowNo>"
               When I Upload a File  
               Then Verify the Filetype and Reporting Cycle
Examples:
|RowNo|
|1|

Scenario Outline:  TC: 0048.16  Upload a File with file type as TC or TD or TA with Reporting Cycle as In Preparation
               Given Open Casper Application in Web Browser "Casper_UploadFile_025" "<RowNo>"
               When I Upload a File  
               Then Verify the Filetype and Reporting Cycle As In Preparation
Examples:
|RowNo|
|1|

Scenario Outline:  TC: 0048.16  Upload any File Type with Reporting Cycle as Closed
               Given Open Casper Application in Web Browser "Casper_UploadFile_026" "<RowNo>"
               When I Upload a File 
               Then Verify the File Status and Reporting Cycle As Closed
Examples:
|RowNo|
|1|









================================================================================================================

Scenario Outline:  TC: 0120 001 Validate Add Entity Group
	Given Open Casper Application in Web Browser "Casper_EntityGroup_001" "<RowNo>"
	When Add Entity Group  
	Then Validate Entity Groups
Examples:
|RowNo|
|1|

Scenario Outline:  TC: 0120 002 Validate Duplicate Entity Group
	Given Open Casper Application in Web Browser "Casper_EntityGroup_002" "<RowNo>"
	When Add Duplicate Entity Group  
	Then Validate Duplicate Entity Groups
Examples:
|RowNo|
|1|


Scenario Outline:  TC: 0120 003 Validate Edit Entity Group
	Given Open Casper Application in Web Browser "Casper_EntityGroup_003" "<RowNo>"
	When Edit Entity Group  
	Then Validate Entity Group update
Examples:
|RowNo|
|1|


Scenario Outline:  TC: 0120 04 Validate Edit Entity Group
	Given Open Casper Application in Web Browser "Casper_EntityGroup_004" "<RowNo>"
	When Delete Entity Group  
	Then Validate Entity Group deletion
Examples:
|RowNo|
|1|
====================================================






Scenario Outline:  0010.23  One or more cycles in the same zip-file allowed
               Given Open Casper Application in Web Browser "Casper_UploadFile_003" "<RowNo>"
               When Upload the Correct File
               Then Multiple File Upload should be Successful
Examples:
|RowNo|
|1|
|2|
|3|



*/