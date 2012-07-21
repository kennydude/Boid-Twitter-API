package com.teamboid.twitterapi.search;

import com.teamboid.twitterapi.status.GeoLocation;

import java.util.Calendar;

/**
 * Represents a Tweet held in search results.
 */
public interface Tweet {

    Calendar getCreatedAt();

    String getProfileImageUrl();

    long getFromUserId();

    String getFromUser();

    String getText();

    long getToUserId();

    long getId();

    GeoLocation getGeo();

    String getIsoLanguageCode();

    String getSource();
}
