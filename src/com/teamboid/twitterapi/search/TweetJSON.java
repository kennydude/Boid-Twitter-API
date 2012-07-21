package com.teamboid.twitterapi.search;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TweetJSON implements Tweet {

    public TweetJSON(JSONObject json) {

    }

    public static Tweet[] createStatusList(JSONArray array) throws Exception {
        ArrayList<Tweet> toReturn = new ArrayList<Tweet>();
        for(int i = 0; i < array.length(); i++) {
            toReturn.add(new TweetJSON(array.getJSONObject(i)));
        }
        return toReturn.toArray(new Tweet[0]);
    }
}
