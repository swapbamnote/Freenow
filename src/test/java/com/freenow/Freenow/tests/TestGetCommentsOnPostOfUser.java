package com.freenow.Freenow.tests;

import java.lang.reflect.Type;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.freenow.Freenow.model.Users;
import com.freenow.Freenow.utils.ConfigManager;
import com.freenow.Freenow.helpers.Helper;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestGetCommentsOnPostOfUser {
	
	private Helper helper;
	public String userName = ConfigManager.getInstance().getString("userName");
	
	@BeforeClass
	public void init() {
		helper = new Helper();
	}
	
	@Test
	public void getAllUsers() {
		Response response = helper.getAllUsers();
		System.out.println(response.getBody().asPrettyString());
		
		Type type = new TypeReference<List<Users>>() {}.getType();
		List<Users> users = response.as(type);
		
		for(int i=0; i<users.size(); i++) {
			if(users.get(i).getUsername().equalsIgnoreCase(userName)) {
				helper.userId = users.get(i).getId();
				//System.out.println("User Id for Samantha is: "+ helper.userId);
				break;
			}
		}
		
		if(helper.userId ==0) {
			throw new NullPointerException("User Not found: " + userName);
		}
		
		
	}

}
