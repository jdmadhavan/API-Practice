package practiceRestAssured;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC10_BestBuy_GetAll_iPhone11ProPrices {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://api.bestbuy.com/v1/";
		
		 String s="name=Apple - iPhone XR*&productTemplate=Cell_Phones";
			Map<String, String> parameters=new HashMap<String, String>();
			        
					parameters.put("format","json");
					parameters.put("show", "sku");
					parameters.put("apiKey", "qUh3qMK14GdwAs9bO59QRSCJ");
		Response response = RestAssured	
			 .given()
			 .queryParams(parameters)
			 
			 .log().all()
			 .get("/products("+s+")");
		
JsonPath jsonPath = response.jsonPath();
		
List<Object> list = jsonPath.getList("products.sku");
for(Object s1:list)
{

Response response1 = RestAssured	
.given()
.log().all()
.get("/products/"+s1+"/stores.json?postalCode=02886&apiKey=qUh3qMK14GdwAs9bO59QRSCJ");

if(response1.jsonPath().getBoolean("ispuEligible")==true)
{
	System.out.println("Proudct's available in the store");
}

	

}
	
		


	}
		
	}
