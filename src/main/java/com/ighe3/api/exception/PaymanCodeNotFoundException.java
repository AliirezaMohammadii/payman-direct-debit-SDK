package com.ighe3.api.exception;

public class PaymanCodeNotFoundException extends BaseException {

    public PaymanCodeNotFoundException(String code, Object... args) {
        super(code, args);
    }
}
