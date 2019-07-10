package getRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class getData

{
//	@Test
	public void testResponsecode() {
		Response resp = RestAssured.get(
				"https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		int code = resp.getStatusCode();
		System.out.println("Status code is: " + code);

		Assert.assertEquals(code, 200);
		
//		ResponseBody s = resp.body();
//		
//		System.out.println(s);
		
	}

//	@Test
	public void testBody() {
		Response resp = RestAssured.get(
				"https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");

		String data = resp.asString();
//		String a = resp.getStatusLine();
		boolean ss = data.contains("light intensity drizzle");
		
		int code = resp.getStatusCode();
		
		System.out.println(ss);
		System.out.println("Data is " + data);
		System.out.println("response time " + resp.getTime());
//		data.
//		Assert.assertEquals(code, 200);
 
	}
	
//	@Test
	public void test_numberOfCircuitsFor2017_Seacon()
	{
		given().
		when().
		get ("http://ergast.com/api/f1/2017/circuits.json").
		then(). 
		assertThat().
		statusCode(200).
		and().
		body("MRData.CircuitTable.Circuits.circuitId", hasSize(20)).
		and().
		header("content-length", equalTo("4551"));
	
	}
	
//	@Test
	public void testRestAssured()
	{
		Response resp = RestAssured.get("http://ergast.com/api/f1/2017/circuits.json");
		int Statuscode = resp.getStatusCode();
		
		String s = resp.asString();
		//Hard Assertion
		resp.then().body("MRData.CircuitTable.Circuits.circuitId[4]", equalTo("catalunya")).body("MRData.CircuitTable.Circuits.circuitName[4]", equalTo("Circuit de Barcelona-Catalunya"))
		.body("MRData.CircuitTable.Circuits.Location[4].locality", equalTo("Montmeló"));
		//Soft Assertion
		resp.then().body("MRData.CircuitTable.Circuits.circuitId[4]", equalTo("catalunya"), "MRData.CircuitTable.Circuits.circuitName[4]", equalTo("Circuit de Barcelona-Catalunya"), "MRData.CircuitTable.Circuits.Location[4].locality", equalTo("Montmeló"));
		System.out.println(Statuscode);
		System.out.println(s);
		
		//other way of validation in rest assured
		//Hard Assert
		Assert.assertEquals(resp.path("MRData.CircuitTable.Circuits.circuitId[4]"), "catalunya");
		
		//Soft Assert
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(resp.path("MRData.CircuitTable.Circuits.circuitId[4]"), "catalunya");
		soft.assertAll();
	
	}
	
	@Test
	public void testPostRequest()
	{
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
		JSONObject json = new JSONObject();
		json.put("name", "test");
		json.put("job", "Automation");
		
		request.body(json.toJSONString());
		
		Response response = request.post("https://reqres.in/api/users");
		
		int code = response.getStatusCode();
		System.out.println(code);
	}
}