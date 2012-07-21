package com.teamboid.twitterapi.status;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents a coordinate with longitude and latitude.
 */
public class GeoLocation {

    public GeoLocation(double _latitude, double _longitude) {
        latitude = _latitude;
        longitude = _longitude;
    }
    public GeoLocation(JSONObject json) throws JSONException {
        String coordinates = json.getString("coordinates");
        coordinates = coordinates.substring(1, coordinates.length() - 1);
        String[] point = coordinates.split(",");
        latitude = Double.parseDouble(point[0]);
        longitude = Double.parseDouble(point[1]);
    }

    private double latitude;
    private double longitude;

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }
}
