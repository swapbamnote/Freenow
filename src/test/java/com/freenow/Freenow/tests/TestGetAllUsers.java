package com.freenow.Freenow.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Type;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.freenow.Freenow.base.test.TestBase;
import com.freenow.Freenow.helpers.UserPostCommentsHelper;
import com.freenow.Freenow.model.Users;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;


public class TestGetAllUsers extends TestBase {
		
	@Test(groups = {"users"})
	@Owner("Swapnil")
	@Description("Get the Id of User name")
	public void getAllUsers() {
		
		logger.info("******** Start Get All User Test *********");
		try {
			response = helper.getAllUsers();
			
			Type type = new TypeReference<List<Users>>() {}.getType();
			List<Users> users = response.as(type);
			
			for(int i=0; i<users.size(); i++) {
				if(users.get(i).getUsername().equalsIgnoreCase(userName)) {
					UserPostCommentsHelper.userId = users.get(i).getId();
					System.out.println("User Id of "+ userName +" is: "+ UserPostCommentsHelper.userId);
					break;
				}
			}
			
			if(UserPostCommentsHelper.userId ==0) {
				logger.error("User Not found: " + userName);
			}
		}catch(Exception e) {
			System.out.println("UserName could not be found");
			logger.error(e.getMessage(), e);
		}
	}
	
	@AfterClass
	void checkResposeBody()
	{
		logger.info("*********** Checking Respose Body **********");
		if(response != null) {
			String responseBody = response.getBody().asString();
			logger.info("Response Body==>"+responseBody);
			assertTrue(responseBody!=null);
			
			logger.info("*********** Checking Status Code **********");
			
			int statusCode = response.getStatusCode(); 
			logger.info("Status Code is ==>" + statusCode); 
			assertEquals(statusCode, 200);
		}
		logger.info("********* Finished Test **********");
		
	}
	
	
}
