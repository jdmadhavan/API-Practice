package uiBank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAccountDetails extends BaseRequestClass{
	
	@Test(dependsOnMethods={"uiBank.BankLogin.login"})
	public void accountCreation()
	
	{
		
		Map<String,String> parameterRequest=new HashMap<String, String>();
		parameterRequest.put("filter[where][userId]", userId);
		Response response=  RestAssured
				.given().log().all()
				.headers("Authorization",autharization)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.get("accounts");
	     List<Map<Object, Object>>list = response.jsonPath().getList("");
	     
	     for(int i=0;i<list.size()-1;i++)
	     {   System.out.println("****************************************");
	    	 System.out.println("Account ID: "+list.get(i).get("id"));
	    	 System.out.println("User ID: "+list.get(i).get("userId"));
	    	 System.out.println("Account No: "+list.get(i).get("accountNumber"));
	    	 System.out.println("Account Type: "+list.get(i).get("type"));
	    	 System.out.println("Account Balance: "+list.get(i).get("balance"));
	    	 System.out.println("****************************************");
	     }
	

}
}
