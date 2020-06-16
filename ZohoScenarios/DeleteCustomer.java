package zohoCutomer;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteCustomer extends BaseRequestCalses {
	
	@Test(dependsOnMethods={"zohoCutomer.UpdateCustomer.updateCustomer"})
	public void deleteCustomer()
	{

	Response response = RestAssured
			.given().log().all()
			.headers(headerVal)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.when()
			
			.delete("customers/"+customer_id+"");
	
	         response.prettyPrint();
	
	
	}
}

