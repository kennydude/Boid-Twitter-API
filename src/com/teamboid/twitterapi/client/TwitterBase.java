package com.teamboid.twitterapi.client;

import com.teamboid.twitterapi.dm.DirectMessage;
import com.teamboid.twitterapi.dm.DirectMessageJSON;
import com.teamboid.twitterapi.search.SearchQuery;
import com.teamboid.twitterapi.search.SearchResult;
import com.teamboid.twitterapi.search.SearchResultJSON;
import com.teamboid.twitterapi.status.Status;
import com.teamboid.twitterapi.status.StatusJSON;
import com.teamboid.twitterapi.status.StatusUpdate;
import com.teamboid.twitterapi.user.User;
import com.teamboid.twitterapi.user.UserJSON;
import org.apache.http.message.BasicNameValuePair;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class TwitterBase extends RequestHandler implements Twitter {

    @Override
    public User verifyCredentials() throws Exception {
        return new UserJSON(getObject(Urls.VERIFY_CREDENTIALS));
    }

    @Override
    public String getAccessToken() {
        return super._accessToken;
    }

    @Override
    public String getAccessSecret() {
        return super._accessSecret;
    }

    @Override
    public Status[] getHomeTimeline(Paging paging) throws Exception {
        String url = Urls.HOME_TIMELINE;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArray(url));
    }

    @Override
    public Status[] getMentions(Paging paging) throws Exception {
        String url = Urls.MENTIONS;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArray(url));
    }

    @Override
    public Status[] getUserTimeline(Paging paging) throws Exception {
        String url = Urls.USER_TIMELINE;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArray(url));
    }

    @Override
    public Status[] getUserTimeline(long userId, Paging paging) throws Exception {
        String url = Urls.USER_TIMELINE + "&user_id=" + userId;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArray(url));
    }

    @Override
    public Status[] getUserTimeline(String screenName, Paging paging) throws Exception {
        String url = Urls.USER_TIMELINE + "&screen_name=" + screenName;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArray(url));
    }

    @Override
    public Status[] getRetweetsOfMe(Paging paging) throws Exception {
        String url = Urls.RETWEETS_OF_ME;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArray(url));
    }

    @Override
    public Status showStatus(long statusId) throws Exception {
        return new StatusJSON(getObject(Urls.SHOW_STATUS.replace("{id}", Long.toString(statusId))));
    }

    @Override
    public Status retweetStatus(long statusId) throws Exception {
        return new StatusJSON(getObject(Urls.RETWEET_STATUS.replace("{id}", Long.toString(statusId))));
    }

    @Override
    public Status destroyStatus(long statusId) throws Exception {
        return new StatusJSON(postObject(Urls.DESTROY_STATUS.replace("{id}", Long.toString(statusId)), null, null));
    }

    @Override
    public User showUser(long userId) throws Exception {
        return new UserJSON(getObject(Urls.SHOW_USER + "&user_id=" + Long.toString(userId)));
    }

    @Override
    public User showUser(String screenName) throws Exception {
        return new UserJSON(getObject(Urls.SHOW_USER + "&screen_name=" + screenName));
    }

    @Override
    public Status[] getRetweets(long statusId, int count) throws Exception {
        String url = Urls.RETWEETS.replace("{id}", Long.toString(statusId));
        if(count > 0) url += "&count=" + count;
        return StatusJSON.createStatusList(getArray(url));
    }

    @Override
    public User[] getRetweetedBy(long statusId, Paging paging) throws Exception {
        String url = Urls.RETWEETED_BY.replace("{id}", Long.toString(statusId));
        if(paging != null) url += paging.getUrlString('?', false);
        return UserJSON.createUserList(getArray(url));
    }

    @Override
    public Status updateStatus(String update) throws Exception {
        StatusUpdate su = StatusUpdate.create(update, 0, null, false, null);
        return updateStatus(su);
    }

    @Override
    public Status updateStatus(StatusUpdate update) throws Exception {
        if(update.getMedia() != null) {
            return new StatusJSON(postObject(Urls.UPDATE_STATUS_MEDIA, update.getBodyParams(), update.getMedia()));
        } else return new StatusJSON(postObject(Urls.UPDATE_STATUS, update.getBodyParams(), update.getMedia()));
    }

    @Override
    public SearchResult search(SearchQuery query) throws Exception {
        return new SearchResultJSON(getObject(query.getUrl()));
    }

    @Override
    public User[] searchUsers(String query, int page, int perPage) throws Exception {
        String url = Urls.SEARCH_USERS + "&query=" + encode(query);
        if(page > 0) url += "&page=" + page;
        if(perPage > 0) url += "&per_page=" + perPage;
        return UserJSON.createUserList(getArray(url));
    }

    @Override
    public DirectMessage[] getDirectMessages(Paging paging) throws Exception {
        String url = Urls.DIRECT_MESSAGES;
        if(paging != null) url += paging.getUrlString('&', true);
        return DirectMessageJSON.createMessageList(getArray(url));
    }

    @Override
    public DirectMessage[] getSentDirectMessages(Paging paging) throws Exception {
        String url = Urls.DIRECT_MESSAGES_SENT;
        if(paging != null) url += paging.getUrlString('&', true);
        return DirectMessageJSON.createMessageList(getArray(url));
    }

    @Override
    public DirectMessage createDirectMessage(String screenName, String text) throws Exception {
        List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        pairs.add(new BasicNameValuePair("text", encode(text)));
        pairs.add(new BasicNameValuePair("screen_name", screenName));
        return new DirectMessageJSON(postObject(Urls.CREATE_DIRECT_MESSAGE, pairs, null));
    }

    @Override
    public DirectMessage createDirectMessage(long userId, String text) throws Exception {
        List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        pairs.add(new BasicNameValuePair("text", encode(text)));
        pairs.add(new BasicNameValuePair("user_id", Long.toString(userId)));
        return new DirectMessageJSON(postObject(Urls.CREATE_DIRECT_MESSAGE, pairs, null));
    }

    @Override
    public DirectMessage showDirectMessage(long msgId) throws Exception {
        return new DirectMessageJSON(getObject(Urls.SHOW_DIRECT_MESSAGE.replace("{id}", Long.toString(msgId))));
    }

    @Override
    public DirectMessage destroyDirectMessage(long msgId) throws Exception {
        return new DirectMessageJSON(deleteObject(Urls.DESTROY_DIRECT_MESSAGE.replace("{id}", Long.toString(msgId))));
    }

    @Override
    public void getRelatedResults(long statusId) throws Exception {
        System.out.println(getArray(Urls.RELATED_RESULTS.replace("{id}", Long.toString(statusId))).toString(4));
    }
}