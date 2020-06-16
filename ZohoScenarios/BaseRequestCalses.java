package zohoCutomer;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseRequestCalses {
	
	
	
	public static Map<String,Object>headerVal=new HashMap<String, Object>();
	public static String organizationId="717292366";
	public static String customer_id;
	@BeforeSuite
	public void baseSetup()	
	{
		

		RestAssured.baseURI="https://subscriptions.zoho.com/api/v1/";	
		RestAssured.authentication=RestAssured.oauth2("1000.633cf4fa1491c96b6a98ce89cb291353.124d21f2b006fb3121096874b0255c51");
		headerVal.put("X-com-zoho-subscriptions-organizationid", organizationId);
		
	

	}

}
