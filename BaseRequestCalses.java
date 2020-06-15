package hotelsBooking;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseRequestCalses {
	
	public static String apiKey;
	public static String secret;
	public static String signature;
	public static Map<String,Object>headerVal=new HashMap<String, Object>();
	public static Object rateKey;
	public static String bookingReferenceNo;
	@BeforeSuite
	public void baseSetup()	
	{
		

		RestAssured.baseURI="https://api.test.hotelbeds.com/hotel-api/1.0/";
		
		apiKey = "sc3s45jzct6cpvs5mjs5wtj7";
		secret = "nKDdfukxeZ";
		
		 signature = org.apache.commons.codec.digest.DigestUtils
								 .sha256Hex(apiKey + secret + System.currentTimeMillis() / 1000);
		
		
		headerVal.put("Api-Key", apiKey);
		headerVal.put("X-Signature", signature);
		
	

	}

}
