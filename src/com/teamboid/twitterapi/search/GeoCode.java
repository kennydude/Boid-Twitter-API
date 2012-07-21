package com.teamboid.twitterapi.search;

import com.teamboid.twitterapi.status.GeoLocation;

/**
 * @author Aidan Follestad
 */
public class GeoCode {

    public GeoCode(GeoLocation location, int distance, DistanceUnit unit) {
        _location = location;
        _distance = distance;
        _unit = unit;
    }

    private GeoLocation _location;
    private int _distance;
    private DistanceUnit _unit;

    public static enum DistanceUnit {
        MI, KM
    }

    public Double getLatitude() { return _location.getLatitude(); }

    public Double getLongitude() { return _location.getLongitude(); }

    public int getDistance() { return _distance; }

    public DistanceUnit getUnit() { return _unit; }
}
