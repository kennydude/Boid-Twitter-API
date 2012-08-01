package com.teamboid.twitterapi.media;

import java.util.HashMap;

/**
 * Registration place for media services
 * @author kennydude
 *
 */
public class MediaServices {
	public static HashMap<String, ExternalMediaService> services = new HashMap<String, ExternalMediaService>();
	
	public static void setupServices(){
		if(services.size() != 0) return;
		registerService( TwitterMediaService.class );
		registerService( yFrogMediaService.class );
		registerService( TwitPicMediaService.class );
		registerService( PosterousMediaService.class );
		registerService( LockerzMediaService.class );
		registerService( ImgurMediaService.class );
		registerService( FlickrMediaService.class );
	}
	
	public static ExternalMediaService getService(String name){
		return services.get(name);
	}
	
	public static void registerService(Class<?> cls){
		try{
			ExternalMediaService ems = (ExternalMediaService) cls.newInstance();
			services.put( ems.getServiceName().toLowerCase(), ems );
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
