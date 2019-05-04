package getRequest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;


public class getData

{
	@Test
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

	@Test
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
	
	@Test
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

}

