@SanityTestScenarios
Feature: 02_SanityTest

  @Cluster1
  Scenario Outline: AUTO.01 Verify Home Page Tabs
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    Then Verify tabs on home screen "Submissions"
    Then Verify tabs on home screen "Collections"
    Then Verify tabs on home screen "Dictionaries"
    Then Verify tabs on home screen "User & Roles"

    Examples: 
      | RowNo |
      |     1 |

  @Cluster2
  Scenario Outline: AUTO.02-AUTO.06 Verify Submission UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Submissions" tab
    Then Verify the "Data Collections" screen displayed
    Then Verify record count in table using column "code"
    Then Verify "Export CSV" button exists
    Then Verify "View Submission List" Action for table record
    Then Verify "View File Vault" Action for table record
    Then Verify data loaded in table for column "code"

    Examples: 
      | RowNo |
      |     2 |

  @Cluster3
  Scenario Outline: AUTO.07-AUTO.12 Verify Submission UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Submissions" tab
    When Search record for filter value "DC_Code"
    When Click on "View Submission List" action
    Then Verify the "Submission List" screen displayed
    Then Verify left menu "Submission List" get displayed
    Then Verify left menu "File Vault" get displayed
    Then Verify record count in table using column "reportingCycle"
    Then Verify "Export CSV" button exists
    Then Verify "Zip Upload" button exists
    Then Verify data loaded in table for column "reportingCycle"
    Then Verify "Download File" Action for table record
    Then Verify "Download Template" Action for table record

    Examples: 
      | RowNo |
      |     3 |

  @Cluster4
  Scenario Outline: AUTO.13 Verify Submission UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Submissions" tab
    When Search record for filter value "DC_Code"
    When Click on "View Submission List" action
    When Click on "Zip Upload" button
    Then Verify the "Zip File Upload" pop up displayed

    Examples: 
      | RowNo |
      |     4 |

  @Cluster5
  Scenario Outline: AUTO.14-AUTO.19 Verify Submission UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Submissions" tab
    When Search record for filter value "DC_Code"
    When Click on "View Submission List" action
    When Click on "File Vault" tab
    Then Verify the "File Vault" screen displayed
    Then Verify record count in table using column "fileName"
    Then Verify "Export CSV" button exists
    When Click on "Submissions" tab
    When Search record for filter value "DC_Code"
    When Click on "View File Vault" action
    Then Verify the "File Vault" screen displayed
    Then Verify data loaded in table for column "fileName"
    Then Verify "Download" Action for table record
    Then Verify "View Validation Results" Action for table record

    Examples: 
      | RowNo |
      |     5 |

  @Cluster6
  Scenario Outline: AUTO.20-AUTO.24, AUTO.134, Auto.135 Verify Definition UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    Then Verify the "Data Collections" screen displayed
    Then Verify record count in table using column "code"
    Then Verify data loaded in table for column "code"
    Then Verify "Add" button exists
    Then Verify "Export CSV" button exists
    Then Verify "View" Action for table record
    Then Verify "Delete" Action for table record
    When Click on "Collections" tab
    #When Search record for filter value "DC_Code"
    When Click on "Delete" action
    Then Verify the "Delete Confirmation" pop up displayed
    When Click on "No" button
    When Click on "View" action
    Then Verify the "Properties" screen displayed
    When Click on "Edit" button
    Then Verify the "Edit Data Collection" pop up displayed
    When Click on "Cancel" button

    Examples: 
      | RowNo |
      |     6 |

  @Cluster7
  Scenario Outline: AUTO.25 Verify Definition UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Click on "Add" button
    Then Verify the "Add Data Collection" pop up displayed

    Examples: 
      | RowNo |
      |     7 |

  @Cluster8
  Scenario Outline: AUTO.26 Verify Definition UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    Then Verify the "Properties" screen displayed

    Examples: 
      | RowNo |
      |     8 |

  @Cluster9
  Scenario Outline: AUTO.27-AUTO.29 Verify Definition and Metadata UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    Then Verify the "Properties" screen displayed
    When Click on "Edit" button
    Then Verify the "Edit Data Collection" pop up displayed
    When Click on "Cancel" button
    Then Verify the "Properties" screen displayed
    Then Check Calculations for "# Data Points" field
    Then Check Calculations for "# Reporting Entities" field

    Examples: 
      | RowNo |
      |     9 |

  @Cluster10
  Scenario Outline: AUTO.30-AUTO.32 Verify Definition and Metadata UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    Then Verify left menu "Metadata" get displayed
    Then Verify left menu "Modules" get displayed
    Then Verify left menu "Population" get displayed
    Then Verify left menu "Obligations" get displayed
    When Click on "Population" tab
    Then Verify left menu "Entities" get displayed
    Then Verify left menu "Custom Attributes" get displayed
    Then Verify left menu "Entity Groups" get displayed
    When Click on "Obligations" tab
    Then Verify left menu "Reporting Cycles" get displayed
    Then Verify left menu "Obligations per Group" get displayed
    Then Verify left menu "Obligations per Entity" get displayed

    Examples: 
      | RowNo |
      |    10 |

  @Cluster11
  Scenario Outline: AUTO.33-AUTO.37, AUTO.136-AUTO.138 Verify Definition Screen
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Modules" tab
    Then Verify the "Modules" screen displayed
    Then Verify record count in table using column "moduleName"
    Then Verify data loaded in table for column "moduleName"
    Then Verify "Add" button exists
    Then Verify "Export CSV" button exists
    Then Filter Module Record with Status "Data Points defined"
    Then Verify "Edit" Action for table record
    Then Verify "Add New Version" Action for table record
    Then Verify "Upload RA Template" Action for table record
    When Click on "Modules" tab
    When Click on "Upload RA Template" action
    Then Verify "Upload" button exists
    When Click on "Close" button
    Then Verify "Delete" Action for table record
    When Click on "Modules" tab
    Then Verify "Validation Rules" Action for table record
    When Click on "Modules" tab
    When Click on "Validation Rules" action
    Then Verify the "Validation Rules" screen displayed
    When Click on "Modules" tab
    When Click on "Modules" tab
    When Click on "Reset View" button
    Then Filter Module Record with Status "Data Points not defined"

    Examples: 
      | RowNo |
      |    11 |

  @Cluster12
  Scenario Outline: AUTO.38-AUTO.39, AUTO.139 Verify Modules UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Modules" tab
    When Click on "Add" button
    Then Verify the "Add Module" pop up displayed
    Then Verify "Save" button exists
    Then Verify "Cancel" button exists
    When Click on "Cancel" button
    Then Filter Module Record with Status "Data Points defined"

    Examples: 
      | RowNo |
      |    12 |

  @Cluster13
  Scenario Outline: AUTO.40 Verify Modules UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Modules" tab
    When Click on "Edit" action
    Then Verify the "Edit Module" pop up displayed

    Examples: 
      | RowNo |
      |    13 |

  @Cluster14
  Scenario Outline: AUTO.41-AUTO.43 Verify Modules UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Modules" tab
    When Click on "Add New Version" action
    Then Verify the "Add New Version" pop up displayed
    Then Verify "Save" button exists
    Then Verify "Cancel" button exists
    When Click on "Cancel" button
    Then Verify the "Modules" screen displayed
    When Search record with filter value
    Then Verify module "Add New Version" action for "moduleName"

    Examples: 
      | RowNo |
      |    14 |

  @Cluster15
  Scenario Outline: AUTO.45-AUTO.51 Verify Population_Entities UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Population" tab
    When Click on "Entities" tab
    Then Verify the "Overview Reporting Entities" screen displayed
    Then Verify "Add" button exists
    Then Verify "Import Entities" button exists
    Then Verify "Export Entities" button exists
    Then Verify "Export CSV" button exists
    Then Verify record count in table using column "collectionUniqueId"
    Then Verify data loaded in table for column "collectionUniqueId"
    When Click on "Add" button
    Then Verify the "Add Reporting Entity" pop up displayed
    Then Verify "Save" button exists
    Then Verify "Cancel" button exists
    When Click on "Cancel" button
    Then Verify "View Entity Attributes" Action for table record
    Then Verify "Edit" Action for table record
    Then Verify "Delete" Action for table record
    When Click on "Entities" tab
    When Click on "View Entity Attributes" action
    Then Verify the "View Entity Attributes" pop up displayed
    Then Verify "Export CSV" button exists
    Then Verify "Close" button exists
    When Click on "Close" button

    Examples: 
      | RowNo |
      |    15 |

  @Cluster16
  Scenario Outline: AUTO.53 Verify Population_Entities UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Population" tab
    When Click on "Entities" tab
    When Click on "Add" button
    Then Verify "Save" button exists
    Then Verify "Cancel" button exists
    When Click on "Cancel" button

    Examples: 
      | RowNo |
      |    16 |

  @Cluster17
  Scenario Outline: AUTO.54 Verify Population_Entities UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Population" tab
    When Click on "Entities" tab
    When Click on "Import Entities" button
    #Then Verify the "Import Entities" pop up displayed
    Then Verify "Upload" button exists
    Then Verify "Close" button exists
    When Click on "Close" button

    Examples: 
      | RowNo |
      |    17 |

  @Cluster18
  Scenario Outline: AUTO.55-AUTO.56 Verify Population_Entities UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Population" tab
    When Click on "Entities" tab
    When Click on "Import Entities" button
    #Then Verify the "Import Entities" pop up displayed
    Then Verify "Upload" button exists
    Then Verify "Close" button exists
    When Click on "Close" button

    Examples: 
      | RowNo |
      |    18 |

  @Cluster19
  Scenario Outline: AUTO.57-AUTO.63 Verify Population_CustomAttributes UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Population" tab
    When Click on "Custom Attributes" tab
    Then Verify the "Custom Entity Attributes" screen displayed
    Then Verify record count in table using column "attrCode"
    Then Verify data loaded in table for column "attrCode"
    Then Verify "Add" button exists
    Then Verify "Export CSV" button exists
    Then Verify "View" Action for table record
    Then Verify "Edit" Action for table record
    Then Verify "Delete" Action for table record
    When Click on "Custom Attributes" tab
    When Click on "View" action
    Then Verify the "View Custom Entity Attribute" pop up displayed
    When Click on "Close" button

    Examples: 
      | RowNo |
      |    19 |

  @Cluster20
  Scenario Outline: AUTO.64-AUTO.65 Verify Population_CustomAttributes UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Population" tab
    When Click on "Custom Attributes" tab
    Then Verify the "Custom Entity Attributes" screen displayed
    When Click on "Add" button
    Then Verify the "Add Custom Entity Attribute" pop up displayed
    Then Verify "Save" button exists
    Then Verify "Cancel" button exists

    Examples: 
      | RowNo |
      |    20 |

  @Cluster21
  Scenario Outline: AUTO.66-AUTO.67 Verify Population_CustomAttributes UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Population" tab
    When Click on "Custom Attributes" tab
    Then Verify the "Custom Entity Attributes" screen displayed
    When Click on "Edit" action
    Then Verify the "Edit Custom Entity Attribute" pop up displayed
    Then Verify "Save" button exists
    Then Verify "Cancel" button exists

    Examples: 
      | RowNo |
      |    21 |

  @Cluster22
  Scenario Outline: AUTO.68-AUTO.74 Verify Population_Entity Groups UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Population" tab
    When Click on "Entity Groups" tab
    Then Verify the "Entity Groups" screen displayed
    Then Verify record count in table using column "name"
    Then Verify "Add" button exists
    Then Verify "Export CSV" button exists
    Then Verify data loaded in table for column "name"
    Then Verify "View Assignments" Action for table record
    Then Verify "Edit Assignments" Action for table record
    Then Verify "Edit Entity Group" Action for table record
    Then Verify "Delete Entity Group" Action for table record
    When Click on "Entity Groups" tab
    When Click on "View Assignments" action
    Then Verify the "View Assigned Entities" pop up displayed
    When Click on "Close" button

    Examples: 
      | RowNo |
      |    22 |

  @Cluster23
  Scenario Outline: AUTO.75 Verify Population_Entity Groups UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Population" tab
    When Click on "Entity Groups" tab
    Then Verify the "Entity Groups" screen displayed
    When Click on "Add" button
    Then Verify the "Add Entity Group" pop up displayed

    Examples: 
      | RowNo |
      |    23 |

  @Cluster24
  Scenario Outline: AUTO.76-AUTO.77 Verify Population_Entity Groups UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Population" tab
    When Click on "Entity Groups" tab
    Then Verify the "Entity Groups" screen displayed
    When Click on "Edit Assignments" action
    Then Verify the "Edit Assigned Entities" pop up displayed
    Then Verify "Save" button exists
    Then Verify "Cancel" button exists

    Examples: 
      | RowNo |
      |    24 |

  @Cluster25
  Scenario Outline: AUTO.78-AUTO.79 Verify Population_Entity Groups UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Population" tab
    When Click on "Entity Groups" tab
    Then Verify the "Entity Groups" screen displayed
    When Click on "Edit Entity Group" action
    Then Verify the "Edit Entity Group" pop up displayed
    Then Verify "Save" button exists
    Then Verify "Cancel" button exists

    Examples: 
      | RowNo |
      |    25 |

  @Cluster26
  Scenario Outline: AUTO.80-AUTO.86 Verify Obligation cycles UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Obligations" tab
    When Click on "Reporting Cycles" tab
    Then Verify the "Reporting Cycles" screen displayed
    Then Verify "Add" button exists
    Then Verify "Export CSV" button exists
    Then Verify record count in table using column "name"
    Then Verify data loaded in table for column "name"
    When Click on "Add" button
    Then Verify the "Add Reporting Cycle" pop up displayed
    Then Verify "Save" button exists
    Then Verify "Cancel" button exists
    When Click on "Cancel" button
    Then Verify "Edit" Action for table record

    Examples: 
      | RowNo |
      |    26 |

  @Cluster27
  Scenario Outline: AUTO.87-AUTO.88 Verify Obligation cycles UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Obligations" tab
    When Click on "Reporting Cycles" tab
    When Click on "Edit" action
    Then Verify the "Edit Reporting Cycle" pop up displayed
    Then Verify "Save" button exists
    Then Verify "Cancel" button exists

    Examples: 
      | RowNo |
      |    27 |

  @Cluster28
  Scenario Outline: AUTO.89-AUTO.96 Verify Obligation Per Group UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Obligations" tab
    When Click on "Obligations per Group" tab
    Then Verify the "Reporting Obligations per Entity Group" screen displayed
    Then Verify record count in table using column "rptCycleName"
    Then Verify data loaded in table for column "rptCycleName"
    Then Verify "Add" button exists
    Then Verify "Export CSV" button exists
    When Click on "Add" button
    Then Verify the "Add Reporting Obligation" pop up displayed
    Then Verify "Save" button exists
    Then Verify "Cancel" button exists
    When Click on "Cancel" button
    Then Verify "View Detail" Action for table record
    Then Verify "Edit" Action for table record
    Then Verify "Delete" Action for table record
    When Click on "Obligations per Group" tab
    When Click on "View Detail" action
    Then Verify the "View Reporting Obligation Detail" pop up displayed

    Examples: 
      | RowNo |
      |    28 |

  @Cluster29
  Scenario Outline: AUTO.97 Verify Obligation Per Group UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Obligations" tab
    When Click on "Obligations per Group" tab
    When Click on "Edit" action
    Then Verify the "Edit Reporting Obligations" pop up displayed

    Examples: 
      | RowNo |
      |    29 |

  @Cluster30
  Scenario Outline: AUTO.98 Verify Obligation Per Group UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Obligations" tab
    When Click on "Obligations per Group" tab
    Then Verify "Delete" Action for table record

    Examples: 
      | RowNo |
      |    30 |

  @Cluster31
  Scenario Outline: AUTO.99-AUTO.102 Verify Obligation Per Group UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Obligations" tab
    When Click on "Obligations per Entity" tab
    Then Verify the "Reporting Obligations per Entity" screen displayed
    Then Verify record count in table using column "rptCycleName"
    Then Verify data loaded in table for column "rptCycleName"
    Then Verify "Export CSV" button exists

    Examples: 
      | RowNo |
      |    31 |

  @Cluster32
  Scenario Outline: AUTO.103-AUTO.108 AUTO.111 Verify Data Dictionary UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    Then Verify tabs on home screen "Dictionaries"
    When Click on "Dictionaries" tab
    Then Verify the "Data Dictionaries" screen displayed
    Then Verify record count in table using column "name"
    Then Verify data loaded in table for column "name"
    Then Verify "Add" button exists
    Then Verify "Export CSV" button exists
    Then Verify "View" Action for table record
    Then Verify "Edit" Action for table record
    Then Verify "Delete" Action for table record
    When Click on "Dictionaries" tab
    When Click on "View" action
    Then Verify the "Data Dictionary Metadata" screen displayed

    Examples: 
      | RowNo |
      |    32 |

  @Cluster33
  Scenario Outline: AUTO.109-AUTO.110 Verify Data Dictionary UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    Then Verify tabs on home screen "Dictionaries"
    When Click on "Dictionaries" tab
    When Click on "Add" button
    Then Verify the "Add Data Dictionary" pop up displayed
    Then Verify "Save" button exists
    Then Verify "Cancel" button exists

    Examples: 
      | RowNo |
      |    33 |

  @Cluster34
  Scenario Outline: AUTO.112-AUTO.113 Verify Data Dictionary UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    Then Verify tabs on home screen "Dictionaries"
    When Click on "Dictionaries" tab
    When Click on "View" action
    Then Verify "Edit" button exists
    When Click on "Edit" button
    Then Verify the "Edit Data Dictionary" pop up displayed

    Examples: 
      | RowNo |
      |    34 |

  @Cluster35
  Scenario Outline: AUTO.114-AUTO.121 Verify User & Roles UI
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    Then Verify tabs on home screen "User & Roles"
    When Click on "User & Roles" tab
    Then Verify the "User - Data Collection Assignment List" screen displayed
    Then Verify left menu "User Assignments" get displayed
    #Then Search for User "Search_User" in User Assignment
    When Click on "Search" button
    Then Filter User DC Assignment by Assignment value "No"
    Then Verify record count in table using column "userId"
    #Then Filter User DC Assignment by Assignment value "Yes"
    When Click on "Assign" action
    Then Verify the "Assign Data Collections" pop up displayed
    When Click on "Cancel" button
    Then Verify "Assign" Action for table record
    When Click on "Reset View" button
    Then Filter User DC Assignment by Assignment value "Yes"
    Then Verify "Unassign" Action for table record
    When Click on "Permission Configuration" left menu
    When Click on "APPR_CASPER_INT_SUPER_USER_ADMIN" tab
    When Click on "Authorisation" element
    Then Scroll down "1000"
    Then Verify "Manage User Assignments to Data Collections" checkbox is checked
    Then Verify "Roles" checkbox is checked

    Examples: 
      | RowNo |
      |    35 |

  @Cluster36
  Scenario Outline: AUTO.122-AUTO.123 Verify "Validation Results" and "Discussions"
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Submissions" tab
    When Search record for filter value "DC_Code"
    Then Verify "View Validation Results" Action for table record
    Then Verify "View Discussions" Action for table record
    When Click on "Submissions" tab
    When Click on "View Submission List" action
    Then Verify the "Submission List" screen displayed
    Then Verify left menu "Submission List" get displayed
    Then Verify left menu "File Vault" get displayed
    Then Verify left menu "Validation Results" get displayed
    Then Verify left menu "Discussions" get displayed

    Examples: 
      | RowNo |
      |    36 |

  @Cluster37
  Scenario Outline: AUTO.124-AUTO.125 Verify "Validation Results" and "Discussions"
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Submissions" tab
    When Search record for filter value "DC_Code"
    When Click on "View Submission List" action
    When Search record for module name
    Then Verify "Download File" action is disabled
    Then Verify "View Validation Results" action is disabled
    Then Verify "Revalidate" action is disabled
    Then Verify "Upload Attachment" action is disabled
    Then Verify "Download Attachment" action is disabled
    Then Verify "Upload File" Action for table record
    Then Verify "Download Template" Action for table record
    Then Verify "View Validation Rules" Action for table record
    Then Search record with Cycle name filter
    Then Verify "Upload File" Action for table record
    Then Verify "Download File" Action for table record
    Then Verify "View Validation Results" Action for table record
    Then Verify "Revalidate" Action for table record
    Then Verify "Download Template" Action for table record
    Then Verify "Upload Attachment" Action for table record
    Then Verify "View Validation Rules" Action for table record
    Then Verify "Download Attachment" action is disabled

    Examples: 
      | RowNo |
      |    37 |

  @Cluster38
  Scenario Outline: AUTO.126-AUTO.133 Verify "Validation Results" and "Discussions"
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Submissions" tab
    When Search record for filter value "DC_Code"
    When Click on "View Submission List" action
    When Click on "Discussions" left menu
    Then Verify the "Discussions" Screen Name
    Then Verify record count in table using column "CYCLE"
    Then Search record with Cycle name filter
    Then Verify "View Submission List" Action for table record
    Then Verify "Discuss" Action for table record
    When Click on "Discussions" left menu
    When Click on "Discuss" action
    Then Verify Expand-Collapse link "Reporting Obligation"
    Then Verify Expand-Collapse link "Discussion Topics"
    Then Verify "Export CSV" button exists
    Then Verify "View Comments" Action for table record
    Then Verify "View Validation Results" Action for table record
    Then Select Discussion Topic record
    Then Scroll down "1000"
    Then Verify Expand-Collapse link "Comments"
    Then Verify "Save" button exists
    Then Verify "Cancel" button exists
    Then Verify "Accepted" Check box exist
    Then Verify "comment" Edit box exist
    Then Verify Comment section "Add Attachment" Action
    Then Verify Comment section "Download Attachment" Action
    Then Scroll Up "1000"
    When Click on "View Validation Results" action
    Then Verify the "Validation Results Per Reporting Obligation & Per Rule" Screen Name

    Examples: 
      | RowNo |
      |    38 |

  @Cluster39
  Scenario Outline: AUTO.140-AUTO-142 Data Points Validations
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Data Points" left menu
    Then Verify "Import Data Points" button exists
    Then Verify "Export Data Points" button exists
    Then Verify "Export CSV" button exists
    Then Verify "Edit" Action for table record

    Examples: 
      | RowNo |
      |    39 |

  @Cluster40
  Scenario Outline: AUTO.150-AUTO-151 Disc Export Screen Validation
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Workflow Settings" left menu
    When Click on "DISC Export" left menu
    Then Verify the "DISC export settings" Screen Name
    Then Verify "Export Now" button exists
    Then Verify "Edit" button exists
    Then Verify "Export CSV" button exists

    Examples: 
      | RowNo |
      |    40 |

  @Cluster41
  Scenario Outline: AUTO.143-AUTO-149 Validaion Rules Screen
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    When Click on "Validation Rules" left menu
    Then Verify the "Validation Rules" Screen Name
    Then Verify "Enable" button exists
    Then Verify "Disable" button exists
    Then Verify "Delete" button exists
    Then Verify "Add" button exists
    Then Verify "Import Validation Rules" button exists
    Then Verify "Export Validation Rules" button exists
    When Click on "Validation Rules" left menu
    When Click on "Add" button
    Then Add validation rule with Severity "Warning Level"
    Then Verify " Saved successfully!" Message on screen
    Then Delete created Validation Rule
    When Click on "Add" button
    Then Add validation rule with Severity "Error Level"
    Then Verify " Saved successfully!" Message on screen
    Then Delete created Validation Rule
    When Click on "Add" button
    Then Add validation rule with Severity "Fatal Error Level"
    Then Verify " Saved successfully!" Message on screen
    Then Delete created Validation Rule
    When Click on "Modules" tab
    When Click on "Validation Rules" left menu
    When Click on "Reset View" button
    Then Verify "Edit" Action for table record
    Then Verify "View" Action for table record
    Then Verify "Delete" Action for table record

    Examples: 
      | RowNo |
      |    41 |

  @Cluster42
  Scenario Outline: AUTO.151 Access Group List Add, Edit, Delete
    Given Open Casper Application in Web Browser "SanityTestData" "SanityTest" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "User & Roles" tab
    When Click on "Access Groups" left menu
    Then Verify "Export CSV" button exists
    Then Verify "Reset View" button exists
    When Click on "View" action
    Then Verify left menu "Access Groups" get displayed
    Then Verify left menu "Access Group List" get displayed
    Then Verify "Edit Assignments" Action for table record
    Then Verify "View Assignments" Action for table record
    Then Verify "Edit" Action for table record
    Then Verify "Delete" Action for table record
    Then Verify "Export CSV" button exists
    Then Verify "Add" button exists
    Then Verify "Reset View" button exists
    Then Add Access Group "SanityTestData.xlsx"
    Then Verify "Saved successfully!" Message on screen

    Examples: 
      | RowNo |
      |    42 |
