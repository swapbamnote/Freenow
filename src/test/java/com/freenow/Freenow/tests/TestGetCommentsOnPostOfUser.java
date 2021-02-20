package com.freenow.Freenow.tests;

import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.freenow.Freenow.helpers.Helper;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestGetCommentsOnPostOfUser {
	
	private Helper helper;
	
	@BeforeClass
	public void init() {
		helper = new Helper();
	}
	
	@Test
	public void getAllUsers() {
		Response response = helper.getAllUsers();
		System.out.println(response.getBody().asPrettyString());
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		List<String> userName = jsonPathEvaluator.get("username");
		System.out.println(userName.size());
	}

}
