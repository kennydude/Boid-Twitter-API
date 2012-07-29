package com.teamboid.twitterapi.list;

import com.teamboid.twitterapi.user.User;

import java.io.Serializable;

/**
 * Represents a list on Twitter, created by you or another user.
 * @author
 */
public interface UserList extends Serializable {

    String getSlug();

    String getName();

    String getUri();

    long getSubscriberCount();

    long getMemberCount();

    UserListMode getMode();

    long getId();

    String getFullName();

    String getDescription();

    User getUser();

    boolean isFollowing();
}
