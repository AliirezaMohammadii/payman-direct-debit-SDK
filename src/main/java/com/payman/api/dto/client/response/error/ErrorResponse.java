package com.payman.api.dto.client.response.error;

import lombok.Data;

@Data
public class ErrorResponse {

    private final String code;
    private final String message;
    private final Object info;

    public ErrorResponse(String code, String message, Object info) {
        this.code = code;
        this.message = message;
        this.info = info;
    }
}
