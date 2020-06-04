package practiceRestAssured;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC01_Top5CountryWithHigehstNewCases {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://covid-19.dataflowkit.com/v1";
		Response response = RestAssured

				.given().contentType(ContentType.JSON).log().all().get();

		JsonPath jsonPath = response.jsonPath();
		List<String> newcaseList = jsonPath.getList("/New Cases_text/");
		List<Integer> getIntegerValNewCase = new ArrayList<Integer>();
		for (int i = 0; i < newcaseList.size() - 1; i++) {
			if (newcaseList.get(i) != "") {
				getIntegerValNewCase.add(Integer.parseInt(newcaseList.get(i).replaceAll("\\D", "")));
			}

		}

		Collections.sort(getIntegerValNewCase);
		Collections.reverse(getIntegerValNewCase);

		for (int i = 1; i < newcaseList.size() - 1; i++) {
			for (int j = 0; j <=5; j++) {
				if (newcaseList.get(i) != "")

					if (newcaseList.get(i).replaceAll("\\D", "")
							.contains(String.valueOf(getIntegerValNewCase.get(j)))) {
						List<Object> CountryList = jsonPath.getList("Country_text");
						System.out.println("***************************************");
						System.out.println("CountryName : " + CountryList.get(i));
						System.out.println("NewCaseCOunt: " + String.valueOf(getIntegerValNewCase.get(j)));
						System.out.println("***************************************");

					}
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
			System.out.println("Time is exceeded 600MS");
		}

		if (response.contentType().equalsIgnoreCase("application/json")) {
			System.out.println("Content type is JSON");
		} else {
			System.out.println("Content type is not JSON");
		}

	}

}