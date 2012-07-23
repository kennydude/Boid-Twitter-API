package com.teamboid.twitterapi.client;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

/**
 * Used to authenticate accounts and get logged in instances of {@link Twitter}
 *
 * @author Aidan Follestad
 */
public class Authorizer {

    private Authorizer(String consumer, String secret, String callback) {
        service = new CommonsHttpOAuthConsumer(consumer, secret);
        provider = new DefaultOAuthProvider(
                "http://twitter.com/oauth/request_token",
                "http://twitter.com/oauth/access_token",
                "http://twitter.com/oauth/authorize");
    }

    private OAuthProvider provider;
    private OAuthConsumer service;
    private String _callback;
    private String tokenKey;
    private String tokenSecret;
    private boolean _debugMode;
    private boolean _ssl;

    /**
     * Intializes a new Authorizer for generating authenticated {@link Twitter} instances.
     * @param consumer The OAuth consumer of your application that's registered on dev.twitter.com.
     * @param secret The OAuth secret of your application that's registered on dev.twitter.com.
     * @param callback The callback URL called after the user authorizes their account on the web page returned from getUrl().
     * @return A new Authorizer instance
     */
    public static Authorizer create(String consumer, String secret, String callback) {
        Authorizer toReturn = new Authorizer(consumer, secret, callback);
        toReturn._callback = callback;
        return toReturn;
    }

    /**
     * This method is used to get an authorized {@link Twitter} instance if you had already gotten
     * authorization previously and stored the returned access token.
     * @param accessKey The access key previously stored.
     * @param accessSecret The access secret preeviously stored.
     * @return An authenticated Twitter instance.
     */
    public Twitter getAuthorizedInstance(String accessKey, String accessSecret) {
        TwitterBase toReturn = new TwitterBase();
        toReturn._accessToken = accessKey;
        toReturn._accessSecret = accessSecret;
        toReturn._consumer = service.getConsumerKey();
        toReturn._consumerSecret = service.getConsumerSecret();
        toReturn._debugOn = _debugMode;
        toReturn._ssl = _ssl;
        return toReturn;
    }

    /**
     * The method called after your receive a callback from the web browser (after using getUrl()).
     * @param verifier The oauth_verifier paramter sent from the browser through the callback.
     */
    public Twitter getAuthorizedInstance(String verifier) throws Exception {
        provider.retrieveAccessToken(service, verifier);
        TwitterBase toReturn = new TwitterBase();
        toReturn._accessToken = service.getToken();
        toReturn._accessSecret = service.getTokenSecret();
        toReturn._consumer = service.getConsumerKey();
        toReturn._consumerSecret = service.getConsumerSecret();
        toReturn._debugOn = _debugMode;
        toReturn._ssl = _ssl;
        return toReturn;
    }

    /**
     * The initial step of authentication, returns the URL of Twitter's authorization page that you must open
     * in the web browser. When they login and click 'Authorize', the callback you specified in the constructor
     * will be invoked. Make sure your app is set up to receive this callback and use the callback() method.
     * @return
     */
    public String getAuthorizeUrl() throws Exception { return provider.retrieveRequestToken(service, _callback); }

    /**
     * Sets whether or not SSL will be used with requests to Twitter. Defaults as true.
     */
    public Authorizer setUseSSL(boolean ssl) {
        _ssl = ssl;
        return this;
    }

    /**
     * Sets whether or not debug is on, useful messages are printed to the console when it's on. Defaults to off.
     */
    public Authorizer setDebugMode(boolean debug) {
        _debugMode = debug;
        return this;
    }
}