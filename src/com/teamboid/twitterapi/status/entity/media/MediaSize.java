package com.teamboid.twitterapi.status.entity.media;

/**
 * Can be used in {@link MediaEntity#getMediaUrl(MediaSize)} or {@link MediaEntity#getMediaUrlHttps(MediaSize)}
 * to specify the size of the image to get the media URL for.
 */
public enum MediaSize {
    THUMB,
    SMALL,
    MEDIUM,
    LARGE
}
