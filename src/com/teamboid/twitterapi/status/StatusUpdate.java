package com.teamboid.twitterapi.status;

import com.teamboid.twitterapi.client.RequestHandler;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Status update, passed to {@link com.teamboid.twitterapi.client.Twitter}.updateStatus().
 *
 * @author Aidan Follestad
 */
public class StatusUpdate {

    private StatusUpdate(String status, long inReplyTo, GeoLocation location, String placeId, boolean displayCoordinates) {
        _status = status;
        _inReplyToStatusId = inReplyTo;
        _location = location;
        _placeId = placeId;
        _displayCoordinates = displayCoordinates;
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
    public static StatusUpdate create(String status) {
        return new StatusUpdate(status, 0, null, null, false);
    }

    /**
     * Builds a StatusUpdate instance to pass in {@link com.teamboid.twitterapi.client.Twitter}.updateStatus().
     * @param status The text of your status update, typically up to 140 characters. URL encode as necessary. t.co link wrapping may effect character counts.
     * @param inReplyToStatusId The ID of an existing status that the update is in reply to.
     * @param location The location this tweet refers to. This parameter will be ignored unless it is inside the range -90.0 to +90.0 (North is positive) inclusive.
     * @param displayCoordinates Whether or not to put a pin on the exact coordinates a tweet has been sent from.
     * @param placeid A place in the world. These IDs can be retrieved from GET geo/reverse_geocode.
     * @return a prepared StatusUpdate instance.
     */
    public static StatusUpdate create(String status, long inReplyToStatusId, GeoLocation location, boolean displayCoordinates, String placeid) {
        return new StatusUpdate(status, inReplyToStatusId, location, placeid, displayCoordinates);
    }

    /**
     * Attaches an image to the status update.
     * @param file The picture file to attach.
     */
    public StatusUpdate addMedia(File file) {
        _media = file;
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
        pairs.add(new BasicNameValuePair("status", RequestHandler.encode(_status)));
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