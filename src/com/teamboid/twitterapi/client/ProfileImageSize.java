package com.teamboid.twitterapi.client;

/**
 * Used to specify {@link Twitter#getUserProfileImage(String, ProfileImageSize)} image sizes.
 * @author Aidan Follestad
 */
public enum ProfileImageSize {
    /**
     * Undefined. This will be the size the image was originally uploaded in. The filesize of original images can be very big so use this parameter with caution.
     */
    ORIGINAL,
    /**
     * 24px by 24px
     */
    MINI,
    /**
     * 48px by 48px
     */
    NORMAL,
    /**
     * 73px by 73px
     */
    BIGGER
}
