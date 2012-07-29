package com.teamboid.twitterapi.status.entity.media;

import java.io.Serializable;

/**
 * Represents a media entity held inside of a {@link com.teamboid.twitterapi.status.Status} object.
 *
 * @author Aidan Follestad
 */
public interface MediaEntity extends Serializable {

    /**
     * The media ID
     */
    long getId();

    /**
     * The URL of the media file (use getSizes() for available sizes).
     */
    String getMediaUrl(MediaSize size);

    /**
     * Same as getMediaUrl(), but with using HTTPS with SSL encryption.
     */
    String getMediaUrlHttps(MediaSize size);

    /**
     * The media URL that was extracted.
     */
    String getUrl();

    /**
     * Not a URL but a string to display instead of the media URL.
     * @return
     */
    String getDisplayUrl();

    /**
     * The fully resolved media URL.
     * @return
     */
    String getExpandedUrl();

    /**
     * The starting index of the character positions the media was extracted from.
     * @return
     */
    int getStartIndex();

    /**
     * The ending index of the character positions the media was extracted from.
     * @return
     */
    int getEndIndex();
}
