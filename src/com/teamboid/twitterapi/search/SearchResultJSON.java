package com.teamboid.twitterapi.search;

import com.teamboid.twitterapi.json.JSONObject;

/**
 * Handles parsing JSON and assigning values to a {@link SearchQuery} interface.
 * @author Aidan Follestad
 */
public class SearchResultJSON implements SearchResult {

    public SearchResultJSON(JSONObject json) throws Exception {
        _completedIn = json.optDouble("completed_in");
        _maxId = json.optLong("max_id");
        _nextPage = json.optString("next_page");
        _page = json.optInt("page");
        _query = json.optString("query");
        _refreshUrl = json.optString("refresh_url");
        if(!json.isNull("results")) {
            _results = TweetJSON.createStatusList(json.getJSONArray("results"));
        }
        _resultsPerPage = json.optInt("results_per_page");
        _sinceId = json.optLong("since_id");
    }

    private double _completedIn;
    private long _maxId;
    private String _nextPage;
    private int _page;
    private String _query;
    private String _refreshUrl;
    private Tweet[] _results;
    private int _resultsPerPage;
    private long _sinceId;

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCompletedIn() { return _completedIn; }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getMaxId() { return _maxId; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNextPage() { return _nextPage; }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPage() { return _page; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getQuery() { return _query; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRefreshUrl() { return _refreshUrl; }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tweet[] getResults() { return _results; }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getResultsPerPage() { return _resultsPerPage; }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getSinceId() { return _sinceId; }
}
