package p1;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Test3 

{
	@Test(groups= {"Regression"}, dependsOnGroups= {"Sanity"})
	public void m4()
	{
		System.out.println("m4");
	}
	
	@Parameters({"AppURL"})
	@Test(groups= {"Sanity"})
	public void m5(String url)
	{
		System.out.println("m5");
		System.out.println("URL = "+ url);
//		int a=1/0;
	}
	
	@Test(groups= {"Regression"}, dependsOnGroups= {"Sanity"}, alwaysRun=true)
	public void m6()
	{
		System.out.println("m6");
	}
}
