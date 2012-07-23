package com.teamboid.twitterapi.entity.media;

import com.teamboid.twitterapi.json.JSONArray;
import com.teamboid.twitterapi.json.JSONException;
import com.teamboid.twitterapi.json.JSONObject;

/**
 * Handles parsing JSON and assigning values to a {@link MediaEntity} interface.
 */
public class MediaEntityJSON implements MediaEntity {

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

    @Override
    public long getId() { return _id; }

    @Override
    public String getMediaUrl(MediaSize size) {
        if(size != MediaSize.MEDIUM) {
            return _mediaUrl + ":" + size.name().toLowerCase();
        } else return _mediaUrl;
   }

    @Override
    public String getMediaUrlHttps(MediaSize size) { return _mediaUrlHttps; }

    @Override
    public String getUrl() { return _url; }

    @Override
    public String getDisplayUrl() { return _displayUrl; }

    @Override
    public String getExpandedUrl() { return _expandedUrl; }

    @Override
    public int getStartIndex() { return _startIndex; }

    @Override
    public int getEndIndex() { return _endIndex; }
}
