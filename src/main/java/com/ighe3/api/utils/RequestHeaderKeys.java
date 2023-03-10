package com.ighe3.api.utils;

public enum RequestHeaderKeys {
    APP_KEY("App-Key"),
    CONTENT_TYPE("Content-Type"),
    ACCEPT("Accept"),
    CLIENT_IP_ADDRESS("Client-Ip-Address"),
    CLIENT_PLATFORM_TYPE("Client-Platform-Type"),
    CLIENT_DEVICE_ID("Client-Device-Id"),
    CLIENT_USER_ID("Client-User-Id"),
    CLIENT_USER_AGENT("Client-User-Agent"),
    AUTHORIZATION("Authorization"),
    MOBILE_NO("mobile-no"),
    NATIONAL_CODE("national-code");

    private final String value;

    public String getValue() { return this.value; }

    private RequestHeaderKeys(String value) { this.value = value; }
}
