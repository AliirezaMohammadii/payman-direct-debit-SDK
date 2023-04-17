package com.payman.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payman.api.config.MessageSourceConfig;
import com.payman.api.exception.enums.ExceptionCodes;
import com.payman.api.exception.InternalException;
import org.springframework.context.i18n.LocaleContextHolder;

public final class GeneralUtils {

    public static String beautifyJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(json));
        } catch (JsonProcessingException e) {
            throw new InternalException(ExceptionCodes.JSON_PROCESSING.code);
        }
    }

    public static String getMessageByCode(String code) {
        return getMessageByCode(code, null);
    }

    public static String getMessageByCode(String code, Object[] args) {
        return MessageSourceConfig.EXCEPTION_MESSAGES_INSTANCE.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    public static String getExceptionMessageFromCode(String code) {
        return getExceptionMessageFromCode(code, null);
    }

    public static String getExceptionMessageFromCode(String code, Object[] args) {
        return MessageSourceConfig.EXCEPTION_MESSAGES_INSTANCE.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
