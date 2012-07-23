package com.teamboid.twitterapi.status;

import com.teamboid.twitterapi.json.JSONArray;
import com.teamboid.twitterapi.json.JSONException;
import com.teamboid.twitterapi.json.JSONObject;

/**
 * Represents a coordinate with longitude and latitude.
 * @author Aidan Follestad
 */
public class GeoLocation {

    public GeoLocation(double _latitude, double _longitude) {
        latitude = _latitude;
        longitude = _longitude;
    }
    public GeoLocation(JSONObject json) throws JSONException {
        JSONArray coordinates = json.getJSONArray("coordinates");
        latitude = coordinates.getDouble(0);
        longitude = coordinates.getDouble(1);
    }

    private double latitude;
    private double longitude;

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }
}
