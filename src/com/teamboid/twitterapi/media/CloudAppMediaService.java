package com.teamboid.twitterapi.media;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.teamboid.twitterapi.client.RequestHandler;
import com.teamboid.twitterapi.client.Twitter;
import com.teamboid.twitterapi.client.TwitterException;
import com.teamboid.twitterapi.json.JSONObject;
import com.teamboid.twitterapi.status.StatusUpdate;
import com.teamboid.twitterapi.status.entity.media.MediaEntity;
import com.teamboid.twitterapi.status.entity.url.UrlEntity;

public class CloudAppMediaService extends ExternalMediaService {

	@Override
	public String getServiceName() {
		return "CloudApp";
	}

	@Override
	public String[] getSupportedUrls() {
		return new String[]{
			"http://cl.ly/*",
			"http://www.cl.ly/*"
		};
	}
	
	DefaultHttpClient getClient(){
		DefaultHttpClient dhc = new DefaultHttpClient();
		dhc.getCredentialsProvider().setCredentials(
				new AuthScope("my.cl.ly", 80),
				new UsernamePasswordCredentials(authMail, authPassword)
		);
		return dhc;
	}
	
	public String getUserName(){ // email in this case
		try{
			HttpGet get = new HttpGet("http://my.cl.ly/account");
			get.addHeader("Accept", "application/json");
			DefaultHttpClient dhc = getClient();
			
			HttpResponse r = dhc.execute(get);
			if(r.getStatusLine().getStatusCode() != 200) throw new TwitterException("Did not return 200");
			
			JSONObject ac = new JSONObject(EntityUtils.toString(r.getEntity()));
			return ac.getString("email");
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public MediaEntity uploadFile(StatusUpdate tweet, Twitter tw,
			RequestHandler client, InputStream file) throws TwitterException {
		try{
			HttpGet get = new HttpGet("http://my.cl.ly/items/new");
			get.addHeader("Accept", "application/json");
			DefaultHttpClient dhc = getClient();
			HttpResponse r = dhc.execute(get);
			
			if(r.getStatusLine().getStatusCode() != 200) throw new TwitterException("CloudApp did not return 200 response code :(");
			
			// Now we should have a JSON response
			JSONObject jo = new JSONObject(EntityUtils.toString(r.getEntity()));
			
			// Send it off to Amazon
			HttpPost amazon = new HttpPost(jo.getString("url"));
			MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			
			JSONObject params = jo.getJSONObject("params");
			@SuppressWarnings("rawtypes")
		    Iterator keys = params.keys();
		    while (keys.hasNext()) {
				 String key = (String) keys.next();
				 entity.addPart(key, new StringBody(params.getString(key)));
		    }
		    entity.addPart("file", new InputStreamBody(file, "BOIDUPLOAD.jpg"));
		    amazon.addHeader("Accept", "application/json");
		    amazon.setEntity(entity);
		    
		    r = dhc.execute(amazon);
		    if(r.getStatusLine().getStatusCode() != 303) throw new TwitterException("Amazon did not return 303");
			
		    get = new HttpGet(r.getFirstHeader("Location").getValue());
		    r = dhc.execute(get);
		    if(r.getStatusLine().getStatusCode() != 200) throw new TwitterException("CloudApp did not acknoledge file. Non-200 response");
		    
		    jo = new JSONObject(EntityUtils.toString(r.getEntity()));
		    
		    ExternalMediaEntity ema = new ExternalMediaEntity(jo.getString("url"));
		    ema.setUrls( jo.getString("remote_url"), jo.getString("remote_url"), jo.getString("remote_url"),  jo.getString("thumbnail_url"));
		    return ema;
		    
		} catch(Exception e){
			e.printStackTrace();
			throw new TwitterException("Failed upload");
		}
	}

	@Override
	public MediaEntity getEntity(UrlEntity in) {
		ExternalMediaEntity ema = new ExternalMediaEntity(in);
		String t = in.getExpandedUrl().replace("cl.ly", "thumbs.cl.ly");
		ema.setUrls(t, t, t, t);
		return ema;
	}
	
	public AuthorizationNeeded getNeededAuthorization(){
		return AuthorizationNeeded.MAIL_AND_PASSWORD;
	}

}
