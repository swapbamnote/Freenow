package com.freenow.Freenow.tests;

import java.lang.reflect.Type;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.freenow.Freenow.base.test.TestBase;
import com.freenow.Freenow.model.Users;


public class TestGetAllUsers extends TestBase {
		
	@Test(groups = {"users"})
	public void getAllUsers() {
		//logger.info("******** Start Get All User Test *********");
		response = helper.getAllUsers();
		Type type = new TypeReference<List<Users>>() {}.getType();
		List<Users> users = response.as(type);
		
		for(int i=0; i<users.size(); i++) {
			if(users.get(i).getUsername().equalsIgnoreCase(userName)) {
				helper.userId = users.get(i).getId();
				System.out.println("User Id of "+ userName +" is: "+ helper.userId);
				break;
			}
		}
		
		if(helper.userId ==0) {
			throw new NullPointerException("User Not found: " + userName);
		}
	}
	
	
}
