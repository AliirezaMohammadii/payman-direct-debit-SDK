package com.ighe3.api.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class HttpResponseMapper {

    public static <R> Object convertJsonToJavaObject(String value, Class<R> responseClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(value, responseClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
