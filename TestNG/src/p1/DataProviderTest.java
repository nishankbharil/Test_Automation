package p1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {
	
	@Test(dataProvider = "MyData")
	public void TestDataProvider()
	{
		System.out.println();
	}
	
	
	@DataProvider(name = "MyData")
	public Object[][] getData()
	{
		Object TestData[][] = {{"ABC", "DEF"}, {"LMN", "XYZ"}};
		return TestData;
	}

}
