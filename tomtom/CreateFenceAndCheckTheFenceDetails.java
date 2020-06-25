package tomtom;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateFenceAndCheckTheFenceDetails extends BaseRequestClass {
	
	@Test(dependsOnMethods={"tomtom.AddProjectAndCheckCreatedProjectIsAvailable.addProject"})
	public void addfence()
	{
		
	  Map<String,Object>params=new HashMap<String, Object>();
	  params.put("key", apiKey);
	  params.put("adminKey", adminKey);
	
	Response fence = RestAssured
			.given().log().all()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.queryParams(params)
			.when()
			.body("{\r\n" + 
					"  \"name\": \"Old School public Campus \",\r\n" + 
					"  \"type\": \"Feature\",\r\n" + 
					"  \"geometry\": {\r\n" + 
					"    \"radius\": 75,\r\n" + 
					"    \"type\": \"Point\",\r\n" + 
					"    \"shapeType\": \"Circle\",\r\n" + 
					"    \"coordinates\": [-67.137343, 45.137451]\r\n" + 
					"  },\r\n" + 
					"  \r\n" + 
					"  \"properties\": {\r\n" + 
					"  \"maxSpeedKmh\": 70\r\n" + 
					"  }\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"}")
			.post("projects/"+projectId+"/fence");
	   fence.prettyPrint();
	fenceId=fence.jsonPath().get("id");
    System.out.println("Fence Id"+ fenceId);
    
 
    Response fenceList = RestAssured
			.given().log().all()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.queryParam("key", apiKey)
			.get("projects/"+projectId+"/fences");
    
    List<Object> listOfFences = fenceList.jsonPath().getList("fences.id");
    
    for(Object fenceVal:listOfFences)
    {
    	if(fenceVal.toString().contains(fenceId))
    	{
    		Response fenceDetails = RestAssured
    				.given().log().all()
    				.contentType(ContentType.JSON)
    				.accept(ContentType.JSON)
    				.queryParam("key", apiKey)
    				.get("fences/"+fenceId+"");
    	System.out.println("**************************************************************");	
    	System.out.println("Name: "+fenceDetails.jsonPath().get("name"));
    	System.out.println("Type: "+fenceDetails.jsonPath().get("type"));
    	System.out.println("Radius: "+fenceDetails.jsonPath().get("geometry.radius"));
    	System.out.println("ShapeType: "+fenceDetails.jsonPath().get("geometry.shapeType"));
    	System.out.println("coordinates: "+fenceDetails.jsonPath().get("geometry.coordinates"));
    	System.out.println("****************************************************************");	
    	}    	
    }
    
   

	}
}

	
    