package com.ighe3.api.exception;

import lombok.Data;

public class PaymanException extends BaseException {

    private final int statusCode;

    public PaymanException(String code, String message, int statusCode) {
        super(code, message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
