package uiBank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class GetLoanStatusAndVerify extends BaseRequestClass{
	
	@Test(dependsOnMethods={"uiBank.ApplyLoan.loanApply"})
	public void accountCreation()
	
	{
		
		Map<String,String> parameterRequest=new HashMap<String, String>();
		
		Response response=  RestAssured
				.given().log().all()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.get("quotes/"+loanId+"");
				
		Object object = response.jsonPath().get("accepted");
		Assert.assertEquals(object, true);
	    
	     }

	

}

