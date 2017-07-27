package com.vova.mobiledevelopertask.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final String DATE_FORMAT = "d/M/yy hh:mm:ss";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    public static String dateToString(Date date) {
        if (date == null) {
            return null;
        } else {
            return dateFormat.format(date);
        }
    }

}
