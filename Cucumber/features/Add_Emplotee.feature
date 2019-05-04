Feature: Add employee feature
Scenario Outline: To add a new employee

Given User is already on login page
When Title of login page is Free CRM
Then User enters "<username>" and "<password>"
Then User clicks on login button
And User is on home page
And User clicks on Admin tab
And User clicks on Users link inside User Management
And User clicks on Add button
And User selects User role
And User selects Username
And User Selects status
Then User clicks on Save button
And User close the browser

Examples:
		|username|password|
		|admin   |admin123|
