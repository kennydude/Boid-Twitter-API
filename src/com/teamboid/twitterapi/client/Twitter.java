package com.teamboid.twitterapi.client;

import com.teamboid.twitterapi.search.SearchQuery;
import com.teamboid.twitterapi.search.SearchResult;
import com.teamboid.twitterapi.status.Status;
import com.teamboid.twitterapi.user.User;
import org.scribe.oauth.OAuthService;

/**
 * The main class used within this library, used for authenticating an account and performing API actions on Twitter.
 */
public interface Twitter {

    User verifyCredentials() throws Exception;

    //OAuth Methods
    String getAccessToken();

    String getAccessSecret();

    //TIME METHODS
    Status[] getPublicTimeline() throws Exception;

    Status[] getHomeTimeline(Paging paging) throws Exception;

    Status[] getMentions(Paging paging) throws Exception;

    Status[] getUserTimeline(Paging paging) throws Exception;

    Status[] getUserTimeline(long userId, Paging paging) throws Exception;

    Status[] getUserTimeline(String screenName, Paging paging) throws Exception;

    Status[] getRetweetsOfMe(Paging paging) throws Exception;

    //STATUS METHODS
    Status showStatus(long statusId) throws Exception;

    Status retweetStatus(long statusId) throws Exception;

    Status destroyStatus(long statusId) throws Exception;

    User showUser(long userId) throws Exception;

    User showUser(String screenName) throws Exception;

    Status[] getRetweets(long statusId, int count) throws Exception;

    User[] getRetweetedBy(long statusId, Paging paging) throws Exception;

    //SEARCH METHODS
    SearchResult search(SearchQuery query) throws Exception;

    User[] searchUsers(String query, int page, int perPage) throws Exception;
}
