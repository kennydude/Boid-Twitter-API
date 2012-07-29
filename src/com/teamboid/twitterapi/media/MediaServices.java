package com.teamboid.twitterapi.media;

import java.util.HashMap;

/**
 * Registration place for media services
 * @author kennydude
 *
 */
public class MediaServices {
	public static HashMap<String, ExternalMediaService> services = new HashMap<String, ExternalMediaService>();
	
	public ExternalMediaService getService(String name){
		return services.get(name);
	}
	
	public static void registerService(Class<?> cls){
		try{
			ExternalMediaService ems = (ExternalMediaService) cls.newInstance();
			services.put( ems.getServiceName(), ems );
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
