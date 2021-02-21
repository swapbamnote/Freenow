package com.freenow.Freenow.tests;


import java.util.List;

import org.testng.annotations.Test;

import com.freenow.Freenow.base.test.TestBase;
import com.freenow.Freenow.model.Comments;
import com.freenow.Freenow.utils.EmailValidation;


public class TestGetCommentsOnPost extends TestBase {
	
	@Test(groups = {"comments"}, dependsOnGroups = {"users", "posts"})
	public void getAllCommentsonPostByUser() {
		//logger.info("********Start Get Comments on a Post by a User Test*********");
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
