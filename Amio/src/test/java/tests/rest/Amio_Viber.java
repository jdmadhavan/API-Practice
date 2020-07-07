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

public class Amio_Viber extends RESTAssuredBase {
	public static String viberMessageId;
	public String channelId="6686123681716651107";
	public String contactId="6686123767297230112";
	
	
	@BeforeTest
	public void setValues() {
		testCaseName = "Amio-viber-Message";
		testDescription = "Send file, verify the Message";
		nodes = "Amio-Viber-Message";
		authors = "Madhavan";
		category = "API";
		dataFileName = "SendFile";
		dataFileType = "JSON";
		}

	@Test(dataProvider = "fetchData")
	public void createChannel(File file) 
	{
	  setLogs();
    //Send File 
   	  Response sendFile= postWithBodyAsFileAndUrl(file, "messages");
   	  viberMessageId=sendFile.jsonPath().get("id");
      
     //Verify created Message available in the list
      Response getMessageList= get("channels/"+channelId+"/contacts/"+contactId+"/messages");
      verifyContentsWithKey(getMessageList, "id", viberMessageId);
      
      System.out.println("*******Completed*************");
	
}

}

