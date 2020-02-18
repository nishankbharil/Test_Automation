@TemplateUploadScenarios
Feature: 05_Block2_UploadTemplates

  @SubBlock3
  Scenario Outline: Open table template upload pre-requisite
    Given Open Casper Application in Web Browser "Modules" "CASPER_Modules" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Navigate to "Dictionaries" screen
    When Click on "Add" button
    When Enter Data Dictionary details "Modules.xlsx"
    When Click on "Save" button
    When Search record for filter value "DD_Name"
    Then Verify new entry created in Data Dictionary List "name"
    When Click on "Collections" tab
    When Click on "Add" button
    When Enter DataCollection details "Modules.xlsx"
    When Save Data collection code in "ModuleFileNames.xlsx"
    When Click on "Save" button
    When Search record for filter value "DC_Code"
    Then Verify new entry created in DataCollection List "code"

    Examples: 
      | RowNo |
      |     1 |
