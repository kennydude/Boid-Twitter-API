package com.teamboid.twitterapi.media;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.teamboid.twitterapi.client.RequestHandler;
import com.teamboid.twitterapi.client.Twitter;
import com.teamboid.twitterapi.client.TwitterException;
import com.teamboid.twitterapi.json.JSONObject;
import com.teamboid.twitterapi.media.ExternalMediaService.AuthorizationNeeded;
import com.teamboid.twitterapi.status.StatusUpdate;
import com.teamboid.twitterapi.status.entity.media.MediaEntity;
import com.teamboid.twitterapi.status.entity.url.UrlEntity;

public class DroplMediaService extends ExternalMediaService {

	@Override
	public String getServiceName() {
		return "Drop.lr";
	}

	@Override
	public String[] getSupportedUrls() {
		return new String[]{};
	}
	
	public String getUserName(){ // email in this case
		return null;
	}
	
	public AuthorizationNeeded getNeededAuthorization(){
		return AuthorizationNeeded.MAIL_AND_PASSWORD;
	}

	@Override
	public MediaEntity uploadFile(StatusUpdate tweet, Twitter tw,
			RequestHandler client, InputStream file) throws TwitterException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediaEntity getEntity(UrlEntity in) {
		// TODO Auto-generated method stub
		return null;
	}

}
