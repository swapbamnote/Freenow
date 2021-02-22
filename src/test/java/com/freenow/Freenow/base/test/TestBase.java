package com.freenow.Freenow.base.test;

import java.util.ArrayList;
import java.util.List;

import com.freenow.Freenow.helpers.UserPostCommentsHelper;
import com.freenow.Freenow.utils.ConfigManager;

import io.restassured.response.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

public class TestBase {

	public Response response;
	public String userName = ConfigManager.getInstance().getString("userName");
	public UserPostCommentsHelper helper;
	public static int userId;
	public static List<Integer> postIds = new ArrayList<Integer>();
	public Logger logger;
	
	public TestBase() {
			helper = new UserPostCommentsHelper();
	}
	
	@BeforeClass
	public void setup(){
		logger=Logger.getLogger("UserPostCommentsRestAPI");
		PropertyConfigurator.configure("/Users/sbamnote/eclipse-workspace/Freenow/resource/log4j.properties"); 
		logger.setLevel(Level.DEBUG);
		
	}
	
}
