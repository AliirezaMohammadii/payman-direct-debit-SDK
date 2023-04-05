package com.ighe3.api.exception;

import com.ighe3.api.utils.GeneralUtils;

public class InternalException extends BaseException {

    public InternalException(String code) {
        super(code, GeneralUtils.getExceptionMessageFromCode(code));
    }
}
