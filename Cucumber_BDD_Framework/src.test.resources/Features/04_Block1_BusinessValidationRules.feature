@BusinessValidationRulesScenario1
Feature: 04_Block1_BusinessValidationRules

  @SubBlock1
  Scenario Outline: Import Business Validation rules
    Given Open Casper Application in Web Browser "BusinessValidationRule" "CASPER_BusinessValidationRules" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Navigate to "Dictionaries" screen
    When Click on "Add" button
    When Enter Data Dictionary details "BusinessValidationRule.xlsx"
    When Click on "Save" button
    When Search record for filter value "DD_Name"
    Then Verify new entry created in Data Dictionary List "name"
    When Click on "Collections" tab
    When Click on "Add" button
    When Enter DataCollection details "BusinessValidationRule.xlsx"
    When Save Data collection code in "BVRFileNames.xlsx"
    When Click on "Save" button
    When Search record for filter value "DC_Code"
    Then Verify new entry created in DataCollection List "code"
    When Click on "View" action
    When Click on "Modules" tab
    When Click on "Add" button
    When Enter Modules details
    When Click on "Save" button
    When Search record for module
    Then Verify new entry created in Modules List "moduleCode"
    When Click on "Upload Template" action
    When Upload a template
    When Click on "Upload" button
    Then Verify and click on "Import History" link on pop up
    When Click on "Modules" tab
    When Search record for module
    Then Check the status "definitionStatus"
    When Click on "Population" tab
    When Click on "Entities" tab
    When Click on "Add" button
    When Enter Entity Details "BusinessValidationRule.xlsx"
    When Click on "Save" button
    When Search record for filter value "ENT_CollUniqueID"
    Then Verify new entry created in Entities List "collectionUniqueId"
    When Click on "Entity Groups" tab
    When Click on "Add" button
    When Enter EntityGroup Details
    When Click on "Save" button
    When Search record for filter value "EG_Name"
    Then Verify new entry created in EntityGroup List "name"
    When Click on "Edit Assignment" action
    When Edit Assigned Entities
    When Click on "Save" button

    Examples: 
      | RowNo |
      |     1 |

  @SubBlock2
  Scenario Outline: Import Business validation Rules(File Upload)
    Given Open Casper Application in Web Browser "BVRFileNames" "CASPER_BVR_ResultCompare" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DataCollection_Code"
    Then Verify new entry created in DataCollection List "DataCollection_Code"
    When Click on "View" action
    When Click on "Validation Rules" tab
    When Click on "Import Validation Rules" button
    When Upload business rules
    When Click on "Upload" button
    Then Verify and click on "Import History" link on pop up
    When Click on "Validation Rules" tab
    When Compare the results of uploaded rules "BusinessValidationRule.xlsx"

    Examples: 
      | RowNo |
      |     1 |
      |     2 |
