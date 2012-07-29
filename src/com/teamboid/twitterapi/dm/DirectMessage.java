package com.teamboid.twitterapi.dm;

import com.teamboid.twitterapi.user.User;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Represents a direct message, sent to another user or received from another user.
 *
 * @author Aidan Follestad
 */
public interface DirectMessage extends Serializable {

    /**
     * Gets the date and time when the direct message was sent.
     */
    Calendar getCreatedAt();

    /**
     * Gets the screen name of the user that sent the direct message.
     */
    String getSenderScreenName();

    /**
     * Gets the {@link User} object of the user that sent the direct message.
     * If you only want the screen name of the sending user, use {@link com.teamboid.twitterapi.dm.DirectMessage#getSenderScreenName()}
     */
    User getSender();

    /**
     * Gets the text content of the direct message, will be 140 character or less in length.
     */
    String getText();

    /**
     * Gets the screen name of the {@link User} that received the message.
     */
    String getRecipientScreenName();

    /**
     * Gets the ID of the direct message.
     */
    long getId();

    /**
     * Gets the {@link User} object of the user that received the direct message.
     * If you only want the screen name of the recipient user, use {@link com.teamboid.twitterapi.dm.DirectMessage#getRecipientScreenName()}
     */
    User getRecipient();

    /**
     * Gets the ID of the user that received the direct message.
     */
    long getRecipientId();

    /**
     * Gets the ID of the user that sent the direct message.
     */
    long getSenderId();
}
