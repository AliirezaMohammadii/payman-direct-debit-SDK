package com.ighe3.api.dto;

import com.ighe3.api.exception.BaseException;
import lombok.Data;

public class ExceptionResponse {

    private String code;
    private String message;

    public ExceptionResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
