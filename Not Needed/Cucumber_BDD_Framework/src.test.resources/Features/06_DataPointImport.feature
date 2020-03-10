@DataPointImportScenarios
Feature: 06_DataPointImport

  @Cluster15_1
  Scenario Outline: Pre-requisite for data point import
    Given Open Casper Application in Web Browser "Definitions" "CASPER_DataPointImport" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search DataCollection in List "DataCollection_Code"
    When Click on "View" action
    When Click on "Modules" tab
    When Click on "Upload Template" action
    When Upload a template
    When Click on "Upload" button
    Then Verify and click on "Import History" link on pop up
    When Click on "Modules" tab
    Then Check the status "definitionStatus"

    Examples: 
      | RowNo |
      |     1 |

  @Cluster15_2
  Scenario Outline: Import entity all scenarios
    Given Open Casper Application in Web Browser "Definitions" "CASPER_DataPointImport" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search DataCollection in List "DataCollection_Code"
    When Click on "View" action
    When Click on "Modules" tab
    Then Pick and change module code in CSV file
    When Click on "Data Points" tab
    When Click on "Import Data Points" button
    When Upload data point CSV File
    When Click on "Upload" button
    Then Verify and click on "Import History" link on pop up
    When Click on "Collections" tab
    When Search DataCollection in List "DataCollection_Code"
    When Click on "View" action
    When Click on "Import History" tab
    Then Search for file and verify status in import history
    When Click on "View Results" action
    Then Verify the result of datapoint imported file

    Examples: 
      | RowNo |
      |    10 |
      |    11 |
