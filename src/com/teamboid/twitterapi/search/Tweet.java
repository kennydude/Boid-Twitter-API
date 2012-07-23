package com.teamboid.twitterapi.search;

import com.teamboid.twitterapi.entity.hashtag.HashtagEntity;
import com.teamboid.twitterapi.entity.media.MediaEntity;
import com.teamboid.twitterapi.entity.mention.MentionEntity;
import com.teamboid.twitterapi.entity.url.UrlEntity;
import com.teamboid.twitterapi.status.GeoLocation;

import java.util.Calendar;

/**
 * Represents a Tweet held in search results.
 * @author Aidan Follestad
 */
public interface Tweet {

    Calendar getCreatedAt();

    String getProfileImageUrl();

    long getFromUserId();

    String getFromUser();

    String getText();

    long getToUserId();

    long getId();

    GeoLocation getGeo();

    String getIsoLanguageCode();

    String getSource();

    int getRecentRetweets();

    SearchQuery.ResultType getResultType();

    UrlEntity[] getUrlEntities();

    MediaEntity[] getMediaEntities();

    HashtagEntity[] getHashtagEntities();

    MentionEntity[] getMentionEntities();
}
