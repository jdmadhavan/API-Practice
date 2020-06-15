package hotelsBooking;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CancelBooking extends BaseRequestCalses{
	@Test(dependsOnMethods= {"hotelsBooking.BookingHotel.booking"})
	public void getBookingInfo()
	{
		
		
		Response response = RestAssured
				.given().log().all()
				.headers(headerVal)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.param("cancellationFlag","CANCELLATION")
				.when()
				.delete("bookings/"+bookingReferenceNo+"");
		
		response.prettyPrint();
		}

}
