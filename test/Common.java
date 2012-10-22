package com.teamboid.twitterapi.test;

import java.util.Properties;
import java.io.*;

public class Common{
	public static final String path = "test";

	public static Properties getProperties(){
		try{
			Properties ret = new Properties();
			ret.load(new FileInputStream(new File(path, "local.properties")));
			return ret;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
