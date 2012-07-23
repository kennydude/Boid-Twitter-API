package com.teamboid.twitterapi.relationship;

import com.teamboid.twitterapi.json.JSONObject;

/**
 * @author Aidan Follestad
 */
public class RelationshipJSON implements Relationship {

    public RelationshipJSON(JSONObject json) throws Exception {
        JSONObject relationship = json.getJSONObject("relationship");
        JSONObject sourceJson = relationship.getJSONObject("source");
        JSONObject targetJson = relationship.getJSONObject("target");
        sourceUserId = sourceJson.optLong("id");
        targetUserId = targetJson.optLong("id");
        sourceUserScreenName = sourceJson.optString("screen_name");
        targetUserScreenName = targetJson.optString("screen_name");
        sourceBlockingTarget = sourceJson.optBoolean("blocking");
        sourceFollowingTarget = sourceJson.optBoolean("following");
        sourceFollowedByTarget = sourceJson.optBoolean("followed_by");
        sourceNotificationsEnabled = sourceJson.optBoolean("notifications_enabled");
    }

    private long targetUserId;
    private String targetUserScreenName;
    private boolean sourceBlockingTarget;
    private boolean sourceNotificationsEnabled;
    private boolean sourceFollowingTarget;
    private boolean sourceFollowedByTarget;
    private long sourceUserId;
    private String sourceUserScreenName;

    @Override
    public long getSourceUserId() { return sourceUserId; }

    @Override
    public long getTargetUserId() { return targetUserId; }

    @Override
    public boolean isSourceBlockingTarget() { return sourceBlockingTarget; }

    @Override
    public String getSourceUserScreenName() { return sourceUserScreenName; }

    @Override
    public String getTargetUserScreenName() { return targetUserScreenName; }

    @Override
    public boolean isSourceFollowingTarget() { return sourceFollowingTarget; }

    @Override
    public boolean isTargetFollowingSource() { return sourceFollowedByTarget; }

    @Override
    public boolean isSourceFollowedByTarget() { return sourceFollowedByTarget; }

    @Override
    public boolean isTargetFollowedBySource() { return sourceFollowingTarget; }

    @Override
    public boolean isSourceNotificationsEnabled() { return sourceNotificationsEnabled; }
}
