package com.payman.api.mapper;

import com.payman.api.exception.InternalException;
import com.payman.api.exception.enums.ExceptionCodes;

public final class ResponseMapper {

    public static <R, PR> Object map(String responseBody, Class<PR> paymanResponseClass, Class<R> responseClass) {
        Object paymanResponse = JsonMapper.mapJsonToJavaObject(responseBody, paymanResponseClass);

        try {
            return responseClass.getConstructor(paymanResponseClass).newInstance(paymanResponse);
        } catch (Exception e) {
            throw new InternalException(ExceptionCodes.RESPONSE_MAPPING);
        }
    }
}
