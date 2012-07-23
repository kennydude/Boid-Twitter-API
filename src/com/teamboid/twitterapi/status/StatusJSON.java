package com.teamboid.twitterapi.status;

import com.teamboid.twitterapi.entity.hashtag.HashtagEntity;
import com.teamboid.twitterapi.entity.hashtag.HashtagEntityJSON;
import com.teamboid.twitterapi.entity.media.MediaEntity;
import com.teamboid.twitterapi.entity.media.MediaEntityJSON;
import com.teamboid.twitterapi.entity.mention.MentionEntity;
import com.teamboid.twitterapi.entity.mention.MentionEntityJSON;
import com.teamboid.twitterapi.entity.url.UrlEntity;
import com.teamboid.twitterapi.entity.url.UrlEntityJSON;
import com.teamboid.twitterapi.user.User;
import com.teamboid.twitterapi.user.UserJSON;
import com.teamboid.twitterapi.utilities.Time;
import com.teamboid.twitterapi.json.JSONArray;
import com.teamboid.twitterapi.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Handles parsing JSON and assigning values to a {@link Status} interface.
 *
 * @author Aidan Follestad
 */
public class StatusJSON implements Status {

    public StatusJSON(JSONObject json) throws Exception {
        if(!json.isNull("created_at")) {
            _createdAt = Time.getTwitterDate(json.getString("created_at"));
        }
        _truncated = json.optBoolean("truncated");
        _favorited = json.optBoolean("favorited");
        if (!json.isNull("entities")) {
            JSONObject entities = json.getJSONObject("entities");
            JSONArray urls = entities.optJSONArray("urls");
            if (urls != null) {
                ArrayList<UrlEntity> toSet = new ArrayList<UrlEntity>();
                for (int i = 0; i < urls.length(); i++) {
                    toSet.add(new UrlEntityJSON(urls.getJSONObject(i)));
                }
                _urlEntities = toSet.toArray(new UrlEntity[0]);
            }
            JSONArray hashtags = entities.optJSONArray("hashtags");
            if (hashtags != null) {
                ArrayList<HashtagEntity> toSet = new ArrayList<HashtagEntity>();
                for (int i = 0; i < urls.length(); i++) {
                    toSet.add(new HashtagEntityJSON(urls.getJSONObject(i)));
                }
                _hashtagEntities = toSet.toArray(new HashtagEntity[0]);
            }
            JSONArray mentions = entities.optJSONArray("user_mentions");
            if (mentions != null) {
                ArrayList<MentionEntity> toSet = new ArrayList<MentionEntity>();
                for (int i = 0; i < urls.length(); i++) {
                    toSet.add(new MentionEntityJSON(urls.getJSONObject(i)));
                }
                _mentionEntities = toSet.toArray(new MentionEntity[0]);
            }
            JSONArray media = entities.optJSONArray("media");
            if (media != null) {
                ArrayList<MediaEntity> toSet = new ArrayList<MediaEntity>();
                for (int i = 0; i < urls.length(); i++) {
                    toSet.add(new MediaEntityJSON(urls.getJSONObject(i)));
                }
                _mediaEntities = toSet.toArray(new MediaEntity[0]);
            }
        }
        if(!json.isNull("text")) {
            _text = json.getString("text");
        }
        _id = json.optLong("id");
        _retweetCount = json.optLong("retweet_count");
        _retweeted = json.optBoolean("retweeted");
        if(!json.isNull("geo")) {
            _geo = new GeoLocation(json.getJSONObject("geo"));
        }
        _inReplyToUserId = json.optLong("in_reply_to_user_id");
        if(!json.isNull("place")) {
            _place = new PlaceJSON(json.getJSONObject("place"));
        }
        _source = json.optString("source");
        //TODO Strips the HTML anchor, leaving just the name of the app. Will only work on Android.
        //_sourcePlain = Html.fromHtml(json.getString("source")).toString();
        if(!json.isNull("user")) {
            _user = new UserJSON(json.getJSONObject("user"));
        }
        _inReplyToScreenName = json.optString("in_reply_to_screen_name");
        _inReplyToStatusId = json.optLong("in_reply_to_status_id");
    }

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
    private GeoLocation _geo;
    private long _inReplyToUserId;
    private Place _place;
    private String _sourcePlain;
    private String _source;
    private User _user;
    private String _inReplyToScreenName;
    private long _inReplyToStatusId;

    @Override
    public long getId() { return _id; }

    @Override
    public boolean isFavorited() { return _favorited; }

    @Override
    public void setFavorited(boolean isFavorited) { _favorited = isFavorited; }

    @Override
    public Calendar getCreatedAt() { return _createdAt; }

    @Override
    public boolean isTruncated() { return _truncated; }

    @Override
    public UrlEntity[] getUrlEntities() { return _urlEntities; }

    @Override
    public MediaEntity[] getMediaEntities() { return _mediaEntities; }

    @Override
    public HashtagEntity[] getHashtagEntities() { return  _hashtagEntities; }

    @Override
    public MentionEntity[] getMentionEntities() { return _mentionEntities; }

    @Override
    public User getUser() { return _user; }

    @Override
    public String getText() { return _text; }

    @Override
    public String getSource() { return _source; }

    @Override
    public long getInReplyToStatusId() { return _inReplyToStatusId; }

    @Override
    public long getInReplyToUserId() { return _inReplyToUserId; }

    @Override
    public String getInReplyToScreenName() { return _inReplyToScreenName; }

    @Override
    public long getRetweetCount() { return _retweetCount; }

    @Override
    public boolean getRetweeted() { return _retweeted; }

    public static Status[] createStatusList(JSONArray array) throws Exception {
        ArrayList<Status> toReturn = new ArrayList<Status>();
        for(int i = 0; i < array.length(); i++) {
            toReturn.add(new StatusJSON(array.getJSONObject(i)));
        }
        return toReturn.toArray(new Status[0]);
    }
}