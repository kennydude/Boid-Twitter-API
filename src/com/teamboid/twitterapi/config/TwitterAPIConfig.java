package com.teamboid.twitterapi.config;

import java.io.Serializable;

/**
 * @author Aidan Follestad
 */
public interface TwitterAPIConfig extends Serializable {

    /**
     * Gets how many characters a {@link com.teamboid.twitterapi.status.StatusUpdate} can contain when it
     * also has media attached (it compensates for the pic.twitter.com URL, when it's wrapped with t.co).
     */
    int getCharactersReservedPerMedia();

    /**
     * Gets the maximum number of pictures that can be attached to a Tweet, for now it's always 1.
     */
    int getMaxMediaPerUpload();

    /**
     * Gets the size limit in bytes of photos that can be uploaded to Twitter in a {@link com.teamboid.twitterapi.status.StatusUpdate}.
     * @return
     */
    long getPhotoSizeLimit();

    /**
     * Gets the length of a shortened URL (that's wrapped with t.co).
     */
    int getShortUrlLength();

    /**
     * Gets the length of a shortened HTTPS URL (that's wrapped with t.co).
     */
    int getShortUrlLengthHttps();
}
