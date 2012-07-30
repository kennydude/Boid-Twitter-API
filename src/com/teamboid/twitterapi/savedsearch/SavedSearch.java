package com.teamboid.twitterapi.savedsearch;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Aidan Follestad
 */
public interface SavedSearch extends Serializable {

    /**
     * Gets the name of the saved search,
     */
    String getName();

    /**
     * Gets the index of the saved search.
     */
    int getPosition();

    /**
     * Gets the date and time that the saved search was created.
     * @return
     */
    Calendar getCreatedAt();

    /**
     * Gets the ID of the saved search.
     */
    long getId();

    /**
     * Gets the query that is searched when performing the saved search.
     */
    String getQuery();
}
