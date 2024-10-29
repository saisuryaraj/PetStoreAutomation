package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//created for crud operations
public class UserEndPoints2 {
	
	static ResourceBundle getURL(){
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response createUser(User Payload) {
		String post_url=getURL().getString("post_url");
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(Payload)
		.when()
			.post(post_url);
		return response;
	}
	
	public static Response getUser(String userName) {
		String get_url=getURL().getString("get_url");
		Response response=given()
			.pathParam("username", userName)
		.when()
			.get(get_url);
		return response;
	}
	
	public static Response updateUser(String userName, User Payload) {
		String put_url=getURL().getString("put_url");
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(Payload)	
		.when()
			.put(put_url);
		return response;
	}
	
	public static Response deleteUser(String userName) {
		String delete_url=getURL().getString("delete_url");
		Response response=given()
			.pathParam("username", userName)
		.when()
			.delete(delete_url);
		return response;
	}
} 
