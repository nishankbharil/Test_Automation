package TestFramework;
import org.testng.annotations.Test;

import googleAPIs.payLoad;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class basics2 extends payLoad{

	
//	@Test
	public void createPlaceAPI()
	{
		RestAssured.baseURI="https://maps.googleapis.com";
		given().
		
		queryParam("key","AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").
		body(payLoad.createPlaceData()).
		when().
		post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK"));
		
	// Create a place =response (place id)
		
	// delete Place = (Request - Place id)
		

	}
	
//	@Test
	public void testResReq()
	{
		RestAssured.baseURI = "https://reqres.in";
		
		Response res = given().

		when().get("/api/users?page=2").
		
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
	
		extract().response();
		
		String s1 = res.asString();
		System.out.println(s1);
		
		JsonPath jp = new JsonPath(s1);
		String s2 = jp.get("data[3].first_name");
		
		System.out.println(s2);
	}
	
	@Test
	public void testResReqpost()
	{
		RestAssured.baseURI = "https://reqres.in";
		
		Response res = given().body(payLoad.getdata()).
				

		when().post("/api/users").
		
		then().assertThat().statusCode(201).and().contentType(ContentType.JSON).header("server", "cloudflare").
	
		
		extract().response();
		
		String s1 = res.asString();
		System.out.println(s1);
		
		
//		JsonPath jp = new JsonPath(s1);
//		String s2 = jp.get("data[3].first_name");
//		
//		System.out.println(s2);
	
	
	}
	
	
	
}
