package tomtom;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseRequestClass {
	
	
	
	public static String apiKey="A9dnVj6Yl4pw0m53AJI3LUHqjuwcuGlo";
	public static String adminKey="nyInWF0JXrTL6f5aFJn1ZAngtRH0ZeaCpeN9ybAVJTcFnzZX";
	public static String projectId;
	public static String fenceId;
	boolean projectAvailable;
	boolean fenceAvailable;
	
	
	@BeforeSuite
	public void baseSetup()	
	{
		

		RestAssured.baseURI="https://api.tomtom.com/geofencing/1/";
	

	}

}
