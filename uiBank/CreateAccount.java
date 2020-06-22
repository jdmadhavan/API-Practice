package uiBank;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateAccount extends BaseRequestClass{
	
	@Test(dependsOnMethods= {"uiBank.BankLogin.login"})
	public void accountCreation()
	
	{
		Response response=  RestAssured
				.given().log().all()
				.headers("Authorization",autharization)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.body("{\r\n" + 
						"    \"friendlyName\": \"MDNTwo\",\r\n" + 
						"    \"type\": \"savings\",\r\n" + 
						"    \"accountNumber\": 24107375\r\n" + 
						"}")
				.post("accounts");
		
		
	    userId=response.jsonPath().get("userId");
		System.out.println("User ID: "+userId);
		
		
	}

}
