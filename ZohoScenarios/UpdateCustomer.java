package zohoCutomer;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateCustomer extends BaseRequestCalses {
	
	@Test(dependsOnMethods={"zohoCutomer.CreateCustomer.createCustomer"})
	public void updateCustomer()
	{
		
		File jsonFilePath = new File("data5.json");

	Response response = RestAssured
			.given().log().all()
			.headers(headerVal)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.when()
			.body(jsonFilePath)
			.put("customers/"+customer_id+"");
	
	response.prettyPrint();
	
	
	}
}

