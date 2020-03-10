@BasicFunctionalTestScenarios
Feature: 03_Basic_Functional_Test

  @Cluster101
  Scenario Outline: ADD/Edit Data Dictionary
    Given Open Casper Application in Web Browser "Definitions" "CASPER_DataDictionary" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Navigate to "Dictionaries" screen
    When Click on "Add" button
    When Enter Data Dictionary details "Definitions.xlsx"
    When Click on "Save" button
    When Search record for filter value "DD_Name"
    Then Verify new entry created in Data Dictionary List "name"
    When Click on "Edit" action
    When Edit Data Dictionary details
    When Click on "Save" button
    When Search record for filter value "DD_Name"
    Then Verify new entry created in Data Dictionary List "owner"
    Then Verify new entry created in Data Dictionary List "description"
    When Search record for filter value "DD_Name"
    When Click on "View" action
    When Click on "Edit" button
    When Edit Data Dictionary details
    When Click on "Save" button
    When Navigate to "Dictionaries" screen
    When Search record for filter value "DD_Name"
    Then Verify new entry created in Data Dictionary List "owner"
    Then Verify new entry created in Data Dictionary List "description"
    When Click on "Add" button
    When Enter duplicate Data Dictionary details
    When Click on "Save" button
    Then Verify error message for entry exists

    Examples: 
      | RowNo |
      |     1 |

  @Cluster102
  Scenario Outline: Add/Edit Data Collection
    Given Open Casper Application in Web Browser "Definitions" "CASPER_DataCollection" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Click on "Add" button
    When Enter DataCollection details "Definitions.xlsx"
    When Click on "Save" button
    When Save Data collection code in "Definitions.xlsx" "CASPER_Modules" "<RowNo>"
    When Save Data collection code in "Definitions.xlsx" "CASPER_Entity" "<RowNo>"
    When Save Data collection code in "Definitions.xlsx" "CASPER_CustomAttribute" "<RowNo>"
    When Save Data collection code in "Definitions.xlsx" "CASPER_EntityUpload" "<RowNo>"
    When Save Data collection code in "Definitions.xlsx" "CASPER_EntityGroups" "<RowNo>"
    When Save Data collection code in "Definitions.xlsx" "CASPER_Cycles" "<RowNo>"
    When Save Data collection code in "Definitions.xlsx" "CASPER_ObligationPerGroup" "<RowNo>"
    When Save Data collection code in "Definitions.xlsx" "CASPER_DISC_Export" "<RowNo>"
    When Save Data collection code in "Definitions.xlsx" "CASPER_IndividualFileUpload" "<RowNo>"
    When Save Data collection code in "Definitions.xlsx" "CASPER_ImportReportingEntity" "<RowNo>"
    When Save Data collection code in "Definitions.xlsx" "CASPER_DataPointImport" "<RowNo>"
    When Search record for filter value "DC_Code"
    Then Verify new entry created in DataCollection List "code"
    When Click on "View" action

    Examples: 
      | RowNo |
      |     1 |

  @Cluster103
  Scenario Outline: Add/Edit Modules
    Given Open Casper Application in Web Browser "Definitions" "CASPER_Modules" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search DataCollection in List "DataCollection_Code"
    When Click on "View" action
    When Click on "Modules" tab
    When Click on "Add" button
    When Enter Modules details "Definitions.xlsx"
    When Click on "Save" button
    When Save module code in "Definitions.xlsx" "CASPER_IndividualFileUpload" "<RowNo>"
    When Search record for module
    Then Verify new entry created in Modules List "moduleCode"
    When Click on "Edit" action
    When Edit Modules details
    When Click on "Save" button
    When Search record for module
    Then Verify new entry created in Modules List "moduleName"
    When Click on "Add" button
    When Enter empty date in Modules
    Then Verify error message for empty value
    When Click on "Cancel" button
    When Search record for module
    When Click on "Edit" action
    When Enter invalid date in Modules
    Then Verify error message for invalid date format
    When Click on "Cancel" button
    When Click on "Add New Version" action
    When Enter past date in Add new version
    Then Verify error message for date limit

    Examples: 
      | RowNo |
      |     1 |

  @Cluster104
  Scenario Outline: Upload template and Add New Version
    Given Open Casper Application in Web Browser "Definitions" "CASPER_Modules" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search DataCollection in List "DataCollection_Code"
    When Click on "View" action
    When Click on "Modules" tab
    When Search record for module
    When Click on "Upload Template" action
    Then Verify the "Upload Excel Templates" pop up displayed
    When Upload a template
    When Click on "Upload" button
    Then Verify and click on "Import History" link on pop up
    When Click on "Modules" tab
    When Search record for module
    Then Check the status "definitionStatus"
    When Click on "Add New Version" action
    When Enter Add New Version details
    When Click on "Save" button
    Then Verify new entry created in Modules List "version"

    Examples: 
      | RowNo |
      |     1 |

  @Cluster105
  Scenario Outline: Add/Edit Custom Attributes
    Given Open Casper Application in Web Browser "Definitions" "CASPER_CustomAttribute" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search DataCollection in List "DataCollection_Code"
    When Click on "View" action
    When Click on "Population" tab
    When Click on "Custom Attributes" tab
    When Click on "Add" button
    When Enter Attribute Details "Definitions.xlsx"
    When Click on "Save" button
    When Search record for filter value "ATTR_Code"
    Then Verify new entry created in Attributes List "attrCode"
    When Click on "Edit" action
    When Edit Attribute Details "Definitions.xlsx"
    When Click on "Save" button
    When Search record for filter value "ATTR_Code"
    Then Verify new entry created in Attributes List "attrCode"
    When Click on "Delete" action
    Then Verify the "Delete Confirmation" pop up displayed
    When Click on "No" button
    When Click on "Add" button
    When Enter empty description in custom attributes
    Then Verify error message for empty value
    When Click on "Cancel" button
    When Click on "Add" button
    When Enter second Attribute Details "Definitions.xlsx"
    #When Click on "Save" button
    When Search record for filter value "ATTR_Code"
    Then Verify new entry created in Attributes List "attrCode"
    When Click on "Edit" action
    When Enter empty entries in custom attributes
    Then Verify error message for empty value

    Examples: 
      | RowNo |
      |     1 |

  @Cluster106
  Scenario Outline: Add/Edit Entities
    Given Open Casper Application in Web Browser "Definitions" "CASPER_Entity" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search DataCollection in List "DataCollection_Code"
    When Click on "View" action
    When Click on "Population" tab
    When Click on "Entities" tab
    When Click on "Add" button
    When Enter Entities Details "Definitions.xlsx"
    When Click on "Save" button
    When Search record for filter value "ENT_CollUniqueID"
    Then Verify new entry created in Entities List "collectionUniqueId"
    When Click on "Edit" action
    When Edit Entities details "Definitions.xlsx"
    When Click on "Save" button
    When Search record for filter value "ENT_CollUniqueID"
    Then Verify new entry created in Entities List "collectionUniqueId"
    When Click on "Delete" action
    Then Verify the "Delete Confirmation" pop up displayed
    When Click on "No" button
    When Click on "Add" button
    When Enter invalid start date in entities
    Then Verify error message for invalid date format
    When Click on "Cancel" button
    When Search record for filter value "ENT_CollUniqueID"
    When Click on "Edit" action
    When Enter start date after end date in entities
    Then Verify error message for start date
    When Click on "Cancel" button
    When Search record for filter value "ENT_CollUniqueID"
    When Click on "Edit" action
    When Enter valid from date after valid to date in entities
    Then Verify error message for valid from date

    Examples: 
      | RowNo |
      |     1 |

  @Cluster107
  Scenario Outline: Import Entities
    Given Open Casper Application in Web Browser "Definitions" "CASPER_EntityUpload" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search DataCollection in List "DataCollection_Code"
    When Click on "View" action
    When Click on "Population" tab
    When Click on "Entities" tab
    When Click on "Import Entities" button
    When Change the COLLECTIONUNIQUEIDENTIFIER in CSV file "Definitions.xlsx"
    When Upload entity CSV File
    When Click on "Upload" button
    Then Verify and click on "Import History" link on pop up
    When Click on "Population" tab
    When Click on "Entities" tab
    When Search record for import entity
    Then Verify new entry created in Entities List via CSV Upload "collectionUniqueId"
    When Click on "Import Entities" button
    When Upload incorrect headers CSV File
    When Click on "Upload" button
    Then Verify and click on "Import History" link on pop up

    Examples: 
      | RowNo |
      |     1 |

  @Cluster108
  Scenario Outline: AUTO.FCT.15/17/16 Add/Edit Entity Group
    Given Open Casper Application in Web Browser "Definitions" "CASPER_EntityGroups" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search DataCollection in List "DataCollection_Code"
    When Click on "View" action
    When Click on "Population" tab
    When Click on "Entity Groups" tab
    When Click on "Add" button
    When Enter EntityGroup Details "Definitions.xlsx"
    When Click on "Save" button
    When Search record for filter value "EG_Name"
    Then Verify new entry created in EntityGroup List "name"
    When Click on "Edit Entity Group" action
    When Edit EntityGroup Details
    When Click on "Save" button
    When Search record for filter value "EG_Name"
    Then Verify new entry created in EntityGroup List "name"
    When Click on "Edit Assignment" action
    When Edit Assigned Entities
    When Click on "Save" button
    When Search record for filter value "EG_Name"
    Then Verify new entry in AssignedEntityGroup List "numberOfEntities"
    When Click on "Delete" action
    Then Verify the "Delete Confirmation" pop up displayed
    When Click on "No" button
    When Click on "Add" button
    When Enter duplicate Entity group details
    When Click on "Save" button
    Then Verify error message for entity exists
    When Click on "Cancel" button
    When Search record for filter value "EG_Name"
    When Click on "Edit Assignment" action
    When Enter invalid valid to date
    Then Verify error message for invalid date format

    Examples: 
      | RowNo |
      |     1 |

  @Cluster109
  Scenario Outline: Add/Edit Cycles
    Given Open Casper Application in Web Browser "Definitions" "CASPER_Cycles" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search DataCollection in List "DataCollection_Code"
    When Click on "View" action
    When Click on "Obligations" tab
    When Click on "Reporting Cycles" tab
    When Click on "Add" button
    When Enter Cycles Details "Definitions.xlsx"
    When Click on "Save" button
    When Search record for filter value "CY_Name"
    Then Verify new entry created in Cycles List "name"
    When Click on "Edit" action
    When Edit Cycles details
    When Click on "Save" button
    When Search record for filter value "CY_Name"
    Then Verify new entry created in Cycles List "description"
    When Click on "Add" button
    When Enter empty start date in Cycles
    Then Verify error message for empty value
    When Click on "Cancel" button
    When Search record for filter value "CY_Name"
    When Click on "Edit" action
    When Enter past end date in Cycles
    Then Verify error message for past end date

    Examples: 
      | RowNo |
      |     1 |

  @Cluster110
  Scenario Outline: Add/Edit Obligations per group
    Given Open Casper Application in Web Browser "Definitions" "CASPER_ObligationPerGroup" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search DataCollection in List "DataCollection_Code"
    When Click on "View" action
    When Click on "Obligations" tab
    When Click on "Obligations per Group" tab
    When Click on "Add" button
    When Enter outside cycle remittance date
    When Click on "Save" button
    Then Verify error message for outside cycle remittance date
    When Click on "Cancel" button
    When Click on "Add" button
    When Enter Obligations per group Details "Definitions.xlsx"
    When Save cycle name in "Definitions.xlsx" "CASPER_IndividualFileUpload" "<RowNo>"
    When Click on "Save" button
    When Search record for Cycle name value
    Then Verify new entry created in Obligations per group List "referenceDate"
    When Click on "Edit" action
    When Edit Obligations per group details
    When Click on "Save" button
    When Search record for Cycle name value
    Then Verify new entry created in Obligations per group List "remittanceDate"
    When Click on "Delete" action
    Then Verify the "Delete Confirmation" pop up displayed
    When Click on "No" button
    When Search record for Cycle name value
    When Click on "Edit" action
    When Enter invalid remittance offset
    Then Verify error message for cycle remittance offset

    Examples: 
      | RowNo |
      |     1 |

  @Cluster111
  Scenario Outline: DISC export
    Given Open Casper Application in Web Browser "Definitions" "CASPER_DISC_Export" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search DataCollection in List "DataCollection_Code"
    When Click on "View" action
    When Click on "Workflow Settings" tab
    When Click on "DISC Export" tab
    Then Verify the "DISC export settings" screen displayed
    Then Verify "active" checkbox is disabled
    Then Verify "active" checkbox is not selected
    Then Verify "Export Now" button is disabled
    When Click on "Edit" button
    Then Verify the "Edit DISC export settings" pop up displayed
    Then Verify "active" checkbox is enabled
    Then Verify "Save" button is disabled
    When Enter Edit DISC export settings details
    When Click on "Save" button
    Then Verify updates successfully message
    Then Verify value on DISC screen for "Target Data Lab for scheduled export"
    Then Verify value on DISC screen for "Interval"
    Then Verify value on DISC screen for "Fulfilled Obligations until"
    When Click on "Export Now" button
    Then Verify export triggered success message
    When Click on "Collections" tab
    When Search DataCollection in List "DataCollection_Code"
    When Click on "View" action
    When Click on "Workflow Settings" tab
    When Click on "DISC Export" tab
    Then Verify new entry created in DISC List "processStatus"
    When Click on "Metadata" tab
    When Click on "Workflow Settings" tab
    When Click on "DISC Export" tab
    When Search record for DISC triggered by
    Then Verify new entry created in DISC List "triggeredBy"
    When Click on "Export Now" button

    Examples: 
      | RowNo |
      |     1 |

  @Cluster112
  Scenario Outline: Roles and permissions
    Given Open Casper Application in Web Browser "Definitions" "CASPER_RolesPersmissions" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "User & Roles" tab
    When Click on "Permission Configuration" left menu
    When Click on user role
    When Click on "Submission Preparation" element
    Then Verify submission "Submissions" permission
    Then Verify submission "Submission List" permission
    Then Verify submission "View" permission
    Then Verify submission "Individual File Upload" permission
    Then Verify submission "Download" permission
    Then Verify file vault "File Vault" permission
    Then Verify file vault "View" permission
    Then Verify file vault "Download" permission
    Then Verify file vault "View Validation Result" permission
    When Click on "Submission Preparation" element
    When Click on "Collection Preparation" element
    #Then Verify definitions "Definitions" permission
    Then Verify definitions "Add Data Collection" permission
    Then Verify metadata "Metadata" permission
    Then Verify metadata "View" permission
    Then Verify metadata "Edit" permission
    Then Verify metadata "Import Metadata Result" permission
    Then Verify metadata "View Import Metadata Result" permission
    Then Verify metadata "View Results" permission
    Then Verify metadata "Cancel" permission
    Then Verify module "Modules" permission
    Then Verify module "View" permission
    Then Verify module "Add" permission
    Then Verify module "Edit" permission
    Then Verify module "Add New Version" permission
    Then Verify module "Upload Template" permission
    Then Verify data points "Data Points" permission
    Then Verify data points "View" permission
    Then Verify data points "Edit" permission
    Then Verify data points "Upload Data Point" permission
    Then Verify entity "Entities" permission
    Then Verify entity "View" permission
    Then Verify entity "Add" permission
    Then Verify entity "Assign Custom Attributes" permission
    Then Verify entity "Edit" permission
    Then Verify entity "Import Entities" permission
    Then Verify entity "Export" permission
    Then Verify entity "View Entity Attributes" permission
    Then Verify entity "Delete" permission
    Then Verify custom attribute "Custom Attributes" permission
    Then Verify custom attribute "View" permission
    Then Verify custom attribute "Add" permission
    Then Verify custom attribute "Edit" permission
    Then Verify custom attribute "Delete" permission
    Then Verify entity group "Entity Groups" permission
    Then Verify entity group "View" permission
    Then Verify entity group "Add" permission
    Then Verify entity group "Edit" permission
    Then Verify entity group "Delete" permission
    Then Verify entity group "View EG Assignment" permission
    Then Verify entity group "Edit  EG Assignment" permission
    #Then Verify reporting cycle "Reporting Cycle" permission
    Then Verify reporting cycle "Cycles" permission
    Then Verify reporting cycle "View" permission
    Then Verify reporting cycle "Add" permission
    Then Verify reporting cycle "Edit" permission
    Then Verify reporting cycle "Delete" permission
    Then Verify obligation per group "Obligations per Group" permission
    Then Verify obligation per group "View" permission
    Then Verify obligation per group "Add" permission
    Then Verify obligation per group "Edit" permission
    Then Verify obligation per group "Delete" permission
    Then Verify obligation per entity "Obligations per Entity" permission
    Then Verify obligation per entity "View" permission
    Then Verify validations "Validation Rules" permission
    Then Verify validations "View" permission
    Then Verify validations "Edit" permission
    Then Verify validations "Enable" permission
    Then Verify validations "Disable" permission
    Then Verify validations "Delete" permission
    Then Verify validations "Add" permission
    Then Verify validations "Import Validation" permission
    Then Verify validations "Export Validation" permission
    Then Verify disc export "DISC Export" permission
    Then Verify disc export "View" permission
    Then Verify disc export "Edit" permission
    Then Verify disc export "Export" permission
    Then Verify disc export "Cancel" permission
    When Click on "Collection Preparation" element
    When Click on "Data Dictionary" element
    Then Verify data dictionary "Dictionaries" permission
    Then Verify data dictionary "Add Data Dictionary" permission
    Then Verify data dictionary "Delete Data Dictionary" permission
    Then Verify data dictionary "Dictionary Metadata" permission
    Then Verify data dictionary "View" permission
    Then Verify data dictionary "Edit" permission
    When Click on "Data Dictionary" element
    When Click on "Authorisation" element
    Then Verify manage user "User & Roles" permission
    Then Verify manage user "Manage User Assignments to Data Collections" permission
    Then Verify manage user "Assign" permission
    Then Verify manage user "Un-Assign" permission
    Then Verify roles "Roles" permission
    Then Verify roles "View" permission
    Then Verify roles "Edit" permission

    Examples: 
      | RowNo |
      |     1 |
      |     2 |
