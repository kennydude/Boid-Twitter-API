package com.teamboid.twitterapi.client;

public class Urls {

    public final static String PUBLIC_TIMELINE = "http://api.twitter.com/1/statuses/public_timeline.json?include_entities=true";
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
    public final static String UPDATE_STATUS = "http://api.twitter.com/1/statuses/update.json";
}
