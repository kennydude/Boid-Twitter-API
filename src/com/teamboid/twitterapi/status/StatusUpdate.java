package com.teamboid.twitterapi.status;

import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Status update, passed to {@link com.teamboid.twitterapi.client.Twitter}.updateStatus().
 *
 * @author Aidan Follestad
 */
public class StatusUpdate {

    private StatusUpdate(String status, long inReplyTo) {
        _status = status;
        _inReplyToStatusId = inReplyTo;
    }

    private String _status;
    private long _inReplyToStatusId;
    private GeoLocation _location;
    private String _placeId;
    private boolean _displayCoordinates;
    private File _media;

    /**
     * Builds a StatusUpdate instance to pass in {@link com.teamboid.twitterapi.client.Twitter}.updateStatus().
     * @param status The text of your status update, typically up to 140 characters. URL encode as necessary. t.co link wrapping may effect character counts.
     * @return a prepared StatusUpdate instance.
     */
    public static StatusUpdate create(String status) { return new StatusUpdate(status, 0); }

    /**
     * Builds a StatusUpdate instance to pass in {@link com.teamboid.twitterapi.client.Twitter}.updateStatus().
     * @param status The text of your status update, typically up to 140 characters. URL encode as necessary. t.co link wrapping may effect character counts.
     * @param inReplyToStatusId The ID of an existing status that the update is in reply to.
     * @return a prepared StatusUpdate instance.
     */
    public static StatusUpdate create(String status, long inReplyToStatusId) {
        return new StatusUpdate(status, inReplyToStatusId);
    }

    /**
     * Attaches an image to the status update.
     * @param file The picture file to attach.
     */
    public StatusUpdate setMedia(File file) {
        _media = file;
        return this;
    }

    /**
     * Attaches location to the status update.
     * <br/><b>NOTE</b>: Attaching your location will only work if the current account has enabled
     * location by checking "Add location to my Tweets" in their account settings on Twitter's website.
     * @param location The coordinates to attach.
     */
    public StatusUpdate setLocation(GeoLocation location) {
        _location = location;
        return this;
    }

    /**
     * Sets whether or not to display your exact coordinates (when location is also attached) as opposed to your location relative to places.
     */
    public StatusUpdate setDisplayCoordinates(boolean displayCoordinates) {
        _displayCoordinates = displayCoordinates;
        return this;
    }

    public StatusUpdate setPlaceId(String placeId) {
        _placeId = placeId;
        return this;
    }

    public String getStatus() { return _status; }

    public long getInReplyToStatusId() { return _inReplyToStatusId; }

    public GeoLocation getLocation() { return _location; }

    public String getPlaceId() { return _placeId; }

    public boolean getDisplayCoordinates() { return _displayCoordinates; }

    public File getMedia() { return _media; }

    /**
     * Builds the body parameters of an HTTP post.
     */
    public List<BasicNameValuePair> getBodyParams() {
        ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        pairs.add(new BasicNameValuePair("status", _status));
        if(_inReplyToStatusId > 0) {
            pairs.add(new BasicNameValuePair("in_reply_to_status_id", Long.toString(_inReplyToStatusId)));
        }
        if(_location != null) {
            pairs.add(new BasicNameValuePair("lat", Double.toString(_location.getLatitude())));
            pairs.add(new BasicNameValuePair("long", Double.toString(_location.getLongitude())));
        }
        if(_placeId != null) {
            pairs.add(new BasicNameValuePair("place_id", _placeId));
        }
        if(_displayCoordinates) {
            pairs.add(new BasicNameValuePair("display_coordinates", Boolean.toString(_displayCoordinates)));
        }
        return pairs;
    }
}