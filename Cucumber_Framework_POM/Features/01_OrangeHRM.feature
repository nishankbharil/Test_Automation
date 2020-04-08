@SanityTest
Feature: OrangeHRM Login

@cluster1
Scenario: Logo present on OrangeHRM home page 
	Given I launched chrome browser 
	When I open orange hrm homepage 
	Then I verify that logo present on page 
	And close the browser 

@cluster2	
Scenario: Login OHRM Application
	Given I launched chrome browser 
	When I open orange hrm homepage 
	Then I login to hrm application with user "Admin" and password "admin123" 
	And close the browser

@cluster3
Scenario: Assign Leave 
	Given I launched chrome browser 
	When I open orange hrm homepage 
	Then I login to hrm application with user "Admin" and password "admin123"
	Then user click on Admin tab
	Then user click on Leave tab
	Then Assign a leave
	Then logout from OHRM application
	Then Verify logout link
	And close the browser