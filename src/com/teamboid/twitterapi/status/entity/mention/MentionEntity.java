package com.teamboid.twitterapi.status.entity.mention;

import java.io.Serializable;

/**
 * Represents a media entity held inside of a {@link com.teamboid.twitterapi.status.Status} object.
 *
 * @author Aidan Follestad
 */
public interface MentionEntity extends Serializable {

    /**
     * The User ID
     */
    long getId();

    /**
     * The User screen name
     */
    String getScreenName();

    /**
     * The User's full name.
     */
    String getName();

    /**
     * The starting index of the character positions the mention was extracted from.
     */
    int getStartIndex();

    /**
     * The ending index of the character positions the mention was extracted from.
     */
    int getEndIndex();
}
