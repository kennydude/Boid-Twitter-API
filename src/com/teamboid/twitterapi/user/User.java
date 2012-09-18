package com.teamboid.twitterapi.user;

import java.io.Serializable;
import java.util.Calendar;

/***
 * Represents a profile on Twitter, this is the basic template of a User, the actual base class that
 * handles parsing of JSON and assigning values is {@link UserJSON}
 *
 * @author Aidan Follestad
 */
public interface User extends Serializable {

    /**
     * Gets the ID of the user.
     */
    long getId();

    /**
     * Gets the real name of the user (e.g. "Aidan Follestad" or "Boid for Twitter").
     */
    String getName();

    /**
     * Gets the screen name of the user (e.g. afollestad or boidapp).
     */
    String getScreenName();

    /**
     * Gets the date that the user created their Twitter profile.
     */
    Calendar getCreatedAt();

    /**
     * Gets the location in the world that the user has specified they live at.
     */
    String getLocation();

    /**
     * Gets the URL to the profile picture of the user.
     */
    String getProfileImageUrl();

    /**
     * Gets the URL of the user's profile.
     */
    String getUrl();

    /**
     * Gets whether or not the user is verified on Twitter.
     */
    boolean isVerified();

    /**
     * Gets whether or not the user is protected (you must request to follow them before you can see their tweets).
     */
    boolean isProtected();

    /**
     * Gets whether or not the user has worked as a translator for Twitter's website.
     */
    boolean isTranslator();

    /**
     * Gets the number of people the user follows.
     */
    long getFriendsCount();

    /**
     * Gets the number of people that follow the user,
     */
    long getFollowersCount();

    /**
     * Gets the total number of tweets composed by this user.
     */
    long getStatusCount();

    /**
     * Gets the locale code of the language the user speaks.
     */
    String getLanguage();

    /**
     * Gets the user's profile description.
     * @return
     */
    String getDescription();

    /**
     * Gets whether or not the authenticated user follows this user, or if there's a pending follow request.
     * This value can be manually set using {@link User#setFollowingType(FollowingType)} if you need to do so.
     */
    FollowingType getFollowingType();

    /**
     * Manually sets whether or not the authenticated user follows this user, this doesn't follow or unfollow them.
     * This is useful for re-inserting a user into a list adapter on Android to update indicators.
     */
    void setFollowingType(FollowingType type);

    /**
     * Gets the number of tweets that this user has favorited.
     */
    long getFavoritesCount();
    
    /**
     * Gets a URL to the profile's background image.
     */
    String getProfileBackgroundImageUrl();
    
    /**
     * Gets the HTML color code for the profile's background (good backup if there's no background image
     * from {@link #getProfileBackgroundImageUrl()}).
     */
    String getProfileBackgroundColor();
    
    /**
     * Get the web version of the profile banner or null if one could not be found
     */
    String getProfileBannerWeb();
    
    /**
     * Get the mobile version of the profile banner or null if one could not be found
     */
    String getProfileBannerMobile();
}