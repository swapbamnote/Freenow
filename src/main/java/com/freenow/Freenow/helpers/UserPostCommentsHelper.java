package com.freenow.Freenow.helpers;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.freenow.Freenow.Endpoints.Endpoints;
import com.freenow.Freenow.model.Comments;
import com.freenow.Freenow.model.Posts;
import com.freenow.Freenow.model.Users;
import com.freenow.Freenow.utils.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserPostCommentsHelper {
	
	private static final String BASE_URL = ConfigManager.getInstance().getString("baseUrl");
	//private static final String PORT = ConfigManager.getInstance().getString("port");
	public static int userId;
	public static List<Integer> postIds = new ArrayList<Integer>();;
	
	public UserPostCommentsHelper() {
		RestAssured.baseURI = BASE_URL;
		//RestAssured.port = Integer.parseInt(PORT);
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
	
	 public List<Posts> getAllPosts() {
		Response response = RestAssured
				.given().log().all()
				.contentType(ContentType.JSON)
				.when()
				.get(Endpoints.GET_ALL_POSTS)
				.andReturn();
	
		assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "OK");
		
		Type type = new TypeReference<List<Posts>>() {}.getType();
		List<Posts> posts = response.as(type);
		return posts;
	 }
	
	 public List<Comments> getAllCommentsForAPost(int postId) {
		// TODO Auto-generated method stub
		Response response = RestAssured
					.given().log().all()
					.contentType(ContentType.JSON)
					.queryParam("postId", postId)
					.get(Endpoints.GET_COMMENTS_BY_POSTID)
					.andReturn();
		
		assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "OK");
		 
		Type type = new TypeReference<List<Comments>>() {}.getType();
		List<Comments> comments = response.as(type);
		return comments;
	 }

	 
	 /*public static RequestSpecification getCommonSpec() {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri(BASE_URL);
		builder.setPort(Integer.parseInt(PORT));
		builder.setContentType(ContentType.JSON);
		RequestSpecification requestSpec = builder.build();
		return requestSpec;
	}*/
	
}
