package p1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {

	@Test(dataProvider = "MyData")
	public void TestDataProvider(String UName, String Pwd) {
		System.out.println(UName + " " + Pwd);
	}

	@DataProvider(name = "MyData")
	public Object[][] getData() {
		Object TestData[][] = { { "ABC", "DEF" }, { "LMN", "XYZ" } };
		return TestData;
	}

}
