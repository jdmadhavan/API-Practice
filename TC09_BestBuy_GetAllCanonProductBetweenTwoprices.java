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

public class TC09_BestBuy_GetAllCanonProductBetweenTwoprices {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://api.bestbuy.com/v1/";
		
		 String s="manufacturer=canon &salePrice>=1000&salePrice<=1500";
			Map<String, String> parameters=new HashMap<String, String>();
			        
					parameters.put("format","json");
					parameters.put("show", "sku,name,salePrice,inStorePickup,platform");
					parameters.put("apiKey", "qUh3qMK14GdwAs9bO59QRSCJ");
		Response response = RestAssured	
			 .given()
			 .queryParams(parameters)
			 
			 .log().all()
			 .get("/products("+s+")");
		
		
		JsonPath jsonPath = response.jsonPath();
		List<Map<Object, Object>> list =jsonPath.getList("products");
		

for (Map<Object, Object> storedetails:list)
{   System.out.println("******************Product-Details**********************");
	System.out.println("Product sku :"+storedetails.get("sku"));
	System.out.println("Product Name:"+storedetails.get("name"));
	System.out.println("SalePrice:"+storedetails.get("salePrice"));
	System.out.println("****************************************");
	
}
	
		


	
		
	}
}