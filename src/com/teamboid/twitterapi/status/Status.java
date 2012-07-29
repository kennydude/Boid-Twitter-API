package com.teamboid.twitterapi.status;

import com.teamboid.twitterapi.status.entity.hashtag.HashtagEntity;
import com.teamboid.twitterapi.status.entity.media.MediaEntity;
import com.teamboid.twitterapi.status.entity.mention.MentionEntity;
import com.teamboid.twitterapi.status.entity.url.UrlEntity;
import com.teamboid.twitterapi.user.User;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Represents a Tweet on Twitter.
 * @author Aidan Follestad
 */
public interface Status extends Serializable {

    /**
     * Gets the ID of the tweet.
     */
    long getId();

    /**
     * Gets whether or not the authenticated user has favorited this tweet.
     * @return
     */
    boolean isFavorited();

    /**
     * This does not actually favorite the Tweet on Twitter, this function is used in the library.
     */
    void setFavorited(boolean isFavorited);

    /**
     * Gets the date and time the tweet was composed.
     */
    Calendar getCreatedAt();

    /**
     * Gets whether or not the Tweet has been truncated.
     */
    boolean isTruncated();

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
     * Gets the {@link User} object of the user that created the tweet.
     */
    User getUser();

    /**
     * Gets the main content contained in the tweet, will be 140 characters or less.
     */
    String getText();

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
     * Gets the ID of the status that this tweet is in reply to (if any).
     */
    long getInReplyToStatusId();

    /**
     * Gets the ID of the user that created the tweet that this tweet is in reply to.
     */
    long getInReplyToUserId();

    /**
     * Gets the screen name of the user that created the tweet that this tweet is in reply to.
     */
    String getInReplyToScreenName();

    /**
     * Gets the number of times this tweet has been retweeted by other users.
     */
    long getRetweetCount();

    /**
     * Gets whether or not the authenticated user has retweeted this tweet.
     */
    boolean isRetweet();

    /**
     * If {@link #isRetweet()} returns true, returns the retweeted status.
     */
    Status getRetweetedStatus();

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

    /**
     * Gets whether or not the authenticated user has retweeted this status.
     */
    boolean isRetweetedByMe();

    /**
     * Gets a {@link Status} representing your own retweet of this status (if {@link #isRetweetedByMe()} returns true).
     */
    Status getMyRetweetedStatus();
}
