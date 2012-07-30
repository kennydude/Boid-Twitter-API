package com.teamboid.twitterapi.trend;

import java.io.Serializable;

/**
 * Represents a single Trend, with a name, query, etc.
 * @author Aidan Follestad
 */
public interface Trend extends Serializable {

    //TODO
    void getEvents();

    //TODO
    void getPromotedContent();

    /**
     * Gets the name of the trend, what should be displayed to a user.
     */
    String getName();

    /**
     * Usually the same as {@link #getName()}, but is URL encoded.
     */
    String getQuery();

    /**
     * Gets the URL you would use to open Twitter's search page in a web browser for this trend.
     */
    String getUrl();
}