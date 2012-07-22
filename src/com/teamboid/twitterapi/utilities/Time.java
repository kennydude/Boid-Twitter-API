package com.teamboid.twitterapi.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Convenience methods for manipulating Time, used throughout the library.
 */
public class Time {

    public final static String TWITTER_DATE_FORMAT = "EEE MMM dd HH:mm:ss Z yyyy";
    public final static String SEARCH_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";

    public static Calendar getTwitterDate(String date) {
        SimpleDateFormat sf = new SimpleDateFormat(TWITTER_DATE_FORMAT, Locale.ENGLISH);
        sf.setLenient(true);
        Date twitterDate = null;
        try { twitterDate = sf.parse(date); }
        catch (Exception e) { e.printStackTrace(); }
        Calendar toReturn = Calendar.getInstance();
        toReturn.setTime(twitterDate);
        return toReturn;
    }
    public static Calendar getTwitterSearchDate(String date) {
        SimpleDateFormat sf = new SimpleDateFormat(SEARCH_DATE_FORMAT, Locale.ENGLISH);
        sf.setLenient(true);
        Date twitterDate = null;
        try { twitterDate = sf.parse(date); }
        catch (Exception e) { e.printStackTrace(); }
        Calendar toReturn = Calendar.getInstance();
        toReturn.setTime(twitterDate);
        return toReturn;
    }
}
