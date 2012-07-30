package com.teamboid.twitterapi.media;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuth10aServiceImpl;
import org.scribe.services.TimestampService;

import com.teamboid.twitterapi.client.HttpParam;
import com.teamboid.twitterapi.client.RequestHandler;
import com.teamboid.twitterapi.client.Twitter;
import com.teamboid.twitterapi.client.TwitterException;
import com.teamboid.twitterapi.status.StatusUpdate;
import com.teamboid.twitterapi.status.entity.media.MediaEntity;
import com.teamboid.twitterapi.status.entity.media.MediaSize;
import com.teamboid.twitterapi.status.entity.url.UrlEntity;

/**
 * External Media Service Base Class
 * @author kennydude
 *
 */
public abstract class ExternalMediaService {
	/**
	 * A Media Entity from an External Source
	 * @author kennydude
	 *
	 */
	public static class ExternalMediaEntity implements MediaEntity{
		private static final long serialVersionUID = 7777783434597358763L;

		public ExternalMediaEntity(UrlEntity baseEntity){
			this.url = baseEntity.getExpandedUrl();
			this.displayUrl = baseEntity.getDisplayUrl();
			start = baseEntity.getStartIndex();
			end = baseEntity.getEndIndex();
		}
		public ExternalMediaEntity(String url){
			this.url = url;
			this.displayUrl = url;
		}
		public void setUrls(String smallUrl, String mediumUrl, String largeUrl, String thumbUrl){
			this.smallUrl = smallUrl;
			this.mediumUrl = mediumUrl;
			this.largeUrl = largeUrl;
			this.thumbUrl = thumbUrl;
		}
		int start = -1, end = -1;
		public String smallUrl, mediumUrl, largeUrl, thumbUrl, url, displayUrl;

		@Override
		public long getId() {
			return -1; // Not Twitter
		}
		
		public String getMediaUrl(MediaSize size){
			if(size.equals(MediaSize.SMALL)){
				return smallUrl;
			} else if(size.equals(MediaSize.MEDIUM)){
				return mediumUrl;
			} else if(size.equals(MediaSize.LARGE)){
				return largeUrl;
			} else if(size.equals(MediaSize.THUMB)){
				return thumbUrl;
			}
			return null;
		}

		@Override
		public String getMediaUrlHttps(MediaSize size) {
			return getMediaUrl(size).replace("http", "https");
		}

		@Override
		public String getUrl() {
			return url;
		}

		@Override
		public String getDisplayUrl() {
			return displayUrl;
		}

		@Override
		public String getExpandedUrl() {
			return url;
		}

		@Override
		public int getStartIndex() {
			return start;
		}

		@Override
		public int getEndIndex() {
			return end;
		}
		
	}
	
	/**
	 * What is the name of this media service
	 * @return
	 */
	public abstract String getServiceName();
	
	/**
	 * Get supported Urls
	 * @return A String[] containing wildcarded urls, for example http://imgur.com/*
	 */
	public abstract String[] getSupportedUrls();
	
	/**
	 * Set an API key for this image service if it uses one
	 * @param key
	 */
	public void setAPIKey(String key){
		apiKey = key;
	}
	String apiKey;
	
	/**
	 * Upload a file to this service
	 * @param file
	 * @return
	 * @throws TwitterException 
	 */
	public MediaEntity uploadFile( StatusUpdate tweet, Twitter client, InputStream file ) throws TwitterException{
		try{
			return uploadFile( tweet, client, (RequestHandler)client, file );
		} catch(ClassCastException e){
			return null;
		}
	}
	public abstract MediaEntity uploadFile( StatusUpdate tweet, Twitter tw, RequestHandler client, InputStream file ) throws TwitterException;
	
	/**
	 * Turn an UrlEntity at this service, to a MediaEntity
	 * @param in
	 * @return The media Entity or null
	 */
	public abstract MediaEntity getEntity( UrlEntity in );
	
	Response doOAuthEchoRequest(String provider, Twitter tw, String url, RequestHandler client, InputStream toSend, List<HttpParam> params){
		try{
			MultipartEntity entity = new MultipartEntity();
	        if (params != null) {
	            for (HttpParam p : params) {
	                entity.addPart(p.getName(), new StringBody(p.getValue()));
	            }
	        }
	        entity.addPart("media", new InputStreamBody(toSend, "BOIDUPLOAD.jpg"));
	        
	        return doOAuthEchoRequest(provider, tw, url, client, entity);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This requires a few hacks to work properly due to OAuth Echo
	 * @param tw
	 * @param url
	 * @param client
	 * @param toSend
	 * @return
	 */
	Response doOAuthEchoRequest(String serviceProvider, Twitter tw, String url, RequestHandler client, HttpEntity toSend){
		try{
			OAuthRequest r = new OAuthRequest(Verb.POST, url);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
	        toSend.writeTo(out);
	        r.addPayload(out.toByteArray());
	        
	        OAuth10aServiceImpl oauth = (OAuth10aServiceImpl)client._oauth;
	        OAuthRequest sr = new OAuthRequest(Verb.GET, serviceProvider);
	        sr.addOAuthParameter(OAuthConstants.TOKEN, client._oauthToken.getToken());
	        oauth.addOAuthParams(sr, client._oauthToken);
	        
	        String sig = sr.getOauthParameters().get(OAuthConstants.SIGNATURE);
	        r.addHeader("X-Auth-Service-Provider",serviceProvider);
	        r.addHeader("X-Verify-Credentials-Authorization", "OAuth realm=\"http://api.twitter.com/\", "+
	        		"oauth_consumer_key=\""+tw.getConsumerKey()+"\", "+
	        		"oauth_signature_method=\"HMAC-SHA1\", "+
	        		"oauth_token=\""+ sr.getOauthParameters().get(OAuthConstants.TOKEN) +"\", " +
	        		"oauth_timestamp=\""+ sr.getOauthParameters().get(OAuthConstants.TIMESTAMP) +"\", " +
	        		"oauth_nonce=\""+ sr.getOauthParameters().get(OAuthConstants.NONCE) + "\", " +
	        		"oauth_version=\"1.0\", " +
	        		"oauth_signature=\""+sig+"\"");
	        System.out.println(r.getHeaders().get("X-Verify-Credentials-Authorization"));
	        
	        return r.send();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static final String TWITTER_XML_PROVIDER = "https://api.twitter.com/1/account/verify_credentials.xml";
	public static final String TWITTER_JSON_PROVIDER = "https://api.twitter.com/1/account/verify_credentials.json";
}
