package com.teamboid.twitterapi.search;

import com.teamboid.twitterapi.status.Place;
import com.teamboid.twitterapi.status.entity.hashtag.HashtagEntity;
import com.teamboid.twitterapi.status.entity.media.MediaEntity;
import com.teamboid.twitterapi.status.entity.mention.MentionEntity;
import com.teamboid.twitterapi.status.entity.url.UrlEntity;
import com.teamboid.twitterapi.status.GeoLocation;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Represents a Tweet held in search results.
 *
 * @author Aidan Follestad
 */
public interface Tweet extends Serializable {

    /**
     * Gets the date and time that the tweet was composed.
     */
    Calendar getCreatedAt();

    /**
     * Gets the URL to the profile image of the user that created the tweet.
     */
    String getProfileImageUrl();

    /**
     * Gets the ID of the user that created the tweet.
     */
    long getFromUserId();

    /**
     * Gets the screen name of the user that created the tweet.
     */
    String getFromUser();

    /**
     * Gets the text content held in the tweet.
     */
    String getText();

    /**
     * Gets the ID of the user that created the tweet that this tweet is in reply to.
     */
    long getToUserId();

    /**
     * Gets the ID of the tweet.
     */
    long getId();

    /**
     * Gets the location attached to the tweet.
     */
    GeoLocation getGeo();

    /**
     * Gets the locale code of the language used in the tweet.
     */
    String getIsoLanguageCode();

    /**
     * Gets the application that was used to create the Tweet. Most apps display text
     * like "via source"; this version contains an HTML anchor (e.g., <a href="URL">name</a>).
     * Use {@link com.teamboid.twitterapi.status.Status#getSourcePlain()} if you want just the name.
     */
    String getSource();

    /**
     * Gets the application that was used to create the Tweet. Most apps display text like
     * "via source"; for an example, the source could be 'Boid for Android'.
     */
    String getSourcePlain();

    /**
     * Gets the number of recent retweets of this tweet.
     */
    int getRecentRetweets();

    /**
     * Gets the result type used when the search was performed.
     */
    SearchQuery.ResultType getResultType();

    /**
     * Gets the URL entities contained in the Tweet; URL entities are useful
     * for expanding t.co URLs.
     */
    UrlEntity[] getUrlEntities();

    /**
     * Gets the URL entities contained in a Tweet; Media entities are useful
     * for extracting the images held in a tweet.
     */
    MediaEntity[] getMediaEntities();

    /**
     * Gets the hashtag entities contained in a Tweet; Hashtag entities are useful
     * for extracting hashtags without using methods like regular expressions.
     */
    HashtagEntity[] getHashtagEntities();

    /**
     * Gets the mention entities contained in a Tweet; Mention entities are useful
     * for extracting mentions without using methods like regular expressions.
     */
    MentionEntity[] getMentionEntities();

    /**
     * Gets the Place, representing the non-coordinate location of the composer of the tweet.
     * @return
     */
    Place getPlace();

    /**
     * Gets the exact location, representing the coordiantes of the compoesr of the tweet.
     * @return
     */
    GeoLocation getGeoLocation();
}
