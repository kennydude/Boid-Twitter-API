package com.teamboid.twitterapi.status;

import com.teamboid.twitterapi.client.HttpParam;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Status update, passed to {@link com.teamboid.twitterapi.client.Twitter}.updateStatus().
 *
 * @author Aidan Follestad
 */
public class StatusUpdate implements Serializable {

	private static final long serialVersionUID = 4396080881052608550L;

	private StatusUpdate(String status) {
        _status = status;
    }

    private String _status;
    private long _inReplyToStatusId;
    private GeoLocation _location;
    private String _placeId;
    private boolean _displayCoordinates;
    private File _media;
    private InputStream _mediaStream;
    private String _mediaStreamName;

    /**
     * Builds a StatusUpdate instance to pass in {@link com.teamboid.twitterapi.client.Twitter}.updateStatus().
     * @param status The text of your status update, typically up to 140 characters. URL encode as necessary. t.co link wrapping may effect character counts.
     * @return a prepared StatusUpdate instance.
     */
    public static StatusUpdate create(String status) { return new StatusUpdate(status); }

    /**
     * Attaches an image to the status update.
     * @param file The picture file to attach.
     */
    public StatusUpdate setMedia(File file) {
        _media = file;
        return this;
    }
    
    /**
     * Attaches an image to the status update.
     * @param file The picture file to attach.
     */
    public StatusUpdate setMedia(InputStream stream, String fileName) {
        _mediaStream = stream;
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

    /**
     * Sets the ID of the status that this tweet is replying to, connecting them in conversation.
     * @param id The ID of another tweet that this tweet is replying to.
     */
    public StatusUpdate setInReplyToStatusId(long id) {
        _inReplyToStatusId = id;
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
    
    public InputStream getMediaStream() { return _mediaStream; }
    public String getMediaStreamName() { return _mediaStreamName; }

    /**
     * Builds the body parameters of an HTTP post.
     */
    public List<HttpParam> getBodyParams() {
        ArrayList<HttpParam> pairs = new ArrayList<HttpParam>();
        pairs.add(new HttpParam("status", _status));
        pairs.add(new HttpParam("include_entities", "true"));
        if(_inReplyToStatusId > 0) {
            pairs.add(new HttpParam("in_reply_to_status_id", Long.toString(_inReplyToStatusId)));
        }
        if(_location != null) {
            pairs.add(new HttpParam("lat", Double.toString(_location.getLatitude())));
            pairs.add(new HttpParam("long", Double.toString(_location.getLongitude())));
        }
        if(_placeId != null) {
            pairs.add(new HttpParam("place_id", _placeId));
        }
        if(_displayCoordinates) {
            pairs.add(new HttpParam("display_coordinates", Boolean.toString(_displayCoordinates)));
        }
        return pairs;
    }
}