package com.teamboid.twitterapi.client;

import com.teamboid.twitterapi.status.Status;

/**
 * The main class used within this library, used for authenticating an account and performing API actions on Twitter.
 */
public interface Twitter {

    Status[] getPublicTimeline() throws Exception;
}
