package com.teamboid.twitterapi.auth;

import com.teamboid.twitterapi.client.Twitter;
import com.teamboid.twitterapi.client.TwitterBase;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * Used to authenticate accounts and get logged in instances of {@link Twitter}
 *
 * @author Aidan Follestad
 */
public class Authorizer {

    private Authorizer(String consumer, String secret, String callback) {
        service = new ServiceBuilder().provider(TwitterApi.class).apiKey(consumer)
                .apiSecret(secret).callback(callback).build();
    }

    private OAuthService service;
    private Token token;

    /**
     * Intializes a new Authorizer for generating authenticated {@link Twitter} instances.
     * @param consumer The OAuth consumer of your application that's registered on dev.twitter.com.
     * @param secret The OAuth secret of your application that's registered on dev.twitter.com.
     * @param callback The callback URL called after the user authorizes their account on the web page returned from getUrl().
     * @return A new Authorizer instance
     */
    public static Authorizer create(String consumer, String secret, String callback) {
        return new Authorizer(consumer, secret, callback);
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
        toReturn.token = new Token(accessKey, accessSecret);
        toReturn.oauth = service;
        return toReturn;
    }

    /**
     * The intitial step of authentication, returns the URL of Twitter's authorization page that you must open
     * in the web browser. When they login and click 'Authorize', the callback you specified in the constructor
     * will be invoked. Make sure your app is set up to receive this callback and use the callback() method.
     * @return
     */
    public String getUrl() {
        token = service.getRequestToken();
        return service.getAuthorizationUrl(token);
    }

    /**
     * The method called after your receive a callback from the web browser (after using getUrl()).
     * @param verifier The oauth_verifier paramter sent from the browser through the callback.
     */
    public Twitter finish(String verifier) {
        TwitterBase toReturn = new TwitterBase();
        toReturn.token = service.getAccessToken(token, new Verifier(verifier));
        toReturn.oauth = service;
        return toReturn;
    }
}
