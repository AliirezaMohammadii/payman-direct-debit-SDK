package com.ighe3.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.config.MessageSourceConfig;
import okhttp3.OkHttpClient;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GeneralUtils {

//    @Value("${credentials.app-key}")
    private static String appKey = "iGhe3";
    public static final String BEARER_PREFIX = "Bearer ";

    public static OkHttpClient buildOkhttpClient() {
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .followRedirects(false)
                .hostnameVerifier((hostname, sslSession) -> true)
                .build();
        return client;
    }

    public static String beautifyJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String beautifiedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(json));
        return beautifiedJson;
    }

    public static String convertJavaObjectToJson(Object object) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            String json = mapper.writeValueAsString(object);
            return json;
        } catch (JsonProcessingException e) {
            // TODO
        }

        return null;
    }

    public static Map<String, Object> getResponseBodyAsMap(String body) {
        return convertJsonToMap(body);
    }

    private static Map<String, Object> convertJsonToMap(String json) {

        try {
            Map<String, Object> result = new ObjectMapper().readValue(json, HashMap.class);
            return result;
        } catch (JsonProcessingException e) {
            // TODO
        }

        return null;
    }

    public static String getMessageByCode(String code) {
        return getMessageByCode(code, null);
    }

    public static String getMessageByCode(String code, Object[] args) {
        return MessageSourceConfig.ERROR_MESSAGES_INSTANCE.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    public static String generateRandomString(int size) {
        byte[] array = new byte[size];
        new Random().nextBytes(array);
        String randomString = new String(array, StandardCharsets.UTF_8);
        return randomString;
    }

    public static String convertToOnlyEnglishCharactersAndNumbers(String text) {
        return encryptByMD5(text);
    }

    private static String encryptByMD5(String str) {
        String hashedStr = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            hashedStr = DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hashedStr;
    }

    public static String getExceptionMessageFromCode(String code, Object[] args) {
        return MessageSourceConfig.ERROR_MESSAGES_INSTANCE.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
