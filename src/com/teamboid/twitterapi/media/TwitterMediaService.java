package com.teamboid.twitterapi.media;

import java.io.InputStream;

import com.teamboid.twitterapi.client.RequestHandler;
import com.teamboid.twitterapi.client.Twitter;
import com.teamboid.twitterapi.client.TwitterException;
import com.teamboid.twitterapi.status.StatusUpdate;
import com.teamboid.twitterapi.status.entity.media.MediaEntity;
import com.teamboid.twitterapi.status.entity.url.UrlEntity;

public class TwitterMediaService extends ExternalMediaService {
	static{
		MediaServices.registerService(TwitterMediaService.class);
	}

	@Override
	public String getServiceName() {
		return "twitter";
	}

	@Override
	public String[] getSupportedUrls() {
		return new String[]{};
	}

	@Override
	public MediaEntity uploadFile(StatusUpdate tweet, Twitter tw,
			RequestHandler client, InputStream file) throws TwitterException {
		tweet.setMedia(file, "upload.jpg");
		return null;
	}

	@Override
	public MediaEntity getEntity(UrlEntity in) {
		return null;
	}

}
