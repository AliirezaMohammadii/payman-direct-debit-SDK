package com.ighe3.api.dto.client.response.error;

import lombok.Data;

@Data
public class ErrorResponse {

    private final String code;
    private final String message;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
