package com.teamboid.twitterapi.client;

import com.teamboid.twitterapi.dm.DirectMessage;
import com.teamboid.twitterapi.dm.DirectMessageJSON;
import com.teamboid.twitterapi.relationship.IDs;
import com.teamboid.twitterapi.relationship.IDsJSON;
import com.teamboid.twitterapi.relationship.Relationship;
import com.teamboid.twitterapi.relationship.RelationshipJSON;
import com.teamboid.twitterapi.search.SearchQuery;
import com.teamboid.twitterapi.search.SearchResult;
import com.teamboid.twitterapi.search.SearchResultJSON;
import com.teamboid.twitterapi.status.Status;
import com.teamboid.twitterapi.status.StatusJSON;
import com.teamboid.twitterapi.status.StatusUpdate;
import com.teamboid.twitterapi.user.FollowingType;
import com.teamboid.twitterapi.user.User;
import com.teamboid.twitterapi.user.UserJSON;
import com.teamboid.twitterapi.utilities.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The base {@link Twitter} client class, contains the actual code content for
 * the functions called when you use the Twitter interface.
 *
 * @author Aidan Follestad
 */
public class TwitterBase extends RequestHandler implements Twitter {

    /**
     * {@inheritDoc}
     */
    @Override
    public User verifyCredentials() throws Exception {
        return new UserJSON(getObject(Urls.VERIFY_CREDENTIALS));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User updateProfileImage(File file) throws Exception {
        List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        pairs.add(new BasicNameValuePair("image", FileUtils.getBase64FromFile(file)));
        return new UserJSON(postObject(Urls.UPDATE_PROFILE_IMAGE, pairs, null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAccessToken() {
        return super._accessToken;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAccessSecret() {
        return super._accessSecret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status[] getHomeTimeline(Paging paging) throws Exception {
        String url = Urls.HOME_TIMELINE;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArray(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status[] getMentions(Paging paging) throws Exception {
        String url = Urls.MENTIONS;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArray(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status[] getUserTimeline(Paging paging) throws Exception {
        String url = Urls.USER_TIMELINE;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArray(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status[] getUserTimeline(long userId, Paging paging) throws Exception {
        String url = Urls.USER_TIMELINE + "&user_id=" + userId;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArray(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status[] getUserTimeline(String screenName, Paging paging) throws Exception {
        String url = Urls.USER_TIMELINE + "&screen_name=" + screenName;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArray(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status[] getRetweetsOfMe(Paging paging) throws Exception {
        String url = Urls.RETWEETS_OF_ME;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArray(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status showStatus(long statusId) throws Exception {
        return new StatusJSON(getObject(Urls.SHOW_STATUS.replace("{id}", Long.toString(statusId))));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status retweetStatus(long statusId) throws Exception {
        return new StatusJSON(getObject(Urls.RETWEET_STATUS.replace("{id}", Long.toString(statusId))));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status destroyStatus(long statusId) throws Exception {
        return new StatusJSON(postObject(Urls.DESTROY_STATUS.replace("{id}", Long.toString(statusId)), null, null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User showUser(long userId) throws Exception {
        return new UserJSON(getObject(Urls.SHOW_USER + "&user_id=" + Long.toString(userId)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User showUser(String screenName) throws Exception {
        return new UserJSON(getObject(Urls.SHOW_USER + "&screen_name=" + screenName));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User[] lookupUsers(String[] screenNames) throws Exception {
        String param = "";
        for(String name : screenNames) {
            param += name + ",";
        }
        return UserJSON.createUserList(getArray(Urls.LOOKUP_USERS + "?screen_name=" + param));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User[] lookupUsers(long[] userIds) throws Exception {
        String param = "";
        for(long id : userIds) {
            param += Long.toString(id) + ",";
        }
        return UserJSON.createUserList(getArray(Urls.LOOKUP_USERS + "?user_id=" + param));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUserProfileImage(String screenName, ProfileImageSize size) throws Exception {
        String url = Urls.GET_PROFILE_IMAGE + "?screen_name=" + screenName;
        if(size != ProfileImageSize.NORMAL) {
            url += "&size=" + size.name().toLowerCase();
        }
        HttpResponse response = getResponse(url, true);
        return response.getHeaders("Location")[0].getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDs getFriends(String screenName, long cursor) throws Exception {
        String url = Urls.GET_FRIENDS + "?screen_name=" + screenName;
        if(cursor >= -1) url += "&cursor=" + Long.toString(cursor);
        return new IDsJSON(getObject(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDs getFriends(long userId, long cursor) throws Exception {
        String url = Urls.GET_FRIENDS + "?user_id=" + Long.toString(userId);
        if(cursor >= -1) url += "&cursor=" + Long.toString(cursor);
        return new IDsJSON(getObject(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDs getFollowers(String screenName, long cursor) throws Exception {
        String url = Urls.GET_FOLLOWERS + "?screen_name=" + screenName;
        if(cursor >= -1) url += "&cursor=" + Long.toString(cursor);
        return new IDsJSON(getObject(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDs getFollowers(long userId, long cursor) throws Exception {
        String url = Urls.GET_FOLLOWERS + "?user_id=" + Long.toString(userId);
        if(cursor >= -1) url += "&cursor=" + Long.toString(cursor);
        return new IDsJSON(getObject(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsFriendship(String fromScreenName, String toScreenName) throws Exception {
        HttpResponse response = getResponse(Urls.FRIENDSHIP_EXISTS +
                "?screen_name_a=" + fromScreenName + "&screen_name_b=" + toScreenName, false);
        return Boolean.parseBoolean(EntityUtils.toString(response.getEntity()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDs getFriendshipsIncoming(long cursor) throws Exception {
        String url = Urls.INCOMING_FRIENDSHIPS;
        if(cursor >= -1) url += "?cursor=" + Long.toString(cursor);
        return new IDsJSON(getObject(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDs getFriendshipsOutgoing(long cursor) throws Exception {
        String url = Urls.OUTGOING_FRIENDSHIPS;
        if(cursor >= -1) url += "?cursor=" + Long.toString(cursor);
        return new IDsJSON(getObject(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Relationship getRelationship(long fromUserId, long toUserId) throws Exception {
        String url = Urls.SHOW_FRIENDSHIP +
                "?source_id=" + Long.toString(fromUserId) + "&target_id=" + Long.toString(toUserId);
        return new RelationshipJSON(getObject(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Relationship getRelationship(String fromScreenName, String toScreenName) throws Exception {
        String url = Urls.SHOW_FRIENDSHIP +
                "?source_screen_name=" + fromScreenName + "&target_screen_name=" + toScreenName;
        return new RelationshipJSON(getObject(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User createFriendship(long userId) throws Exception {
        List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        pairs.add(new BasicNameValuePair("user_id", Long.toString(userId)));
        User toReturn = new UserJSON(postObject(Urls.CREATE_FRIENDSHIP, pairs, null));
        /*
         * The User JSON returned from the above HTTP POST doesn't seem to actually change the isFollowing value, so we do that manually.
         */
        toReturn.setFollowingType(FollowingType.FOLLOWING);
        return toReturn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User createFriendship(String screenName) throws Exception {
        List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        pairs.add(new BasicNameValuePair("screen_name", screenName));
        User toReturn = new UserJSON(postObject(Urls.CREATE_FRIENDSHIP, pairs, null));
        /*
         * The User JSON returned from the above HTTP POST doesn't seem to actually change the isFollowing value, so we do that manually.
         */
        toReturn.setFollowingType(FollowingType.FOLLOWING);
        return toReturn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User destroyFriendship(long userId) throws Exception {
        List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        pairs.add(new BasicNameValuePair("user_id", Long.toString(userId)));
        User toReturn = new UserJSON(postObject(Urls.DESTROY_FRIENDSHIP, pairs, null));
        /*
         * The User JSON returned from the above HTTP POST doesn't seem to actually change the isFollowing value, so we do that manually.
         */
        toReturn.setFollowingType(FollowingType.NOT_FOLLOWING);
        return toReturn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User destroyFriendship(String screenName) throws Exception {
        List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        pairs.add(new BasicNameValuePair("screen_name", screenName));
        User toReturn = new UserJSON(postObject(Urls.DESTROY_FRIENDSHIP, pairs, null));
        /*
         * The User JSON returned from the above HTTP POST doesn't seem to actually change the isFollowing value, so we do that manually.
         */
        toReturn.setFollowingType(FollowingType.NOT_FOLLOWING);
        return toReturn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status[] getRetweets(long statusId, int count) throws Exception {
        String url = Urls.RETWEETS.replace("{id}", Long.toString(statusId));
        if(count > 0) url += "&count=" + count;
        return StatusJSON.createStatusList(getArray(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User[] getRetweetedBy(long statusId, Paging paging) throws Exception {
        String url = Urls.RETWEETED_BY.replace("{id}", Long.toString(statusId));
        if(paging != null) url += paging.getUrlString('?', false);
        return UserJSON.createUserList(getArray(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status updateStatus(StatusUpdate update) throws Exception {
        if(update.getMedia() != null) {
            return new StatusJSON(postObject(Urls.UPDATE_STATUS_MEDIA, update.getBodyParams(), update.getMedia()));
        } else return new StatusJSON(postObject(Urls.UPDATE_STATUS, update.getBodyParams(), update.getMedia()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SearchResult search(SearchQuery query) throws Exception {
        return new SearchResultJSON(getObject(query.getUrl()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User[] searchUsers(String query, int page, int perPage) throws Exception {
        String url = Urls.SEARCH_USERS + "&query=" + encode(query);
        if(page > 0) url += "&page=" + page;
        if(perPage > 0) url += "&per_page=" + perPage;
        return UserJSON.createUserList(getArray(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DirectMessage[] getDirectMessages(Paging paging) throws Exception {
        String url = Urls.DIRECT_MESSAGES;
        if(paging != null) url += paging.getUrlString('&', true);
        return DirectMessageJSON.createMessageList(getArray(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DirectMessage[] getSentDirectMessages(Paging paging) throws Exception {
        String url = Urls.DIRECT_MESSAGES_SENT;
        if(paging != null) url += paging.getUrlString('&', true);
        return DirectMessageJSON.createMessageList(getArray(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DirectMessage createDirectMessage(String screenName, String text) throws Exception {
        List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        pairs.add(new BasicNameValuePair("text", encode(text)));
        pairs.add(new BasicNameValuePair("screen_name", screenName));
        return new DirectMessageJSON(postObject(Urls.CREATE_DIRECT_MESSAGE, pairs, null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DirectMessage createDirectMessage(long userId, String text) throws Exception {
        List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        pairs.add(new BasicNameValuePair("text", encode(text)));
        pairs.add(new BasicNameValuePair("user_id", Long.toString(userId)));
        return new DirectMessageJSON(postObject(Urls.CREATE_DIRECT_MESSAGE, pairs, null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DirectMessage showDirectMessage(long msgId) throws Exception {
        return new DirectMessageJSON(getObject(Urls.SHOW_DIRECT_MESSAGE.replace("{id}", Long.toString(msgId))));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DirectMessage destroyDirectMessage(long msgId) throws Exception {
        return new DirectMessageJSON(deleteObject(Urls.DESTROY_DIRECT_MESSAGE.replace("{id}", Long.toString(msgId))));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status[] getFavorites(Paging paging) throws Exception {
        String url = Urls.GET_FAVORITES;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArray(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status[] getFavorites(Paging paging, String screenName) throws Exception {
        String url = Urls.GET_FAVORITES + "&screen_name=" + screenName;
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArray(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status[] getFavorites(Paging paging, long userId) throws Exception {
        String url = Urls.GET_FAVORITES + "&user_id=" + Long.toString(userId);
        if(paging != null) url += paging.getUrlString('&', true);
        return StatusJSON.createStatusList(getArray(url));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status createFavorite(long statusId) throws Exception {
        Status toReturn = new StatusJSON(postObject(Urls.CREATE_FAVORITE.replace("{id}", Long.toString(statusId)), null, null));
        /*
         * The Status JSON returned from the above HTTP POST doesn't seem to actually change the isFavorited value, so we do that manually.
         */
        toReturn.setFavorited(true);
        return toReturn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status destroyFavorite(long statusId) throws Exception {
        Status toReturn = new StatusJSON(postObject(Urls.DESTROY_FAVORITE.replace("{id}", Long.toString(statusId)), null, null));
        /*
         * The Status JSON returned from the above HTTP POST doesn't seem to actually change the isFavorited value, so we do that manually.
         */
        toReturn.setFavorited(false);
        return toReturn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getRelatedResults(long statusId) throws Exception {
        System.out.println(getArray(Urls.RELATED_RESULTS.replace("{id}", Long.toString(statusId))).toString(4));
    }
}