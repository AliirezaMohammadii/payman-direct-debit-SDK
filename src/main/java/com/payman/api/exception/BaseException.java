package com.payman.api.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final String code;
    private final String message;
    private final Object info;

    public BaseException(String code, String message, Object info) {
        this.code = code;
        this.message = message;
        this.info = info;
    }
}
