package tests.selenium;

import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;
import lib.selenium.PreAndPost;
import lib.utils.HTMLReporter;
import pages.selenium.LoginPage;

public class TC003_CreateIncidentUsingRestAndVerifyUsingSelenium extends PreAndPost{

	@BeforeTest
	public void setValues() {

		testCaseName = "Search Create (Using Selenium) After Creating CR (Using REST Assured)";
		testDescription = "Create CR (Using REST Assured) and Search with Selenium";
		nodes = "Incident Management";
		authors = "Madhavan";
		category = "UI & API";
		dataSheetName = "TC003";

	}

	@Test(dataProvider = "fetchData")
	public void createIncident(String filter) {

		// Post the request
		Response response = RESTAssuredBase.post("table/change_request");

		RESTAssuredBase.verifyResponseCode(response, 201);

		//Verify the Content by Specific Key
		incidentNumber = RESTAssuredBase.getContentWithKey(response, "result.number");

		// Selenium - Find Incident		
		new LoginPage(driver,test)
			.loginApp()
			.searchUsingFilter(filter)
			.clickOpen()
			.typeAndEnterSearch(incidentNumber)
			.verifyResult(incidentNumber);
	
	}


}





