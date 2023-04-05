package com.ighe3.api.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.exception.enums.ExceptionCodes;
import com.ighe3.api.exception.InternalException;

public class JsonMapper {

    public static String mapJavaObjectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new InternalException(ExceptionCodes.JSON_PROCESSING.code);
        }
    }

    public static <R> Object mapJsonToJavaObject(String value, Class<R> responseClass) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(value, responseClass);
        } catch (JsonProcessingException e) {
            throw new InternalException(ExceptionCodes.JSON_PROCESSING.code);
        }
    }
}
