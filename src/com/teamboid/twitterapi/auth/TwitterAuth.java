package com.teamboid.twitterapi.auth;

import com.teamboid.twitterapi.client.Twitter;
import com.teamboid.twitterapi.client.TwitterBase;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * Handles the authentication of Twitter accounts use OAuth.
 */
public class TwitterAuth {

    private TwitterAuth(String consumer, String secret, String callback) {
        service = new ServiceBuilder().provider(TwitterApi.class).apiKey(consumer)
                .apiSecret(secret).callback(callback).build();
    }

    private OAuthService service;
    private Token token;

    public static TwitterAuth create(String consumer, String secret, String callback) {
        return new TwitterAuth(consumer, secret, callback);
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
