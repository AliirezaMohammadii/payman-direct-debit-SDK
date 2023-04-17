package com.payman.api.mapper;

import com.payman.api.exception.InternalException;
import com.payman.api.exception.enums.ExceptionCodes;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public final class RequestMapper {

    public static <R, PR> RequestBody map(R request, Class<R> requestClass, Class<PR> paymanRequestClass) {
        Object requestBody;

        try {
            requestBody = paymanRequestClass.getConstructor(requestClass).newInstance(request);
        } catch (Exception e) {
            throw new InternalException(ExceptionCodes.REQUEST_MAPPING.code);
        }

        String json = JsonMapper.mapJavaObjectToJson(requestBody);
        return RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
    }
}
