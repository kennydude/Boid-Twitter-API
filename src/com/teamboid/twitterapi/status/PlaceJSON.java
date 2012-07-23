package com.teamboid.twitterapi.status;

import com.teamboid.twitterapi.json.JSONArray;
import com.teamboid.twitterapi.json.JSONException;
import com.teamboid.twitterapi.json.JSONObject;

/**
 * Handles parsing JSON and assigning values to a {@link Place} interface.
 */
public class PlaceJSON implements Place {

    /**
     * This initialization code is also taken from Twitter4J and slightly modified.
     */
    public PlaceJSON(JSONObject json) throws Exception {
        name = json.getString("name");
        streetAddress = json.optString("street_address");
        countryCode = json.getString("country_code");
        id = json.getString("id");
        country = json.getString("country");
        if (!json.isNull("place_type")) {
            placeType = json.getString("place_type");
        } else placeType = json.getString("type");
        url = json.getString("url");
        fullName = json.getString("full_name");
        if (!json.isNull("bounding_box")) {
            JSONObject boundingBoxJSON = json.getJSONObject("bounding_box");
            boundingBoxType = boundingBoxJSON.getString("type");
            JSONArray array = boundingBoxJSON.getJSONArray("coordinates");
            boundingBoxCoordinates = coordinatesAsGeoLocationArray(array);
        }
        if (!json.isNull("geometry")) {
            JSONObject geometryJSON = json.getJSONObject("geometry");
            geometryType = geometryJSON.getString("type");
            JSONArray array = geometryJSON.getJSONArray("coordinates");
            if (geometryType.equals("Point")) {
                geometryCoordinates = new GeoLocation[1][1];
                geometryCoordinates[0][0] = new GeoLocation(array.getDouble(0), array.getDouble(1));
            } else if (geometryType.equals("Polygon")) {
                geometryCoordinates = coordinatesAsGeoLocationArray(array);
            } else {
                // MultiPolygon currently unsupported.
                geometryType = null;
                geometryCoordinates = null;
            }
        }
        if (!json.isNull("contained_within")) {
            JSONArray containedWithInJSON = json.getJSONArray("contained_within");
            containedWithIn = new Place[containedWithInJSON.length()];
            for (int i = 0; i < containedWithInJSON.length(); i++) {
                containedWithIn[i] = new PlaceJSON(containedWithInJSON.getJSONObject(i));
            }
        }
    }

    /**
     * This code was extracted from Twitter4J's source code.
     */
    private GeoLocation[][] coordinatesAsGeoLocationArray(JSONArray coordinates) throws JSONException {
        GeoLocation[][] boundingBox = new GeoLocation[coordinates.length()][];
        for (int i = 0; i < coordinates.length(); i++) {
        JSONArray array = coordinates.getJSONArray(i);
            boundingBox[i] = new GeoLocation[array.length()];
            for (int j = 0; j < array.length(); j++) {
                JSONArray coordinate = array.getJSONArray(j);
                boundingBox[i][j] = new GeoLocation(coordinate.getDouble(1), coordinate.getDouble(0));
            }
        }
        return boundingBox;
    }

    private String name;
    private String streetAddress;
    private String countryCode;
    private String id;
    private String country;
    private String placeType;
    private String url;
    private String fullName;
    private String boundingBoxType;
    private GeoLocation[][] boundingBoxCoordinates;
    private String geometryType;
    private GeoLocation[][] geometryCoordinates;
    private Place[] containedWithIn;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getStreetAddress() {
        return streetAddress;
    }

    @Override
    public String getCountryCode() {
        return countryCode;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String getPlaceType() {
        return placeType;
    }

    @Override
    public String getURL() {
        return url;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getBoundingBoxType() {
        return boundingBoxType;
    }

    @Override
    public GeoLocation[][] getBoundingBoxCoordinates() {
        return boundingBoxCoordinates;
    }

    @Override
    public String getGeometryType() {
        return geometryType;
    }

    @Override
    public GeoLocation[][] getGeometryCoordinates() {
        return geometryCoordinates;
    }

    @Override
    public Place[] getContainedWithIn() {
        return containedWithIn;
    }
}