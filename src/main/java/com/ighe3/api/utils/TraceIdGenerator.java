package com.ighe3.api.utils;

public class TraceIdGenerator {

    public static String generate() {
        String randomString = GeneralUtils.generateRandomString(10);
        return GeneralUtils.convertToOnlyEnglishCharactersAndNumbers(randomString);
    }
}
