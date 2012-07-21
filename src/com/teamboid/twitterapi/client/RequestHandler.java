package com.teamboid.twitterapi.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 * Handles web requests for {@link TwitterBase}
 */
public class RequestHandler {

    public Token token;
    public OAuthService oauth;

    protected JSONObject get(String url) throws Exception {
        HttpClient cl = new DefaultHttpClient();
        HttpResponse resp = cl.execute(new HttpGet(url));
        return new JSONObject(EntityUtils.toString(resp.getEntity()));
    }
    protected JSONArray getArray(String url) throws Exception {
        HttpClient cl = new DefaultHttpClient();
        HttpResponse resp = cl.execute(new HttpGet(url));
        return new JSONArray(EntityUtils.toString(resp.getEntity()));
    }
    protected JSONObject getAuth(String url) throws Exception {
        OAuthRequest request = new OAuthRequest(Verb.GET, url);
        oauth.signRequest(token, request);
        //TODO make sure this uses the correct encoding
        Response response = request.send();
        return new JSONObject(response.getBody());
    }
    protected JSONArray getArrayAuth(String url) throws Exception {
        System.out.println("Requesting array from: " + url);
        OAuthRequest request = new OAuthRequest(Verb.GET, url);
        oauth.signRequest(token, request);
        //TODO make sure this uses the correct encoding
        Response response = request.send();
        return new JSONArray(response.getBody());
    }
}
