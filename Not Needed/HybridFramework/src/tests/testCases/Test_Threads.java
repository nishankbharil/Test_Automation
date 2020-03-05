package tests.testCases;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import libs.Common;
import pageObjects.ThreadsPage;
import pageObjects.WelcomePage;

public class Test_Threads 

{

	@Test
	public void testThreadsPage()
	{
		WelcomePage welcome = PageFactory.initElements(Common.getDriver(), WelcomePage.class);
		welcome.clickThreads();
		
		List<String> data1 = Common.getTestData("TestWelcomePage.testFillForm");
		
		ThreadsPage threads = PageFactory.initElements(Common.getDriver(), ThreadsPage.class);
		threads.filter(data1.get(1));
	}
	
}
