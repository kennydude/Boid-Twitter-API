package com.teamboid.twitterapi.status.entity.hashtag;

import java.io.Serializable;

/**
 * Represents a hashtag entity held inside of a {@link com.teamboid.twitterapi.status.Status} object.
 *
 * @author Aidan Follestad
 */
public interface HashtagEntity extends Serializable {

    /**
     * The hashtag text.
     */
    String getText();

    /**
     * The starting index of the character positions the hashtag was extracted from.
     */
    int getStartIndex();

    /**
     * The ending index of the character positions the hashtag was extracted from.
     */
    int getEndIndex();
}
