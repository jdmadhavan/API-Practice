package tests.selenium;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;
import lib.selenium.PreAndPost;
import lib.utils.HTMLReporter;
import pages.selenium.LoginPage;

public class TC002_CreateIncidentAndGetFromRest extends PreAndPost{

	@BeforeTest
	public void setValues() {

		testCaseName = "Create Incident and Verify";
		testDescription = "Create Incident (Using Selenium) and Verify using REST";
		nodes = "Incident Management";
		authors = "Babu";
		category = "UI & API";
		dataSheetName = "TC002";

	}

	@Test(dataProvider = "fetchData")
	public void createIncident(String filter, String user, String short_desc) {
		
		// Selenium - Create Incident		
		new LoginPage(driver,test)
		.loginApp()
		.searchUsingFilter(filter)
		.clickCreateNew()
		.getIncidentNumber()
		.selectUser(user)
		.typeShortDescription(short_desc)
		.clickSubmit();
		
		
		// Verify Using REST Assured		
		Response response = RESTAssuredBase.get("table/incident?number="+incidentNumber);
		RESTAssuredBase.verifyResponseCode(response, 200);
		RESTAssuredBase.verifyContentsWithKey(response, "result.number",incidentNumber);
	}


}





