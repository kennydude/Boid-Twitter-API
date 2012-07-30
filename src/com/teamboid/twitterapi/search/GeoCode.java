package com.teamboid.twitterapi.search;

import com.teamboid.twitterapi.status.GeoLocation;

import java.io.Serializable;

/**
 * Used to perform a {@link SearchQuery} to get search results near a specific location.
 * @author Aidan Follestad
 */
public class GeoCode implements Serializable {

	private static final long serialVersionUID = 5237908361480391826L;

	private GeoCode(GeoLocation location, int distance, DistanceUnit unit) {
        _location = location;
        _distance = distance;
        _unit = unit;
    }

    private GeoLocation _location;
    private int _distance;
    private DistanceUnit _unit;

    /**
     * Craetes a GeoCode instance.
     * @param location The location, containing the latitude and longitude of which to get tweets nearby.
     * @param distance The maximum distance from the specified location to get tweets near.
     * @param unit Whether the distance is in miles or kilometers.
     */
    public static GeoCode create(GeoLocation location, int distance, DistanceUnit unit) {
        return new GeoCode(location, distance, unit);
    }

    public static enum DistanceUnit {
        MI, KM
    }

    /**
     * Gets the latitude of the coordinates to get tweets near.
     */
    public Double getLatitude() { return _location.getLatitude(); }

    /**
     * Gets the longitude of the coordinates to get tweets near.
     */
    public Double getLongitude() { return _location.getLongitude(); }

    /**
     * Gets the maximum distance from the specified location to get tweets near.
     */
    public int getDistance() { return _distance; }

    /**
     * Gets whether the distance is in miles or kilometers.
     * @return
     */
    public DistanceUnit getUnit() { return _unit; }
}
