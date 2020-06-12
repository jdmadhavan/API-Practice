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

public class TC08_BestBuy_FindStoreNameAndAddressOf_02864PostalCode {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://api.bestbuy.com/v1/stores(area(02864,100))?";
		
			Map<String, String> parameters=new HashMap<String, String>();
					parameters.put("format","json");
					parameters.put("show", "storeId,storeType,name,city,region");
					parameters.put("apiKey", "qUh3qMK14GdwAs9bO59QRSCJ");
		Response response = RestAssured	
			 .given()
			 .queryParams(parameters)
			 
			 .log().all()
			 .get();
		
		JsonPath jsonPath = response.jsonPath();
		List<Map<Object, Object>> list =jsonPath.getList("stores");
		

for (Map<Object, Object> storedetails:list)
{   System.out.println("****************************************");
	System.out.println("StoreID :"+storedetails.get("storeId"));
	System.out.println("StoreType:"+storedetails.get("storeType"));
	System.out.println("StoreName:"+storedetails.get("name"));
	System.out.println("City:"+storedetails.get("city"));
	System.out.println("Region:"+storedetails.get("region"));
	System.out.println("****************************************");
	
}
		
		


	
		
	}
}