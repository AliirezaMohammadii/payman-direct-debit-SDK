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

    public static long paymanDateTimeFormatToEpochMillis(String text) {
        LocalDateTime dateTime = LocalDateTime.parse(text, formatter);
        return dateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }
}
