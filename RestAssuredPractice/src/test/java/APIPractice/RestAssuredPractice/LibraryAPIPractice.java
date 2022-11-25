package APIPractice.RestAssuredPractice;

import org.testng.annotations.Test;

import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class LibraryAPIPractice {
	
	@Test
	public void addBook(){

	RestAssured.baseURI="http://216.10.245.166";
	given().log().all().header("Content-Type","application/json").body("{\r\n"
			+ "\r\n"
			+ "\"name\":\"Learn api Automation with Java\",\r\n"
			+ "\"isbn\":\"nhf\",\r\n"
			+ "\"aisle\":\"277\",\r\n"
			+ "\"author\":\"John loe\"\r\n"
			+ "}\r\n"
			+ "")
	.when().post("Library/Addbook.php")
	.then().log().all().assertThat().statusCode(200).extract().response();
	//JsonPath js=new JsonPath(resp);
	//JsonPath js=ReusableMethods.rawToJson(resp);
	//String id=js.getString("ID");
	//System.out.println(id);
	
	}
}
