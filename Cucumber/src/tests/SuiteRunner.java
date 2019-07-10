package tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features= {"features/tagging.feature"}, //path of feature file
		plugin= {"html:results/html", "json:results/json/result.json", "junit:junit_xml/cucumber.xml"}, 
		format= {"pretty", "html:test-outout", "json:json_output/cucumber.json"},
//		dryRun=true,
//		glue= {"stepDefinations"}, //path of the step defination file
		monochrome= true, //display the console output in a proper readable format
//		strict=true
		tags= {"~@Regression", "~@Sanity"}
		) //where to generate reports
public class SuiteRunner 
{
	
}

//tags= {"Sanity"}
//druRun  = true(This checks if mapping is completed)

//with Examples and without examples keyword