package com.freenow.Freenow.tests;


import java.lang.reflect.Type;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.freenow.Freenow.model.Users;
import com.freenow.Freenow.utils.ConfigManager;
import com.freenow.Freenow.model.Posts;
import com.freenow.Freenow.model.Comments;
import com.freenow.Freenow.utils.EmailValidation;

import io.restassured.response.Response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.freenow.Freenow.helpers.UserPostCommentsHelper;

public class E2E {
	
	private UserPostCommentsHelper helper;
	public String userName = ConfigManager.getInstance().getString("userName");
	
	@BeforeClass
	public void init() {
		helper = new UserPostCommentsHelper();
	}
	
	@Test(priority=1)
	public void getAllUsers() {
		Response response = helper.getAllUsers();
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
	
	@Test(dependsOnMethods = {"getAllUsers"})
	public void getAllPostsOfUser() {
		if(helper.userId !=0) {
			List<Posts> posts = helper.getAllPosts();
			
			for(int i=0; i<posts.size(); i++) {
				if(posts.get(i).getUserId() == helper.userId) {
					int postIds = posts.get(i).getId();
					helper.postIds.add(postIds);
				}
			}
			if(helper.postIds.isEmpty()) {
				throw new NullPointerException("No Posts by: "+ userName);
			}
			System.out.println("PostIds "+helper.postIds+ " of " +userName+"'s userId= "+helper.userId);
		}
	}
		
	@Test(dependsOnMethods = {"getAllUsers", "getAllPostsOfUser"})
	public void getAllCommentsonPostByUser() {
		for(int i=0; i<helper.postIds.size(); i++) {
			List<Comments> comments = helper.getAllCommentsForAPost(helper.postIds.get(i));
			
			if(!comments.isEmpty()) {
				for(int j=0; j<comments.size();j++) {
					String isValidEmail = EmailValidation.isValid(comments.get(j).getEmail());
					
					System.out.println("Email Id "+ comments.get(j).getEmail()+ " for post Id "
							+helper.postIds.get(i)
							+ " of User "+userName+" having used Id "+helper.userId+ "is "+isValidEmail);
				}
			}
			else {
				throw new NullPointerException("No Comments found on posts Ids "
						+ helper.postIds +" of : "
						+ userName);
			}
		}
	}		
		

}
