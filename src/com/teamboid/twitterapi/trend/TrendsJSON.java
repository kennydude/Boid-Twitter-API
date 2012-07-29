package com.teamboid.twitterapi.trend;

import com.teamboid.twitterapi.json.JSONArray;
import com.teamboid.twitterapi.json.JSONObject;
import com.teamboid.twitterapi.utilities.Time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 * @author Aidan Follestad
 */
public class TrendsJSON implements Trends, Serializable {

	private static final long serialVersionUID = 8152676401237837038L;

	public TrendsJSON(String date, JSONArray trends) throws Exception {
        _date = Time.getTrendsDate(date);
        _trends = TrendJSON.createTrendList(trends);
    }

    private Calendar _date;
    private Trend[] _trends;

    /**
     * {@inheritDoc}
     */
    @Override
    public Calendar getTime() { return _date; }

    /**
     * {@inheritDoc}
     */
    @Override
    public Trend[] getTrends() { return _trends; }

    public static Trends[] createTrendsList(JSONObject json) throws Exception {
        json = json.getJSONObject("trends");
        ArrayList<Trends> toReturn = new ArrayList<Trends>();
        Iterator<?> keys = json.keys();
        while(keys.hasNext()) {
            String name = (String)keys.next();
            toReturn.add(new TrendsJSON(name, json.getJSONArray(name)));
        }
        return toReturn.toArray(new Trends[0]);
    }
}