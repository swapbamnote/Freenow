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
import com.freenow.Freenow.model.Posts;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;

public class TestGetAllPosts extends TestBase {

	@Test(groups = {"posts"}, dependsOnGroups = {"users"})
	@Owner("Swapnil")
	@Description("Get all posts of Samantha")
	public void getAllPostsOfUser() {
		logger.info("******** Start Get All Posts Test *********");
		try {
			if(UserPostCommentsHelper.userId !=0) {
				response = helper.getAllPosts();
				
				Type type = new TypeReference<List<Posts>>() {}.getType();
				List<Posts> posts = response.as(type);
				
				for(int i=0; i<posts.size(); i++) {
					if(posts.get(i).getUserId() == UserPostCommentsHelper.userId) {
						int postIds = posts.get(i).getId();
						UserPostCommentsHelper.postIds.add(postIds);
					}
				}
				if(UserPostCommentsHelper.postIds.isEmpty()) {
					logger.error("No Posts by: "+ userName);
				}
				System.out.println("PostIds "+UserPostCommentsHelper.postIds+ " of " +userName+"'s userId= "+UserPostCommentsHelper.userId);
			}
		}catch(Exception e) {
			System.out.println("No Posts Found");
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

