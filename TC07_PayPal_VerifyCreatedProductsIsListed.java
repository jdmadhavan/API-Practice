package practiceRestAssured;

import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.xml.internal.ws.wsdl.writer.document.soap12.Body; 

public class TC07_PayPal_VerifyCreatedProductsIsListed {
	
	
	@DataProvider(name="files",parallel=true)
	public String[] getfiles()
	{
		String[]files=new String[1];
		files[0]="product1.json";
		return files;		
	};

	
	@Test(dataProvider="files")
	public void creatProductwithExteranalFiles(String fileName) {
		RestAssured.baseURI="https://api.sandbox.paypal.com/v1/catalogs/products/";
		RestAssured.authentication=RestAssured.oauth2("A21AAFlkHeIlYcJpmmrfOO00qgrHKPFNwOIE2RDxOlWlchJIpPuapLzJkcRzuz7G2ZIAYHVVsZRxlVs33bCFfn2N3DoHK299A");
		File jsonFilePath=new File(fileName);
		Response response = RestAssured
				.given()

				.log().all()

				.contentType(ContentType.JSON)
				.body(jsonFilePath)
				.post();
		  Response res= RestAssured
				.given()
				.log().all()
				.when()
				.get("https://api.sandbox.paypal.com/v1/catalogs/products?page_size=100&page=1&total_required=true");
			   
			   
					   
			 JsonPath jsonPath = res.jsonPath();
			 List<String> productList = jsonPath.getList("products.name");
			 for(String name:productList)
			 {
				 if (name.equalsIgnoreCase("MM Airlines"))
				 {
					 System.out.println("Created Product is available is the list");
				 }
			 }
			     
		
		
		
		
		
		
		
		
		
	

	}
}
