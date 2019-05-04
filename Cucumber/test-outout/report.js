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
formatter.before({
  "duration": 2017730500,
  "status": "passed"
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
  "duration": 4237012600,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefination.title_of_login_page_is_free_crm()"
});
formatter.result({
  "duration": 7495600,
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
  "duration": 48318100,
  "error_message": "org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element: {\"method\":\"name\",\"selector\":\"txtUsername\"}\n  (Session info: chrome\u003d73.0.3683.103)\n  (Driver info: chromedriver\u003d2.42.591088 (7b2b2dca23cca0862f674758c9a3933e685c27d5),platform\u003dWindows NT 10.0.17763 x86_64) (WARNING: The server did not provide any stacktrace information)\nCommand duration or timeout: 0 milliseconds\nFor documentation on this error, please visit: http://seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00273.14.0\u0027, revision: \u0027aacccce0\u0027, time: \u00272018-08-02T20:13:22.693Z\u0027\nSystem info: host: \u0027DESKTOP-U94MBI6\u0027, ip: \u0027127.0.0.1\u0027, os.name: \u0027Windows 10\u0027, os.arch: \u0027amd64\u0027, os.version: \u002710.0\u0027, java.version: \u00271.8.0_201\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities {acceptInsecureCerts: false, acceptSslCerts: false, applicationCacheEnabled: false, browserConnectionEnabled: false, browserName: chrome, chrome: {chromedriverVersion: 2.42.591088 (7b2b2dca23cca0..., userDataDir: C:\\Users\\NISHAN~1\\AppData\\L...}, cssSelectorsEnabled: true, databaseEnabled: false, goog:chromeOptions: {debuggerAddress: localhost:52277}, handlesAlerts: true, hasTouchScreen: false, javascriptEnabled: true, locationContextEnabled: true, mobileEmulationEnabled: false, nativeEvents: true, networkConnectionEnabled: false, pageLoadStrategy: normal, platform: XP, platformName: XP, rotatable: false, setWindowRect: true, takesHeapSnapshot: true, takesScreenshot: true, unexpectedAlertBehaviour: , unhandledPromptBehavior: , version: 73.0.3683.103, webStorageEnabled: true}\nSession ID: da42d7f031d5b7fdb5cd757384bd5d55\n*** Element info: {Using\u003dname, value\u003dtxtUsername}\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(Unknown Source)\r\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(Unknown Source)\r\n\tat java.lang.reflect.Constructor.newInstance(Unknown Source)\r\n\tat org.openqa.selenium.remote.ErrorHandler.createThrowable(ErrorHandler.java:214)\r\n\tat org.openqa.selenium.remote.ErrorHandler.throwIfResponseFailed(ErrorHandler.java:166)\r\n\tat org.openqa.selenium.remote.http.JsonHttpResponseCodec.reconstructValue(JsonHttpResponseCodec.java:40)\r\n\tat org.openqa.selenium.remote.http.AbstractHttpResponseCodec.decode(AbstractHttpResponseCodec.java:80)\r\n\tat org.openqa.selenium.remote.http.AbstractHttpResponseCodec.decode(AbstractHttpResponseCodec.java:44)\r\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:158)\r\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:83)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:548)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:322)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementByName(RemoteWebDriver.java:400)\r\n\tat org.openqa.selenium.By$ByName.findElement(By.java:284)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:314)\r\n\tat tests.stepDefinations.LoginStepDefination.User_enters_username_and_password(LoginStepDefination.java:40)\r\n\tat ✽.Then User enters \"admin\" and \"admin123\"(features/login.feature:19)\r\n",
  "status": "failed"
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
formatter.before({
  "duration": 2000582300,
  "status": "passed"
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
  "duration": 2839669300,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefination.title_of_login_page_is_free_crm()"
});
formatter.result({
  "duration": 14376700,
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
  "duration": 29023700,
  "error_message": "org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element: {\"method\":\"name\",\"selector\":\"txtUsername\"}\n  (Session info: chrome\u003d73.0.3683.103)\n  (Driver info: chromedriver\u003d2.42.591088 (7b2b2dca23cca0862f674758c9a3933e685c27d5),platform\u003dWindows NT 10.0.17763 x86_64) (WARNING: The server did not provide any stacktrace information)\nCommand duration or timeout: 0 milliseconds\nFor documentation on this error, please visit: http://seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00273.14.0\u0027, revision: \u0027aacccce0\u0027, time: \u00272018-08-02T20:13:22.693Z\u0027\nSystem info: host: \u0027DESKTOP-U94MBI6\u0027, ip: \u0027127.0.0.1\u0027, os.name: \u0027Windows 10\u0027, os.arch: \u0027amd64\u0027, os.version: \u002710.0\u0027, java.version: \u00271.8.0_201\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities {acceptInsecureCerts: false, acceptSslCerts: false, applicationCacheEnabled: false, browserConnectionEnabled: false, browserName: chrome, chrome: {chromedriverVersion: 2.42.591088 (7b2b2dca23cca0..., userDataDir: C:\\Users\\NISHAN~1\\AppData\\L...}, cssSelectorsEnabled: true, databaseEnabled: false, goog:chromeOptions: {debuggerAddress: localhost:52287}, handlesAlerts: true, hasTouchScreen: false, javascriptEnabled: true, locationContextEnabled: true, mobileEmulationEnabled: false, nativeEvents: true, networkConnectionEnabled: false, pageLoadStrategy: normal, platform: XP, platformName: XP, rotatable: false, setWindowRect: true, takesHeapSnapshot: true, takesScreenshot: true, unexpectedAlertBehaviour: , unhandledPromptBehavior: , version: 73.0.3683.103, webStorageEnabled: true}\nSession ID: 6a7ae741e9e8b1fcf1a9b6b3f170362e\n*** Element info: {Using\u003dname, value\u003dtxtUsername}\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(Unknown Source)\r\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(Unknown Source)\r\n\tat java.lang.reflect.Constructor.newInstance(Unknown Source)\r\n\tat org.openqa.selenium.remote.ErrorHandler.createThrowable(ErrorHandler.java:214)\r\n\tat org.openqa.selenium.remote.ErrorHandler.throwIfResponseFailed(ErrorHandler.java:166)\r\n\tat org.openqa.selenium.remote.http.JsonHttpResponseCodec.reconstructValue(JsonHttpResponseCodec.java:40)\r\n\tat org.openqa.selenium.remote.http.AbstractHttpResponseCodec.decode(AbstractHttpResponseCodec.java:80)\r\n\tat org.openqa.selenium.remote.http.AbstractHttpResponseCodec.decode(AbstractHttpResponseCodec.java:44)\r\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:158)\r\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:83)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:548)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:322)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElementByName(RemoteWebDriver.java:400)\r\n\tat org.openqa.selenium.By$ByName.findElement(By.java:284)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:314)\r\n\tat tests.stepDefinations.LoginStepDefination.User_enters_username_and_password(LoginStepDefination.java:40)\r\n\tat ✽.Then User enters \"admin\" and \"admin123\"(features/login.feature:19)\r\n",
  "status": "failed"
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