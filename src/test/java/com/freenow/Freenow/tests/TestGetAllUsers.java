package com.freenow.Freenow.tests;

import java.util.List;

import org.testng.annotations.Test;

import com.freenow.Freenow.base.test.TestBase;
import com.freenow.Freenow.model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;


public class TestGetAllUsers extends TestBase {
		
	@Test(groups = {"users"})
	public void getAllUsers() {
		List<Users> users = helper.getAllUsers();
		
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
