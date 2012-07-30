package com.teamboid.twitterapi.media;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.scribe.model.Response;

import com.teamboid.twitterapi.client.HttpParam;
import com.teamboid.twitterapi.client.RequestHandler;
import com.teamboid.twitterapi.client.Twitter;
import com.teamboid.twitterapi.client.TwitterException;
import com.teamboid.twitterapi.json.JSONObject;
import com.teamboid.twitterapi.media.ExternalMediaService.ExternalMediaEntity;
import com.teamboid.twitterapi.status.StatusUpdate;
import com.teamboid.twitterapi.status.entity.media.MediaEntity;
import com.teamboid.twitterapi.status.entity.url.UrlEntity;

public class TwitPicMediaService extends ExternalMediaService {

	@Override
	public String getServiceName() {
		return "TwitPic";
	}

	@Override
	public String[] getSupportedUrls() {
		return new String[]{
				"http://twitpic.com/*",
				"https://twitpic.com/*",
				"http://www.twitpic.com/*",
				"https://www.twitpic.com/*"
		};
	}

	@Override
	public MediaEntity uploadFile(StatusUpdate tweet, Twitter tw,
			RequestHandler client, InputStream file) throws TwitterException {
		try{
			List<HttpParam> params = new ArrayList<HttpParam>();
			params.add(new HttpParam("key", this.apiKey));
			params.add(new HttpParam("message", tweet.getStatus()));
			Response r = this.doOAuthEchoRequest(ExternalMediaService.TWITTER_XML_PROVIDER, tw, "http://api.twitpic.com/2/upload.json", client, file, params);
		
			if(!r.isSuccessful()) return null;
			JSONObject obj = new JSONObject( r.getBody() );
			
			String url = obj.getString("url");
			ExternalMediaEntity ret = new ExternalMediaEntity(url);
        	setEntityURLs(ret, url);
        	return ret;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private void setEntityURLs(ExternalMediaEntity ret, String url) {
		String[] parts = url.split("/");
		String hash = parts[ parts.length - 1 ];
		if(hash == "") hash = parts[parts.length - 2];
		url = "http://twitpic.com/show/";
		ret.setUrls(url + "thumb/" + hash, url + "large/" + hash, url + "full/" + hash, url + "mini/" + hash);
	}

	@Override
	public MediaEntity getEntity(UrlEntity in) {
		ExternalMediaEntity me = new ExternalMediaEntity(in);
		String url = in.getExpandedUrl();
		setEntityURLs(me, url);
		return me;
	}

}
