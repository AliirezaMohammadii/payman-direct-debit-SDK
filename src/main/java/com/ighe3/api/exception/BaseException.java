package com.ighe3.api.exception;

import com.ighe3.api.utils.GeneralUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {

    private final String code;
    private final String message;

    public BaseException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
