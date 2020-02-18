package DataProvider;

import org.testng.annotations.Test;
import org.testng.annotations.*;

public class DataProviderTest {
	
	@Test(dataProvider = "TestData" )
	public void test(String userName, String password)
	{
		System.out.println(userName+ " "+ password);
	}

	@DataProvider(name = "TestData")
	public Object[][] getData()
	{
		Object[][] data = new Object[3][2];
		
		data[0][0] = "user1";
		data[0][1] = "password1";
				
		data[1][0] = "user2";
		data[1][1] = "password2";
				
		data[2][0] = "user3";
		data[2][1] = "password3";
				
		
		return data;
	}
}
