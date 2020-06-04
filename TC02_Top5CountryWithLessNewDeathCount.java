package practiceRestAssured;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC02_Top5CountryWithLessNewDeathCount {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://covid-19.dataflowkit.com/v1";
		Response response = RestAssured

				.given().contentType(ContentType.JSON).log().all().get();

		JsonPath jsonPath = response.jsonPath();
		List<String> newDeathList = jsonPath.getList("/New Deaths_text/");
		List<Integer> getIntegerValDeathList = new ArrayList<Integer>();
		for (int i = 0; i < newDeathList.size() - 1; i++) {
			if (newDeathList.get(i) != "") {
				getIntegerValDeathList.add(Integer.parseInt(newDeathList.get(i).replaceAll("\\D", "")));

			}

		}

		Collections.sort(getIntegerValDeathList);
		for (int i = 1; i < newDeathList.size() - 1; i++) {
			for (int j = 0; j<=4; j++) {
				if (newDeathList.get(i) != "") {
					if (newDeathList.get(i).replaceAll("\\D", "")
							.equalsIgnoreCase(String.valueOf(getIntegerValDeathList.get(j)))) {

						List<Object> CountryList = jsonPath.getList("Country_text");
						System.out.println("***************************************");
						System.out.println("Country Name : " + CountryList.get(i));
						System.out.println("Death Count: " + String.valueOf(getIntegerValDeathList.get(j)));
						System.out.println("***************************************");
						break;
					}
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
			System.out.println("Time is exceeded the 600MS");
		}

		if (response.contentType().equalsIgnoreCase("application/json")) {
			System.out.println("Content type is JSON");
		} else {
			System.out.println("Content type is not JSON");
		}

	}

}