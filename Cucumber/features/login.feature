Feature: free CRM login feature
#withot examples keyword

#Scenario: free CRM login test scenario
#
#Given User is already on login page
#When Title of login page is Free CRM
#Then User enters "admin" and "admin123"
#Then User clicks on login button
#And User is on home page
#And User close the browser


#with examples keyword
Scenario Outline: free CRM login2 test scenario

Given User is already on login page
When Title of login page is Free CRM
Then User enters "<username>" and "<password>"
Then User clicks on login button
And User is on home page
And User close the browser

Examples:
		|username|password|
		|admin   |admin123|
		|admin   |admin123|

