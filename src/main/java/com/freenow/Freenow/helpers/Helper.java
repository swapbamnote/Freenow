package com.freenow.Freenow.helpers;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;

import com.freenow.Freenow.Endpoints.Endpoints;
import com.freenow.Freenow.utils.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Helper {
	
	private static final String BASE_URL = ConfigManager.getInstance().getString("baseUrl");
	private static final String PORT = ConfigManager.getInstance().getString("port");
	public static int userId;
	public static List<Integer> postIds = new ArrayList<Integer>();;
	
	public Helper() {
		RestAssured.baseURI = BASE_URL;
		RestAssured.port = Integer.parseInt(PORT);
	}
	
 public Response getAllUsers() {
		
		Response response = RestAssured
				.given().log().all()
				.contentType(ContentType.JSON)
				.when()
				.get(Endpoints.GET_ALL_USERS)
				.andReturn();

		assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "OK");
		
		return response;
	}

 public Response getAllPosts() {
	Response response = RestAssured
			.given().log().all()
			.contentType(ContentType.JSON)
			.when()
			.get(Endpoints.GET_ALL_POSTS)
			.andReturn();

	assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "OK");
	
	return response;
 }

 public Response getAllCommentsForAPost(int postId) {
	// TODO Auto-generated method stub
	Response response = RestAssured
				.given().log().all()
				.queryParam("postId", postId)
				.get(Endpoints.GET_COMMENTS_BY_POSTID)
				.andReturn();
	assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "OK");
	 
	return response;
 }

}
