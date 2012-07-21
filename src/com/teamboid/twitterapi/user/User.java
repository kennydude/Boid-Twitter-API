package com.teamboid.twitterapi.user;

import java.util.Calendar;

/***
 * Represents a profile on Twitter, this is the basic template of a User, the actual base class that
 * handles parsing of JSON and assigning values is {@link UserJSON}
 *
 * @author Aidan Follestad
 */
public interface User {

    long getId();

    String getName();

    String getScreenName();

    Calendar getCreatedAt();

    String getLocation();

    String getProfileImageUrl();

    String getUrl();

    boolean isVerified();

    boolean isProtected();

    boolean isTranslator();

    long getFriendsCount();

    long getFollowersCount();

    long getStatusCount();

    String getLanguage();

    String getDescription();

    FollowingType getFollowingType();

    void setFollowingType(FollowingType type);

    long getFavoritesCount();
}