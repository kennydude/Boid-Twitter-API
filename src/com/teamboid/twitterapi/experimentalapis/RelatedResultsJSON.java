package com.teamboid.twitterapi.experimentalapis;

import java.io.Serializable;
import java.util.ArrayList;

import com.teamboid.twitterapi.json.JSONArray;
import com.teamboid.twitterapi.json.JSONObject;
import com.teamboid.twitterapi.status.Status;
import com.teamboid.twitterapi.status.StatusJSON;

/**
 * @author Aidan Follestad
 */
public class RelatedResultsJSON implements RelatedResults, Serializable {

	private static final long serialVersionUID = -3473968157288732708L;

	public RelatedResultsJSON(JSONArray jsonArray) throws Exception {
		_withConvo = new ArrayList<Status>();
		_withReply = new ArrayList<Status>();
		_fromUser = new ArrayList<Status>();
		
		for (int i = 0, listLen = jsonArray.length(); i < listLen; ++i) {
            JSONObject o = jsonArray.getJSONObject(i);
            if (!"Tweet".equals(o.getString("resultType"))) {
                continue;
            }
            String groupName = o.getString("groupName");
            if (groupName.length() == 0) continue;
            
            JSONArray results = o.getJSONArray("results");
            for (int j = 0, resultsLen = results.length(); j < resultsLen; ++j) {
                JSONObject json = results.getJSONObject(j).getJSONObject("value");
                Status status = new StatusJSON(json);
                if(groupName.equals(TWEETS_WITH_CONVERSATION)) {
                	_withConvo.add(status);
                } else if(groupName.equals(TWEETS_WITH_REPLY)) {
                	_withReply.add(status);
                } else if(groupName.equals(TWEETS_FROM_USER)) {
                	_fromUser.add(status);
                }
            }
		}
	}
	
	private static final String TWEETS_WITH_CONVERSATION = "TweetsWithConversation";
	private ArrayList<Status> _withConvo;
	
    private static final String TWEETS_WITH_REPLY = "TweetsWithReply";
    private ArrayList<Status> _withReply;
    
    private static final String TWEETS_FROM_USER = "TweetsFromUser";
    private ArrayList<Status> _fromUser;
    
	@Override
	public Status[] getTweetsWithConversation() { return _withConvo.toArray(new Status[0]); }
	
	@Override
	public Status[] getTweetsWithReply() { return _withReply.toArray(new Status[0]); }
	
	@Override
	public Status[] getTweetsFromUser() { return _fromUser.toArray(new Status[0]); }
}
