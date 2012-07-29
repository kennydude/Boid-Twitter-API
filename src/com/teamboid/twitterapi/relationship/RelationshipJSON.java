package com.teamboid.twitterapi.relationship;

import com.teamboid.twitterapi.json.JSONObject;

import java.io.Serializable;

/**
 * @author Aidan Follestad
 */
public class RelationshipJSON implements Relationship, Serializable {

	private static final long serialVersionUID = -4840718718275558050L;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public long getSourceUserId() { return sourceUserId; }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getTargetUserId() { return targetUserId; }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSourceBlockingTarget() { return sourceBlockingTarget; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSourceUserScreenName() { return sourceUserScreenName; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTargetUserScreenName() { return targetUserScreenName; }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSourceFollowingTarget() { return sourceFollowingTarget; }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTargetFollowingSource() { return sourceFollowedByTarget; }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSourceFollowedByTarget() { return sourceFollowedByTarget; }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTargetFollowedBySource() { return sourceFollowingTarget; }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSourceNotificationsEnabled() { return sourceNotificationsEnabled; }
}
