$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:Features/01_OrangeHRM.feature");
formatter.feature({
  "name": "OrangeHRM Login",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@SanityTest"
    }
  ]
});
formatter.scenario({
  "name": "Assign Leave",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@SanityTest"
    },
    {
      "name": "@cluster3"
    }
  ]
});
formatter.before({
  "error_message": "java.lang.NullPointerException\r\n\tat stepDefinitions.stepDefinitions.setupSuite(stepDefinitions.java:49)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "I launched chrome browser",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.i_launched_chrome_browser()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I open orange hrm homepage",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.i_open_orange_hrm_homepage()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "I login to hrm application with user \"Admin\" and password \"admin123\"",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.i_login_to_hrm_application_with_user_and_password(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "user click on Admin tab",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.user_click_on_tab()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "user click on Leave tab",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.user_click_on_Leave_tab()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "Assign a leave",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.assign_a_leave()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "logout from OHRM application",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.logout_from_OHRM_application()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "Verify logout link",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.verify_logout_link()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "close the browser",
  "keyword": "And "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.close_the_browser()"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "error_message": "java.lang.NullPointerException\r\n\tat stepDefinitions.stepDefinitions.tearDownMethod(stepDefinitions.java:68)\r\n",
  "status": "failed"
});
});