package com.teamboid.twitterapi.status.entity.url;

import com.teamboid.twitterapi.json.JSONArray;
import com.teamboid.twitterapi.json.JSONException;
import com.teamboid.twitterapi.json.JSONObject;

/**
 * Handles parsing JSON and assigning values to a {@link UrlEntity} interface.
 */
public class UrlEntityJSON implements UrlEntity {

    public UrlEntityJSON(JSONObject json) throws JSONException {
        _url = json.getString("url");
        _displayUrl = json.getString("display_url");
        _expandedUrl = json.getString("expanded_url");
        JSONArray indices = json.getJSONArray("indices");
        _startIndex = indices.getInt(0);
        _endIndex = indices.getInt(1);
    }

    private String _url;
    private String _displayUrl;
    private String _expandedUrl;
    private int _startIndex;
    private int _endIndex;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUrl() { return _url; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDisplayUrl() { return _displayUrl; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getExpandedUrl() { return _expandedUrl; }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStartIndex() { return _startIndex; }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEndIndex() { return _endIndex; }
}