package tests.rest;

import java.io.File;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;


public class Creat_Estimate extends RESTAssuredBase {
	public static int estimateId;
	public static String accountId="kEkrMx";	
	
	@BeforeTest
	public void setValues() {
		testCaseName = "FreshBooks Single Estimate ";
		testDescription = "Create Single Line estimate,Verify,update";
		nodes = "FreshBooks Scenaro Two";
		authors = "Madhavan";
		category = "API";
		dataFileName = "CreateSingleLineEstimate";
		dataFileType = "JSON";
			
	}

	@Test(dataProvider = "fetchData")
	public void createEstimate(File file) throws IOException {

	  setLogs();
    //create estimate
   	  Response createEstimate= postWithBodyAsFileAndUrl(file, "accounting/account/"+accountId+"/estimates/estimates");
   	  createEstimate.prettyPrint();
   	  estimateId=createEstimate.jsonPath().get("response.result.estimate.id");
   	  System.out.println("Estimation ID:"+estimateId);
 
   
      
      //Updte and Verify Estimate
      File updateEstimate=new File("./data/updateEstimate.json");
      Response EstimateDetails=putWithBodyAsStringAndUrl(updateEstimate,"accounting/account/"+accountId+"/estimates/estimates/"+estimateId+"");
      EstimateDetails.then().assertThat().body("response.result.estimate.lname",containsString("MDNUpdated"));
     
      	 
	  System.out.println("*******Completed*************");
	
	}
	
}


