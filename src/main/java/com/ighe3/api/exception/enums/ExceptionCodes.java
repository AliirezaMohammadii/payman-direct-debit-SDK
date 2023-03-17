package com.ighe3.api.exception.enums;

public enum ExceptionCodes {

    INTERNAL_EXCEPTION("0"),
    RESPONSE_MAPPING("1"),
    REQUEST_MAPPING("2"),
    JSON_PROCESSING("3"),
    READING_RESPONSE_BUFFER("4"),
    RESPONSE_BODY_IS_NULL("5");

    public final String code;

    private ExceptionCodes(String code) {
        this.code = code;
    }
}
