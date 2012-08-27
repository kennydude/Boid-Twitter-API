package com.teamboid.twitterapi.media;

import java.io.InputStream;
import java.security.SignatureException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Base64;

import com.teamboid.twitterapi.client.RequestHandler;
import com.teamboid.twitterapi.client.Twitter;
import com.teamboid.twitterapi.client.TwitterException;
import com.teamboid.twitterapi.json.JSONObject;
import com.teamboid.twitterapi.status.StatusUpdate;
import com.teamboid.twitterapi.status.entity.media.MediaEntity;
import com.teamboid.twitterapi.status.entity.url.UrlEntity;
import com.teamboid.twitterapi.utilities.Utils;

/**
 * Drop.lr
 * 
 * To get access you must contact Drop.lr yourself.
 * ApiKey = Public Key
 * ApiSecret = Private Key
 * @author kennydude
 *
 */
public class DroplrMediaService extends ExternalMediaService {
	public static final String ENDPOINT = "https://api.droplr.com";
	
	public HttpClient getClient(){
		DefaultHttpClient client = new DefaultHttpClient();
		return client;
	}

	@Override
	public String getServiceName() {
		return "Droplr";
	}

	@Override
	public String[] getSupportedUrls() {
		return new String[]{};
	}
	
	public String getUserName(){ // email in this case
		try{
			HttpGet get = new HttpGet(ENDPOINT + "/account.json");
			authorizeRequest(get);
			
			HttpResponse r = getClient().execute(get);
			if(r.getStatusLine().getStatusCode() == 200){
				JSONObject jo = new JSONObject( EntityUtils.toString(r.getEntity()) );
				return jo.getString("email");
			} else{
				throw new Exception("Droplr did not return 200: "+ r.getFirstHeader("x-droplr-errordetails").getValue());
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	void authorizeRequest(HttpRequestBase request){
		request.addHeader("Date", System.currentTimeMillis()+"");
		request.addHeader("User-Agent", "Droplr+Boid+Lib/1.0");
		request.addHeader("Authorization", getDroplrSignature(request));
	}
	
	/**
	 * This is a lot of work!
	 * @param request
	 * @return
	 */
	String getDroplrSignature(HttpRequestBase request){
		try{
			String password = Utils.sha1(authPassword);
			
			String accessKey = Base64.encodeToString(
					new StringBuilder().append(apiKey).append(":").append(authMail).toString().getBytes()
			, Base64.DEFAULT);
			String accessSecret = new StringBuilder().append(apiSecret).append(":").append(password).toString();
			
			// Signature
			// String contentType = request.getFirstHeader("Content-Type").getValue();
	        //if (contentType == null) {
	            String contentType = "";
	        // }
	        String date = request.getFirstHeader("Date").getValue();
			String stringToSign = new StringBuilder()
					.append(request.getRequestLine().getMethod())
					.append(' ')
					.append(request.getURI().getPath())
					.append(' ').append(request.getRequestLine().getProtocolVersion().toString()).append('\n')
					.append(contentType).append('\n')
					.append(date)
					.toString();
			
			String signature = calculateRfc2104Hmac(stringToSign, accessSecret);
			return new StringBuilder().append("droplr ").append(accessKey).append(":").append(signature).toString();
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    /**
     * Computes RFC 2104-compliant HMAC signature.
     *
     * @param data The data to be signed.
     * @param key The signing key.
     *
     * @return The Base64-encoded RFC 2104-compliant HMAC signature.
     *
     * @throws java.security.SignatureException when signature generation fails
     */
    public static String calculateRfc2104Hmac(String data, String key) throws SignatureException {
        String result;
        try {
            // get an hmac_sha1 key from the raw key bytes
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
            // get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            // compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(data.getBytes());
            // base64-encode the hmac
            result = Base64.encodeToString(rawHmac, Base64.DEFAULT);
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC: " + e.getMessage());
        }

        return result;
    }
	
	public AuthorizationNeeded getNeededAuthorization(){
		return AuthorizationNeeded.MAIL_AND_PASSWORD;
	}

	@Override
	public MediaEntity uploadFile(StatusUpdate tweet, Twitter tw,
			RequestHandler client, InputStream file) throws TwitterException {
		try{
			HttpPost post = new HttpPost(ENDPOINT + "/files.json?filename=BoidUpload.jpg");
			authorizeRequest(post);
			
			post.setEntity(new InputStreamEntity(file, 0));
			
			HttpResponse r = getClient().execute(post);
			if(r.getStatusLine().getStatusCode() == 200){
				JSONObject jo = new JSONObject(EntityUtils.toString(r.getEntity()));
				ExternalMediaEntity ema = new ExternalMediaEntity(jo.getString("shortlink"));
				
				return ema;
			} else{
				throw new Exception("Droplr did not return 200: "+ r.getFirstHeader("x-droplr-errordetails").getValue());
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MediaEntity getEntity(UrlEntity in) {
		// TODO Auto-generated method stub
		return null;
	}

}
