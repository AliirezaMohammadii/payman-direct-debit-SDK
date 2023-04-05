package com.payman.api.mapper;

import com.payman.api.exception.InternalException;
import com.payman.api.exception.enums.ExceptionCodes;

public final class ResponseMapper {

    public static <R, PR> Object mapResponse(String responseBody, Class<PR> paymanRespnseClass, Class<R> responseClass) {
        Object paymanResponse = JsonMapper.mapJsonToJavaObject(responseBody, paymanRespnseClass);

        try {
            return responseClass.getConstructor(paymanRespnseClass).newInstance(paymanResponse);
        } catch (Exception e) {
            throw new InternalException(ExceptionCodes.RESPONSE_MAPPING.code);
        }
    }
}
