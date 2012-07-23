package com.teamboid.twitterapi.dm;

import com.teamboid.twitterapi.user.User;
import com.teamboid.twitterapi.user.UserJSON;
import com.teamboid.twitterapi.utilities.Time;
import com.teamboid.twitterapi.json.JSONArray;
import com.teamboid.twitterapi.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Aidan Follestad
 */
public class DirectMessageJSON implements DirectMessage {

    public DirectMessageJSON(JSONObject json) throws Exception {
        _createdAt = Time.getTwitterDate(json.getString("created_at"));
        _senderScreenName = json.getString("sender_screen_name");
        _sender = new UserJSON(json.getJSONObject("sender"));
        _text = json.getString("text");
        _recipientScreenName = json.getString("recipient_screen_name");
        _id = json.getLong("id");
        _recipient = new UserJSON(json.getJSONObject("recipient"));
        _recipientId = json.getLong("recipient_id");
        _senderId = json.getLong("sender_id");
    }

    private Calendar _createdAt;
    private String _senderScreenName;
    private User _sender;
    private String _text;
    private String _recipientScreenName;
    private long _id;
    private User _recipient;
    private long _recipientId;
    private long _senderId;

    @Override
    public Calendar getCreatedAt() { return _createdAt; }

    @Override
    public String getSenderScreenName() { return _senderScreenName; }

    @Override
    public User getSender() { return _sender; }

    @Override
    public String getText() { return _text; }

    @Override
    public String getRecipientScreenName() { return _recipientScreenName; }

    @Override
    public long getId() { return _id; }

    @Override
    public User getRecipient() { return _recipient; }

    @Override
    public long getRecipientId() { return _recipientId; }

    @Override
    public long getSenderId() { return _senderId; }

    public static DirectMessage[] createMessageList(JSONArray array) throws Exception {
        ArrayList<DirectMessage> toReturn = new ArrayList<DirectMessage>();
        for(int i = 0; i < array.length(); i++) {
            toReturn.add(new DirectMessageJSON(array.getJSONObject(i)));
        }
        return toReturn.toArray(new DirectMessage[0]);
    }
}