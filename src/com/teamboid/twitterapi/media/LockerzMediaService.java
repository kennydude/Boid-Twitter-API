package com.teamboid.twitterapi.media;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.scribe.model.Response;

import com.teamboid.twitterapi.client.HttpParam;
import com.teamboid.twitterapi.client.RequestHandler;
import com.teamboid.twitterapi.client.Twitter;
import com.teamboid.twitterapi.client.TwitterException;
import com.teamboid.twitterapi.status.StatusUpdate;
import com.teamboid.twitterapi.status.entity.media.MediaEntity;
import com.teamboid.twitterapi.status.entity.url.UrlEntity;

public class LockerzMediaService extends ExternalMediaService {

	@Override
	public String getServiceName() {
		return "Lockerz";
	}

	@Override
	public String[] getSupportedUrls() {
		return new String[]{
			"http://*lockerz.com/s/"
		};
	}

	@Override
	public MediaEntity uploadFile(StatusUpdate tweet, Twitter tw,
			RequestHandler client, InputStream file) throws TwitterException {
		try{
			List<HttpParam> params = new ArrayList<HttpParam>();
			params.add(new HttpParam("api_key", apiKey));
			Response r = this.doOAuthEchoRequest(ExternalMediaService.TWITTER_XML_PROVIDER, tw, "http://api.plixi.com/api/upload.aspx", client, file, params);
			
			if(! r.isSuccessful()) return null;
			
			String response = r.getBody();
			if (response.contains("<Status>OK</Status>")) {
	            String url = response.substring(response.indexOf("<MediaUrl>") + "<MediaUrl>".length(), response.indexOf("</MediaUrl>"));
	            ExternalMediaEntity ema = new ExternalMediaEntity(url);
	            setUrls(ema, url);
	            return ema;
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	void setUrls(ExternalMediaEntity ema, String url){
		url = "http://api.plixi.com/api/tpapi.svc/imagefromurl?url="+url+"&size=";
		ema.setUrls(url + "small", url+ "mobile", url+"medium", url+"thumbnail");
	}

	@Override
	public MediaEntity getEntity(UrlEntity in) {
		ExternalMediaEntity ema = new ExternalMediaEntity(in);
		setUrls(ema, in.getExpandedUrl());
		return ema;
	}

}
