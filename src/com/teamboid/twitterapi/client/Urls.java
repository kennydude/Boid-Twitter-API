package com.teamboid.twitterapi.client;

/**
 * Contains URL constants for the Twitter API functions.
 * @author Aidan Follestad
 */
public class Urls {

    public final static String BASE_API_URL = "http://api.twitter.com/1";
    public final static String BASE_MEDIA_URL = "http://upload.twitter.com/1";


    public final static String HOME_TIMELINE = BASE_API_URL + "/statuses/home_timeline.json?include_entities=true&include_rts=true";
    public final static String MENTIONS = BASE_API_URL + "/statuses/mentions.json?include_entities=true&include_rts=true";
    public final static String USER_TIMELINE = BASE_API_URL + "/statuses/user_timeline.json?include_entities=true&include_rts=true";

    public final static String SHOW_STATUS = BASE_API_URL + "/statuses/show/{id}.json?include_entities=true";
    public final static String RETWEET_STATUS = BASE_API_URL + "/statuses/retweet/{id}.json?include_entities=true";
    public final static String DESTROY_STATUS = BASE_API_URL + "/statuses/destroy/{id}.json?include_entities=true";

    public final static String SHOW_USER = BASE_API_URL + "/users/show.json?include_entities=true";
    public final static String LOOKUP_USERS = BASE_API_URL + "/users/lookup.json";
    public final static String GET_PROFILE_IMAGE = BASE_API_URL + "/users/profile_image";

    public final static String RETWEETS = BASE_API_URL + "/statuses/retweets/{id}.json?include_entities=true";
    public final static String RETWEETS_OF_ME = BASE_API_URL + "/statuses/retweets_of_me.json?include_entities=true";
    public final static String RETWEETED_BY = BASE_API_URL + "/statuses/{id}/retweeted_by.json";

    public final static String SEARCH_QUERY = "http://search.twitter.com/search.json";
    public final static String SEARCH_USERS = BASE_API_URL + "/users/search.json?include_entities=true";

    public final static String VERIFY_CREDENTIALS = BASE_API_URL + "/account/verify_credentials.json?include_entities=true";
    public final static String RELATED_RESULTS = BASE_API_URL + "/related_results/show/{id}.json";

    public final static String UPDATE_STATUS = BASE_API_URL + "/statuses/update.json";
    public final static String UPDATE_STATUS_MEDIA = BASE_MEDIA_URL + "/statuses/update_with_media.json";

    public final static String DIRECT_MESSAGES = BASE_API_URL + "/direct_messages.json?include_entities=true";
    public final static String DIRECT_MESSAGES_SENT = BASE_API_URL + "/direct_messages/sent.json?include_entities=true";
    public final static String CREATE_DIRECT_MESSAGE = BASE_API_URL + "/direct_messages/new.json";
    public final static String SHOW_DIRECT_MESSAGE = BASE_API_URL + "/direct_messages/show/{id}.json";
    public final static String DESTROY_DIRECT_MESSAGE = BASE_API_URL + "/direct_messages/destroy/{id}.json";

    public final static String GET_FAVORITES = BASE_API_URL + "/favorites.json?include_entities=true";
    public final static String CREATE_FAVORITE = BASE_API_URL + "/favorites/create/{id}.json";
    public final static String DESTROY_FAVORITE = BASE_API_URL + "/favorites/destroy/{id}.json";

    public final static String GET_FOLLOWERS = BASE_API_URL + "/followers/ids.json";
    public final static String GET_FRIENDS = BASE_API_URL + "/friends/ids.json";
    public final static String FRIENDSHIP_EXISTS = BASE_API_URL + "/friendships/exists.json";
    public final static String INCOMING_FRIENDSHIPS = BASE_API_URL + "/friendships/incoming.json";
    public final static String OUTGOING_FRIENDSHIPS = BASE_API_URL + "/friendships/outgoing.json";
    public final static String SHOW_FRIENDSHIP = BASE_API_URL + "/friendships/show.json";
    public final static String CREATE_FRIENDSHIP = BASE_API_URL + "/friendships/create.json";
    public final static String DESTROY_FRIENDSHIP = BASE_API_URL + "/friendships/destroy.json";
}
