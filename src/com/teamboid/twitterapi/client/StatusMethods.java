package com.teamboid.twitterapi.client;

import com.teamboid.twitterapi.status.Status;
import com.teamboid.twitterapi.user.User;

public interface StatusMethods {

    Status showStatus(long statusId) throws Exception;

    Status retweetStatus(long statusId) throws Exception;

    Status destroyStatus(long statusId) throws Exception;

    User showUser(long userId) throws Exception;

    User showUser(String screenName) throws Exception;

    Status[] getRetweets(long statusId, int count) throws Exception;

    User[] getRetweetedBy(long statusId, Paging paging) throws Exception;
}
