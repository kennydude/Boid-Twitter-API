package com.teamboid.twitterapi.client;

public class Urls {

    public final static String HOME_TIMELINE = "http://api.twitter.com/1/statuses/home_timeline.json?include_entities=true&include_rts=true";
    public final static String MENTIONS = "http://api.twitter.com/1/statuses/mentions.json?include_entities=true&include_rts=true";
    public final static String USER_TIMELINE = "http://api.twitter.com/1/statuses/user_timeline.json?include_entities=true&include_rts=true";

    public final static String SHOW_STATUS = "http://api.twitter.com/1/statuses/show/{id}.json?include_entities=true";
    public final static String RETWEET_STATUS = "http://api.twitter.com/1/statuses/retweet/{id}.json?include_entities=true";
    public final static String DESTROY_STATUS = "http://api.twitter.com/1/statuses/destroy/{id}.json?include_entities=true";

    public final static String SHOW_USER = "http://api.twitter.com/1/users/show.json?include_entities=true";

    public final static String RETWEETS = "http://api.twitter.com/1/statuses/retweets/{id}.json?include_entities=true";
    public final static String RETWEETS_OF_ME = "http://api.twitter.com/1/statuses/retweets_of_me.json?include_entities=true";
    public final static String RETWEETED_BY = "http://api.twitter.com/1/statuses/{id}/retweeted_by.json";

    public final static String SEARCH_QUERY = "http://search.twitter.com/search.json";
    public final static String SEARCH_USERS = "http://api.twitter.com/1/users/search.json?include_entities=true";

    public final static String VERIFY_CREDENTIALS = "http://api.twitter.com/1/account/verify_credentials.json?include_entities=true";
    public final static String RELATED_RESULTS = "http://api.twitter.com/1/related_results/show/{id}.json";

    public final static String UPDATE_STATUS = "http://api.twitter.com/1/statuses/update.json";
    public final static String UPDATE_STATUS_MEDIA = "http://upload.twitter.com/1/statuses/update_with_media.json";

    public final static String DIRECT_MESSAGES = "http://api.twitter.com/1/direct_messages.json?include_entities=true";
    public final static String DIRECT_MESSAGES_SENT = "https://api.twitter.com/1/direct_messages/sent.json?include_entities=true";
    public final static String CREATE_DIRECT_MESSAGE = "https://api.twitter.com/1/direct_messages/new.json";
    public final static String SHOW_DIRECT_MESSAGE = "https://api.twitter.com/1/direct_messages/show/{id}.json";
    public final static String DESTROY_DIRECT_MESSAGE = "http://api.twitter.com/1/direct_messages/destroy/{id}.json";
}
