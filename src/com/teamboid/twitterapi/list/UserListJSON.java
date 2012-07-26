package com.teamboid.twitterapi.list;

import com.teamboid.twitterapi.json.JSONArray;
import com.teamboid.twitterapi.json.JSONObject;
import com.teamboid.twitterapi.user.User;
import com.teamboid.twitterapi.user.UserJSON;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Aidan Follestad
 */
public class UserListJSON implements UserList, Serializable {

	private static final long serialVersionUID = 2942749834192545944L;

	public UserListJSON(JSONObject json) throws Exception {
        _slug = json.optString("slug");
        _name = json.optString("name");
        _uri = json.optString("uri");
        _subscriberCount = json.optLong("subscriber_count");
        _memberCount = json.optLong("member_count");
        _mode= UserListMode.valueOf(json.optString("mode").toUpperCase());
        _id = json.optLong("id");
        _fullName = json.optString("full_name");
        _description = json.optString("description");
        if(!json.isNull("user")) {
            _user = new UserJSON(json.getJSONObject("user"));
        }
        _following = json.optBoolean("following");
    }

    private String _slug;
    private String _name;
    private String _uri;
    private long _subscriberCount;
    private long _memberCount;
    private UserListMode _mode;
    private long _id;
    private String _fullName;
    private String _description;
    private User _user;
    private boolean _following;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSlug() { return _slug; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() { return _name; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUri() { return _uri; }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getSubscriberCount() { return _subscriberCount; }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getMemberCount() { return _memberCount; }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserListMode getMode() { return _mode; }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getId() { return _id; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFullName() { return _fullName; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() { return _description; }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser() { return _user; }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFollowing() { return _following; }

    public static UserList[] createStatusList(JSONArray array) throws Exception {
        ArrayList<UserList> toReturn = new ArrayList<UserList>();
        for(int i = 0; i < array.length(); i++) {
            toReturn.add(new UserListJSON(array.getJSONObject(i)));
        }
        return toReturn.toArray(new UserList[0]);
    }
}
