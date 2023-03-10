package com.ighe3.api.utils;

import com.ighe3.api.exception.BaseException;
import com.ighe3.api.exception.PaymanCodeNotFoundException;
import com.ighe3.api.service.BaseService;
import org.springframework.stereotype.Component;

@Component
public class ExceptionTranslator {

    public <T extends BaseService> BaseException translate(String code, Class<T> serviceClass) {

        BaseException exception;

        switch (code) {
            case "":
            default:
                exception = new PaymanCodeNotFoundException("");
        }

        return exception;
    }
}
