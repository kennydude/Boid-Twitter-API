package com.teamboid.twitterapi.status;

import com.teamboid.twitterapi.entity.hashtag.HashtagEntity;
import com.teamboid.twitterapi.entity.media.MediaEntity;
import com.teamboid.twitterapi.entity.mention.MentionEntity;
import com.teamboid.twitterapi.entity.url.UrlEntity;
import com.teamboid.twitterapi.user.User;

import java.util.Calendar;

public interface Status {

    long getId();

    boolean isFavorited();

    /**
     * This does not actually favorite the Tweet on Twitter, this function is used in the library.
     */
    void setFavorited(boolean isFavorited);

    Calendar getCreatedAt();

    boolean isTruncated();

    UrlEntity[] getUrlEntities();

    MediaEntity[] getMediaEntities();

    HashtagEntity[] getHashtagEntities();

    MentionEntity[] getMentionEntities();

    User getUser();

    String getText();

    String getSource();

    long getInReplyToStatusId();

    long getInReplyToUserId();

    String getInReplyToScreenName();

    long getRetweetCount();

    boolean getRetweeted();
}
