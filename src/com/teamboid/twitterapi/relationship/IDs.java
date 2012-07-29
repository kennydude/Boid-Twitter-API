package com.teamboid.twitterapi.relationship;

import java.io.Serializable;

/**
 * @author Aidan Follestad
 */
public interface IDs extends Serializable {

    Long getPreviousCursor();

    Long[] getIds();

    Long getNextCursor();
}
