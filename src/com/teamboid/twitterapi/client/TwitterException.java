package com.teamboid.twitterapi.client;

import org.json.JSONObject;

/**
 * Represents a TwitterException, wrapping an error message returned from Twitter in JSON formatting.
 *
 * @author Aidan Follestad
 */
public class TwitterException extends Exception {

    public TwitterException(String message) {
        super(message);
    }
    public TwitterException(JSONObject json) {
        super(json.optString("error"));
        _request = json.optString("request");
    }

    private String _request;

    public String getRequest() { return _request; }

    @Override
    public String toString() {
        return "\n[MESSAGE]: " + getMessage() + "\n[REQUEST]: " + _request;
    }
}
