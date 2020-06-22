package uiBank;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseRequestClass {
	
	
	
	public static String autharization;
	public static String userId;
	public static String loanId;
	public static Map<String,Object>headerVal=new HashMap<String, Object>();
	
	@BeforeSuite
	public void baseSetup()	
	{
		

		RestAssured.baseURI="https://uibank-api.azurewebsites.net/api";
		
		
		
	

	}

}
