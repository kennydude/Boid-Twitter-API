package com.teamboid.twitterapi.client;

import com.teamboid.twitterapi.status.Status;
import com.teamboid.twitterapi.status.StatusJSON;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class TwitterBase implements Twitter {

    @Override
    public Status[] getPublicTimeline() throws Exception {
        return StatusJSON.createStatusList(getArray("http://api.twitter.com/1/statuses/public_timeline.json?include_entities=true"));
    }

    private JSONObject get(String url) throws Exception {
        HttpClient cl = new DefaultHttpClient();
        HttpResponse resp = cl.execute(new HttpGet(url));
        return new JSONObject(EntityUtils.toString(resp.getEntity()));
    }
    private JSONArray getArray(String url) throws Exception {
        HttpClient cl = new DefaultHttpClient();
        HttpResponse resp = cl.execute(new HttpGet(url));
        return new JSONArray(EntityUtils.toString(resp.getEntity()));
    }
}
