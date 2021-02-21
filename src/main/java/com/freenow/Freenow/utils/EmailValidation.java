package com.freenow.Freenow.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

	public static String isValid(String email) {
		
		String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(email);
	    return matcher.matches() ? "valid" : "invalid";
	    //System.out.println("Email " + email + " is " + (matcher.matches() ? "valid" : "invalid"));
	   }
}
