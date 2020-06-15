package hotelsBooking;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BookingHotel extends BaseRequestCalses {
	
	@Test(dependsOnMethods= {"hotelsBooking.CheckRoomAvailabilityAndGetTheRoomRates.checkingRoomAvailabilityandGettingPrice"})
	public void booking()
	{
	
	Response response = RestAssured
			.given().log().all()
			.headers(headerVal)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.when()
			.body("{\r\n" + 
					"    \"holder\": {\r\n" + 
					"        \"name\": \"MDN\",\r\n" + 
					"        \"surname\": \"MJ\"\r\n" + 
					"    },\r\n" + 
					"    \"rooms\": [\r\n" + 
					"        {\r\n" + 
					"            \"rateKey\": \""+rateKey+"\",\r\n" + 
					"            \"paxes\": [\r\n" + 
					"                {\r\n" + 
					"                    \"roomId\": 1,\r\n" + 
					"                    \"type\": \"AD\",\r\n" + 
					"                    \"name\": \"MDN\",\r\n" + 
					"                    \"surname\": \"MJ\"\r\n" + 
					"                }\r\n" + 
					"                \r\n" + 
					"            ]\r\n" + 
					"        }\r\n" + 
					"    ],\r\n" + 
					"    \"clientReference\": \"IntegrationAgency\",\r\n" + 
					"    \"remark\": \"Booking remarks are to be written here.\",\r\n" + 
					"    \"tolerance\": 2\r\n" + 
					"}")
			.post("bookings");
	
	bookingReferenceNo = response.jsonPath().get("booking.reference");
	System.out.println("Bookin Reference Number : "+bookingReferenceNo);
	

	}
}
