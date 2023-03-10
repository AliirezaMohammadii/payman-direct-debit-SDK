package com.ighe3.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import okhttp3.Headers;

@Data
@AllArgsConstructor
public class CustomizedResponse {
    private Headers headers;
    private String body;
    private Integer statusCode;
    private Boolean successful;

    public boolean isSuccessful() {
        return successful;
    }
}
