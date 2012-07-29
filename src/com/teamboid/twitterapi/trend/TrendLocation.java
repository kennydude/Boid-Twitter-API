package com.teamboid.twitterapi.trend;

import java.io.Serializable;

/**
 * @author Aidan Follestad
 */
public interface TrendLocation extends Serializable {

    int getWoeId();

    String getName();

    String getCountryCode();

    int getParentId();

    String getPlaceTypeName();

    int getPlaceTypeCode();

    String getUrl();

    String getCountry();
}
