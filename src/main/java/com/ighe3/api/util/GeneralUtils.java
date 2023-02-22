package com.ighe3.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import okhttp3.Headers;
import okhttp3.OkHttpClient;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.util.HashMap;
import java.util.Map;

public class GeneralUtils {

    public static final String BEARER_PREFIX = "Bearer ";

    public static OkHttpClient buildOkhttpClient() {
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession sslSession) {
                        return true;
                    }
                })
                .build();
        return client;
    }

    public static String beautifyJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String beautifiedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(json));
        return beautifiedJson;
    }

    public static String convertJavaObjectToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(object);
        return json;
    }

    public static Headers getGeneralHeaders() {
        Headers headers = new Headers.Builder()
                .add(RequestHeaderKeys.APP_KEY.getValue(), SecurityUtils.APP_KEY)
                .add(RequestHeaderKeys.CONTENT_TYPE.getValue(), RequestHeaderValues.APPLICATION_JSON.getValue())
                .add(RequestHeaderKeys.ACCEPT.getValue(), RequestHeaderValues.APPLICATION_JSON.getValue())
                .add(RequestHeaderKeys.CLIENT_IP_ADDRESS.getValue(), "127.0.0.1")
                .add(RequestHeaderKeys.CLIENT_PLATFORM_TYPE.getValue(), RequestHeaderValues.WEB.getValue())
                .add(RequestHeaderKeys.CLIENT_DEVICE_ID.getValue(), "127.0.0.1")
                .add(RequestHeaderKeys.CLIENT_USER_ID.getValue(), "09120000000")
                .add(RequestHeaderKeys.CLIENT_USER_AGENT.getValue(), "firefox5.0")
                .build();
        return headers;
    }

    public static Map<String, Object> getResponseBodyAsMap(String body) throws Exception {
        return convertJsonToMap(body);
    }

    private static Map<String, Object> convertJsonToMap(String json) throws Exception {
        Map<String, Object> result = new ObjectMapper().readValue(json, HashMap.class);
        return result;
    }
}
