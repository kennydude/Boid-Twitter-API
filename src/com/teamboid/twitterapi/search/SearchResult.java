package com.teamboid.twitterapi.search;

/**
 * Represents the search results returned from performing a {@link SearchQuery}
 * via {@link com.teamboid.twitterapi.client.Twitter#search(SearchQuery)}
 * @author Aidan Follestad
 */
public interface SearchResult {

    /**
     * Gets the seconds the search was performed in,
     */
    double getCompletedIn();

    /**
     * The max_id specified when the search query was performed.
     * @return
     */
    long getMaxId();

    /**
     * Gets the URL that would be used to get the next page of search results.
     * @return
     */
    String getNextPage();

    /**
     * Gets the current page index.
     */
    int getPage();

    /**
     * Gets the search query that was used when performing the search.
     */
    String getQuery();

    /**
     * Gets the URL that would be used to refresh the search.
     */
    String getRefreshUrl();

    /**
     * Gets the actual search results, containing a list of {@link Tweet} objects.
     * @return
     */
    Tweet[] getResults();

    /**
     * Gets the results per page (a.k.a count) specifed when performing the search.
     * @return
     */
    int getResultsPerPage();

    /**
     * The since_id specified when the search query was performed.
     */
    long getSinceId();
}
