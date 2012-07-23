package com.teamboid.twitterapi.client;

/**
 * Contains URL constants for the Twitter API functions.
 * @author Aidan Follestad
 */
public class Urls {

    public final static String BASE_API_URL = "http://api.twitter.com/1";
    public final static String BASE_MEDIA_URL = "http://upload.twitter.com/1";


    public final static String HOME_TIMELINE = "/statuses/home_timeline.json?include_entities=true&include_rts=true";
    public final static String MENTIONS = "/statuses/mentions.json?include_entities=true&include_rts=true";
    public final static String USER_TIMELINE = "/statuses/user_timeline.json?include_entities=true&include_rts=true";

    public final static String SHOW_STATUS = "/statuses/show/{id}.json?include_entities=true";
    public final static String RETWEET_STATUS = "/statuses/retweet/{id}.json?include_entities=true";
    public final static String DESTROY_STATUS = "/statuses/destroy/{id}.json?include_entities=true";

    public final static String SHOW_USER = "/users/show.json?include_entities=true";
    public final static String LOOKUP_USERS = "/users/lookup.json";
    public final static String GET_PROFILE_IMAGE = "/users/profile_image";

    public final static String RETWEETS = "/statuses/retweets/{id}.json?include_entities=true";
    public final static String RETWEETS_OF_ME = "/statuses/retweets_of_me.json?include_entities=true";
    public final static String RETWEETED_BY = "/statuses/{id}/retweeted_by.json";

    public final static String SEARCH_QUERY = "http://search.twitter.com/search.json";
    public final static String SEARCH_USERS = "/users/search.json?include_entities=true";

    public final static String VERIFY_CREDENTIALS = "/account/verify_credentials.json?include_entities=true";
    public final static String API_CONFIG = "/help/configuration.json";

    public final static String RELATED_RESULTS = "/related_results/show/{id}.json";

    public final static String UPDATE_STATUS = "/statuses/update.json";
    public final static String UPDATE_STATUS_MEDIA = BASE_MEDIA_URL + "/statuses/update_with_media.json";

    public final static String DIRECT_MESSAGES = "/direct_messages.json?include_entities=true";
    public final static String DIRECT_MESSAGES_SENT = "/direct_messages/sent.json?include_entities=true";
    public final static String CREATE_DIRECT_MESSAGE = "/direct_messages/new.json";
    public final static String SHOW_DIRECT_MESSAGE = "/direct_messages/show/{id}.json";
    public final static String DESTROY_DIRECT_MESSAGE = "/direct_messages/destroy/{id}.json";

    public final static String GET_FAVORITES = "/favorites.json?include_entities=true";
    public final static String CREATE_FAVORITE = "/favorites/create/{id}.json";
    public final static String DESTROY_FAVORITE = "/favorites/destroy/{id}.json";

    public final static String GET_FOLLOWERS = "/followers/ids.json";
    public final static String GET_FRIENDS = "/friends/ids.json";
    public final static String FRIENDSHIP_EXISTS = "/friendships/exists.json";
    public final static String INCOMING_FRIENDSHIPS = "/friendships/incoming.json";
    public final static String OUTGOING_FRIENDSHIPS = "/friendships/outgoing.json";
    public final static String SHOW_FRIENDSHIP = "/friendships/show.json";
    public final static String CREATE_FRIENDSHIP = "/friendships/create.json";
    public final static String DESTROY_FRIENDSHIP = "/friendships/destroy.json";

    public final static String UPDATE_PROFILE_IMAGE = "/account/update_profile_image.json";
}
