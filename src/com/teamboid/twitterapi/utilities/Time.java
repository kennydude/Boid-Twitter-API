package com.teamboid.twitterapi.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Convenience methods for manipulating Time, used throughout the library.
 */
public class Time {

    public final static String TWITTER_DATE_FORMAT = "EEE MMM dd HH:mm:ss Z yyyy";
    public final static String SEARCH_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";
    public final static String TRENDS_DATE_FORMAT_1 = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public final static String TRENDS_DATE_FORMAT_2 = "yyyy-MM-dd HH:mm";
    public final static String TRENDS_DATE_FORMAT_3 = "yyyy-MM-dd";

    public static Calendar getTwitterDate(String date) throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat(TWITTER_DATE_FORMAT, Locale.ENGLISH);
        sf.setLenient(true);
        Calendar toReturn = Calendar.getInstance();
        toReturn.setTime(sf.parse(date));
        return toReturn;
    }
    public static Calendar getTwitterSearchDate(String date) throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat(SEARCH_DATE_FORMAT, Locale.ENGLISH);
        Calendar toReturn = Calendar.getInstance();
        toReturn.setTime(sf.parse(date));
        return toReturn;
    }
    public static Calendar getTrendsDate(String asOfStr) throws Exception {
        SimpleDateFormat sf = null;
        switch (asOfStr.length()) {
            case 10:
                sf = new SimpleDateFormat(TRENDS_DATE_FORMAT_3, Locale.ENGLISH);
                break;
            case 16:
                sf = new SimpleDateFormat(TRENDS_DATE_FORMAT_2, Locale.ENGLISH);
                break;
            case 20:
                sf = new SimpleDateFormat(TRENDS_DATE_FORMAT_1, Locale.ENGLISH);
                break;
            default:
                sf = new SimpleDateFormat(SEARCH_DATE_FORMAT, Locale.ENGLISH);
                break;
        }
        sf.setLenient(true);
        Calendar toReturn = Calendar.getInstance();
        toReturn.setTime(sf.parse(asOfStr));
        return toReturn;
    }
}
