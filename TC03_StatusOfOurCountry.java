package practiceRestAssured;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC03_StatusOfOurCountry {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://covid-19.dataflowkit.com/v1";
		Response response = RestAssured

				.given().contentType(ContentType.JSON).log().all().get();

		JsonPath jsonPath = response.jsonPath();
		List<String> countryList = jsonPath.getList("Country_text");
		List<String> totalCases = jsonPath.getList("/Total Cases_text/");
		List<String> newCases = jsonPath.getList("/New Cases_text/");
		List<String> totalDeath = jsonPath.getList("/Total Deaths_text/");
		List<String> newDeath = jsonPath.getList("/New Deaths_text/");
		List<String> totalRecover = jsonPath.getList("/Total Recovered_text/");
		List<String> activeCases = jsonPath.getList("/Active Cases_text/");
		
		
		for(int i=0;i<countryList.size()-1;i++)
		{
			
			
			if(countryList.get(i).equals("India"))
			{
				System.out.println("*****************************************");
				
				System.out.println("Country Name :"+countryList.get(i));
				System.out.println("Total Case   :"+totalCases.get(i));
				System.out.println("New Case     :"+newCases.get(i));
				System.out.println("Total Death  :"+totalDeath.get(i));
				System.out.println("New Death    :"+newDeath.get(i));
				System.out.println("Total Recover:"+totalRecover.get(i));
				System.out.println("Active Case  :"+activeCases.get(i));
				
				System.out.println("*****************************************");
				
				
			}
		}
		
		

		if (response.getStatusCode() == 200) {
			System.out.println("StatusCode Is Matched");
		} else {
			System.out.println("StatusCode Is NotMatched");
		}

		if (response.getTime() < 600) {
			System.out.println("Time not exceeded 600MS");
		} else {
			System.out.println("Time is exceeded of more the 600MS");
		}

		if (response.contentType().equalsIgnoreCase("application/json")) {
			System.out.println("Content type is JSON");
		} else {
			System.out.println("Content type is not JSON");
		}


	}
	}