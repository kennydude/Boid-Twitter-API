package com.teamboid.twitterapi.search;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Aidan Follestad
 */
public class SearchResultJSON implements SearchResult {

    public SearchResultJSON(JSONObject json) throws Exception {
        _completedIn = json.getDouble("completed_in");
        _maxId = json.getLong("max_id");
        _nextPage = json.getString("next_page");
        _page = json.getInt("page");
        _query = json.getString("query");
        _refreshUrl = json.getString("refresh_url");
        _results = TweetJSON.createStatusList(json.getJSONArray("results"));
        _resultsPerPage = json.getInt("results_per_page");
        _sinceId = json.getLong("since_id");
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

    @Override
    public double getCompletedIn() { return _completedIn; }

    @Override
    public long getMaxId() { return _maxId; }

    @Override
    public String getNextPage() { return _nextPage; }

    @Override
    public int getPage() { return _page; }

    @Override
    public String getQuery() { return _query; }

    @Override
    public String getRefreshUrl() { return _refreshUrl; }

    @Override
    public Tweet[] getResults() { return _results; }

    @Override
    public int getResultsPerPage() { return _resultsPerPage; }

    @Override
    public long getSinceId() { return _sinceId; }
}
