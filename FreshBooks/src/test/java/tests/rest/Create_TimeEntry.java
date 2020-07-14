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


public class Create_TimeEntry extends RESTAssuredBase {
	public static int newTimeEntryId;
	public static String businessId="3601569";	
	
	@BeforeTest
	public void setValues() {
		testCaseName = "FreshBooks Time Entry";
		testDescription = "Create time entry,Verify new time entry,update,Delet";
		nodes = "FreshBooks Scenaro One";
		authors = "Madhavan";
		category = "API";
		dataFileName = "CreateTimeEntry";
		dataFileType = "JSON";
			
	}

	@Test(dataProvider = "fetchData")
	public void createTimeEntry(File file) throws IOException {

	  setLogs();
    //create TimeEntry
   	  Response timeEntry= postWithBodyAsFileAndUrl(file, "timetracking/business/"+businessId+"/time_entries");
   	  newTimeEntryId=timeEntry.jsonPath().get("time_entry.id");
   	  System.out.println("Time Entry ID:"+newTimeEntryId);
 
     //Verify create time entry available in the list
      Response getTimeEntries= get("timetracking/business/"+businessId+"/time_entries");   
      getTimeEntries.then().assertThat().body("time_entries.id", hasItem(newTimeEntryId));
    
      
      //Updte time entry  
      File updateTimeEntry=new File("./data/updateTimeEntry.json");
      Response updateEntry=putWithBodyAsStringAndUrl(updateTimeEntry,"timetracking/business/"+businessId+"/time_entries/"+newTimeEntryId+"");
      updateEntry.prettyPrint();
      updateEntry.then().assertThat().body("time_entry.note",containsString("Updated"));
     
      
     
      //Delete the Time Entry
	  Response deletEntry=delete("timetracking/business/"+businessId+"/time_entries/"+newTimeEntryId+"");
	  verifyResponseCode(deletEntry, 204);
	 
	  System.out.println("*******Completed*************");
	
	}
	
}


