package com.teamboid.twitterapi.client;

public enum Feature {
	/**
	 * Allows you to update your profile
	 */
	UPDATE_PROFILE,
	
	/**
	 * Allows you to privately message other users
	 */
	DIRECT_MESSAGE,
	
	/**
	 * If the service allows you to repost
	 */
	REPOST,
	
	/**
	 * Allows you to get information on reposts
	 */
	GET_REPOST_INFO,
	
	/**
	 * If the service has a favorite service (view and deletion)
	 */
	FAVORITES,
	
	/**
	 * If the service allows you to have custom lists of users in some format
	 */
	USER_LISTS,
	
	/**
	 * Search tweets
	 */
	SEARCH_TWEETS,
	
	/**
	 * Search users
	 */
	SEARCH_USERS,
	
	/**
	 * If the service has trends
	 */
	TRENDS,
	
	/**
	 * Has a related statuses method
	 */
	RELATED_STATUSES,
	
	/**
	 * If the service has a places method
	 */
	PLACES,
	
	/**
	 * If the service supports reverse geocoding
	 */
	REVERSE_GEOCODE
}
