package com.teamboid.twitterapi.entity.url;

/**
 * Represents a URL entity held inside of a {@link com.teamboid.twitterapi.status.Status} object.
 * @author Aidan Follestad
 */
public interface UrlEntity {

    /**
     * The URL that was extracted.
     */
    String getUrl();

    /**
     * (only for t.co links) Not a URL but a string to display instead of the URL.
     */
    String getDisplayUrl();

    /**
     * (only for t.co links) The fully resolved URL.
     */
    String getExpandedUrl();

    /**
     * The starting index of the character positions the URL was extracted from.
     */
    int getStartIndex();

    /**
     * The ending index of the character positions the URL was extracted from.
     */
    int getEndIndex();
}