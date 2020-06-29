package tests.rest;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.tokens.Token.ID;

import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class ScenarioTwo_Newsletter2go extends RESTAssuredBase {
	public static String userId="lzck4llz";
	
	
	@BeforeTest
	public void setValues() {
		testCaseName = "News Letter Scenario Two";
		testDescription = "Get All user,Get your User Details,update the user details and verify";
		nodes = "NewsLetter - Scenario #2";
		authors = "Madhavan";
		category = "API";
	}

	@Test
	public void getuserDetails() {

		// Create New List
		setLogs();
		Response getAllUsers =get("users");
	
		List<String> userList = getAllUsers.jsonPath().getList("value.id");
		System.out.println("********************User List***************");
		for(String user:userList)
		{   
			System.out.println(user.toString());
			
		}
		System.out.println("********************USer List END***************");
		
		
		
		// My User Details
		Response getUserDetails =get("users/"+userId+"");
		List<Map<String, String>> userdetails = getUserDetails.jsonPath().getList("value");
		System.out.println("********************User Details***************");
		for(Map<String, String> userInfo:userdetails)
		{
			System.out.println("User ID: "+userInfo.get("id").toString());
			System.out.println("User FirstName: "+userInfo.get("first_name").toString());
			System.out.println("User LastName: "+userInfo.get("last_name").toString());
			
		}
	    System.out.println("********************U****************************");

	    
	    //Update Last Name of the user Details and Verify its updated
	    setLogs();
	    Response updateUserDetails = patchWithJsonAsBody("{\r\n" + 
	    		"	\"last_name\": \"Ramkumr\"\r\n" + 
	    		"}", "users/"+userId+"");
	    
	    updateUserDetails.prettyPrint(); 
	    
	    
	    verifyResponseCode(updateUserDetails,200);
	    
	    Response userLastName =get("users/"+userId+"");
	    
	 List<String> list= userLastName.jsonPath().getList("value.last_name");
	 Assert.assertTrue(list.get(0).equalsIgnoreCase(("Ramkumr")));
	 
	 System.out.println("Scenario Two is Completed");
	 
	    
	     
	 
	}
}
