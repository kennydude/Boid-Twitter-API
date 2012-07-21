package com.teamboid.twitterapi.status;

/**
 * Represents a description of a physical place in the world, contained in a {@link Status} object.
 */
public interface Place {

    String getName();

    String getStreetAddress();

    String getCountryCode();

    String getId();

    String getCountry();

    String getPlaceType();

    String getURL();

    String getFullName();

    String getBoundingBoxType();

    GeoLocation[][] getBoundingBoxCoordinates();

    String getGeometryType();

    GeoLocation[][] getGeometryCoordinates();

    Place[] getContainedWithIn();
}
