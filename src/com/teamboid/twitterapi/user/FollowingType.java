package com.teamboid.twitterapi.user;

/**
 * Used by {@link User} to specify whether or not the authenticated user follows another user.
 * @author Aidan Follestad
 */
public enum FollowingType {
    /**
     * It's not known whether or not you follow the user, either because an error occured or because it was manually set.
     */
    UNKNOWN,
    /**
     * A following request has been sent, and is currently pending.
     */
    REQUEST_SENT,
    /**
     * You are following the user.
     */
    FOLLOWING,
    /**
     * You are <b>not</b> following the user.
     */
    NOT_FOLLOWING
}
