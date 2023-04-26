package com.payman.api.exception;

import com.payman.api.utils.GeneralUtils;

public class InternalException extends BaseException {

    public InternalException(String code) {
        super(code, GeneralUtils.getExceptionMessageFromCode(code), null);
    }
}
