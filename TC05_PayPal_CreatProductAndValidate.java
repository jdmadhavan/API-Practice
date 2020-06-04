package practiceRestAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.*;
public class TC05_PayPal_CreatProductAndValidate {

	public static void main(String[] args) {
		RestAssured.baseURI="https://api.sandbox.paypal.com/v1/catalogs/products/";
		RestAssured.authentication=RestAssured.oauth2("A21AAFlkHeIlYcJpmmrfOO00qgrHKPFNwOIE2RDxOlWlchJIpPuapLzJkcRzuz7G2ZIAYHVVsZRxlVs33bCFfn2N3DoHK299A");
		Response response = RestAssured
				.given()

				.log().all()

				.contentType(ContentType.JSON)
				.body("{\r\n" + 
						"  \"name\": \"Test Automation\",\r\n" + 
						"  \"description\": \"Providing the testing as a service\",\r\n" + 
						"  \"type\": \"SERVICE\",\r\n" + 
						"  \"category\": \"SOFTWARE\",\r\n" + 
						"  \"image_url\": \"https://example.com/streaming.jpg\",\r\n" + 
						"  \"home_url\": \"https://example.com/home\"\r\n" + 
						"}")
				.post();

		JsonPath jsonPath = response.jsonPath();
		String  productId = jsonPath.get("id");
		System.out.println("Product ID is :"+productId);
		ValidatableResponse responseNew= RestAssured
				.given()
				.log().all()
				.when()
				.get(""+productId+"")
				.then()
				.assertThat()
				.statusCode(200)
		        .assertThat().body("category",containsString("SOFTWARE"))
		        .assertThat().body("type",containsString("SERVICE"));
	

	}
}
