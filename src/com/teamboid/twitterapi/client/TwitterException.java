package com.teamboid.twitterapi.client;

import com.teamboid.twitterapi.json.JSONObject;

/**
 * Represents a TwitterException, wrapping an error message returned from Twitter in JSON formatting.
 *
 * @author Aidan Follestad
 */
public class TwitterException extends Exception {

	private static final long serialVersionUID = -3230695323511824754L;

	public TwitterException(String message) {
        super(message);
    }
    public TwitterException(JSONObject json, int httpCode) {
        super(json.optString("error"));
        _request = json.optString("request");
        _httpCode = httpCode;
    }

    private String _request;
    private int _httpCode;

    /**
     * Gets the URL of the request made that caused this error to occur.
     */
    public String getRequest() { return _request; }

    /**
     * Gets the HTTP status code if the error happened while requesting network data.
     */
    public int getStatusCode() { return _httpCode; }

    @Override
    public String toString() {
        return "\n[MESSAGE]: " + getMessage() + "\n[REQUEST]: " + _request;
    }
}
