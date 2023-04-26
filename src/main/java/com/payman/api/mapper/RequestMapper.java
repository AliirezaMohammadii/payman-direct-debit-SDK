package com.payman.api.mapper;

import com.payman.api.exception.InternalException;
import com.payman.api.exception.enums.ExceptionCodes;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.function.BiFunction;

public final class RequestMapper {

    // TODO: 4/18/23 Excellent. i have a suggestion to implement this method another way. you can use BiFunction java 8
    public static <R, PR> RequestBody map(R request, Class<R> requestClass, Class<PR> paymanRequestClass) {
        Object requestBody;

        try {
            requestBody = paymanRequestClass.getConstructor(requestClass).newInstance(request);
        } catch (Exception e) {
            throw new InternalException(ExceptionCodes.REQUEST_MAPPING);
        }

        String json = JsonMapper.mapJavaObjectToJson(requestBody);
        return RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
    }
}
