package com.teamboid.twitterapi.client;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Handles web requests for {@link TwitterBase}
 *
 * @author Aidan Follestad
 */
public class RequestHandler {

    public String _accessToken;
    public String _accessSecret;
    public String _consumer;
    public String _consumerSecret;

    /**
     * Gets a new OAuthConsumer instance, this must be done for each request because Signpost OAuth isn't thread safe
     * and Android requires network operations to be performed on a seperate thread. Therefore you must re-initialize
     * the consumer on each thread.
     * @return
     */
    private OAuthConsumer getConsumer() {
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(_consumer, _consumerSecret);
        consumer.setTokenWithSecret(_accessToken, _accessSecret);
        return consumer;
    }

    public JSONObject getObject(String url) throws Exception {
        HttpGet request = new HttpGet(url);
        getConsumer().sign(request);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);
        if(response.getStatusLine().getStatusCode() != 200) {
            throw new Exception("HTTP GET failed; " + response.getStatusLine().getReasonPhrase());
        }
        String body = EntityUtils.toString(response.getEntity());
        if(body.contains("\"error\":")) {
            throw new TwitterException(new JSONObject(body));
        }
        return new JSONObject(body);
    }

    public HttpResponse getResponse(String url, boolean expectRedirect) throws Exception {
        System.out.println("Requesting: " + url);
        HttpGet request = new HttpGet(url);
        getConsumer().sign(request);
        HttpParams params = new BasicHttpParams();
        HttpClientParams.setRedirecting(params, false);
        HttpClient httpClient = new DefaultHttpClient(params);
        HttpResponse response = httpClient.execute(request);
        if(response.getStatusLine().getStatusCode() != 302 && expectRedirect) {
            throw new Exception("HTTP GET FAILED; " + response.getStatusLine().getReasonPhrase());
        } else if(response.getStatusLine().getStatusCode() != 200) {
            throw new Exception("HTTP GET FAILED; " + response.getStatusLine().getReasonPhrase());
        }
        String body = EntityUtils.toString(response.getEntity());
        if(body.contains("\"error\":")) {
            throw new TwitterException(new JSONObject(body));
        }
        return response;
    }

    public JSONArray getArray(String url) throws Exception {
        HttpGet request = new HttpGet(url);
        getConsumer().sign(request);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);
        if(response.getStatusLine().getStatusCode() != 200) {
            throw new Exception("HTTP POST failed; " + response.getStatusLine().getReasonPhrase());
        }
        String body = EntityUtils.toString(response.getEntity());
        if(body.contains("\"error\":")) {
            throw new TwitterException(new JSONObject(body));
        }
        return new JSONArray(body);
    }

    public JSONObject postObject(String url, List<BasicNameValuePair> p, File file) throws Exception {
        HttpPost request = new HttpPost(url);
        HttpClient httpClient = null;
        if(file != null) {
            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setUseExpectContinue(params, false);
            httpClient = new DefaultHttpClient(params);
            MultipartEntity entity = new MultipartEntity(HttpMultipartMode.STRICT);
            FileBody imageBody = new FileBody(file, "image/png");
            entity.addPart("media[]", imageBody);
            for(BasicNameValuePair pair : p) {
                entity.addPart(pair.getName(), new StringBody(pair.getValue()));
            }
            request.setEntity(entity);
        } else {
            httpClient = new DefaultHttpClient();
            if(p != null) request.setEntity(new UrlEncodedFormEntity(p, HTTP.UTF_8));
        }
        getConsumer().sign(request);
        HttpResponse response = httpClient.execute(request);
        if(response.getStatusLine().getStatusCode() != 200) {
            throw new Exception("HTTP POST failed; " + response.getStatusLine().getReasonPhrase());
        }
        String body = EntityUtils.toString(response.getEntity());
        if(body.contains("\"error\":")) {
            throw new TwitterException(new JSONObject(body));
        }
        return new JSONObject(body);
    }

    public JSONObject deleteObject(String url) throws Exception {
        HttpDelete request = new HttpDelete(url);
        getConsumer().sign(request);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);
        if(response.getStatusLine().getStatusCode() != 200) {
            throw new Exception("HTTP DELETE failed; " + response.getStatusLine().getReasonPhrase());
        }
        String body = EntityUtils.toString(response.getEntity());
        if(body.contains("\"error\":")) {
            throw new TwitterException(new JSONObject(body));
        }
        return new JSONObject(body);
    }

    public JSONArray postArray(String url) throws Exception {
        HttpPost request = new HttpPost(url);
        getConsumer().sign(request);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);
        if(response.getStatusLine().getStatusCode() != 200) {
            throw new Exception("HTTP POST failed; " + response.getStatusLine().getReasonPhrase());
        }
        String body = EntityUtils.toString(response.getEntity());
        if(body.contains("\"error\":")) {
            throw new TwitterException(new JSONObject(body));
        }
        return new JSONArray(body);
    }

    public static String encode(String value) {
        String encoded = null;
        try { encoded = URLEncoder.encode(value, "UTF-8"); }
        catch (UnsupportedEncodingException e) { e.printStackTrace(); }
        StringBuilder buf = new StringBuilder(encoded.length());
        char focus;
        for (int i = 0; i < encoded.length(); i++) {
            focus = encoded.charAt(i);
            if (focus == '*') buf.append("%2A");
            else if (focus == '+') buf.append("%20");
            else if (focus == '%' && (i + 1) < encoded.length()
                    && encoded.charAt(i + 1) == '7' && encoded.charAt(i + 2) == 'E') {
                buf.append('~');
                i += 2;
            } else buf.append(focus);
        }
        return buf.toString();
    }
}