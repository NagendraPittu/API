package APIPractice.RestAssuredPractice;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import Files.Payload;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AddPlacewithSerialization {

	public static void main(String[] args) {
		SerilizationPractice addPlace=new SerilizationPractice();
		addPlace.setAccuracy(50);
		addPlace.setName("Frontline house");
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setAddress("29, side layout, cohen 09");
		addPlace.setWebsite("http://google.com");
		addPlace.setLanguage("French-IN");
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLag(33.427362);
		addPlace.setLocation(l);
		
		List<String> myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		addPlace.setTypes(myList);
		
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		Response response=given().queryParam("key", "qaclick123")//.header("Content-Type", "application/json")
		.body(addPlace)
		.when().post("maps/api/place/add/json")
		.then().statusCode(200).extract().response();
		String resp=response.asString();
		System.out.println(resp);
	}

}
