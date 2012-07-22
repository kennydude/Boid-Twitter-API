package com.teamboid.twitterapi.search;

/**
 * @author Aidan Follestad
 */
public interface SearchResult {

    double getCompletedIn();

    long getMaxId();

    String getNextPage();

    int getPage();

    String getQuery();

    String getRefreshUrl();

    Tweet[] getResults();

    int getResultsPerPage();

    long getSinceId();
}
