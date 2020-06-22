package uiBank;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BankLogin extends BaseRequestClass {
	
	@Test
	public void login()
	{
	
	Response response = RestAssured
			.given().log().all()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.when()
			.body("{\r\n" + 
					"    \"username\": \"madhavanr\",\r\n" + 
					"    \"password\": \"Abcdefmj1@\"\r\n" + 
					"}")
			.post("users/login");
	        autharization=response.jsonPath().get("id");
	        userId=response.jsonPath().get("userId");
	        System.out.println("Autherization: "+autharization);
	
	
	
	

	}
}
