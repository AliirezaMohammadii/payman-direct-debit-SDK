package com.payman.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

public final class DateUtils {

    private static final DateTimeFormatter requestDatetimeFormatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.systemDefault());

    private static final DateTimeFormatter responseDatetimeFormatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(ZoneId.systemDefault());

    public static String epochMillisToPaymanDateTimeFormat(Long epochMillis) {
        if (Objects.isNull(epochMillis))
            return null;

        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), ZoneId.systemDefault());
        return dateTime.format(requestDatetimeFormatter);
    }

    /**
     * Classes like 'Contract', are used in both request and response classes;
     * for request, faraboom accepts yyyy-MM-dd'T'HH:mm:ss datetime format, and
     * for response, in some cases, faraboom provides epochMillis format, and in some cases yyyy-MM-dd'T'HH:mm:ss'Z'.
     * I didn't create two separate instances of Contract class for request and response;
     * Instead, I handled it by a condition.
     *
     * Datatype of epochMillis that faraboom provides, is Long, but App accepts String. This conversion
     * is done automatically while mapping response.
     */
    public static Long paymanDateTimeFormatToEpochMillis(String text) {
        if (Objects.isNull(text))
            return null;

        if (datetimeIsInEpochMillisFormat(text))
            return Long.parseLong(text);

        else {
            LocalDateTime dateTime = LocalDateTime.parse(text, responseDatetimeFormatter);
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
