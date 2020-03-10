$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:Features/OrangeHRM.feature");
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
  "name": "Logo present on OrangeHRM home page",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@SanityTest"
    },
    {
      "name": "@cluster1"
    }
  ]
});
formatter.step({
  "name": "I launched chrome browser",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.i_launched_chrome_browser()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I open orange hrm homepage",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.i_open_orange_hrm_homepage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I verify that logo present on page",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.i_verify_that_logo_present_on_page()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "close the browser",
  "keyword": "And "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.close_the_browser()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Login OHRM Application",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@SanityTest"
    },
    {
      "name": "@cluster2"
    }
  ]
});
formatter.step({
  "name": "I launched chrome browser",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.i_launched_chrome_browser()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I open orange hrm homepage",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.i_open_orange_hrm_homepage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I login to hrm application with user \"Admin\" and password \"admin123\"",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.i_login_to_hrm_application_with_user_and_password(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "close the browser",
  "keyword": "And "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.close_the_browser()"
});
formatter.result({
  "status": "passed"
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
formatter.step({
  "name": "I launched chrome browser",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.i_launched_chrome_browser()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I open orange hrm homepage",
  "keyword": "When "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.i_open_orange_hrm_homepage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I login to hrm application with user \"Admin\" and password \"admin123\"",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.i_login_to_hrm_application_with_user_and_password(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user click on Admin tab",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.user_click_on_tab()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user click on Leave tab",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.user_click_on_Leave_tab()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Assign a leave",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.assign_a_leave()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "logout from OHRM application",
  "keyword": "Then "
});
formatter.match({
  "location": "stepDefinitions.stepDefinitions.logout_from_OHRM_application()"
});
formatter.result({
  "error_message": "org.openqa.selenium.ElementNotInteractableException: element not interactable\n  (Session info: chrome\u003d80.0.3987.132)\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027DESKTOP-U94MBI6\u0027, ip: \u002710.0.75.1\u0027, os.name: \u0027Windows 10\u0027, os.arch: \u0027amd64\u0027, os.version: \u002710.0\u0027, java.version: \u00271.8.0_201\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities {acceptInsecureCerts: false, browserName: chrome, browserVersion: 80.0.3987.132, chrome: {chromedriverVersion: 79.0.3945.36 (3582db32b3389..., userDataDir: C:\\Users\\NISHAN~1\\AppData\\L...}, goog:chromeOptions: {debuggerAddress: localhost:56864}, javascriptEnabled: true, networkConnectionEnabled: false, pageLoadStrategy: normal, platform: WINDOWS, platformName: WINDOWS, proxy: Proxy(), setWindowRect: true, strictFileInteractability: false, timeouts: {implicit: 0, pageLoad: 300000, script: 30000}, unhandledPromptBehavior: dismiss and notify}\nSession ID: 06142e9b546e6894b2679bea6746a0d6\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\r\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\r\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\r\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.createException(W3CHttpResponseCodec.java:187)\r\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:122)\r\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:49)\r\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:158)\r\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:83)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\r\n\tat org.openqa.selenium.remote.RemoteWebElement.execute(RemoteWebElement.java:285)\r\n\tat org.openqa.selenium.remote.RemoteWebElement.click(RemoteWebElement.java:84)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:498)\r\n\tat org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler.invoke(LocatingElementHandler.java:51)\r\n\tat com.sun.proxy.$Proxy20.click(Unknown Source)\r\n\tat pageObjects.HomePage.clickOnLogout(HomePage.java:32)\r\n\tat stepDefinitions.stepDefinitions.logout_from_OHRM_application(stepDefinitions.java:88)\r\n\tat ✽.logout from OHRM application(file:///C:/My%20Documents/Selenium/Cucumber/Features/OrangeHRM.feature:26)\r\n",
  "status": "failed"
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
});