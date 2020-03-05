$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/tagging.feature");
formatter.feature({
  "line": 1,
  "name": "free CRM login2 test scenario",
  "description": "",
  "id": "free-crm-login2-test-scenario",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 2,
  "name": "free CRM login2 test scenario",
  "description": "",
  "id": "free-crm-login2-test-scenario;free-crm-login2-test-scenario",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "User is already on login page",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "Title of login page is Free CRM",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "User enters \"\u003cusername\u003e\" and \"\u003cpassword\u003e\"",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "User clicks on login button",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "User is on home page",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "User close the browser",
  "keyword": "And "
});
formatter.examples({
  "line": 11,
  "name": "",
  "description": "",
  "id": "free-crm-login2-test-scenario;free-crm-login2-test-scenario;",
  "rows": [
    {
      "cells": [
        "username",
        "password"
      ],
      "line": 12,
      "id": "free-crm-login2-test-scenario;free-crm-login2-test-scenario;;1"
    },
    {
      "cells": [
        "admin",
        "admin123"
      ],
      "line": 13,
      "id": "free-crm-login2-test-scenario;free-crm-login2-test-scenario;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 2013442800,
  "status": "passed"
});
formatter.scenario({
  "line": 13,
  "name": "free CRM login2 test scenario",
  "description": "",
  "id": "free-crm-login2-test-scenario;free-crm-login2-test-scenario;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "User is already on login page",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "Title of login page is Free CRM",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "User enters \"admin\" and \"admin123\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "User clicks on login button",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "User is on home page",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "User close the browser",
  "keyword": "And "
});
formatter.match({
  "location": "LoginStepDefination.user_already_on_login_page()"
});
formatter.result({
  "duration": 9261163800,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefination.title_of_login_page_is_free_crm()"
});
formatter.result({
  "duration": 12107100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "admin",
      "offset": 13
    },
    {
      "val": "admin123",
      "offset": 25
    }
  ],
  "location": "LoginStepDefination.User_enters_username_and_password(String,String)"
});
formatter.result({
  "duration": 198967600,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefination.User_clicks_on_login_button()"
});
formatter.result({
  "duration": 4612543400,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefination.User_is_on_home_page()"
});
formatter.result({
  "duration": 1274737700,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefination.User_Close_The_Browser()"
});
formatter.result({
  "duration": 687082800,
  "status": "passed"
});
});