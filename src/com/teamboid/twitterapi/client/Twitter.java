package com.teamboid.twitterapi.client;

import com.teamboid.twitterapi.status.Status;
import com.teamboid.twitterapi.user.User;

/**
 * The main class used within this library, used for authenticating an account and performing API actions on Twitter.
 */
public interface Twitter {

    void getAuthorization(String consumerKey, String consumerSecret,
                             String accessToken, String accessSecret);





}
