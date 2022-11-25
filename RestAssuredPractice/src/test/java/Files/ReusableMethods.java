package Files;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {

public static JsonPath rawToJson(String resp){

	JsonPath js1=new JsonPath(resp);
	return js1;
}


}
