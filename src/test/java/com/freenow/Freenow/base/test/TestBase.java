package com.freenow.Freenow.base.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.freenow.Freenow.helpers.UserPostCommentsHelper;
import com.freenow.Freenow.utils.ConfigManager;

import io.restassured.response.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

	public Response response;
	public String userName = ConfigManager.getInstance().getString("userName");
	public UserPostCommentsHelper helper;
	public static int userId;
	public static List<Integer> postIds = new ArrayList<Integer>();
	//public Logger logger;
	
	public TestBase() {
			helper = new UserPostCommentsHelper();
	}
	
	/*@BeforeClass
	public void setup(){
		logger=Logger.getLogger("UserPostCommentsRestAPI");
		PropertyConfigurator.configure("Log4j.properties"); 
		logger.setLevel(Level.DEBUG);
		
	}*/
	
	@AfterClass
	void checkResposeBody()
	{
		//logger.info("*********** Checking Respose Body **********");
		
		String responseBody = response.getBody().asString();
		//logger.info("Response Body==>"+responseBody);
		assertTrue(responseBody!=null);
		
	}
	
	@AfterClass
	void checkStatusCode()
	{
		//logger.info("*********** Checking Status Code **********");
		
		int statusCode = response.getStatusCode(); 
		//logger.info("Status Code is ==>" + statusCode); 
		assertEquals(statusCode, 200);
		
	}
	/*@AfterClass
	void tearDown()
	{
		logger.info("********* Finished Test **********");
	}*/
	
}
