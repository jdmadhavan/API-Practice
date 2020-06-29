package tests.rest;

import java.io.File;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.tokens.Token.ID;

import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class ScenarioOne_Newsletter2go extends RESTAssuredBase {
	public static String listId;
	public static String segmentId;
	boolean idExist;

	@BeforeTest
	public void setValues() {
		testCaseName = "News Letter Scenario One";
		testDescription = "Creat New List,Create new Segment,Get All list and Verify the lis i present,Delete the created new list";
		nodes = "NewsLetter - Scenario #1";
		authors = "Madhavan";
		category = "API";
		dataFileName = "CreateNewList";
		dataFileType = "JSON";
	}

	@Test(dataProvider = "fetchData")
	public void newsletterScenarioOne(File file) {

		// Create New List
		setLogs();
		Response createNewList = postWithBodyAsFileAndUrl(file, "lists");
		createNewList.prettyPrint();
		List<String> list = createNewList.jsonPath().getList("value.id");
		listId = list.get(0);
		System.out.println(listId);

		// Get All List and verify the existence of new list
		Response getAllList = get("lists");
		List<String> ListVal = getAllList.jsonPath().getList("value.id");

		for (String ids : ListVal) {
			if (ids.contains(listId)) {
				idExist = true;
			}
		}

		Assert.assertEquals(idExist, true);

		// Create New Segment in the Created List
		setLogs();
		Response createNewSegment = postWithJsonAsBody("{\r\n" + "	\"list_id\": \"" + listId + "\",\r\n"
				+ "	\"name\": \"Segment New\",\r\n" + "	\"is_dynamic\": false\r\n" + "}", "groups");
		createNewSegment.prettyPrint();
		List<Object> segment = createNewList.jsonPath().getList("value.id");
		segmentId = list.get(0);
		System.out.println(segmentId);

		// Update Created Segment 
		setLogs();
		Response updateSegment = patchWithJsonAsBody("{\r\n" + "	\"name\": \"Segment update\"\r\n" + "}",
				"groups/" + segmentId + "");

		verifyResponseCode(updateSegment, 200);

		// Delete created New List
		Response deletList = delete("lists/" + listId + "");
		verifyResponseCode(deletList, 204);

		System.out.println("Scenario One is Completed");
		

	}

}
