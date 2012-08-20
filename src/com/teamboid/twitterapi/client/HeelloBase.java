package com.teamboid.twitterapi.client;

import java.io.File;
import java.io.InputStream;

import com.teamboid.twitterapi.config.TwitterAPIConfig;
import com.teamboid.twitterapi.dm.DirectMessage;
import com.teamboid.twitterapi.experimentalapis.RelatedResults;
import com.teamboid.twitterapi.list.UserList;
import com.teamboid.twitterapi.list.UserListMode;
import com.teamboid.twitterapi.relationship.IDs;
import com.teamboid.twitterapi.relationship.Relationship;
import com.teamboid.twitterapi.savedsearch.SavedSearch;
import com.teamboid.twitterapi.search.SearchQuery;
import com.teamboid.twitterapi.search.SearchResult;
import com.teamboid.twitterapi.status.GeoLocation;
import com.teamboid.twitterapi.status.Granularity;
import com.teamboid.twitterapi.status.Place;
import com.teamboid.twitterapi.status.Status;
import com.teamboid.twitterapi.status.StatusUpdate;
import com.teamboid.twitterapi.trend.Trend;
import com.teamboid.twitterapi.trend.TrendLocation;
import com.teamboid.twitterapi.trend.Trends;
import com.teamboid.twitterapi.user.User;

/**
 * Testing. DO NOT USE THIS AT ALL PLZ
 * @author kennydude
 *
 */
public class HeelloBase implements Twitter {

	@Override
	public boolean supportsFeature(Feature feature) {
		switch(feature){
		case SEARCH_USERS:
		case PLACES:
		case REPOST:
			return true;
		default:
			return false;
		}
	}

	@Override
	public Twitter setSslEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getSslEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User verifyCredentials() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TwitterAPIConfig getAPIConfiguration() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateProfileImage(File file) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAccessToken() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAccessSecret() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status[] getHomeTimeline(Paging paging) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status[] getMentions(Paging paging) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status[] getUserTimeline(Paging paging, boolean includeRetweets)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status[] getUserTimeline(Long userId, Paging paging,
			boolean includeRetweets) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status[] getUserTimeline(String screenName, Paging paging,
			boolean includeRetweets) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status[] getRetweetsOfMe(Paging paging) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status showStatus(Long statusId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status retweetStatus(Long statusId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status destroyStatus(Long statusId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status[] getRetweets(Long statusId, int count) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User[] getRetweetedBy(Long statusId, Paging paging) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status updateStatus(StatusUpdate update) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchResult search(SearchQuery query) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User[] searchUsers(String query, int page, int perPage)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DirectMessage[] getDirectMessages(Paging paging) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DirectMessage[] getSentDirectMessages(Paging paging)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DirectMessage createDirectMessage(String screenName, String text)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DirectMessage createDirectMessage(Long userId, String text)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DirectMessage showDirectMessage(Long msgId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DirectMessage destroyDirectMessage(Long msgId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status[] getFavorites(Paging paging) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status[] getFavorites(Paging paging, String screenName)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status[] getFavorites(Paging paging, Long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status createFavorite(Long statusId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status destroyFavorite(Long statusId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User showUser(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User showUser(String screenName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User[] lookupUsers(String[] screenNames) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User[] lookupUsers(Long[] userIds) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserProfileImage(String screenName, ProfileImageSize size)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDs getFriends(String screenName, Long cursor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDs getFriends(Long userId, Long cursor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDs getFollowers(String screenName, Long cursor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDs getFollowers(Long userId, Long cursor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsFriendship(String fromScreenName, String toScreenName)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IDs getFriendshipsIncoming(Long cursor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDs getFriendshipsOutgoing(Long cursor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Relationship getRelationship(Long fromUserId, Long toUserId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Relationship getRelationship(String fromUserScreenName,
			String toUserScreenName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createFriendship(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createFriendship(String screenName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User destroyFriendship(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User destroyFriendship(String screenName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User reportSpam(String screenName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User reportSpam(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User[] getBlocking(Long cursor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDs getBlockingIds(Long cursor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsBlock(String screenName) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existsBlock(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User createBlock(String screenName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createBlock(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User destroyBlock(String screenName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User destroyBlock(Long userID) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SavedSearch[] getSavedSearches() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SavedSearch getSavedSearch(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SavedSearch createSavedSearch(String query) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SavedSearch destroySavedSearch(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserList createList(String name, UserListMode mode,
			String description) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createListMembers(long listId, long[] userIds) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createListMembers(long listId, String[] screenNames)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroyListMembers(long listId, long[] userIds)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroyListMembers(long listId, String[] screenNames)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserList updateList(long listId, String name, UserListMode mode,
			String description) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserList destroyList(long listId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserList getList(long listId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserList[] getLists() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserList[] getLists(long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserList[] getLists(String screenName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status[] getListTimeline(long listId, Paging paging)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User[] getListMembers(long listId, long cursor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User[] getListSubscribers(long listId, long cursor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserList createListSubscription(long listId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserList destroyListSubscription(long listId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trends[] getTrendsDaily() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trends[] getTrendsWeekly() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TrendLocation[] getTrendsAvailable(GeoLocation location)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trend[] getLocationTrends(int woeid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trend[] getTrendsGlobal() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Place getPlaceDetails(String placeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RelatedResults getRelatedResults(Long statusId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Place[] getReverseGeocode(GeoLocation coordinates, String accuracy,
			Granularity gran, int maxResults) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateProfile(String name, String url, String location,
			String description) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateProfileImage(InputStream imageStream) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status[] getUserMediaTimeline(String userName, Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

}
