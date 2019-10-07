package Test_Cases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_GET_Authentication_API {
	@Test(priority = 7)
	void RegistrationSuccessful() {

		// Specify base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";

		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();

		authScheme.setUserName("ToolsQA");
		authScheme.setPassword("TestPassword");

		RestAssured.authentication = authScheme;

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object
		Response response = httpRequest.request(Method.GET, "/");

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" + responseBody);

		// status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);

	}
}
