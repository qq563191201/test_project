package com.email;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyEmail {
	
	public static void main(String[] args){
		 Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

		 Matcher matcher = pattern.matcher("11212121gfd@1aa.com");

		 System.out.println(verifyEmail("11212121gfd@1aa.com"));
	}
	
	public static boolean verifyEmail(String email){
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(email);
		boolean isEmail = matcher.matches();
		return isEmail;
	}
}
