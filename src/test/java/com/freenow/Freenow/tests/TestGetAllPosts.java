package com.freenow.Freenow.tests;

import java.util.List;

import org.testng.annotations.Test;

import com.freenow.Freenow.base.test.TestBase;
import com.freenow.Freenow.model.Posts;

public class TestGetAllPosts extends TestBase {

	@Test(groups = {"posts"}, dependsOnGroups = {"users"})
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
}

