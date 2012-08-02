package com.teamboid.twitterapi.user;

import com.teamboid.twitterapi.utilities.Time;
import com.teamboid.twitterapi.json.JSONArray;
import com.teamboid.twitterapi.json.JSONObject;
import com.teamboid.twitterapi.utilities.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Handles parsing JSON and assigning values to a {@link User} interface.
 *
 * @author Aidan Follestad
 */
public class UserJSON implements User, Serializable {

	private static final long serialVersionUID = 8223189548035894012L;

	public UserJSON(JSONObject json) throws Exception {
        _id = json.getInt("id");
        _name = json.getString("name");
        if(!json.isNull("url")) {
            _url = json.getString("url");
        }
        _createdAt = Time.getTwitterDate(json.getString("created_at"));
        _profileImageUrl = Utils.unescape(json.getString("profile_image_url"));
        if(!json.isNull("location")) {
            _location = Utils.unescape(json.getString("location"));
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
            _description = Utils.unescape(json.getString("description"));
        }
        _friendsCount = json.getLong("friends_count");
        _statusCount = json.getLong("statuses_count");
        if(_following != FollowingType.REQUEST_SENT && !json.isNull("following")) {
            if(json.getBoolean("following")) {
                _following = FollowingType.FOLLOWING;
            } else _following = FollowingType.NOT_FOLLOWING;
        }
        _screenName = json.getString("screen_name");
        _profileBgImage = Utils.unescape(json.optString("profile_background_image_url"));
        _profileBgColor = json.optString("profile_background_color");
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
    private String _profileBgImage;
    private String _profileBgColor;

    /**
     * {@inheritDoc}
     */
    @Override
    public long getId() { return _id; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() { return _name; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getScreenName() { return _screenName; }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calendar getCreatedAt() { return _createdAt; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUrl() { return _url; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLocation() { return _location; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProfileImageUrl() { return _profileImageUrl; }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVerified() { return _isVerified; }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isProtected() { return _isProtected; }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTranslator() { return _isTranslator; }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getFriendsCount() { return _friendsCount; }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getFollowersCount() { return _followersCount; }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getStatusCount() { return _statusCount; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLanguage() { return _language; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() { return _description; }

    /**
     * {@inheritDoc}
     */
    @Override
    public FollowingType getFollowingType() { return _following; }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFollowingType(FollowingType type) { _following = type; }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getFavoritesCount() { return _favoritesCount; }

    public static User[] createUserList(JSONArray array) throws Exception {
        ArrayList<User> toReturn = new ArrayList<User>();
        for(int i = 0; i < array.length(); i++) {
            toReturn.add(new UserJSON(array.getJSONObject(i)));
        }
        return toReturn.toArray(new User[0]);
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public String getProfileBackgroundImageUrl() { return _profileBgImage; }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getProfileBackgroundColor() { return _profileBgColor; }
}