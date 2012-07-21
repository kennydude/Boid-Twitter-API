package com.teamboid.twitterapi.search;

import com.teamboid.twitterapi.client.Paging;
import com.teamboid.twitterapi.client.Urls;
import com.teamboid.twitterapi.status.GeoLocation;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Aidan Follestad
 */
public class SearchQuery {

    private SearchQuery(String query, Paging paging) {
        _query = query;
        _paging = paging;
        _resultType = ResultType.DEFAULT;
    }
    private SearchQuery(String query, GeoCode geo, Paging paging, ResultType resultType, Date until) {
        _query = query;
        _geo = geo;
        _paging = paging;
        _resultType = resultType;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        _until = sf.format(until);
    }

    private String _query;
    private GeoCode _geo;
    private Paging _paging;
    private String _lang;
    private String _until;
    private ResultType _resultType;

    public static enum ResultType {
        /**
         * The result_type parameter will not be included in your search.
         */
        DEFAULT,
        /**
         * Include both popular and real time results in the response.
         */
        MIXED,
        /**
         * Return only the most recent results in the response.
         */
        RECENT,
        /**
         * Return only the most popular results in the response.
         */
        POPULAR
    }

    public String getUrl() {
        try {
            String url = Urls.SEARCH_QUERY + "?" + URLEncoder.encode(_query, "UTF8");
            if(_geo != null) {
                url += ("&" + _geo.getLatitude() + "," + _geo.getLongitude() + "," +
                        _geo.getDistance() + _geo.getUnit().name().toLowerCase());
            }
            if(_paging != null) {
                url += _paging.getUrlString('&', true).replace("count=", "rpp=");
            }
            if(_lang != null) url += "&lang=" + _lang;
            if(_until != null) url += "&until=" + _until;
            if(_resultType != ResultType.DEFAULT) {
                url += "&result_type=" + _resultType.name().toLowerCase();
            }
            return url;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
