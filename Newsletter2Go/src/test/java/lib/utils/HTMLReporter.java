package lib.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.restassured.RestAssured;

public abstract class HTMLReporter {

	public static ExtentHtmlReporter html;
	public static ExtentReports extent;
	public static ExtentTest svcTest;
	public ExtentTest testSuite, test;
	public String testCaseName, testDescription, nodes, authors,category;
	
	public void startReport() {
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./src/test/resources/config.properties")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		RestAssured.baseURI = "https://"+prop.getProperty("server")+"/"+prop.getProperty("resources")+"/";
		//RestAssured.authentication = RestAssured.basic(prop.getProperty("username"), prop.getProperty("password"));

		html = new ExtentHtmlReporter("./reports/result.html");
		html.setAppendExisting(true);
		html.loadXMLConfig("./src/test/resources/extent-config.xml");
		extent = new ExtentReports();
		extent.attachReporter(html);		
	}

	public ExtentTest startTestCase(String testCaseName, String testDescription) {
		testSuite = extent.createTest(testCaseName, testDescription);		
		return testSuite;
	}

	public ExtentTest startTestModule(String nodes) {
		test = testSuite.createNode(nodes);
		return test;
	}

	public void endResult() {
		extent.flush();
	}

	public abstract long takeSnap();

	public void reportStep(String desc,String status, boolean bSnap) {

		MediaEntityModelProvider img = null;

		if(bSnap && !status.equalsIgnoreCase("INFO")){

			long snapNumber = 100000L;
			snapNumber = takeSnap();
			try {
				img = MediaEntityBuilder.createScreenCaptureFromPath
						("./../reports/images/"+snapNumber+".png").build();
			} catch (IOException e) {

			}
		}		

		if(status.equalsIgnoreCase("PASS")) {
			test.pass(desc, img);		
		}else if(status.equalsIgnoreCase("FAIL")) {
			test.fail(desc, img);
			throw new RuntimeException();
		}else if(status.equalsIgnoreCase("WARNING")) {
			test.warning(desc, img);		
		}else {
			test.info(desc);
		}
	}

	public void reportStep(String desc, String status) {
		reportStep(desc, status, true);
	}

	public static void reportRequest(String desc, String status) {
		
		MediaEntityModelProvider img = null;
		if(status.equalsIgnoreCase("PASS")) {
			svcTest.pass(desc, img);		
		}else if(status.equalsIgnoreCase("FAIL")) {
			svcTest.fail(desc, img);
			throw new RuntimeException();
		}else if(status.equalsIgnoreCase("WARNING")) {
			svcTest.warning(desc, img);		
		}else {
			svcTest.info(desc);
		}	
	}


}
