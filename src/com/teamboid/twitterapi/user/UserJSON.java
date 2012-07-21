package com.teamboid.twitterapi.user;

import com.teamboid.twitterapi.utilities.Time;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Handles parsing JSON and assigning values to a {@link User} interface.
 *
 * @author Aidan Follestad
 */
public class UserJSON implements User {

    public UserJSON(JSONObject json) throws JSONException {
        _id = json.getInt("id");
        _name = json.getString("name");
        if(!json.isNull("url")) {
            _url = json.getString("url");
        }
        _createdAt = Time.getTwitterDate(json.getString("created_at"));
        _profileImageUrl = json.getString("profile_image_url");
        if(!json.isNull("location")) {
            _location = json.getString("location");
        }
        if(!json.isNull("follow_request_sent")) {
            if(json.getBoolean("follow_request_sent")) {
                _following = FollowingType.REQUEST_SENT;
            }
        }
        _isTranslator = json.getBoolean("is_translator");
        _favoritesCount = json.getLong("favourites_count");
        _isProtected = json.getBoolean("protected");
        _followersCount = json.getLong("followers_count");
        _language = json.getString("lang");
        _isVerified = json.getBoolean("verified");
        if(!json.isNull("description")) {
            _description = json.getString("description");
        }
        _friendsCount = json.getLong("friends_count");
        _statusCount = json.getLong("statuses_count");
        if(_following != FollowingType.REQUEST_SENT && !json.isNull("following")) {
            if(json.getBoolean("following")) {
                _following = FollowingType.FOLLOWING;
            } else _following = FollowingType.NOT_FOLLOWING;
        }
        _screenName = json.getString("screen_name");
    }

    private long _id;
    private String _name;
    private String _url;
    private String _screenName;
    private Calendar _createdAt;
    private String _location;
    private String _profileImageUrl;
    private boolean _isVerified;
    private boolean _isProtected;
    private boolean _isTranslator;
    private long _friendsCount;
    private long _followersCount;
    private long _statusCount;
    private String _language;
    private String _description;
    private FollowingType _following;
    private long _favoritesCount;

    @Override
    public long getId() { return _id; }

    @Override
    public String getName() { return _name; }

    @Override
    public String getScreenName() { return _screenName; }

    @Override
    public Calendar getCreatedAt() { return _createdAt; }

    @Override
    public String getUrl() { return _url; }

    @Override
    public String getLocation() { return _location; }

    @Override
    public String getProfileImageUrl() { return _profileImageUrl; }

    @Override
    public boolean isVerified() { return _isVerified; }

    @Override
    public boolean isProtected() { return _isProtected; }

    @Override
    public boolean isTranslator() { return _isTranslator; }

    @Override
    public long getFriendsCount() { return _friendsCount; }

    @Override
    public long getFollowersCount() { return _followersCount; }

    @Override
    public long getStatusCount() { return _statusCount; }

    @Override
    public String getLanguage() { return _language; }

    @Override
    public String getDescription() { return _description; }

    @Override
    public FollowingType getFollowingType() { return _following; }

    @Override
    public void setFollowingType(FollowingType type) { _following = type; }

    @Override
    public long getFavoritesCount() { return _favoritesCount; }

    public static User[] createUserList(JSONArray array) throws Exception {
        ArrayList<User> toReturn = new ArrayList<User>();
        for(int i = 0; i < array.length(); i++) {
            toReturn.add(new UserJSON(array.getJSONObject(i)));
        }
        return toReturn.toArray(new User[0]);
    }
}