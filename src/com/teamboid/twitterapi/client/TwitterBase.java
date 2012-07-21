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
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;

public class TwitterBase extends RequestHandler implements Twitter {

    @Override
    public void getAuthorization(String consumerKey, String consumerSecret,
                                 String accessToken, String accessSecret) {
        super.token = new Token(accessToken, accessSecret);
        super.oauth = new ServiceBuilder().provider(TwitterApi.class).apiKey(consumerKey)
                .apiSecret(consumerSecret).build();
    }

    @Override
    public Status[] getPublicTimeline() throws Exception {
        return StatusJSON.createStatusList(getArray("https://api.twitter.com/1/statuses/public_timeline.json?include_entities=true"));
    }

    @Override
    public Status[] getHomeTimeline(Paging paging) throws Exception {
        String url = "https://api.twitter.com/1/statuses/home_timeline.json?include_entities=true&include_rts=true";
        if(paging != null) url += paging.getUrlString();
        return StatusJSON.createStatusList(getArrayAuth(url));
    }
}
