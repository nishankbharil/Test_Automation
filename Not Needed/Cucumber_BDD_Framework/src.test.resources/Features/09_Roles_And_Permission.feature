@RoleAndPermissionScenarios
Feature: 09_Roles_And_Permission

  @Cluster1.1
  Scenario Outline: Verify INT_COLLECTION_APPROVER Permissions  [TC_01 to TC_06]
    Given Open Casper Application in Web Browser "Definitions" "CASPER_RolesPersmissions" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "Collections" tab
    Then Verify "Add" button exists
    When Search record for filter value "DC_Code"
    When Click on "View" action
    Then Verify "Edit" button exists
    When Navigate to "Dictionaries" screen
    Then Verify "Add" button exists
    When Click on "Add" button
    When Enter Data Dictionary details "Definitions.xlsx"
    When Click on "Save" button
    When Search record for filter value "DD_Name"
    When Click on "View" action
    Then Verify "Edit" button exists
    When Navigate to "Dictionaries" screen
    When Search record for filter value "DD_Name"
    When Click on "Delete" action
    Then Verify the "Delete Confirmation" pop up displayed
    When Click on "Yes" button

    Examples: 
      | RowNo |
      |     3 |

  @Cluster1.2
  Scenario Outline: INT_COLLECTION_DESIGNER
    Given Open Casper Application in Web Browser "Definitions" "CASPER_RolesPersmissions" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    Then Verify tabs on home screen "Collections"
    Then Verify tabs on home screen "Dictionaries"
    When Click on "Collections" tab
    When Search record for filter value "DC_Code"
    When Click on "View" action
    Then Verify left menu "Metadata" get displayed
    Then Verify left menu "Import History" get displayed
    When Click on "Import History" left menu
    Then Verify "View Results" Action for table record
    Then Verify "Cancel" Action for table record
    Then Verify left menu "Modules" get displayed
    When Click on "Modules" tab
    When Search record for module
    Then Verify new entry created in Modules List "moduleCode"
    Then Verify "Add" button exists
    Then Verify "Edit" Action for table record
    Then Verify "Add New Version" Action for table record
    Then Verify "Upload Template" Action for table record
    Then Verify left menu "Data Points" get displayed
    When Click on "Data Points" tab
    Then Verify "Export Data Points" button exists
    Then Verify "Import Data Points" button exists
    Then Verify "Edit" Action for table record
    When Click on "Population" left menu
    Then Verify left menu "Entities" get displayed
    When Click on "Entities" left menu
    Then Verify "Add" button exists
    Then Verify "Import Entities" button exists
    Then Verify "Export Entities" button exists
    Then Verify "View Entity Attributes" Action for table record
    Then Verify "Edit" Action for table record
    Then Verify "Delete" Action for table record
    Then Verify left menu "Custom Attributes" get displayed
    When Click on "Custom Attributes" left menu
    Then Verify "Add" button exists
    Then Verify "Edit" Action for table record
    Then Verify "Delete" Action for table record
    Then Verify left menu "Entity Groups" get displayed
    When Click on "Entity Groups" left menu
    Then Verify "Add" button exists
    Then Verify "Edit Entity Group" Action for table record
    Then Verify "Delete Entity Group" Action for table record
    Then Verify "View Assignments" Action for table record
    Then Verify "Edit Assignments" Action for table record
    When Click on "Population" left menu
    When Click on "Obligations" left menu
    Then Verify left menu "Reporting Cycles" get displayed
    When Click on "Reporting Cycles" left menu
    Then Verify "Add" button exists
    Then Verify "Edit" Action for table record
    Then Verify "Delete" Action for table record
    Then Verify left menu "Obligations per Group" get displayed
    When Click on "Obligations per Group" left menu
    Then Verify "Add" button exists
    Then Verify "Edit" Action for table record
    Then Verify "Delete" Action for table record
    Then Verify left menu "Obligations per Entity" get displayed
    When Click on "Obligations per Entity" left menu
    When Click on "Obligations" left menu
    Then Verify left menu "Validation Rules" get displayed
    When Click on "Validation Rules" left menu
    Then Verify "Add" button exists
    Then Verify "Enable" button exists
    Then Verify "Disable" button exists
    Then Verify "Delete" button exists
    Then Verify "Import Validation Rules" button exists
    Then Verify "Export Validation Rules" button exists
    Then Verify "View" Action for table record
    Then Verify "Edit" Action for table record
    Then Verify "Delete" Action for table record
    When Click on "Workflow Settings" left menu
    Then Verify left menu "DISC Export" get displayed
    When Click on "DISC Export" left menu
    Then Verify "Edit" button exists
    Then Verify "Export Now" button exists
    When Click on "Export Now" button
    When Click on "Notification" left menu
    When Click on "DISC Export" left menu
    Then Verify "Cancel" Action for table record
    When Navigate to "Dictionaries" screen
    When Search record for filter value "DD_Name"
    When Click on "View" action
    Then Verify left menu "Metadata" get displayed
    When Click on "User & Roles" tab
    Then Verify tab not present on home screen "User Assignments"
    When Click on "Submissions" tab
    When Search DataCollection in List "DC_Code"
    Then Verify "View Submission List" Action for table record
    When Click on "Submissions" tab
    When Click on "View Submission List" action
    Then Verify "Revalidate" Action for table record

    Examples: 
      | RowNo |
      |     4 |

  @Cluster1.3
  Scenario Outline: EXT_DATA_SUBMITTER
    Given Open Casper Application in Web Browser "Definitions" "CASPER_RolesPersmissions" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    Then Verify tabs on home screen "Submissions"
    Then Verify tab not present on home screen "Collections"
    Then Verify tab not present on home screen "Dictionaries"
    Then Verify tab not present on home screen "User & Roles"
    When Click on "Submissions" tab
    When Search DataCollection in List "DC_Code"
    Then Verify "View Submission List" Action for table record
    When Click on "Submissions" tab
    When Click on "View Submission List" action
    Then Verify the "Submission List" Screen Name
    Then Verify "Zip Upload" button exists
    Then Verify left menu "Submission List" get displayed
    Then Verify left menu "File Vault" get displayed
    Then Verify left menu "Validation Results" get displayed
    Then Verify "Download File" Action for table record
    Then Verify "View Validation Results" Action for table record
    Then Verify "Download Template" Action for table record
    Then Verify "View Validation Rules" Action for table record
    When Click on "File Vault" left menu
    Then Verify the "File Vault" Screen Name
    Then Verify "Download" Action for table record
    Then Verify "View Validation Results" Action for table record
    When Click on "File Vault" left menu
    When Click on "View Validation Results" action
    Then Verify the "Validation Results for Submission" Screen Name

    Examples: 
      | RowNo |
      |     5 |

  @Cluster1.4
  Scenario Outline: INT_USER_ADMIN
    Given Open Casper Application in Web Browser "Definitions" "CASPER_RolesPersmissions" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    Then Verify tabs on home screen "User & Roles"
    Then Verify tab not present on home screen "Collections"
    Then Verify tab not present on home screen "Dictionaries"
    Then Verify tab not present on home screen "Submissions"
    When Click on "User & Roles" tab
    Then Verify the "User - Data Collection Assignment List" Screen Name
    Then Verify left menu "User Assignments" get displayed
    Then Verify left menu "Permission Configuration" get displayed
    Then Search for User "UserName" in User Assignment
    Then Verify "Assign" Action for table record
    Then Verify "Unassign" Action for table record
    When Click on "Permission Configuration" left menu
    Then Verify tabs on home screen "APPR_CASPER_EXT_DATA_SUBMITTER"
    Then Verify tabs on home screen "APPR_CASPER_EXT_MANAGER"
    Then Verify tabs on home screen "APPR_CASPER_EXT_TECHNICAL_USER"
    Then Verify tabs on home screen "APPR_CASPER_EXT_USER_ADMIN"
    Then Verify tabs on home screen "APPR_CASPER_INT_COLLECTION_APPROVER"
    Then Verify tabs on home screen "APPR_CASPER_INT_COLLECTION_DESIGNER"
    Then Verify tabs on home screen "APPR_CASPER_INT_COLLECTION_USER"
    Then Verify tabs on home screen "APPR_CASPER_INT_SUPER_USER_ADMIN"
    Then Verify tabs on home screen "APPR_CASPER_INT_SYSTEM_SUPPORT"
    Then Verify tabs on home screen "APPR_CASPER_INT_TECHNICAL_USER"
    Then Verify tabs on home screen "APPR_CASPER_INT_USER_ADMIN"
    When Click on "APPR_CASPER_INT_USER_ADMIN" tab
    When Click on "Submission Preparation" element
    When Click on "Submission Preparation" element
    When Click on "Collection Preparation" element
    When Click on "Collection Preparation" element
    When Click on "Data Dictionary" element
    When Click on "Data Dictionary" element
    When Click on "Authorisation" element
    When Click on "Authorisation" element

    Examples: 
      | RowNo |
      |     6 |

  @Cluster1.5
  Scenario Outline: INT_SUPER_USER_ADMIN
    Given Open Casper Application in Web Browser "Definitions" "CASPER_RolesPersmissions" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    Then Verify tabs on home screen "User & Roles"
    Then Verify tab not present on home screen "Collections"
    Then Verify tab not present on home screen "Dictionaries"
    Then Verify tab not present on home screen "Submissions"
    When Click on "User & Roles" tab
    Then Verify the "User - Data Collection Assignment List" Screen Name
    Then Verify left menu "User Assignments" get displayed
    Then Verify left menu "Permission Configuration" get displayed
    Then Search for User "UserName" in User Assignment
    Then Verify "Assign" Action for table record
    Then Verify "Unassign" Action for table record
    When Click on "Permission Configuration" left menu
    Then Verify "Edit" button exists
    Then Verify tabs on home screen "APPR_CASPER_EXT_DATA_SUBMITTER"
    Then Verify tabs on home screen "APPR_CASPER_EXT_MANAGER"
    Then Verify tabs on home screen "APPR_CASPER_EXT_TECHNICAL_USER"
    Then Verify tabs on home screen "APPR_CASPER_EXT_USER_ADMIN"
    Then Verify tabs on home screen "APPR_CASPER_INT_COLLECTION_APPROVER"
    Then Verify tabs on home screen "APPR_CASPER_INT_COLLECTION_DESIGNER"
    Then Verify tabs on home screen "APPR_CASPER_INT_COLLECTION_USER"
    Then Verify tabs on home screen "APPR_CASPER_INT_SUPER_USER_ADMIN"
    Then Verify tabs on home screen "APPR_CASPER_INT_SYSTEM_SUPPORT"
    Then Verify tabs on home screen "APPR_CASPER_INT_TECHNICAL_USER"
    Then Verify tabs on home screen "APPR_CASPER_INT_USER_ADMIN"
    When Click on "APPR_CASPER_INT_SUPER_USER_ADMIN" tab
    When Click on "Submission Preparation" element
    When Click on "Submission Preparation" element
    When Click on "Collection Preparation" element
    When Click on "Collection Preparation" element
    When Click on "Data Dictionary" element
    When Click on "Data Dictionary" element
    When Click on "Authorisation" element
    When Click on "Authorisation" element

    Examples: 
      | RowNo |
      |     7 |

  @Cluster1.6
  Scenario Outline: INT_SUPER_USER_ADMIN
    Given Open Casper Application in Web Browser "Definitions" "CASPER_RolesPersmissions" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    Then Verify tabs on home screen "Submissions"
    Then Verify tab not present on home screen "Collections"
    Then Verify tab not present on home screen "Dictionaries"
    Then Verify tab not present on home screen "User & Roles"
    Then Verify "URL" Tempering error

    Examples: 
      | RowNo |
      |     8 |

  @Cluster1.7
  Scenario Outline: INT_SUPER_USER_ADMIN
    Given Open Casper Application in Web Browser "Definitions" "CASPER_RolesPersmissions" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    Then Verify tabs on home screen "User & Roles"

    Examples: 
      | RowNo |
      |     7 |
