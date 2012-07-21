package com.teamboid.twitterapi.client;

import com.teamboid.twitterapi.search.SearchQuery;
import com.teamboid.twitterapi.search.Tweet;

/**
 * @author Aidan Follestad
 */
public interface SearchMethods {

    Tweet[] search(SearchQuery query);
}
