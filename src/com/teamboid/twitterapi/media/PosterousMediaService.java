package com.teamboid.twitterapi.media;

import java.io.InputStream;

import org.scribe.model.Response;

import com.teamboid.twitterapi.client.RequestHandler;
import com.teamboid.twitterapi.client.Twitter;
import com.teamboid.twitterapi.client.TwitterException;
import com.teamboid.twitterapi.json.JSONObject;
import com.teamboid.twitterapi.status.StatusUpdate;
import com.teamboid.twitterapi.status.entity.media.MediaEntity;
import com.teamboid.twitterapi.status.entity.url.UrlEntity;

public class PosterousMediaService extends ExternalMediaService {

	@Override
	public String getServiceName() {
		return "Posterous";
	}

	@Override
	public String[] getSupportedUrls() {
		return new String[]{};
	}

	@Override
	public MediaEntity uploadFile(StatusUpdate tweet, Twitter tw,
			RequestHandler client, InputStream file) throws TwitterException {
		try{
			Response r = this.doOAuthEchoRequest(ExternalMediaService.TWITTER_JSON_PROVIDER, tw, "http://posterous.com/api2/upload.json", client, file, null);
			if(!r.isSuccessful()) return null;
			
			JSONObject response = new JSONObject(r.getBody());
			
			ExternalMediaEntity ema = new ExternalMediaEntity(response.getString("url"));
			return ema;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	

	@Override
	public MediaEntity getEntity(UrlEntity in) {
		return null;
	}

}
