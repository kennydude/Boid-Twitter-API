package com.teamboid.twitterapi.savedsearch;

import com.teamboid.twitterapi.json.JSONArray;
import com.teamboid.twitterapi.json.JSONObject;
import com.teamboid.twitterapi.utilities.Time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Aidan Follestad
 */
public class SavedSearchJSON implements SavedSearch, Serializable {

	private static final long serialVersionUID = 5122274312861363425L;

	public SavedSearchJSON(JSONObject json) throws Exception {
        _name = json.optString("name");
        _pos = json.optInt("position");
        _createdAt = Time.getTwitterDate(json.getString("created_at"));
        _id = json.optLong("id");
        _query = json.optString("query");
    }

    private String _name;
    private int _pos;
    private Calendar _createdAt;
    private long _id;
    private String _query;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() { return _name; }

    /**     * {@inheritDoc}
     */
    @Override
    public int getPosition() { return _pos; }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calendar getCreatedAt() { return _createdAt; }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getId() { return _id; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getQuery() { return _query; }

    public static SavedSearch[] createSavedSearchList(JSONArray array) throws Exception {
        ArrayList<SavedSearch> toReturn = new ArrayList<SavedSearch>();
        for(int i = 0; i < array.length(); i++) {
            toReturn.add(new SavedSearchJSON(array.getJSONObject(i)));
        }
        return toReturn.toArray(new SavedSearch[0]);
    }

}