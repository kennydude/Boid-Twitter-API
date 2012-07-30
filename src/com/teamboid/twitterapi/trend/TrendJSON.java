package com.teamboid.twitterapi.trend;

import com.teamboid.twitterapi.json.JSONArray;
import com.teamboid.twitterapi.json.JSONObject;
import com.teamboid.twitterapi.utilities.Utils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Handles parsing JSON and assigning values to a {@link Trend} interface.
 * @author Aidan Follestad
 */
public class TrendJSON implements Trend, Serializable {

	private static final long serialVersionUID = -4466427622837789801L;

	public TrendJSON(JSONObject json) {
        _query = json.optString("query");
        _name = Utils.unescape(json.optString("name"));
        _url = json.optString("url");
    }

    private String _query;
    private String _name;
    private String _url;

    /**
     * This function is not functional yet as the the intended value is unknown.
     */
    @Override
    public void getEvents() {
        //TODO
    }

    /**
     * This function is not functional yet as the the intended value is unknown.
     */
    @Override
    public void getPromotedContent() {
        //TODO
    }

    @Override
    public String getName() { return _name; }

    @Override
    public String getQuery() { return _query; }

    @Override
    public String getUrl() { return _url; }

    public static Trend[] createTrendList(JSONArray array) throws Exception {
        ArrayList<Trend> toReturn = new ArrayList<Trend>();
        for(int i = 0; i < array.length(); i++) {
            toReturn.add(new TrendJSON(array.getJSONObject(i)));
        }
        return toReturn.toArray(new Trend[0]);
    }
}