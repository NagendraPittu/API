package E2EAPITesting;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.List;

public class EComAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://www.rahulshettyacademy.com/";
		String response=given().header("Content-Type", "application/json").body("{\r\n"
				+ "    \"userEmail\": \"nbpittu@gmail.com\",\r\n"
				+ "    \"userPassword\": \"Nagendra*6\"\r\n"
				+ "}").
		when().post("api/ecom/auth/login").
		then().log().all().statusCode(200).extract().response().asString();
		JsonPath js=new JsonPath(response);
		String userID=js.getString("userId");
		String token=js.getString("token");
		
		String prodResponse=given().header("Authorization",token).header("Content-Type", "multipart/form-data").multiPart("productName","qwerty").multiPart("productAddedBy",userID).multiPart("productCategory","fashion")
		.multiPart("productSubCategory","shirts").multiPart("productPrice","11500").multiPart("productDescription","Addias Originals")
		.multiPart("productFor","women").multiPart("productImage",new File("C:\\Users\\16475\\eclipse-workspace\\Adidas-Qwerty.png")).
		when().post("api/ecom/product/add-product").
		then().log().all().statusCode(201).extract().response().asString();
		JsonPath js1=new JsonPath(prodResponse);
		String productID=js1.getString("productId");

		String orderResponse=given().header("Authorization",token).header("Content-Type", "application/json").body("{\r\n"
				+ "    \"orders\": [\r\n"
				+ "        {\r\n"
				+ "            \"country\": \"India\",\r\n"
				+ "            \"productOrderedId\": \""+productID+"\"\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}").
				when().post("api/ecom/order/create-order").
				then().log().all().statusCode(201).extract().response().asString();
				JsonPath js2=new JsonPath(orderResponse);
				List<String> orderID=js2.getList("orders");
				String firstOrderID=orderID.get(0);
				System.out.println(orderID.get(0));
				
				given().header("Authorization",token).when().delete("api/ecom/product/delete-product/"+productID+"")
				.then().log().all().statusCode(200).extract().response();
				
				given().header("Authorization",token).when().get("api/ecom/order/get-orders-details?id="+firstOrderID+"")
				.then().log().all().statusCode(200).extract().response();
	}

}
