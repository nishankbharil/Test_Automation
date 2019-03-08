$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/Threads.feature");
formatter.feature({
  "line": 1,
  "name": "Threads feature",
  "description": "",
  "id": "threads-feature",
  "keyword": "Feature"
});
formatter.before({
  "duration": 1991825260,
  "status": "passed"
});
formatter.scenario({
  "line": 2,
  "name": "user is able to click on Threads page",
  "description": "",
  "id": "threads-feature;user-is-able-to-click-on-threads-page",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 3,
  "name": "that user is on welcome page",
  "keyword": "Given "
});
formatter.step({
  "line": 4,
  "name": "user clicks on Threads link",
  "keyword": "When "
});
formatter.match({
  "location": "ConnectPageStepDefs.launchWelcome()"
});
formatter.result({
  "duration": 8501308451,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Threads",
      "offset": 15
    }
  ],
  "location": "ConnectPageStepDefs.clickLinks(String)"
});
formatter.result({
  "duration": 11715432908,
  "status": "passed"
});
});