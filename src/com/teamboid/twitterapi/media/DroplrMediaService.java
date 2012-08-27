package com.teamboid.twitterapi.media;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SignatureException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.teamboid.twitterapi.client.RequestHandler;
import com.teamboid.twitterapi.client.Twitter;
import com.teamboid.twitterapi.client.TwitterException;
import com.teamboid.twitterapi.json.JSONObject;
import com.teamboid.twitterapi.media.ExternalMediaService.AuthorizationNeeded;
import com.teamboid.twitterapi.status.StatusUpdate;
import com.teamboid.twitterapi.status.entity.media.MediaEntity;
import com.teamboid.twitterapi.status.entity.url.UrlEntity;

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
	public static final String ENDPOINT = "https://api.droplr.com/";
	
	public HttpClient getClient(){
		DefaultHttpClient client = new DefaultHttpClient();
		return client;
	}

	@Override
	public String getServiceName() {
		return "Drop.lr";
	}

	@Override
	public String[] getSupportedUrls() {
		return new String[]{};
	}
	
	public String getUserName(){ // email in this case
		try{
			HttpGet get = new HttpGet(ENDPOINT + "/account.json");
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This is a heck of work!
	 * @param request
	 * @return
	 */
	String getDroplrSignature(HttpRequestBase request){
		try{
			String password = authPassword; // TODO: sha1
			
			String accessKey = Base64.encode(
					new StringBuilder(apiKey.length()+1+authMail.length()).append(apiKey).append(":").append(authMail).toString().getBytes()
			);
			String accessSecret = new StringBuilder(apiSecret.length()+1+password).append(apiSecret).append(":").append(password).toString();
			
			// Signature
			String contentType = request.getFirstHeader(HttpHeaders.CONTENT_TYPE).getValue();
	        if (contentType == null) {
	            contentType = "";
	        }
	        String date = request.getFirstHeader(HttpHeaders.DATE).getValue();
	        String uri = request.getURI().getPath();
			String stringToSign = new StringBuilder()
					.append(request.getMethod()).append(' ')
					.append(uri).append(' ')
					.append(request.getProtocolVersion()).append('\n')
					.append(contentType).append('\n')
					.append(date)
					.toString();
			
			String signature = calculateRfc2104Hmac(stringToSign, accessSecret);
			return new StringBuilder(7+accessKey.length()+1+signature.length()).append(" droplr").append(accessKey).append(":").append(signature).toString();
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
            result = Base64.encode(rawHmac);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediaEntity getEntity(UrlEntity in) {
		// TODO Auto-generated method stub
		return null;
	}

}
