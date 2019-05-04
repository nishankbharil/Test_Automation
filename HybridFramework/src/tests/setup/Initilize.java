package tests.setup;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import libs.Common;

public class Initilize

{
	@Parameters({ "Url" })
	@BeforeSuite
	public void setup(String appUrl) {

		Common.launchBrowser("Chrome", appUrl);
		Common.sleep(3000);

	}

}
