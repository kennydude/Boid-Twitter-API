package com.teamboid.twitterapi.test;

import java.util.Properties;
import com.teamboid.twitterapi.media.*;

public class DroplrTest{
	public static void main(String[] args){
		System.out.println("hello world");

		Properties props = Common.getProperties();

		DroplrMediaService dms = new DroplrMediaService();
		dms.setAPIKey(props.getProperty("droplr_api_key"));
		dms.setAPISecret(props.getProperty("droplr_api_secret"));
		dms.setMailAndPassword(
			props.getProperty("droplr_email"),
			props.getProperty("droplr_password")
		);
		System.out.println(props.size() + "");
		System.out.println("Username: " + props.getProperty("droplr_email"));
		System.out.println("Password: " + props.getProperty("droplr_password"));

		System.out.println("Check username");

		System.out.println(dms.getUserName());
	}
}
