package hotelsBooking;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetBookingDetails extends BaseRequestCalses{
	@Test(dependsOnMethods= {"hotelsBooking.BookingHotel.booking"})
	public void getBookingInfo()
	{
		
		
		Response response = RestAssured
				.given().log().all()
				.headers(headerVal)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
				.get("bookings/"+bookingReferenceNo+"");
		
		System.out.println("*****************************************************************");
		System.out.println("HotelName: "+response.jsonPath().get("booking.hotel.name"));
		System.out.println("StarValue: "+response.jsonPath().get("booking.hotel.categoryName"));
		System.out.println("Location:"+response.jsonPath().get("booking.hotel.destinationName"));
		System.out.println("Price: "+response.jsonPath().get("booking.hotel.totalNet"));
		System.out.println("*****************************************************************");	
	}

}
