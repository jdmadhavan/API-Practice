package zohoCutomer;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateCustomer extends BaseRequestCalses {
	
	@Test
	public void createCustomer()
	{File jsonFilePath = new File("data4.json");

	Response response = RestAssured
			.given().log().all()
			.headers(headerVal)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.when()
			.body(jsonFilePath)
			.post("customers");
	
	response.prettyPrint();
	customer_id = response.jsonPath().get("customer.customer_id");
	
	System.out.println("Cutomer ID"+ customer_id);
	
	}
}

