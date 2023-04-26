package com.payman.api.exception;

import com.payman.api.dto.provider.response.error.PaymanSubErrorResponse;
import com.payman.api.utils.GeneralUtils;
import lombok.Getter;

import java.util.List;

@Getter
public class PaymanException extends BaseException {

    private final int statusCode;

    public PaymanException(String code, String message, int statusCode, List<PaymanSubErrorResponse> errors) {
        super(code, message, errors);
        this.statusCode = statusCode;
    }

    public PaymanException(String code, int statusCode, List<PaymanSubErrorResponse> errors) {
        this(code, GeneralUtils.getExceptionMessageFromCode(code), statusCode, errors);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
