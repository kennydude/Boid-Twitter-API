package com.teamboid.twitterapi.client;

import com.teamboid.twitterapi.status.Status;

public interface TimelineMethods {

    Status[] getPublicTimeline() throws Exception;

    Status[] getHomeTimeline(Paging paging) throws Exception;

    Status[] getMentions(Paging paging) throws Exception;

    Status[] getUserTimeline(Paging paging) throws Exception;

    Status[] getUserTimeline(long userId, Paging paging) throws Exception;

    Status[] getUserTimeline(String screenName, Paging paging) throws Exception;

    Status[] getRetweetsOfMe(Paging paging) throws Exception;
}
