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
        _id = json.optInt("id");
        _name = json.optString("name");
        if(!json.isNull("url")) {
            _url = json.optString("url");
        }
        _createdAt = Time.getTwitterDate(json.optString("created_at"));
        _profileImageUrl = Utils.unescape(json.optString("profile_image_url"));
        if(!json.isNull("location")) {
            _location = Utils.unescape(json.optString("location"));
        }
        if(!json.isNull("follow_request_sent")) {
            if(json.optBoolean("follow_request_sent")) {
                _following = FollowingType.REQUEST_SENT;
            }
        }
        _isTranslator = json.optBoolean("is_translator");
        _favoritesCount = json.optLong("favourites_count");
        _isProtected = json.optBoolean("protected");
        _followersCount = json.optLong("followers_count");
        _language = json.optString("lang");
        _isVerified = json.optBoolean("verified");
        if(!json.isNull("description")) {
            _description = Utils.unescape(json.optString("description"));
        }
        _friendsCount = json.optLong("friends_count");
        _statusCount = json.optLong("statuses_count");
        if(_following != FollowingType.REQUEST_SENT && !json.isNull("following")) {
            if(json.optBoolean("following")) {
                _following = FollowingType.FOLLOWING;
            } else _following = FollowingType.NOT_FOLLOWING;
        }
        _screenName = json.optString("screen_name");
        _profileBgImage = Utils.unescape(json.optString("profile_background_image_url"));
        _profileBgColor = json.optString("profile_background_color");
        _profileBannerImage = json.optString("profile_banner_url");
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
    private String _profileBgColor, _profileBannerImage;

    /**
     * {@inheritDoc}
     */
    @Override
    public long getId() { return _id; }

    public void setName(String name) { _name = name; }

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

    public void setUrl(String url) { _url = url; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUrl() { return _url; }

    public void setLocation(String loc) { _location = loc; }

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

    public void setDescription(String desc) { _description = desc; }

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
            toReturn.add(new UserJSON(array.optJSONObject(i)));
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

	@Override
	public String getProfileBannerWeb() {
		return _profileBannerImage + "/web";
	}

	@Override
	public String getProfileBannerMobile() {
		return _profileBannerImage + "/mobile";
	}
}