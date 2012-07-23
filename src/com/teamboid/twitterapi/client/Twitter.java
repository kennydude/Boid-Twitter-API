package com.teamboid.twitterapi.client;

import com.teamboid.twitterapi.dm.DirectMessage;
import com.teamboid.twitterapi.relationship.IDs;
import com.teamboid.twitterapi.relationship.Relationship;
import com.teamboid.twitterapi.search.SearchQuery;
import com.teamboid.twitterapi.search.SearchResult;
import com.teamboid.twitterapi.status.Status;
import com.teamboid.twitterapi.status.StatusUpdate;
import com.teamboid.twitterapi.user.User;

import java.io.File;

/**
 * The main class used within this library, used for performing API actions on Twitter.
 * Use the {@link Authorizer} to authenticate an account and
 * get an authorized instance of this class.
 *
 * @author Aidan Follestad
 */
public interface Twitter {

    /**
     * Gets the current authenticated user's {@link User} object,
     * @throws Exception
     */
    User verifyCredentials() throws Exception;

    /**
     * Updates the authenticated user's profile picture.
     * @param file The image file to update the profile picture with.
     * @throws Exception
     */
    User updateProfileImage(File file) throws Exception;

    /**
     * Gets the access token used to authenticate requests made to Twitter,
     */
    String getAccessToken();

    /**
     * Gets the access secret used to authenticate requests made to Twitter.
     */
    String getAccessSecret();

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
    Status[] getUserTimeline(Paging paging) throws Exception;

    /**
     * Gets the timeline of another user.
     * @param userId The ID of the user to get the timeline of.
     * @throws Exception
     */
    Status[] getUserTimeline(long userId, Paging paging) throws Exception;

    /**
     * Gets the timeline of another user.
     * @param screenName The screen name of the user to get the timeline of.
     * @throws Exception
     */
    Status[] getUserTimeline(String screenName, Paging paging) throws Exception;

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
    Status showStatus(long statusId) throws Exception;

    /**
     * Reteets a Status on twitter.
     * @param statusId The ID of the status to retweet.
     * @throws Exception
     */
    Status retweetStatus(long statusId) throws Exception;

    /**
     * Deletes a Status on Twitter that was created by the authenticated user.
     * @param statusId The ID of the status to delete.
     * @throws Exception
     */
    Status destroyStatus(long statusId) throws Exception;

    /**
     * Gets retweets of a specified tweet.
     * @param statusId The ID of the status to get retweets of.
     * @param count The optional number of retweets to retrieve.
     * @throws Exception
     */
    Status[] getRetweets(long statusId, int count) throws Exception;

    /**
     * Gets a list of {@link User} objects that retweeted a specified status.
     * @param statusId The ID of the status to get retweeters of.
     * @throws Exception
     */
    User[] getRetweetedBy(long statusId, Paging paging) throws Exception;

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
    DirectMessage createDirectMessage(long userId, String text) throws Exception;

    /**
     * Retrieves a direct message by its ID.
     * @param msgId The ID of the message to get.
     * @throws Exception
     */
    DirectMessage showDirectMessage(long msgId) throws Exception;

    /**
     * Deletes a direct message.
     * @param msgId The ID of the direct message to delete.
     * @throws Exception
     */
    DirectMessage destroyDirectMessage(long msgId) throws Exception;

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
    Status[] getFavorites(Paging paging, long userId) throws Exception;

    /**
     * Favorites a Status on Twitter.
     * @param statusId The ID of the status to favorite.
     * @throws Exception
     */
    Status createFavorite(long statusId) throws Exception;

    /**
     * Un-favorites a Status on Twitter.
     * @param statusId The ID of the status to un-favorite.
     * @throws Exception
     */
    Status destroyFavorite(long statusId) throws Exception;

    /**
     * Retrieves the {@link User} of a Twitter profile.
     * @param userId The ID of the user to get.
     * @throws Exception
     */
    User showUser(long userId) throws Exception;

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
    User[] lookupUsers(long[] userIds) throws Exception;

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
     * @param cursor Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried. To begin paging provide a value of -1 as the cursor. The response from the API will include a previous_cursor and next_cursor to allow paging back and forth.
     * @throws Exception
     */
    IDs getFriends(String screenName, long cursor) throws Exception;

    /**
     * Returns {@link IDs} containing every user the specified user is following.
     * @param userId The ID of the user for whom to return results for.
     * @param cursor Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried. To begin paging provide a value of -1 as the cursor. The response from the API will include a previous_cursor and next_cursor to allow paging back and forth.
     * @throws Exception
     */
    IDs getFriends(long userId, long cursor) throws Exception;

    /**
     * Returns {@link IDs} containing every user following the specified user.
     * @param screenName The screen name of the user for whom to return results for.
     * @param cursor Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried. To begin paging provide a value of -1 as the cursor. The response from the API will include a previous_cursor and next_cursor to allow paging back and forth.
     * @throws Exception
     */
    IDs getFollowers(String screenName, long cursor) throws Exception;

    /**
     * Returns {@link IDs} containing every user following the specified user.
     * @param userId The ID of the user for whom to return results for.
     * @param cursor Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried. To begin paging provide a value of -1 as the cursor. The response from the API will include a previous_cursor and next_cursor to allow paging back and forth.
     * @throws Exception
     */
    IDs getFollowers(long userId, long cursor) throws Exception;

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
    IDs getFriendshipsIncoming(long cursor) throws Exception;

    /**
     * Returns {@link IDs} containing every user who has a pending request to follow the authenticating user.
     * @param cursor Breaks the results into pages. This is recommended for users who are following many users. Provide a value of -1 to begin paging. Provide values as returned in the response body's next_cursor and previous_cursor attributes to page back and forth in the list.
     * @throws Exception
     */
    IDs getFriendshipsOutgoing(long cursor) throws Exception;

    /**
     * Returns detailed information about the relationship between two users.
     * @param fromUserId The ID of the source user.
     * @param toUserId The ID of the target user.
     * @throws Exception
     */
    Relationship getRelationship(long fromUserId, long toUserId) throws Exception;

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
    User createFriendship(long userId) throws Exception;

    /**
     * Follows the specified user for the authenticated account.
     * @param screenName The screen name of the user to follow.
     */
    User createFriendship(String screenName) throws Exception;

    /**
     * Un-follows the specified user for the authenticated account.
     * @param userId The ID of the user to un-follow.
     */
    User destroyFriendship(long userId) throws Exception;

    /**
     * Un-follows the specified user for the authenticated account.
     * @param screenName The screen name of the user to un-follow.
     */
    User destroyFriendship(String screenName) throws Exception;

    //TODO
    void getRelatedResults(long statusId) throws Exception;
}