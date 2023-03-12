package com.ighe3.api.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class HttpResponseMapper {

    public static <T> Object convertJsonToJavaObject(String value, Class<T> responseClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(value, responseClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
