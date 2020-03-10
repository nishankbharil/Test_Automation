@EndToEndScenarios
Feature: 01_EndToEndTest

  @Cluster_01
  Scenario Outline: End To End test
    Given Open Casper Application in Web Browser "Definitions" "CASPER_EndToEnd" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Navigate to "Dictionaries" screen
    When Click on "Add" button
    When Enter Data Dictionary details "Definitions.xlsx"
    When Click on "Save" button
    When Search record for filter value "DD_Name"
    Then Verify new entry created in Data Dictionary List "name"
    When Click on "Collections" tab
    When Click on "Add" button
    When Enter DataCollection details E2E "Definitions.xlsx"
    When Click on "Save" button
    When Search record for filter value "DC_Code"
    Then Verify new entry created in DataCollection List "code"
    When Click on "View" action
    When Click on "Modules" tab
    When Click on "Add" button
    When Enter Modules details "Definitions.xlsx"
    When Click on "Save" button
    When Search record for module
    Then Verify new entry created in Modules List "moduleCode"
    When Click on "Upload Template" action
    Then Verify the "Upload Excel Templates" pop up displayed
    When Upload a template
    When Click on "Upload" button
    Then Verify and click on "Import History" pop up link
    When Click on "Modules" tab
    When Search record for module
    Then Check the status "definitionStatus"
    When Click on "Population" tab
    When Click on "Custom Attributes" tab
    When Click on "Add" button
    When Enter Attribute Details "Definitions.xlsx"
    When Click on "Save" button
    When Search record for filter value "ATTR_Code"
    Then Verify new entry created in Attributes List "attrCode"
    When Click on "Add" button
    When Enter Attribute Details "Definitions.xlsx"
    When Click on "Save" button
    When Search record for filter value "ATTR_Code"
    Then Verify new entry created in Attributes List "attrCode"
    When Click on "Entities" tab
    When Click on "Add" button
    When Enter Entities Details for E2E "Definitions.xlsx"
    When Click on "Save" button
    When Search record for filter value "ENT_CollUniqueID"
    Then Verify new entry created in Entities List "collectionUniqueId"
    When Click on "Entity Groups" tab
    When Click on "Add" button
    When Enter EntityGroup Details "Definitions.xlsx"
    When Click on "Save" button
    When Search record for filter value "EG_Name"
    Then Verify new entry created in EntityGroup List "name"
    When Click on "Edit Assignment" action
    When Edit Assigned Entities
    When Click on "Save" button
    When Search record for filter value "EG_Name"
    Then Verify new entry in AssignedEntityGroup List "numberOfEntities"
    When Click on "Obligations" tab
    When Click on "Reporting Cycles" tab
    When Click on "Add" button
    When Enter Cycles Details "Definitions.xlsx"
    When Click on "Save" button
    When Search record for filter value "CY_Name"
    Then Verify new entry created in Cycles List "name"
    When Click on "Obligations per Group" tab
    When Click on "Add" button
    When Enter Obligations per group Details "Definitions.xlsx"
    When Click on "Save" button
    When Search record for Cycle name value
    Then Verify new entry created in Obligations per group List "referenceDate"
    When Click on "Obligations per Entity" tab
    Then Verify new record added in the table
    When Click on "Validation Rules" left menu
    Then Verify the "Validation Rules" Screen Name
    Then Verify "Enable" button exists
    Then Verify "Disable" button exists
    Then Verify "Delete" button exists
    Then Verify "Add" button exists
    Then Verify "Import Validation Rules" button exists
    Then Verify "Export Validation Rules" button exists
    #When Click on "Validation Rules" left menu
    When Click on "Add" button
    Then Add validation rule with Severity in E2E test "Warning Level"
    Then Verify " Saved successfully!" Message on screen
    #Then Wait for few minutes
    When Click on "Submissions" tab
    When Search record for filter value "DC_Code"
    When Click on "View Submission List" action
    Then Verify new record added in the table
    When Search record for module name
    Then Verify new entry created in Modules List "module"
    When Upload a Zip File in submission list "Definitions.xlsx"
    Then Click on "Link to File Vault" link on pop up
    When Click on "Submissions" tab
    When Search record for filter value "DC_Code"
    When Click on "View Submission List" action
    When Search record for module name
    Then Verify new entry created in Modules List "module"
    Then Verify file status in submission list for column "obligationStatus"
    When Click on "File Vault" tab
    Then Verify new entry created in Modules List "module"
    Then Verify file status in file vault list for column "submissionStatus"

    Examples: 
      | RowNo |
      |     1 |