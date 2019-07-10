@SmokeTestScenarios

Feature: Smoke Test
@Cluster1
Scenario Outline:  AUTO.01 Verify Home Page Tabs
    Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	Then Verify tabs on home screen "Submissions"
	Then Verify tabs on home screen "Collections"
	Then Verify tabs on home screen "Dictionaries"
	Then Verify tabs on home screen "User & Roles"
Examples:
|RowNo|
|1|

@Cluster2
Scenario Outline:  AUTO.02-AUTO.06 Verify Submission UI
   
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	When Click on "Submissions" tab
	Then Verify the "Data Collections" screen displayed
	Then Verify record count in table using column "code"
	Then Verify "Export CSV" button exists
	Then Verify "View Submission List" Action for table record
	Then Verify "View File Vault" Action for table record
	Then Verify data loaded in table for column "code"
Examples:
|RowNo|
|1|

@Cluster3
Scenario Outline:  AUTO.07-AUTO.12 Verify Submission UI
  
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
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
	Then Verify "Download" Action for table record
Examples:
|RowNo|
|1|

@Cluster4
Scenario Outline:  AUTO.13 Verify Submission UI
  
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	When Click on "Submissions" tab
	When Search record for filter value "DC_Code"
	When Click on "View Submission List" action
	When Click on "Zip Upload" button
	Then Verify the "Zip File Upload" pop up displayed
Examples:
|RowNo|
|1| 

@Cluster5
Scenario Outline:  AUTO.14-AUTO.19 Verify Submission UI
  
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	When Click on "Submissions" tab
	When Search record for filter value "DC_Code"
	When Click on "View Submission List" action
	When Click on "File Vault" tab
	Then Verify the "File Vault" screen displayed
	Then Verify record count in table using column "fileName"
	Then Verify "Export CSV" button exists
	When Click on "Submissions" tab
	When Click on "View File Vault" action
	Then Verify the "File Vault" screen displayed
	Then Verify data loaded in table for column "fileName"
	Then Verify "Download" Action for table record
	Then Verify "View Validation Results" Action for table record
Examples:
|RowNo|
|1|

@Cluster6
Scenario Outline: AUTO.20-AUTO.24 Verify Definition UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	When Click on "Collections" tab
	When Search record for filter value "DC_Code"
	Then Verify the "Data Collections" screen displayed
	Then Verify record count in table using column "code"
	Then Verify data loaded in table for column "code"
	Then Verify "Add" button exists
	Then Verify "Export CSV" button exists
	Then Verify "View" Action for table record
	Then Verify "Edit" Action for table record
Examples:
|RowNo|
|1|

@Cluster7
Scenario Outline: AUTO.25 Verify Definition UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	When Click on "Collections" tab
	When Click on "Add" button
	Then Verify the "Add Data Collection" pop up displayed
Examples:
|RowNo|
|1|

@Cluster8
Scenario Outline: AUTO.26 Verify Definition UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	When Click on "Collections" tab
	When Search record for filter value "DC_Code"
	When Click on "Edit" action
	Then Verify the "Edit Data Collection" pop up displayed
Examples:
|RowNo|
|1|

@Cluster9
Scenario Outline: AUTO.27-AUTO.29 Verify Definition and Metadata UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	When Click on "Collections" tab
	When Search record for filter value "DC_Code"
	When Click on "View" action
	Then Verify the "Metadata" screen displayed
	When Click on "Edit" button
	Then Verify the "Edit Data Collection" pop up displayed
	When Click on "Cancel" button
	Then Verify the "Metadata" screen displayed
	Then Check Calculations for "Max. # Data Points" field
	Then Check Calculations for "# Data Points" field
	Then Check Calculations for "Max. # Reporting Entities" field
	Then Check Calculations for "# Reporting Entities" field
Examples:
|RowNo|
|1|

@Cluster10
Scenario Outline: AUTO.30-AUTO.32 Verify Definition and Metadata UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
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
	Then Verify left menu "Cycles" get displayed
	Then Verify left menu "Obligations per Group" get displayed
	Then Verify left menu "Obligations per Entity" get displayed
Examples:
|RowNo|
|1|

@Cluster11
Scenario Outline: AUTO.33-AUTO.37 Verify Definition Screen
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	When Click on "Collections" tab
	When Search record for filter value "DC_Code"
	When Click on "View" action
	When Click on "Modules" tab
	Then Verify the "Modules" screen displayed
	Then Verify record count in table using column "moduleName"
	Then Verify data loaded in table for column "moduleName"
	Then Verify "Add" button exists
	Then Verify "Export CSV" button exists
	Then Verify "Edit" Action for table record
	Then Verify "Add New Version" Action for table record
	Then Verify "Upload Template" Action for table record
Examples:
|RowNo|
|1|

@Cluster12
Scenario Outline: AUTO.38-AUTO.39 Verify Modules UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	When Click on "Collections" tab
	When Search record for filter value "DC_Code"
	When Click on "View" action
	When Click on "Modules" tab
	When Click on "Add" button
	Then Verify the "Add Module" pop up displayed
	Then Verify "Save" button exists
	Then Verify "Cancel" button exists
Examples:
|RowNo|
|1|

@Cluster13
Scenario Outline: AUTO.40 Verify Modules UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	When Click on "Collections" tab
	When Search record for filter value "DC_Code"
	When Click on "View" action
	When Click on "Modules" tab
	When Click on "Edit" action
	Then Verify the "Edit Module" pop up displayed
Examples:
|RowNo|
|1|

@Cluster14
Scenario Outline: AUTO.41-AUTO.43 Verify Modules UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
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
|RowNo|
|1|

@Cluster16
Scenario Outline: AUTO.45-AUTO.51 Verify Population_Entities UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
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
	When Click on "View Entity Attributes" action
	Then Verify the "View Entity Attributes" pop up displayed
	Then Verify "Export CSV" button exists
	Then Verify "Close" button exists
	When Click on "Close" button

Examples:
|RowNo|
|1|	
	
@Cluster17
Scenario Outline: AUTO.53 Verify Population_Entities UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
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
|RowNo|
|1|		
	
@Cluster18
Scenario Outline: AUTO.54 Verify Population_Entities UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	When Click on "Collections" tab
	When Search record for filter value "DC_Code"
	When Click on "View" action
	When Click on "Population" tab
	When Click on "Entities" tab
	When Click on "Import Entities" button
	Then Verify the "Import Entities" pop up displayed
	Then Verify "Upload" button exists
	Then Verify "Cancel" button exists
	When Click on "Cancel" button
Examples:
|RowNo|
|1|	

@Cluster19
Scenario Outline: AUTO.55-AUTO.56 Verify Population_Entities UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	When Click on "Collections" tab
	When Search record for filter value "DC_Code"
	When Click on "View" action
	When Click on "Population" tab
	When Click on "Entities" tab
	When Click on "Import Entities" button
	Then Verify the "Import Entities" pop up displayed
	Then Verify "Upload" button exists
	Then Verify "Cancel" button exists
	When Click on "Cancel" button
Examples:
|RowNo|
|1|	

@Cluster20
Scenario Outline: AUTO.57-AUTO.63 Verify Population_CustomAttributes UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
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
	When Click on "View" action
	Then Verify the "View Custom Entity Attribute" pop up displayed
	When Click on "Close" button
Examples:
|RowNo|
|1|	

@Cluster21
Scenario Outline: AUTO.64-AUTO.65 Verify Population_CustomAttributes UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
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
|RowNo|
|1|

@Cluster22
Scenario Outline: AUTO.66-AUTO.67 Verify Population_CustomAttributes UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
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
|RowNo|
|1|

@Cluster23
Scenario Outline: AUTO.68-AUTO.74 Verify Population_Entity Groups UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
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
	When Click on "View Assignments" action
	Then Verify the "View Assigned Entities" pop up displayed
	When Click on "Close" button
Examples:
|RowNo|
|1|

@Cluster24
Scenario Outline: AUTO.75 Verify Population_Entity Groups UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	When Click on "Collections" tab
	When Search record for filter value "DC_Code"
	When Click on "View" action
	When Click on "Population" tab
	When Click on "Entity Groups" tab
	Then Verify the "Entity Groups" screen displayed
	When Click on "Add" button
	Then Verify the "Add Entity Group" pop up displayed	
Examples:
|RowNo|
|1|

@Cluster25
Scenario Outline: AUTO.76-AUTO.77 Verify Population_Entity Groups UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
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
|RowNo|
|1|

@Cluster26
Scenario Outline: AUTO.78-AUTO.79 Verify Population_Entity Groups UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
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
|RowNo|
|1|

@Cluster27
Scenario Outline: AUTO.80-AUTO.86 Verify Obligation cycles UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	When Click on "Collections" tab
	When Search record for filter value "DC_Code"
	When Click on "View" action
	When Click on "Obligations" tab
	When Click on "Cycles" tab
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
|RowNo|
|1|

@Cluster28
Scenario Outline: AUTO.87-AUTO.88 Verify Obligation cycles UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	When Click on "Collections" tab
	When Search record for filter value "DC_Code"
	When Click on "View" action
	When Click on "Obligations" tab
	When Click on "Cycles" tab
	When Click on "Edit" action
	Then Verify the "Edit Reporting Cycle" pop up displayed
	Then Verify "Save" button exists
	Then Verify "Cancel" button exists
Examples:
|RowNo|
|1|

@Cluster29
Scenario Outline: AUTO.89-AUTO.96 Verify Obligation Per Group UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
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
	When Click on "View Detail" action
	Then Verify the "View Reporting Obligation Detail" pop up displayed
Examples:
|RowNo|
|1|

@Cluster30
Scenario Outline: AUTO.97 Verify Obligation Per Group UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	When Click on "Collections" tab
	When Search record for filter value "DC_Code"
	When Click on "View" action
	When Click on "Obligations" tab
	When Click on "Obligations per Group" tab
	When Click on "Edit" action
	Then Verify the "Edit Reporting Obligations" pop up displayed
Examples:
|RowNo|
|1|

@Cluster31
Scenario Outline: AUTO.98 Verify Obligation Per Group UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	When Click on "Collections" tab
	When Search record for filter value "DC_Code"
	When Click on "View" action
	When Click on "Obligations" tab
	When Click on "Obligations per Group" tab
	Then Verify "Delete" Action for table record
Examples:
|RowNo|
|1|

@Cluster32
Scenario Outline: AUTO.99-AUTO.102 Verify Obligation Per Group UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
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
|RowNo|
|1|

@Cluster33
Scenario Outline: AUTO.103-AUTO.108 AUTO.111 Verify Data Dictionary UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
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
	When Click on "View" action
	Then Verify the "Data Dictionary Metadata" screen displayed
	Examples:
|RowNo|
|1|

@Cluster34
Scenario Outline: AUTO.109-AUTO.110 Verify Data Dictionary UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	Then Verify tabs on home screen "Dictionaries"
	When Click on "Dictionaries" tab
	When Click on "Add" button
	Then Verify the "Add Data Dictionary" pop up displayed
	Then Verify "Save" button exists
	Then Verify "Cancel" button exists
	Examples:
|RowNo|
|1|

@Cluster35
Scenario Outline: AUTO.112-AUTO.113 Verify Data Dictionary UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	Then Verify tabs on home screen "Dictionaries"
	When Click on "Dictionaries" tab
	When Click on "View" action
	Then Verify "Edit" button exists
	When Click on "Edit" button
	Then Verify the "Edit Data Dictionary" pop up displayed
	Examples:
|RowNo|
|1|

@Cluster36
Scenario Outline: AUTO.114-AUTO.121 Verify User & Roles UI
	Given Open Casper Application in Web Browser "CASPERLogin" "Login" "<RowNo>"
	Then Verify tabs on home screen "User & Roles"
	When Click on "User & Roles" tab
	Then Verify the "User - Data Collection Assignment List" screen displayed
	Then Verify left menu "Users" get displayed
	When Click on "Search" button
	Then Verify record count in table using column "userId"
	Then Verify "Assign" Action for table record
	Then Verify "Unassign" Action for table record
	When Click on "Assign" action
	Then Verify the "Assign Data Collections" pop up displayed
	When Click on "Cancel" button
	When Click on "Roles" left menu
	When Click on "APPR_CASPER_INT_SUPER_USER_ADMIN" tab
    When Click on "Authorisation" element    
    Then Verify "Manage User Assignments to data collections" checkbox is checked
    Then Verify "Roles" checkbox is checked
	Examples:
|RowNo|
|2|