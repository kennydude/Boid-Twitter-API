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

/**
 * yFrog Media Service.
 * Requires API key for upload
 * @author kennydude
 *
 */
public class yFrogMediaService extends ExternalMediaService {
	static{
		MediaServices.registerService( yFrogMediaService.class );
	}
	
	@Override
	public String getServiceName() {
		return "yfrog";
	}

	@Override
	public String[] getSupportedUrls() {
		return new String[]{
			"http://*yfrog.com/*",
			"https://*yfrog.com/*"
		};
	}

	@Override
	public MediaEntity uploadFile(StatusUpdate tweet, Twitter tw, RequestHandler client, InputStream file) throws TwitterException {
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("key", this.apiKey));
		params.add(new HttpParam("media", "upload.jpg"));
		Response r = this.doOAuthEchoRequest(tw, "https://yfrog.com/api/xauth_upload", client, file, params );
		
		String b = r.getBody();
		if (b.contains("<rsp stat=\"fail\">")) {
            String error = b.substring(b.indexOf("msg") + 5, b.lastIndexOf("\""));
            throw new TwitterException("YFrog image upload failed with this error message: " + error);
        }
        if (b.contains("<rsp stat=\"ok\">")) {
        	String url = b.substring(b.indexOf("<mediaurl>") + "<mediaurl>".length(), b.indexOf("</mediaurl>"));
        	ExternalMediaEntity ret = new ExternalMediaEntity(url);
        	setEntityURLs(ret, url);
        	return ret;
        }
		return null;
	}

	@Override
	public MediaEntity getEntity(UrlEntity in) {
		ExternalMediaEntity me = new ExternalMediaEntity(in);
		String url = in.getExpandedUrl();
		setEntityURLs(me, url);
		return me;
	}
	void setEntityURLs(ExternalMediaEntity me, String url){
		me.setUrls(url + ":small", url + ":iphone", url + ":medium", url + ":small");
	}

}
