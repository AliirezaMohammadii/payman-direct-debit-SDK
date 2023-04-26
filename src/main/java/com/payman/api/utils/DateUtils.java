package com.payman.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public final class DateUtils {

    private static final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.systemDefault());

    public static String epochMillisToPaymanDateTimeFormat(long epochMillis) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneId.systemDefault());
        return dateTime.format(formatter);
    }

    /**
     * Classes like 'Contract', are used in both request and response classes;
     * for request, faraboom accepts yyyy-MM-dd'T'HH:mm:ss'Z' datetime format, and
     * for response, faraboom provides epochMillis format.
     * I didn't create two separate instances of Contract class for request and response;
     * instead, I handled the datetime problem in below method.
     *
     * Datatype of epochMillis that faraboom provides, is Long, but App accepts String. This conversion
     * is done automatically while mapping response.
     *
     * The condition is for when faraboom decides to provide datetime in the format it accepts.
     */
    public static long paymanDateTimeFormatToEpochMillis(String text) {
        if (datetimeIsInEpochMillisFormat(text))
            return Long.parseLong(text);

        else {
            LocalDateTime dateTime = LocalDateTime.parse(text, formatter);
            return dateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
        }
    }

    private static boolean datetimeIsInEpochMillisFormat(String text) {
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
