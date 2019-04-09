package MyRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
		@CucumberOptions(
				features = "C:\\My Documents\\Selenium\\Selenium_Practice\\FreeCRMBDDFramework\\src\\main\\java\\Features\\login.feature", //the path of the feature files
				glue={"stepDefinitions"} )
		 
		public class TestRunner
		{
		 	
		}