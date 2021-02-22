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
import com.freenow.Freenow.model.Comments;
import com.freenow.Freenow.utils.EmailValidation;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;


public class TestGetCommentsOnPost extends TestBase {
	
	@Test(groups = {"comments"}, dependsOnGroups = {"users", "posts"})
	@Owner("Swapnil")
	@Description("Get all the comments on a post by Samantha and check the email format of all people who commented")
	public void getAllCommentsonPostByUser() {
		logger.info("********Start Get Comments on a Post by a User Test*********");
		try {
			if(!UserPostCommentsHelper.postIds.isEmpty()) {
				for(int i=0; i<UserPostCommentsHelper.postIds.size(); i++) {
					response = helper.getAllCommentsForAPost(helper.postIds.get(i));
					
					Type type = new TypeReference<List<Comments>>() {}.getType();
					List<Comments> comments = response.as(type);
					
					if(!comments.isEmpty()) {
						for(int j=0; j<comments.size();j++) {
							String isValidEmail = EmailValidation.isValid(comments.get(j).getEmail());
							
							System.out.println("Email Id "+ comments.get(j).getEmail()+ " for post Id "
									+UserPostCommentsHelper.postIds.get(i)
									+ " of User "+userName+" having used Id "+UserPostCommentsHelper.userId+ " is "+isValidEmail);
						}
					}
				}
			}
			else{
				logger.error("No Comments found on posts Ids "
						+ postIds +" of : "
						+ userName);
			}
		}catch(Exception e) {
			System.out.println("Comments Not found on all posts");
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
