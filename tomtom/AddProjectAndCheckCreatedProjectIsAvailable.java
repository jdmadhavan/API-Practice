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

public class AddProjectAndCheckCreatedProjectIsAvailable extends BaseRequestClass {
	
	@Test
	public void addProject()
	{
		
	  Map<String,Object>params=new HashMap<String, Object>();
	  params.put("key", apiKey);
	  params.put("adminKey", adminKey);
	
	Response addProject = RestAssured
			.given().log().all()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.queryParams(params)
			.when()
			.body("{\r\n" + 
					"  \"name\" :\"Neem Sky new\"\r\n" + 
					"}")
			.post("projects/project");
	        
	projectId=addProject.jsonPath().get("id");
    System.out.println("Project Id"+ projectId);
    
    
    Response projects = RestAssured
			.given().log().all()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.queryParam("key", apiKey)
			.get("projects");
    
    List<Object> listOfProject = projects.jsonPath().getList("projects.id");
    
    for(Object project:listOfProject)
    {
    	if(project.toString().contains(projectId))
    	{
    	  projectAvailable=true;
    	}    	
    }
    
    Assert.assertEquals(projectAvailable, true);
    }
}
