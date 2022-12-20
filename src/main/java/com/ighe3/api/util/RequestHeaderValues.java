package com.ighe3.api.util;

public enum RequestHeaderValues {
    APPLICATION_JSON("application/json"),
    WEB("web");

    private final String value;

    public String getValue() { return this.value; }

    private RequestHeaderValues(String value) { this.value = value; }
}
