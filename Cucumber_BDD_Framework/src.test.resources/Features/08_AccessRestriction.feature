@EntityBasedAccessRestriction
Feature: 08_AccessRestriction

  @Cluster501
  Scenario Outline: Access Group List Add, Edit, Delete
    Given Open Casper Application in Web Browser "Definitions" "CASPER_EntityRestriction" "<RowNo>"
    Then Add "TC_Description" and "UserName"
    When Click on "User & Roles" tab
    When Click on "Access Groups" left menu
    Then Verify "Export CSV" button exists
    Then Verify "Reset View" button exists
    Then Search data collection in Access group "DCName"
    When Click on "View" action
    Then Verify left menu "Access Groups" get displayed
    Then Verify left menu "Access Group List" get displayed
    Then Verify "Edit Assignments" Action for table record
    Then Verify "View Assignments" Action for table record
    Then Verify "Delete" Action for table record
    Then Verify "Export CSV" button exists
    Then Verify "Add" button exists
    Then Verify "Reset View" button exists
    Then Add Access Group "Definitions.xlsx"
    Then Verify "Saved successfully!" Message on screen
    Then Search data collection in Access group "Access_Group_Name"
    When Click on "Edit Assignments" action
    Then Edit assigned Entities "RE01" and "RE03"
    When Click on "Save" button
    Then Search data collection in Access group "Access_Group_Name"
    When Click on "Delete" action
    Then Verify the "Confirmation" pop up displayed
    When Click on "Yes" button
    Then Verify "Deleted successfully!" Message on screen
    Then Add Access Group "Definitions.xlsx"
    Then Verify "Saved successfully!" Message on screen
    Then Search data collection in Access group "Access_Group_Name"
    When Click on "Edit Assignments" action
    Then Verify "Export CSV" button exists
    Then Edit assigned Entities "RE01" and "RE02"
    When Click on "Save" button

    Examples: 
      | RowNo |
      |     1 |

  #@Cluster502
  #Scenario Outline: Assign Access Group
    #Given Open Casper Application in Web Browser "Definitions" "CASPER_EntityRestriction" "<RowNo>"
    #Then Add "TC_Description" and "UserName"
    #When Click on "User & Roles" tab

    #Examples: 
      #| RowNo |
      #|     1 |
