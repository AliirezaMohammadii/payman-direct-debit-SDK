package com.ighe3.api.exception;

public class PaymanException extends BaseException {

    public PaymanException(String code, Object... args) {
        super(code, args);
    }
}
