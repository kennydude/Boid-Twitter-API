package com.teamboid.twitterapi.trend;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Contains a set of trends for different a specific date and/or time (e.g., each item in daily or weekly trends).
 * @author Aidan Follestad
 */
public interface Trends extends Serializable {

    /**
     * Gets the time of the trend set; would be each day in a given week if
     * weekly trends, or each hour in a day if daily trends.
     */
    Calendar getTime();

    /**
     * Gets the set of trends for the time.
     */
    Trend[] getTrends();
}
