package com.teamboid.twitterapi.dm;

import com.teamboid.twitterapi.user.User;

import java.util.Calendar;

/**
 * Represents a direct message, sent to another user or received from another user.
 *
 * @author Aidan Follestad
 */
public interface DirectMessage {

    Calendar getCreatedAt();

    String getSenderScreenName();

    User getSender();

    String getText();

    String getRecipientScreenName();

    long getId();

    User getRecipient();

    long getRecipientId();

    long getSenderId();
}
