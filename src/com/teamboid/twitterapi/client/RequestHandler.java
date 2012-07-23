package com.teamboid.twitterapi.client;

import com.teamboid.twitterapi.json.JSONArray;
import com.teamboid.twitterapi.json.JSONObject;
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

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Handles web requests for {@link TwitterBase}
 * @author Aidan Follestad
 */
class RequestHandler {

    public String _accessToken;
    public String _accessSecret;
    public String _consumer;
    public String _consumerSecret;

    public boolean _debugOn;
    public boolean _ssl;
    private String getApiUrl() {
        if(_ssl) return "https://api.twitter.com/1";
        else return "http://api.twitter.com/1";
    }

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
        url = getApiUrl() + url;
        if(_debugOn) System.out.println("[GET OBJECT]: " + url);
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
        url = getApiUrl() + url;
        if(_debugOn) System.out.println("[GET RESPONSE]: " + url);
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
        url = getApiUrl() + url;
        if(_debugOn) System.out.println("[GET ARRAY]: " + url);
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
        System.out.println("\n" + new JSONArray(body).toString(3) + "\n");
        return new JSONArray(body);
    }

    public JSONObject postObject(String url, List<BasicNameValuePair> p, File file) throws Exception {
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setUseExpectContinue(params, false);
        HttpClient httpClient = new DefaultHttpClient(params);
        HttpPost request = null;
        if(file != null) {
            url = Urls.BASE_MEDIA_URL + url;
            request = new HttpPost(url);
            if(_debugOn) System.out.println("[POST OBJECT]: " + url);
            MultipartEntity entity = new MultipartEntity(HttpMultipartMode.STRICT);
            FileBody imageBody = new FileBody(file, "image/png");
            entity.addPart("media[]", imageBody);
            for(BasicNameValuePair pair : p) {
                entity.addPart(pair.getName(), new StringBody(pair.getValue()));
            }
            request.setEntity(entity);
        } else if(p != null) {
            url = getApiUrl() + url;
            request = new HttpPost(url);
            if(_debugOn) System.out.println("[POST OBJECT]: " + url);
            request.setEntity(new UrlEncodedFormEntity(p, "UTF-8"));
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
        url = getApiUrl() + url;
        if(_debugOn) System.out.println("[DELETE OBJECT]: " + url);
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
}