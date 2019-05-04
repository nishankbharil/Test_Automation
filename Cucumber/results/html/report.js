$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/login.feature");
formatter.feature({
  "line": 1,
  "name": "free CRM login feature",
  "description": "",
  "id": "free-crm-login-feature",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "comments": [
    {
      "line": 2,
      "value": "#withot examples keyword"
    },
    {
      "line": 4,
      "value": "#Scenario: free CRM login test scenario"
    },
    {
      "line": 5,
      "value": "#"
    },
    {
      "line": 6,
      "value": "#Given User is already on login page"
    },
    {
      "line": 7,
      "value": "#When Title of login page is Free CRM"
    },
    {
      "line": 8,
      "value": "#Then User enters \"admin\" and \"admin123\""
    },
    {
      "line": 9,
      "value": "#Then User clicks on login button"
    },
    {
      "line": 10,
      "value": "#And User is on home page"
    },
    {
      "line": 11,
      "value": "#And User close the browser"
    },
    {
      "line": 14,
      "value": "#with examples keyword"
    }
  ],
  "line": 15,
  "name": "free CRM login2 test scenario",
  "description": "",
  "id": "free-crm-login-feature;free-crm-login2-test-scenario",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 17,
  "name": "User is already on login page",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "Title of login page is Free CRM",
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "User enters \"\u003cusername\u003e\" and \"\u003cpassword\u003e\"",
  "keyword": "Then "
});
formatter.step({
  "line": 20,
  "name": "User clicks on login button",
  "keyword": "Then "
});
formatter.step({
  "line": 21,
  "name": "User is on home page",
  "keyword": "And "
});
formatter.step({
  "line": 22,
  "name": "User close the browser",
  "keyword": "And "
});
formatter.examples({
  "line": 24,
  "name": "",
  "description": "",
  "id": "free-crm-login-feature;free-crm-login2-test-scenario;",
  "rows": [
    {
      "cells": [
        "username",
        "password"
      ],
      "line": 25,
      "id": "free-crm-login-feature;free-crm-login2-test-scenario;;1"
    },
    {
      "cells": [
        "admin",
        "admin123"
      ],
      "line": 26,
      "id": "free-crm-login-feature;free-crm-login2-test-scenario;;2"
    },
    {
      "cells": [
        "admin",
        "admin123"
      ],
      "line": 27,
      "id": "free-crm-login-feature;free-crm-login2-test-scenario;;3"
    }
  ],
  "keyword": "Examples"
});
formatter.scenario({
  "line": 26,
  "name": "free CRM login2 test scenario",
  "description": "",
  "id": "free-crm-login-feature;free-crm-login2-test-scenario;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 17,
  "name": "User is already on login page",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "Title of login page is Free CRM",
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "User enters \"admin\" and \"admin123\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 20,
  "name": "User clicks on login button",
  "keyword": "Then "
});
formatter.step({
  "line": 21,
  "name": "User is on home page",
  "keyword": "And "
});
formatter.step({
  "line": 22,
  "name": "User close the browser",
  "keyword": "And "
});
formatter.match({
  "location": "LoginStepDefination.user_already_on_login_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "LoginStepDefination.title_of_login_page_is_free_crm()"
});
formatter.result({
  "status": "skipped"
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
  "status": "skipped"
});
formatter.match({
  "location": "LoginStepDefination.User_clicks_on_login_button()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "LoginStepDefination.User_is_on_home_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "LoginStepDefination.User_Close_The_Browser()"
});
formatter.result({
  "status": "skipped"
});
formatter.scenario({
  "line": 27,
  "name": "free CRM login2 test scenario",
  "description": "",
  "id": "free-crm-login-feature;free-crm-login2-test-scenario;;3",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 17,
  "name": "User is already on login page",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "Title of login page is Free CRM",
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "User enters \"admin\" and \"admin123\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 20,
  "name": "User clicks on login button",
  "keyword": "Then "
});
formatter.step({
  "line": 21,
  "name": "User is on home page",
  "keyword": "And "
});
formatter.step({
  "line": 22,
  "name": "User close the browser",
  "keyword": "And "
});
formatter.match({
  "location": "LoginStepDefination.user_already_on_login_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "LoginStepDefination.title_of_login_page_is_free_crm()"
});
formatter.result({
  "status": "skipped"
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
  "status": "skipped"
});
formatter.match({
  "location": "LoginStepDefination.User_clicks_on_login_button()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "LoginStepDefination.User_is_on_home_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "LoginStepDefination.User_Close_The_Browser()"
});
formatter.result({
  "status": "skipped"
});
});