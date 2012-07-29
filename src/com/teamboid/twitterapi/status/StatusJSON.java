package com.teamboid.twitterapi.status;

import com.teamboid.twitterapi.status.entity.hashtag.HashtagEntity;
import com.teamboid.twitterapi.status.entity.hashtag.HashtagEntityJSON;
import com.teamboid.twitterapi.status.entity.media.MediaEntity;
import com.teamboid.twitterapi.status.entity.media.MediaEntityJSON;
import com.teamboid.twitterapi.status.entity.mention.MentionEntity;
import com.teamboid.twitterapi.status.entity.mention.MentionEntityJSON;
import com.teamboid.twitterapi.status.entity.url.UrlEntity;
import com.teamboid.twitterapi.status.entity.url.UrlEntityJSON;
import com.teamboid.twitterapi.user.User;
import com.teamboid.twitterapi.user.UserJSON;
import com.teamboid.twitterapi.utilities.Time;
import com.teamboid.twitterapi.json.JSONArray;
import com.teamboid.twitterapi.json.JSONObject;
import com.teamboid.twitterapi.utilities.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Handles parsing JSON and assigning values to a {@link Status} interface.
 *
 * @author Aidan Follestad
 */
public class StatusJSON implements Status, Serializable {

	private static final long serialVersionUID = 8853180403951169750L;

	public StatusJSON(JSONObject json) throws Exception {
        if(!json.isNull("created_at")) {
            _createdAt = Time.getTwitterDate(json.getString("created_at"));
        }
        _truncated = json.optBoolean("truncated");
        _favorited = json.optBoolean("favorited");
        if (!json.isNull("entities")) {
            JSONObject entities = json.getJSONObject("entities");
            if (!entities.isNull("urls")) {
                JSONArray urls = entities.optJSONArray("urls");
                ArrayList<UrlEntity> toSet = new ArrayList<UrlEntity>();
                for (int i = 0; i < urls.length(); i++) {
                    toSet.add(new UrlEntityJSON(urls.getJSONObject(i)));
                }
                _urlEntities = toSet.toArray(new UrlEntity[0]);
            }
            if (!entities.isNull("hashtags")) {
                JSONArray hashtags = entities.optJSONArray("hashtags");
                ArrayList<HashtagEntity> toSet = new ArrayList<HashtagEntity>();
                for (int i = 0; i < hashtags.length(); i++) {
                    toSet.add(new HashtagEntityJSON(hashtags.getJSONObject(i)));
                }
                _hashtagEntities = toSet.toArray(new HashtagEntity[0]);
            }
            if (!entities.isNull("user_mentions")) {
                JSONArray mentions = entities.optJSONArray("user_mentions");
                ArrayList<MentionEntity> toSet = new ArrayList<MentionEntity>();
                for (int i = 0; i < mentions.length(); i++) {
                    toSet.add(new MentionEntityJSON(mentions.getJSONObject(i)));
                }
                _mentionEntities = toSet.toArray(new MentionEntity[0]);
            }
            if (!entities.isNull("media")) {
                JSONArray media = entities.optJSONArray("media");
                ArrayList<MediaEntity> toSet = new ArrayList<MediaEntity>();
                for (int i = 0; i < media.length(); i++) {
                    toSet.add(new MediaEntityJSON(media.getJSONObject(i)));
                }
                _mediaEntities = toSet.toArray(new MediaEntity[0]);
            }
        }
        if(!json.isNull("text")) {
            _text = Utils.unescape(json.getString("text"));
        }
        _id = json.optLong("id");
        _retweetCount = json.optLong("retweet_count");
        if(!json.isNull("retweeted_status")) {
        	_retweeted = true;
            _retweetedStatus = new StatusJSON(json.getJSONObject("retweeted_status"));
        }
        if(!json.isNull("geo")) {
            _geo = new GeoLocation(json.getJSONObject("geo"));
        }
        _inReplyToUserId = json.optLong("in_reply_to_user_id");
        if(!json.isNull("place")) {
            _place = new PlaceJSON(json.getJSONObject("place"));
        }
        _source = Utils.unescape(json.optString("source"));
        _sourcePlain = Utils.stripAnchor(_source);
        if(!json.isNull("user")) {
            _user = new UserJSON(json.getJSONObject("user"));
        }
        _inReplyToScreenName = json.optString("in_reply_to_screen_name");
        _inReplyToStatusId = json.optLong("in_reply_to_status_id");
        if (!json.isNull("current_user_retweet")) {
            _myRetweet = new StatusJSON(json.getJSONObject("current_user_retweet"));
            _retweetedByMe = true;
        }
    }

    private Status _myRetweet;
    private boolean _retweetedByMe;
    private Calendar _createdAt;
    private boolean _truncated;
    private boolean _favorited;
    private UrlEntity[] _urlEntities;
    private MediaEntity[] _mediaEntities;
    private HashtagEntity[] _hashtagEntities;
    private MentionEntity[] _mentionEntities;
    private String _text;
    private long _id;
    private long _retweetCount;
    private boolean  _retweeted;
    private Status _retweetedStatus;
    private long _inReplyToUserId;
    private GeoLocation _geo;
    private Place _place;
    private String _sourcePlain;
    private String _source;
    private User _user;
    private String _inReplyToScreenName;
    private long _inReplyToStatusId;

    /**
     * {@inheritDoc}
     */
    @Override
    public long getId() { return _id; }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFavorited() { return _favorited; }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFavorited(boolean isFavorited) { _favorited = isFavorited; }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calendar getCreatedAt() { return _createdAt; }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTruncated() { return _truncated; }

    /**
     * {@inheritDoc}
     */
    @Override
    public UrlEntity[] getUrlEntities() { return _urlEntities; }

    /**
     * {@inheritDoc}
     */
    @Override
    public MediaEntity[] getMediaEntities() { return _mediaEntities; }

    /**
     * {@inheritDoc}
     */
    @Override
    public HashtagEntity[] getHashtagEntities() { return  _hashtagEntities; }

    /**
     * {@inheritDoc}
     */
    @Override
    public MentionEntity[] getMentionEntities() { return _mentionEntities; }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser() { return _user; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText() { return _text; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSource() { return _source; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSourcePlain() { return _sourcePlain; }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getInReplyToStatusId() { return _inReplyToStatusId; }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getInReplyToUserId() { return _inReplyToUserId; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInReplyToScreenName() { return _inReplyToScreenName; }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getRetweetCount() { return _retweetCount; }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRetweet() { return _retweeted; }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status getRetweetedStatus() { return _retweetedStatus; }

    /**
     * {@inheritDoc}
     */
    @Override
    public Place getPlace() { return _place; }

    /**
     * {@inheritDoc}
     */
    @Override
    public GeoLocation getGeoLocation() { return _geo; }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRetweetedByMe() { return _retweetedByMe; }

    /**
     * {@inheritDoc}
     */
    @Override
    public Status getMyRetweetedStatus() { return _myRetweet; }

    public static Status[] createStatusList(JSONArray array) throws Exception {
        ArrayList<Status> toReturn = new ArrayList<Status>();
        for(int i = 0; i < array.length(); i++) {
            toReturn.add(new StatusJSON(array.getJSONObject(i)));
        }
        return toReturn.toArray(new Status[0]);
    }
}