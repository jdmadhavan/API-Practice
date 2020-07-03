package tests.rest;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class ScenarioOne_OKTA extends RESTAssuredBase{
	public static String clientId;
	public static String updatedClientName;
	boolean clientIdExist;
	boolean nameUpdate;

	
	
	@BeforeTest
	public void setValues() {
	
	testCaseName = "OKTA Scenario One";
	testDescription = "Create Client,Verify Client,Update Client and VerifyCLient,Delete Client";
	nodes = "OKTA - Scenario #1";
	authors = "Madhavan";
	category = "API";
	dataFileName = "CreateClient";
	dataFileType = "JSON";
	

	}
	
	@Test(dataProvider = "fetchData")
	public void scenarioOne_OKTA(File file) {

		// Create New Client
		setLogs();
	    
		Response createClient = postWithHeaderParamWithFile(file,headerVal,"oauth2/v1/clients");
		createClient.prettyPrint();
		clientId=createClient.jsonPath().get("client_id");
		System.out.println("Client ID"+clientId);
		
		//Verify The Create Client is Available in the list
		
		Response getclients = getWithHeader(headerVal,"oauth2/v1/clients/?limit=150");
		List<String> listofClientId = getclients.jsonPath().getList("client_id");
		for(String clients:listofClientId)
		{
			if(clients.contains(clientId))
			{
				clientIdExist=true;
			}
		}
		Assert.assertEquals(clientIdExist, true);
		
		
		
    //Updated Client Deatils
		
		Response updateClient = putWithJsonAsBodyAndHeaders(headerVal,
				
				" \r\n" + 
				" {      \"client_id\": \""+clientId+"\",\r\n" + 
				"        \"client_secret_expires_at\": 0,\r\n" + 
				"        \"client_name\": \"MDN Test"+clientId+"\",\r\n" + 
				"        \"client_uri\": \"https://www.example-application.com\",\r\n" + 
				"        \"logo_uri\": \"https://www.example-application.com/logo.png\",\r\n" + 
				"        \"redirect_uris\": [\r\n" + 
				"            \"https://www.example-application.com/oauth2/redirectUri\"\r\n" + 
				"        ],\r\n" + 
				"        \"post_logout_redirect_uris\": [\r\n" + 
				"            \"https://www.example-application.com/oauth2/postLogoutRedirectUri\"\r\n" + 
				"        ],\r\n" + 
				"        \"response_types\": [\r\n" + 
				"            \"code\",\r\n" + 
				"            \"id_token\"\r\n" + 
				"        ],\r\n" + 
				"        \"grant_types\": [\r\n" + 
				"            \"implicit\",\r\n" + 
				"            \"authorization_code\",\r\n" + 
				"            \"refresh_token\"\r\n" + 
				"        ],\r\n" + 
				"        \"initiate_login_uri\": \"https://www.example-application.com/oauth2/login\",\r\n" + 
				"        \"token_endpoint_auth_method\": \"client_secret_post\",\r\n" + 
				"        \"application_type\": \"web\"\r\n" + 
				"    }\r\n" + 
				"", "oauth2/v1/clients/"+clientId+"");
		updateClient.prettyPrint();
		updatedClientName= updateClient.jsonPath().get("client_name").toString();		
		System.out.println("ClientName"+updatedClientName);
		
		//Validate the name has been updated 
		Response getclientsNames = getWithHeader(headerVal,"oauth2/v1/clients?limit=150");
		List<String> listClientName = getclientsNames.jsonPath().getList("client_name");
		System.out.println(listClientName.size());
		for(int i=0;i<listClientName.size();i++)
		{
			System.out.println(listClientName.get(i).toString());
			if(listClientName.get(i).toString().equalsIgnoreCase(updatedClientName)){
				nameUpdate=true;
				System.out.println("set true");
				
			}
		}
		
		
		System.out.println(nameUpdate);
		Assert.assertEquals(nameUpdate, true);
		verifyResponseCode(getclientsNames, 200);
		
		//Delete the Client
		Response deletClientId = delete("oauth2/v1/clients/"+clientId+"");
		System.out.println("Scenario One is Completed");
		
		
		

	}

	
	
}
