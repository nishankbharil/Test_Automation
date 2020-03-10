package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(plugin = {	"html:target/Cucumber reports" },
				 features = "Features/", 
				 glue = {"stepDefinitions"})
//				  
//				 dryRun = false, monochrome = false)
// tags= {"@Cluster11,@Cluster12"})
public class testRunner extends AbstractTestNGCucumberTests {
	
	

}
