package tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features= {"features/Threads.feature"}, //what to run
		plugin= {"html:results/html", "json:results/json/result.json"}) //where to generate reports
public class SuiteRunner 
{
	
}

//tags= {"Sanity"}
//druRun  = true(This checks if mapping is completed)