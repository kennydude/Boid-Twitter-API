package com.teamboid.twitterapi.relationship;

/**
 * Represents the relationship between two users on Twitter.
 * @author Aidan Follestad
 */
public interface Relationship {

    /**
     * Returns the source user id
     */
    long getSourceUserId();

    /**
     * Returns the target user id
     */
    long getTargetUserId();

    /**
     * Returns if the source user is blocking the target user
     */
    boolean isSourceBlockingTarget();

    /**
     * Returns the source user screen name
     */
    String getSourceUserScreenName();

    /**
     * Returns the target user screen name
     */
    String getTargetUserScreenName();

    /**
     * Checks if source user is following target user
     */
    boolean isSourceFollowingTarget();

    /**
     * Checks if target user is following source user.<br>
     * This method is equivalent to isSourceFollowedByTarget().
     */
    boolean isTargetFollowingSource();

    /**
     * Checks if source user is being followed by target user
     */
    boolean isSourceFollowedByTarget();

    /**
     * Checks if target user is being followed by source user.<br>
     * This method is equivalent to isSourceFollowingTarget().
     */
    boolean isTargetFollowedBySource();

    /**
     * Checks if the source user has enabled notifications for updates of the target user
     */
    boolean isSourceNotificationsEnabled();
}
