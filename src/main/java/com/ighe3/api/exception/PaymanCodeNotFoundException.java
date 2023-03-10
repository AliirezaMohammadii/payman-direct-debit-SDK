package com.ighe3.api.exception;

public class PaymanCodeNotFoundException extends BaseException {

    public PaymanCodeNotFoundException(String message, String code, Object[] args) {
        super(message, code, args);
    }
}
