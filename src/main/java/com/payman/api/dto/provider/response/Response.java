package com.payman.api.dto.provider.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import okhttp3.Headers;

/**
 * This object is created for using body several times. Response object of okhttp3 has a
 * response which its body is made from a buffer, and it will be empty after reading its content,
 * and it will be useless after that.
 */
@Data
@AllArgsConstructor
public class Response {

    private Headers headers;
    private String body;
    private Integer statusCode;
    private Boolean successful;

    public boolean isSuccessful() {
        return successful;
    }
}
