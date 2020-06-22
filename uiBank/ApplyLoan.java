package uiBank;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApplyLoan extends BaseRequestClass{
	
	@Test
	public void loanApply()
	
	{
		Response response=  RestAssured
				.given().log().all()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.body("{\r\n" + 
						"    \"email\": \"madhavanram1@gmail.com\",\r\n" + 
						"    \"amount\": 70000,\r\n" + 
						"    \"term\": 5,\r\n" + 
						"    \"income\": 600000,\r\n" + 
						"    \"age\": 29\r\n" + 
						"}")
				.post("quotes/newquote");
		
		response.prettyPrint();
	    loanId=response.jsonPath().get("quoteid");
		System.out.println("Loan ID: "+loanId);
		
		
	}

}
