package hotelsBooking;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class CheckRoomAvailabilityAndGetTheRoomRates extends BaseRequestCalses {
	
	List<Map<Object, Object>> rates;
	@Test
	public void checkingRoomAvailabilityandGettingPrice() {
        
		File jsonFilePath = new File("data3.json");

		Response response = RestAssured
				.given().log().all()
				.headers(headerVal)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.body(jsonFilePath)
				.post("hotels");

		JsonPath jsonPath = response.jsonPath();
		List<Map<Object, Object>> hotles = jsonPath.getList("hotels.hotels");
		for (int i = 0; i < hotles.size() - 1; i++) {

			List<Map<Object, Object>> rooms = jsonPath.getList("hotels.hotels[" + i + "].rooms");

			for (int j = 0; j < rooms.size() - 1; j++) {

				rates = jsonPath.getList("hotels.hotels[" + i + "].rooms[" + j + "].rates");
				for (int k = 0; k < rates.size() - 1; k++) {
					if (rates.get(k).get("net") != null && rates.get(k).get("sellingRate") != null) {
						System.out.println("*****************************************************");
						System.out.println("HotelName---->" + hotles.get(i).get("name").toString());
						System.out.println("Rooms Availability -->" + rooms.get(j).get("name"));
						System.out.println("Rooms Rooms Net Rate  -->" + rates.get(k).get("net"));
						System.out.println("Rooms Selling Rate  -->" + rates.get(k).get("sellingRate"));
						System.out.println("Rooms Selling Rate  -->" + rates.get(k).get("rateType"));
						System.out.println("*****************************************************");
					}

				}

			}

		}
		//For booking Hotel 
		for(Map<Object, Object>  findRateKey:rates)
		{
			if(findRateKey.get("rateType").toString().equalsIgnoreCase("BOOKABLE"))
			{
				rateKey=findRateKey.get("rateKey");
				System.out.println("Ratekeys is"+rateKey);
				break;
			
		}
		

	}

	}
}
