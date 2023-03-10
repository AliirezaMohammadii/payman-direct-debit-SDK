package com.ighe3.api.utils;

public class TraceIdGenerator {

    private final static int TRACE_ID_LENGTH = 10;

    public static String generate() {
        String randomString = GeneralUtils.generateRandomString(TRACE_ID_LENGTH);
        return GeneralUtils.convertToOnlyEnglishCharactersAndNumbers(randomString);
    }
}
