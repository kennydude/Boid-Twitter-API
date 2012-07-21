package com.teamboid.twitterapi.client;

import com.teamboid.twitterapi.search.SearchQuery;
import com.teamboid.twitterapi.search.SearchResult;
import com.teamboid.twitterapi.search.SearchResultJSON;
import com.teamboid.twitterapi.search.Tweet;
import com.teamboid.twitterapi.status.Status;
import com.teamboid.twitterapi.status.StatusJSON;
import com.teamboid.twitterapi.user.User;
import com.teamboid.twitterapi.user.UserJSON;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import java.net.URLEncoder;

public class TwitterBase extends RequestHandler implements Twitter {

    @Override
    public String getAccessToken() {
        if(super.token == null) return null;
        return super.token.getToken();
    }

    @Override
    public String getAccessSecret() {
        if(super.token == null) return null;
        return super.token.getSecret();
    }

    @Override
    public Status[] getPublicTimeline() throws Exception {
        return StatusJSON.createStatusList(getArray(Urls.PUBLIC_TIMELINE));
    }

    @Override
    public Status[] getHomeTimeline(Paging paging) throws Exception {
        String url = Urls.HOME_TIMELINE;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArrayAuth(url));
    }

    @Override
    public Status[] getMentions(Paging paging) throws Exception {
        String url = Urls.MENTIONS;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArrayAuth(url));
    }

    @Override
    public Status[] getUserTimeline(Paging paging) throws Exception {
        String url = Urls.USER_TIMELINE;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArrayAuth(url));
    }

    @Override
    public Status[] getUserTimeline(long userId, Paging paging) throws Exception {
        String url = Urls.USER_TIMELINE + "&user_id=" + userId;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArrayAuth(url));
    }

    @Override
    public Status[] getUserTimeline(String screenName, Paging paging) throws Exception {
        String url = Urls.USER_TIMELINE + "&screen_name=" + screenName;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArrayAuth(url));
    }

    @Override
    public Status[] getRetweetsOfMe(Paging paging) throws Exception {
        String url = Urls.RETWEETS_OF_ME;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArrayAuth(url));
    }

    @Override
    public Status showStatus(long statusId) throws Exception {
        return new StatusJSON(getAuth(Urls.SHOW_STATUS.replace("{id}", Long.toString(statusId))));
    }

    @Override
    public Status retweetStatus(long statusId) throws Exception {
        return new StatusJSON(getAuth(Urls.RETWEET_STATUS.replace("{id}", Long.toString(statusId))));
    }

    @Override
    public Status destroyStatus(long statusId) throws Exception {
        return new StatusJSON(getAuth(Urls.DESTROY_STATUS.replace("{id}", Long.toString(statusId))));
    }

    @Override
    public User showUser(long userId) throws Exception {
        return new UserJSON(getAuth(Urls.SHOW_USER + "&user_id=" + Long.toString(userId)));
    }

    @Override
    public User showUser(String screenName) throws Exception {
        return new UserJSON(getAuth(Urls.SHOW_USER + "&screen_name=" + screenName));
    }

    @Override
    public Status[] getRetweets(long statusId, int count) throws Exception {
        String url = Urls.RETWEETS.replace("{id}", Long.toString(statusId));
        if(count > 0) url += "&count=" + count;
        return StatusJSON.createStatusList(getArrayAuth(url));
    }

    @Override
    public User[] getRetweetedBy(long statusId, Paging paging) throws Exception {
        String url = Urls.RETWEETED_BY.replace("{id}", Long.toString(statusId));
        if(paging != null) url += paging.getUrlString('?', false);
        return UserJSON.createUserList(getArrayAuth(url));
    }

    @Override
    public SearchResult search(SearchQuery query) throws Exception {
        return new SearchResultJSON(getAuth(query.getUrl()));
    }

    @Override
    public User[] searchUsers(String query, int page, int perPage) throws Exception {
        String url = Urls.SEARCH_USERS + "&query=" + URLEncoder.encode(query, "UTF8");
        if(page > 0) url += "&page=" + page;
        if(perPage > 0) url += "&per_page=" + perPage;
        return UserJSON.createUserList(getArrayAuth(url));
    }
}