package com.teamboid.twitterapi.trend;

import com.teamboid.twitterapi.json.JSONArray;
import com.teamboid.twitterapi.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Aidan Follestad
 */
public class TrendLocationJSON implements TrendLocation, Serializable {

	private static final long serialVersionUID = 3666580020698044751L;

	public TrendLocationJSON(JSONObject json) throws Exception {
        _woeid = json.optInt("woeid");
        _name = json.optString("name");
        _countryCode = json.optString("countryCode");
        _parentId = json.optInt("parentid");
        _url = json.optString("url");
        _country = json.optString("country");
        if(!json.isNull("placeType")) {
            JSONObject placeType = json.getJSONObject("placeType");
            _placeName = placeType.optString("name");
            _placeCode = placeType.optInt("code");
        }
    }

    private int _woeid;
    private String _name;
    private String _countryCode;
    private int _parentId;
    private String _placeName;
    private int _placeCode;
    private String _url;
    private String _country;

    @Override
    public int getWoeId() { return _woeid; }

    @Override
    public String getName() { return _name; }

    @Override
    public String getCountryCode() { return _countryCode; }

    @Override
    public int getParentId() { return _parentId; }

    @Override
    public String getPlaceTypeName() { return _placeName; }

    @Override
    public int getPlaceTypeCode() { return _placeCode; }

    @Override
    public String getUrl() { return _url; }

    @Override
    public String getCountry() { return _country; }

    public static TrendLocation[] createLocationList(JSONArray array) throws Exception {
        ArrayList<TrendLocation> toReturn = new ArrayList<TrendLocation>();
        for(int i = 0; i < array.length(); i++) {
            toReturn.add(new TrendLocationJSON(array.getJSONObject(i)));
        }
        return toReturn.toArray(new TrendLocation[0]);
    }
}