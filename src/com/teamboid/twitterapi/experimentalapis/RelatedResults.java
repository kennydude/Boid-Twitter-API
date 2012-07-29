package com.teamboid.twitterapi.experimentalapis;

import java.io.Serializable;

import com.teamboid.twitterapi.status.Status;

/**
 * @author Aidan Follestad
 */
public interface RelatedResults extends Serializable {
	
	/**
     * Returns the 8 or less statuses with conversation
     */
    Status[] getTweetsWithConversation();

    /**
     * Returns the 8 or less statuses with reply.
     */
    Status[] getTweetsWithReply();

    /**
     * Return the 3 or less latest statuses from the user who sent the origin tweet.
     */
    Status[] getTweetsFromUser();
}
