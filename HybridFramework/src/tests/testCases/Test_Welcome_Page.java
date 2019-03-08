package tests.testCases;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import libs.Common;
import pageObjects.ConnectPage;
import pageObjects.ThreadsPage;
import pageObjects.WelcomePage;

public class Test_Welcome_Page

{
	@Test
	public void testFillForm() {
		WelcomePage welcome = PageFactory.initElements(Common.getDriver(), WelcomePage.class);

		welcome.clickConnect();

		List<String> data = Common.getTestData("TestWelcomePage.testFillForm");

		ConnectPage Conn = PageFactory.initElements(Common.getDriver(), ConnectPage.class);

		Conn.fillConnectForm(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4));

	}


}
