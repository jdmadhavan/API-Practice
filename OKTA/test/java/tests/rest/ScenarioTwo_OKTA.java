package tests.rest;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class ScenarioTwo_OKTA extends RESTAssuredBase{
	public static String appId;
	
	 //File testUploadFile = new File("./data/image.jpg");
	public String testUploadFile="./data/imagenew.png";
	boolean appIdExist;
	

	
	
	@BeforeTest
	public void setValues() {
	
	testCaseName = "OKTA Scenario Two";
	testDescription = "Create App,Update Logo,Verify App in the listed apps";
	nodes = "OKTA - Scenario #2";
	authors = "Madhavan";
	category = "API";
	dataFileName = "CreateApp";
	dataFileType = "JSON";
	

	}
	
	@Test(dataProvider = "fetchData")
	public void scenarioOne_OKTA(File file) {
		
		// Create New App
		setLogs();
	    
		Response createClient = postWithHeaderParamWithFile(file,headerVal,"api/v1/apps");
		createClient.prettyPrint();
		appId=createClient.jsonPath().get("id");
		System.out.println("AppId"+appId);
		
		
		
		
		//Verify App is available in the list
		
		Response getAppIds = getWithHeader(headerVal,"api/v1/apps");
		List<String> listofAppId = getAppIds.jsonPath().getList("id");
		
		
		for(String ids:listofAppId)
		{
			if(ids.contains(appId))
			{
				appIdExist=true;
			}
		}
		Assert.assertEquals(appIdExist, true);
		System.out.println("OKTA Scenario 2 Completed");
		
		
		
		//Update Logo
		
		Response updateApp = postWithHeaderParamWithMultipart(testUploadFile,headerValNew,"/admin/app/bookmark/instance/"+appId+"/edit-link");
		updateApp.prettyPrint();
		System.out.println(updateApp.statusCode());
		
		
		
		
	}

	
	
}
