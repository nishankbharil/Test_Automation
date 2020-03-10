@ReportingEntityUploadScenarios
Feature: 07_ReportingEntityUpload

  @Cluster14_1
  Scenario Outline: Import entity pre-requisite
    Given Open Casper Application in Web Browser "Definitions" "CASPER_ImportReportingEntity" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search DataCollection in List "DataCollection_Code"
    When Click on "View" action
    When Click on "Population" tab
    When Click on "Custom Attributes" tab
    When Click on "Add" button
    When Enter Attribute Details for Attribute1
    When Click on "Save" button
    When Click on "Add" button
    When Enter Attribute Details for Attribute2
    When Click on "Save" button

    Examples: 
      | RowNo |
      |     1 |

  @Cluster14_2
  Scenario Outline: Import entity all scenarios
    Given Open Casper Application in Web Browser "Definitions" "CASPER_ImportReportingEntity" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search DataCollection in List "DataCollection_Code"
    When Click on "View" action
    When Click on "Population" tab
    When Click on "Entities" tab
    When Click on "Import Entities" button
    Then Pick and change collection unique identifier in CSV "<RowNo>"
    When Upload entity CSV File
    When Click on "Upload" button
    Then Verify and click on "Import History" link on pop up
    When Click on "Collections" tab
    When Search DataCollection in List "DataCollection_Code"
    When Click on "View" action
    When Click on "Import History" tab
    Then Search for file and verify status in import history
    When Click on "View Results" action
    Then Verify the result for reporting entity imported file

    Examples: 
      | RowNo |
      |     1 |
      |     2 |
      |     3 |
      |     4 |
      |     5 |
      |     6 |
      |     7 |
      |     8 |
      |     9 |
      |    10 |
      |    11 |
      |    12 |
