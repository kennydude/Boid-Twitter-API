package com.teamboid.twitterapi.config;

import com.teamboid.twitterapi.json.JSONObject;

import java.io.Serializable;

/**
 * @author Aidan Follestad
 */
public class TwitterAPIConfigJSON implements TwitterAPIConfig, Serializable {

	private static final long serialVersionUID = -732383731301789034L;

	public TwitterAPIConfigJSON(JSONObject json) {
        _charsPerMedia = json.optInt("characters_reserved_per_media");
        _maxMedia = json.optInt("max_media_per_upload");
        _photoSizeLimit = json.optLong("photo_size_limit");
        _shortUrlLength = json.optInt("short_url_length");
        _shortUrlHttpsLength = json.optInt("short_url_length_https");
    }

    private int _charsPerMedia;
    private int _maxMedia;
    private long _photoSizeLimit;
    private int _shortUrlLength;
    private int _shortUrlHttpsLength;

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCharactersReservedPerMedia() { return _charsPerMedia; }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxMediaPerUpload() { return _maxMedia; }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getPhotoSizeLimit() { return _photoSizeLimit; }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getShortUrlLength() { return _shortUrlLength; }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getShortUrlLengthHttps() { return _shortUrlHttpsLength; }
}
