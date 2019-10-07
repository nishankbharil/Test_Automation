package Test_Cases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_AddNewEmployees {

	@Test(priority = 8, dataProvider="empdataprovider")
	void postNewEmployees(String ename, String esalary,String eage) {


		// Specify base URI
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Request paylaod sending along with post request
		JSONObject requestParams = new JSONObject();

		requestParams.put("name", ename);
		requestParams.put("salary", esalary);
		requestParams.put("age", eage);

		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(requestParams.toJSONString()); // attach above data to the request

		// Response object
		Response response = httpRequest.request(Method.POST, "/create");

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" + responseBody);
		
		// status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);

		Assert.assertEquals(responseBody.contains(ename), true);
		Assert.assertEquals(responseBody.contains(esalary), true);
		Assert.assertEquals(responseBody.contains(eage), true);

	}
	
	@DataProvider(name = "empdataprovider")
	String [][] getEmpData()
	{
//		String path = "C:\\My Documents\\Selenium\\Git_Repository_Learn_Selenium\\restAssuredFramework\\TestData\\TestData.xlsx";
//		String path = System.getProperty("user.dir")+ "\\TestData\\TestData.xlsx";
		String empdata[][]= {{"abc1231", "30001", "20"}, {"abc3211", "40001", "25"}, {"abc32141", "45002", "30"}};
		return (empdata);
	}
	
	

}
