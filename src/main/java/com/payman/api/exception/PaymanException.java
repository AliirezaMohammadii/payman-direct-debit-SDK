package com.payman.api.exception;

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
