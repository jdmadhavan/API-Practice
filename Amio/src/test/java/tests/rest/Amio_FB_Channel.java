package tests.rest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.tokens.Token.ID;

import com.aventstack.extentreports.utils.FileUtil;

import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class Amio_FB_Channel extends RESTAssuredBase {
	public static String channelId;
	public static String channelName;	
	
	@BeforeTest
	public void setValues() {
		testCaseName = "Amio- FB-Channel";
		testDescription = "Create Channel,Verif Channel,Delete Channel";
		nodes = "Amio- FB-Channel";
		authors = "Madhavan";
		category = "API";
		dataFileName = "CreateFB_Channel";
		dataFileType = "JSON";
			
	}

	@Test(dataProvider = "fetchData")
	public void createChannel(File file) throws IOException, ParseException {

		setLogs();
    //create FB- Channel
   	  Response createChannel= postWithBodyAsFileAndUrl(file, "channels");
	  createChannel.prettyPrint();
      channelId=createChannel.jsonPath().get("id");
      channelName=createChannel.jsonPath().get("name");
          
     //Verify Chennel is available in the list
      Response getChannel= get("channels");
      verifyContentsWithKey(getChannel, "name",channelName);
     
      //Delete the Created Channels
	   Response deletChannel=delete("channels/"+channelId+"");
	   verifyResponseCode(deletChannel, 204);
	 
	  System.out.println("*******Completed*************");
	
	}
	
}


