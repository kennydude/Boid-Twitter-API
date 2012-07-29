package com.teamboid.twitterapi.status.entity.media;

import com.teamboid.twitterapi.json.JSONArray;
import com.teamboid.twitterapi.json.JSONException;
import com.teamboid.twitterapi.json.JSONObject;

import java.io.Serializable;

/**
 * Handles parsing JSON and assigning values to a {@link MediaEntity} interface.
 */
public class MediaEntityJSON implements MediaEntity, Serializable {

	private static final long serialVersionUID = 6368987139014226315L;

	public MediaEntityJSON(JSONObject json) throws JSONException {
        _id = json.getLong("id");
        _mediaUrl = json.getString("media_url");
        _mediaUrlHttps = json.getString("media_url_https");
        _url = json.getString("url");
        _displayUrl = json.getString("display_url");
        _expandedUrl = json.getString("expanded_url");
        JSONArray indicides = json.getJSONArray("indices");
        _startIndex = indicides.getInt(0);
        _endIndex = indicides.getInt(1);
    }

    private long _id;
    private String _mediaUrl;
    private String _mediaUrlHttps;
    private String _url;
    private String _displayUrl;
    private String _expandedUrl;
    private int _startIndex;
    private int _endIndex;

    /**
     * {@inheritDoc}
     */
    @Override
    public long getId() { return _id; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMediaUrl(MediaSize size) {
        if(size != MediaSize.MEDIUM) {
            return _mediaUrl + ":" + size.name().toLowerCase();
        } else return _mediaUrl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMediaUrlHttps(MediaSize size) { return _mediaUrlHttps; }

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
