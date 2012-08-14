package com.teamboid.twitterapi.client;

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

import java.io.File;
import java.io.InputStream;

/**
 * The main class used within this library, used for performing API actions on Twitter.
 * Use the {@link Authorizer} to authenticate an account and
 * get an authorized instance of this class.
 *
 * @author Aidan Follestad
 */
public interface Twitter {
	
	/**
	 * Sets the consumer key for use with OAuth-Echo services
	 * @param key
	 */
	void setConsumerKey(String key);
	
	/**
	 * Get the consumer key for use with OAuth-Echo services
	 * @return
	 */
	String getConsumerKey();

    /**
     * Sets whether or not SSL is enabled for network interactions.
     */
    Twitter setSslEnabled(boolean enabled);

    /**
     * Gets whether or not SSL is enabled for network interactions.
     */
    boolean getSslEnabled();

    /**
     * Gets the current authenticated user's {@link User} object,
     * @throws Exception
     */
    User verifyCredentials() throws Exception;

    /**
     * Gets Twitter's current API configuration, returns useful numbers that many apps need.
     * @throws Exception
     */
    TwitterAPIConfig getAPIConfiguration() throws Exception;

    /**
     * Sets values that users are able to set under the "Account" tab of their settings page.
     * Only the parameters specified will be updated.
     *
     * @param name Full name associated with the profile. Maximum of 20 characters.
     * @param url URL associated with the profile. Will be prepended with "http://" if not present. Maximum of 100 characters.
     * @param location The city or country describing where the user of the account is located. The contents are not normalized or geocoded in any way. Maximum of 30 characters.
     * @param description A description of the user owning the account. Maximum of 160 characters.
     * @throws Exception
     */
    User updateProfile(String name, String url, String location, String description) throws Exception;

    /**
     * Updates the authenticated user's profile picture.
     * @param file The image file to update the profile picture with.
     * @throws Exception
     */
    User updateProfileImage(File file) throws Exception;

    /**
     * Updates the authenticated user's profile picture.
     * @param imageStream The stream containing a raw image to update the profile picture with.
     * @throws Exception
     */
    User updateProfileImage(InputStream imageStream) throws Exception;

    /**
     * Gets the access token used to authenticate requests made to Twitter,
     */
    String getAccessToken() throws Exception;

    /**
     * Gets the access secret used to authenticate requests made to Twitter.
     */
    String getAccessSecret() throws Exception;

    /**
     * Gets the home timeline of the current authenticated user.
     * @throws Exception
     */
    Status[] getHomeTimeline(Paging paging) throws Exception;

    /**
     * Gets the mentions of the current authenticated user.
     * @throws Exception
     */
    Status[] getMentions(Paging paging) throws Exception;

    /**
     * Gets the timeline of the current authenticated user's profile.
     * @throws Exception
     */
    Status[] getUserTimeline(Paging paging, boolean includeRetweets) throws Exception;

    /**
     * Gets the timeline of another user.
     * @param userId The ID of the user to get the timeline of.
     * @throws Exception
     */
    Status[] getUserTimeline(Long userId, Paging paging, boolean includeRetweets) throws Exception;

    /**
     * Gets the timeline of another user.
     * @param screenName The screen name of the user to get the timeline of.
     * @throws Exception
     */
    Status[] getUserTimeline(String screenName, Paging paging, boolean includeRetweets) throws Exception;

    /**
     * Gets a timeline of recent retweets of the current authenticated user's tweets.
     * @throws Exception
     */
    Status[] getRetweetsOfMe(Paging paging) throws Exception;

    /**
     * Retrieves a Status on Twitter by its ID.
     * @param statusId The ID of the status to get.
     * @throws Exception
     */
    Status showStatus(Long statusId) throws Exception;

    /**
     * Reteets a Status on twitter.
     * @param statusId The ID of the status to retweet.
     * @throws Exception
     */
    Status retweetStatus(Long statusId) throws Exception;

    /**
     * Deletes a Status on Twitter that was created by the authenticated user.
     * @param statusId The ID of the status to delete.
     * @throws Exception
     */
    Status destroyStatus(Long statusId) throws Exception;

    /**
     * Gets retweets of a specified tweet.
     * @param statusId The ID of the status to get retweets of.
     * @param count The optional number of retweets to retrieve.
     * @throws Exception
     */
    Status[] getRetweets(Long statusId, int count) throws Exception;

    /**
     * Gets a list of {@link User} objects that retweeted a specified status.
     * @param statusId The ID of the status to get retweeters of.
     * @throws Exception
     */
    User[] getRetweetedBy(Long statusId, Paging paging) throws Exception;

    /**
     * Composes a Tweet for the authenticated user.
     * @throws Exception
     */
    Status updateStatus(StatusUpdate update) throws Exception;

    /**
     * Searches for {@link com.teamboid.twitterapi.search.Tweet} objects on Twitter.
     * @throws Exception
     */
    SearchResult search(SearchQuery query) throws Exception;

    /**
     * Searches for {@link User} objects on Twitter.
     * @throws Exception
     */
    User[] searchUsers(String query, int page, int perPage) throws Exception;

    /**
     * Gets a list of direct messages sent to you.
     * @throws Exception
     */
    DirectMessage[] getDirectMessages(Paging paging) throws Exception;

    /**
     * Gets a list of direct messages sent by you to others.
     * @throws Exception
     */
    DirectMessage[] getSentDirectMessages(Paging paging) throws Exception;

    /**
     * Composes a direct message.
     * @param screenName The screen name of the user that will receive the direct message.
     * @param text The text in the direct message, must be 140 characters or less.
     * @throws Exception
     */
    DirectMessage createDirectMessage(String screenName, String text) throws Exception;

    /**
     * Composes a direct message.
     * @param userId The ID of the user that will receive the direct message.
     * @param text The text in the direct message, must be 140 characters or less.
     * @throws Exception
     */
    DirectMessage createDirectMessage(Long userId, String text) throws Exception;

    /**
     * Retrieves a direct message by its ID.
     * @param msgId The ID of the message to get.
     * @throws Exception
     */
    DirectMessage showDirectMessage(Long msgId) throws Exception;

    /**
     * Deletes a direct message.
     * @param msgId The ID of the direct message to delete.
     * @throws Exception
     */
    DirectMessage destroyDirectMessage(Long msgId) throws Exception;

    /**
     * Gets the authenticated uesr's favorites.
     * @throws Exception
     */
    Status[] getFavorites(Paging paging) throws Exception;

    /**
     * Gets a specified user's favorites.
     * @param screenName The screen name of the user to get favorites of.
     * @throws Exception
     */
    Status[] getFavorites(Paging paging, String screenName) throws Exception;

    /**
     * Gets a specified user's favorites.
     * @param userId The ID of the user to get favorites of.
     * @throws Exception
     */
    Status[] getFavorites(Paging paging, Long userId) throws Exception;

    /**
     * Favorites a Status on Twitter.
     * @param statusId The ID of the status to favorite.
     * @throws Exception
     */
    Status createFavorite(Long statusId) throws Exception;

    /**
     * Un-favorites a Status on Twitter.
     * @param statusId The ID of the status to un-favorite.
     * @throws Exception
     */
    Status destroyFavorite(Long statusId) throws Exception;

    /**
     * Retrieves the {@link User} of a Twitter profile.
     * @param userId The ID of the user to get.
     * @throws Exception
     */
    User showUser(Long userId) throws Exception;

    /**
     * Retrieves the {@link User} of a Twitter profile.
     * @param screenName The screen name of the user to get.
     * @throws Exception
     */
    User showUser(String screenName) throws Exception;

    /**
     * Looks up multiple users by their screen names (up to 100 at a time).
     * @param screenNames The screen names to lookup, must be 100 or less.
     * @throws Exception
     */
    User[] lookupUsers(String[] screenNames) throws Exception;

    /**
     * Looks up multiple users by their IDs (up to 100 at a time).
     * @param userIds The IDs to lookup, must be 100 or less.
     * @throws Exception
     */
    User[] lookupUsers(Long[] userIds) throws Exception;

    /**
     * Gets the URL to a user's high quality profile picture.
     * @param screenName The screen name of the user to get the profile picture of.
     * @param size The size of the profile image to retrieve.
     * @throws Exception
     */
    String getUserProfileImage(String screenName, ProfileImageSize size) throws Exception;

    /**
     * Returns {@link IDs} containing every user the specified user is following.
     * @param screenName The screen name of the user for whom to return results for.
     * @param cursorsetMaxId Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried. To begin paging provide a value of -1 as the cursor. The response from the API will include a previous_cursor and next_cursor to allow paging back and forth.
     * @throws Exception
     */
    IDs getFriends(String screenName, Long cursor) throws Exception;

    /**
     * Returns {@link IDs} containing every user the specified user is following.
     * @param userId The ID of the user for whom to return results for.
     * @param cursor Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried. To begin paging provide a value of -1 as the cursor. The response from the API will include a previous_cursor and next_cursor to allow paging back and forth.
     * @throws Exception
     */
    IDs getFriends(Long userId, Long cursor) throws Exception;

    /**
     * Returns {@link IDs} containing every user following the specified user.
     * @param screenName The screen name of the user for whom to return results for.
     * @param cursor Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried. To begin paging provide a value of -1 as the cursor. The response from the API will include a previous_cursor and next_cursor to allow paging back and forth.
     * @throws Exception
     */
    IDs getFollowers(String screenName, Long cursor) throws Exception;

    /**
     * Returns {@link IDs} containing every user following the specified user.
     * @param userId The ID of the user for whom to return results for.
     * @param cursor Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried. To begin paging provide a value of -1 as the cursor. The response from the API will include a previous_cursor and next_cursor to allow paging back and forth.
     * @throws Exception
     */
    IDs getFollowers(Long userId, Long cursor) throws Exception;

    /**
     * Checks whether or not one user is following another user.
     * @param fromScreenName Is this user following the other?
     * @param toScreenName Is this user followed by the other?
     * @throws Exception
     */
    boolean existsFriendship(String fromScreenName, String toScreenName) throws Exception;

    /**
     * Returns {@link IDs} containing every protected user for whom the authenticating user has a pending follow request.
     * @param cursor Breaks the results into pages. This is recommended for users who are following many users. Provide a value of -1 to begin paging. Provide values as returned in the response body's next_cursor and previous_cursor attributes to page back and forth in the list.
     * @throws Exception
     */
    IDs getFriendshipsIncoming(Long cursor) throws Exception;

    /**
     * Returns {@link IDs} containing every user who has a pending request to follow the authenticating user.
     * @param cursor Breaks the results into pages. This is recommended for users who are following many users. Provide a value of -1 to begin paging. Provide values as returned in the response body's next_cursor and previous_cursor attributes to page back and forth in the list.
     * @throws Exception
     */
    IDs getFriendshipsOutgoing(Long cursor) throws Exception;

    /**
     * Returns detailed information about the relationship between two users.
     * @param fromUserId The ID of the source user.
     * @param toUserId The ID of the target user.
     * @throws Exception
     */
    Relationship getRelationship(Long fromUserId, Long toUserId) throws Exception;

    /**
     * Returns detailed information about the relationship between two users.
     * @param fromUserScreenName The screen name of the source user.
     * @param toUserScreenName The screen name of the target user.
     * @throws Exception
     */
    Relationship getRelationship(String fromUserScreenName, String toUserScreenName) throws Exception;

    /**
     * Follows the specified user for the authenticated account.
     * @param userId The ID of the user to follow.
     */
    User createFriendship(Long userId) throws Exception;

    /**
     * Follows the specified user for the authenticated account.
     * @param screenName The screen name of the user to follow.
     */
    User createFriendship(String screenName) throws Exception;

    /**
     * Un-follows the specified user for the authenticated account.
     * @param userId The ID of the user to un-follow.
     */
    User destroyFriendship(Long userId) throws Exception;

    /**
     * Un-follows the specified user for the authenticated account.
     * @param screenName The screen name of the user to un-follow.
     */
    User destroyFriendship(String screenName) throws Exception;

    /**
     * Reports a user for spam, which also blocks the user from the authenticated account.
     * @param screenName The screen name of the user to report.
     * @throws  Exception
     */
    User reportSpam(String screenName) throws Exception;

    /**
     * Reports a user for spam, which also blocks the user from the authenticated account.
     * @param userId The ID of the user to report.
     * @throws  Exception
     */
    User reportSpam(Long userId) throws Exception;

    /**
     * Returns an array of user objects that the authenticating user is blocking.
     * Consider using {@link Twitter#getBlockingIds(Long)} with {@link Twitter#lookupUsers(Long[])} instead of this method.
     * @param cursor Causes the list of users to be broken into pages of no more than 5000
     *               IDs at a time. The number of IDs returned is not guaranteed to be 5000
     *               as suspended users are filtered out after connections are queried.
     *               To begin paging provide a value of -1 as the cursor. The response from
     *               the API will include a previous_cursor and next_cursor to allow paging
     *               back and forth. If the cursor is not provided the API will attempt to
     *               return all IDs. For users with many blockings this will probably fail.
     * @throws Exception
     */
    User[] getBlocking(Long cursor) throws Exception;

    /**
     * Returns an {@link IDs} containing user IDs that the authenticating user is blocking.
     * @param cursor Causes the list of users to be broken into pages of no more than 5000
     *               IDs at a time. The number of IDs returned is not guaranteed to be 5000
     *               as suspended users are filtered out after connections are queried.
     *               To begin paging provide a value of -1 as the cursor. The response from
     *               the API will include a previous_cursor and next_cursor to allow paging
     *               back and forth. If the cursor is not provided the API will attempt to
     *               return all IDs. For users with many blockings this will probably fail.
     * @throws Exception
     */
    IDs getBlockingIds(Long cursor) throws Exception;

    /**
     * Returns if the authenticating user is blocking a target user.
     * @param screenName The screen name of the user to check if the authenticated user has blocked.
     * @throws Exception
     */
    boolean existsBlock(String screenName) throws Exception;

    /**
     * Returns if the authenticating user is blocking a target user.
     * @param userId The ID of the user to check if the authenticated user has blocked.
     * @throws Exception
     */
    boolean existsBlock(Long userId) throws Exception;

    /**
     * Blocks the specified user from following the authenticating user. In addition the
     * blocked user will not show in the authenticating users mentions or timeline (unless
     * retweeted by another user). If a follow or friend relationship exists it is destroyed.
     *
     * @param screenName The screen name of the user to block.
     * @throws Exception
     */
    User createBlock(String screenName) throws Exception;

    /**
     * Blocks the specified user from following the authenticating user. In addition the
     * blocked user will not show in the authenticating users mentions or timeline (unless
     * retweeted by another user). If a follow or friend relationship exists it is destroyed.
     *
     * @param userId The ID of the user to block.
     * @throws Exception
     */
    User createBlock(Long userId) throws Exception;

    /**
     * Un-blocks the specified user for the authenticating user. Returns the un-blocked user
     * in the requested format when successful. If relationships existed before the block was
     * instated, they will not be restored.
     *
     * @param screenName The screen name of the user to unblock.
     * @throws Exception
     */
    User destroyBlock(String screenName) throws Exception;

    /**
     * Un-blocks the specified user for the authenticating user. Returns the un-blocked user
     * in the requested format when successful. If relationships existed before the block was
     * instated, they will not be restored.
     *
     * @param userID The ID of the user to unblock.
     * @throws Exception
     */
    User destroyBlock(Long userID) throws Exception;

    /**
     * Returns the authenticated user's saved search queries.
     * @throws Exception
     */
    SavedSearch[] getSavedSearches() throws Exception;

    /**
     * Retrieve the information for the saved search represented by the given id.
     * The authenticating user must be the owner of saved search ID being requested.
     * @param id The ID of the saved search to get.
     * @throws Exception
     */
    SavedSearch getSavedSearch(long id) throws Exception;

    /**
     * Create a new saved search for the authenticated user. A user may only have 25 saved searches.
     * @param query The query of the search the user would like to save.
     * @throws Exception
     */
    SavedSearch createSavedSearch(String query) throws Exception;

    /**
     * Destroys a saved search for the authenticating user. The authenticating user
     * must be the owner of saved search id being destroyed.
     * @param id The ID of the saved search.
     * @throws Exception
     */
    SavedSearch destroySavedSearch(long id) throws Exception;

    /**
     * Creates a new list for the authenticated user. Note
     * that you can't create more than 20 lists per account.
     * @param name The name for the list. A list's name must start with
     *             a letter and can consist only of 25 or fewer letters,
     *             numbers, "-", or "_" characters.
     * @param mode Whether your list is public or private.
     * @param description The description to give the list.
     * @throws Exception
     */
    UserList createList(String name, UserListMode mode, String description) throws Exception;

    /**
     * Adds multiple members to a list, by specifying a comma-separated list of member
     * ids or screen names. The authenticated user must own the list to be able to add
     * members to it. Note that lists can't have more than 500 members, and you are
     * limited to adding up to 100 members to a list at a time with this method.
     * <br/><br/>
     * Please note that there can be issues with lists that rapidly remove and add
     * memberships. Take care when using these methods such that you are not too rapidly
     * switching between removals and adds on the same list.
     *
     * @param listId The ID of the list to add members to.
     * @param userIds The IDs of users to add to the list.
     * @throws Exception
     */
    void createListMembers(long listId, long[] userIds) throws Exception;

    /**
     * Adds multiple members to a list, by specifying a comma-separated list of member
     * ids or screen names. The authenticated user must own the list to be able to add
     * members to it. Note that lists can't have more than 500 members, and you are
     * limited to adding up to 100 members to a list at a time with this method.
     * <br/><br/>
     * Please note that there can be issues with lists that rapidly remove and add
     * memberships. Take care when using these methods such that you are not too rapidly
     * switching between removals and adds on the same list.
     *
     * @param listId The ID of the list to add members to.
     * @param screenNames The screen names of users to add to the list.
     * @throws Exception
     */
    void createListMembers(long listId, String[] screenNames) throws Exception;

    /**
     * Removes multiple members to a list, by specifying a comma-separated list of member
     * ids or screen names. The authenticated user must own the list to be able to remove
     * members from it. Note that lists can't have more than 500 members, and you are
     * limited to removing up to 100 members to a list at a time with this method.
     * <br/><br/>
     * Please note that there can be issues with lists that rapidly remove and add
     * memberships. Take care when using these methods such that you are not too rapidly
     * switching between removals and adds on the same list.
     *
     * @param listId The ID of the list to remove members from.
     * @param userIds The IDs of users to remove from the list.
     * @throws Exception
     */
    void destroyListMembers(long listId, long[] userIds) throws Exception;

    /**
     * Removes multiple members to a list, by specifying a comma-separated list of member
     * ids or screen names. The authenticated user must own the list to be able to remove
     * members from it. Note that lists can't have more than 500 members, and you are
     * limited to removing up to 100 members to a list at a time with this method.
     * <br/><br/>
     * Please note that there can be issues with lists that rapidly remove and add
     * memberships. Take care when using these methods such that you are not too rapidly
     * switching between removals and adds on the same list.
     *
     * @param listId The ID of the list to remove members from.
     * @param screenNames The screen names of users to remove from the list.
     * @throws Exception
     */
    void destroyListMembers(long listId, String[] screenNames) throws Exception;

    /**
     * Updates the specified list. The authenticated user
     * must own the list to be able to update it.
     * @param listId The ID of the list to update.
     * @param name The name for the list. A list's name must start with
     *             a letter and can consist only of 25 or fewer letters,
     *             numbers, "-", or "_" characters.
     * @param mode Whether your list is public or private.
     * @param description The description to give the list.
     * @throws Exception
     */
    UserList updateList(long listId, String name, UserListMode mode, String description) throws Exception;

    /**
     * Deletes the specified list. The authenticated user
     * must own the list to be able to destroy
     * @param listId The ID of the list to delete.
     * @throws Exception
     */
    UserList destroyList(long listId) throws Exception;

    /**
     * Returns the specified list. Private lists will only be shown if
     * the authenticated user owns the specified list.
     * @param listId The ID of the list to get.
     * @throws Exception
     */
    UserList getList(long listId) throws Exception;

    /**
     * Returns all {@link UserList}s the authenticating subscribes to, including their own.
     * @throws Exception
     */
    UserList[] getLists() throws Exception;

    /**
     * Returns all {@link UserList}s the specified user subscribes to, including their own.
     * @param userId The ID of the user to get lists for.
     * @throws Exception
     */
    UserList[] getLists(long userId) throws Exception;

    /**
     * Returns all {@link UserList}s the specified user subscribes to, including their own.
     * @param screenName The screen name of the user to get lists for.
     * @throws Exception
     */
    UserList[] getLists(String screenName) throws Exception;

    /**
     * Returns tweet timeline for members of the specified list.
     * @param listId The ID of the list to get statuses for.
     * @throws Exception
     */
    Status[] getListTimeline(long listId, Paging paging) throws Exception;

    /**
     * Returns the members of the specified list. Private list members will
     * only be shown if the authenticated user owns the specified list.
     * @param listId The ID of the list to get members of.
     * @param cursor Causes the list of connections to be broken into pages
     *               of no more than 5000 IDs at a time. The number of IDs
     *               returned is not guaranteed to be 5000 as suspended users
     *               are filtered out after connections are queried. To begin
     *               paging provide a value of -1 as the cursor.
     * @throws Exception
     */
    User[] getListMembers(long listId, long cursor) throws Exception;

    /**
     * Returns the subscribers of the specified list. Private list subscribers
     * will only be shown if the authenticated user owns the specified list.
     * @param listId The ID of the list to get subscribers of.
     * @param cursor Causes the list of connections to be broken into pages
     *               of no more than 5000 IDs at a time. The number of IDs
     *               returned is not guaranteed to be 5000 as suspended users
     *               are filtered out after connections are queried. To begin
     *               paging provide a value of -1 as the cursor.
     * @throws Exception
     */
    User[] getListSubscribers(long listId, long cursor) throws Exception;

    /**
     * Subscribes the authenticated user to the specified list.
     * @param listId The ID of the list to subscribe to.
     * @throws Exception
     */
    UserList createListSubscription(long listId) throws Exception;

    /**
     * Unsubscribes the authenticated user from the specified list.
     * @param listId The ID of the list to unsubscribe from.
     * @throws Exception
     */
    UserList destroyListSubscription(long listId) throws Exception;

    /**
     * Returns the top 20 trending topics for each hour in a given day.
     * @throws Exception
     */
    Trends[] getTrendsDaily() throws Exception;

    /**
     * Returns the top 30 trending topics for each day in a given week.
     * @throws Exception
     */
    Trends[] getTrendsWeekly() throws Exception;

    /**
     * Returns the locations that Twitter has trending topic information for. <br/><br/>
     * The response is an array of "locations" that encode the location's WOEID and some
     * other human-readable information such as a canonical name and country the location
     * belongs in.
     *
     * @param location The coordinates to sort the results (from close to far) with.
     * @throws Exception
     */
    TrendLocation[] getTrendsAvailable(GeoLocation location) throws Exception;

    /**
     * Returns the top 10 trending topics for a specific WOEID, if trending information is available for it.
     * @param woeid The WOEID extracted from the {@link Twitter#getTrendsAvailable(com.teamboid.twitterapi.status.GeoLocation)} function results.
     *
     * @throws Exception
     */
    Trend[] getLocationTrends(int woeid) throws Exception;

    /**
     * Gets global trends.
     *
     * @throws Exception
     */
    Trend[] getTrendsGlobal() throws Exception;

    /**
     * Returns all the information about a known place.
     * @throws Exception
     */
    Place getPlaceDetails(String placeId) throws Exception;

    /**
     * If available, returns an array of replies and mentions related to the specified Tweet. 
     * There is no guarantee there will be any replies or mentions in the response. This method 
     * is only available to users who have access to #newtwitter.
     * <br/><br/>This method has not been finalized and the interface is subject to change in incompatible ways.
     * 
     * @undocumented
     * @throws Exception
     */
    RelatedResults getRelatedResults(Long statusId) throws Exception;
    
    /**
     * Given a latitude and a longitude, searches for up to 20 places that can 
     * be used as a place_id when updating a status.
     * <br/><br/>
     * This request is an informative call and will deliver generalized results about geography
     * 
     * @param coordinates The current location to find nearby places for.
     * @param accuracy A hint on the "region" in which to search. If a number, then this is a 
     * 		radius in meters, but it can also take a string that is suffixed with ft to specify 
     * 		feet. If this is not passed in, then it is assumed to be 0m. If coming from a device, 
     * 		in practice, this value is whatever accuracy the device has measuring its location 
     * 		(whether it be coming from a GPS, WiFi triangulation, etc.).
     * @param gran This is the minimal granularity of place types to return and must be one of: 
     * 		poi, neighborhood, city, admin or country. If no granularity is provided for the request 
     * 		neighborhood is assumed. Setting this to city, for example, will find places which have 
     * 		a type of city, admin or country.
     * @param maxResults A hint as to the number of results to return. This does not guarantee that
     * 		the number of results returned will equal max_results, but instead informs how many "nearby" 
     * 		results to return. Ideally, only pass in the number of places you intend to display to the 
     * 		user here.
     * @throws Exception
     */
    Place[] getReverseGeocode(GeoLocation coordinates, String accuracy, Granularity gran, int maxResults) throws Exception;
}