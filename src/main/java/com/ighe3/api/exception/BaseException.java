package com.ighe3.api.exception;

import com.ighe3.api.utils.GeneralUtils;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public abstract class BaseException extends RuntimeException {

    private String code;
    private String message;

    public BaseException(String code, Object... args) {
        this.code = code;
        this.message = GeneralUtils.getExceptionMessageFromCode(code, args);
    }
}
