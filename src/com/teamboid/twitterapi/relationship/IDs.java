package com.teamboid.twitterapi.relationship;

/**
 * @author Aidan Follestad
 */
public interface IDs {

    Long getPreviousCursor();

    Long[] getIds();

    Long getNextCursor();
}
