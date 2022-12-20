package com.ighe3.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ighe3.api.management.PaymanGetAccessTokenManager;
import com.ighe3.api.service.PaymanService;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    public static String getBeautifiedJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        String beautifiedJson = null;

        try {
            beautifiedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(json));
        } catch (Exception e) {
            System.err.println("Exception occurred while beautifying input json");
        }

        return beautifiedJson;
    }

    public static String convertJavaObjectToJson(Object object) {
        ObjectWriter ow = new ObjectMapper().writer();
        String json = "";

        try {
            json = ow.writeValueAsString(object);
        } catch (Exception e) {
            System.err.println("error occurred while converting java object to json");
        }

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

    public static Map<String, Object> convertJsonToMap(String json) {
        Map<String, Object> result = null;
        try {
            result = new ObjectMapper().readValue(json, HashMap.class);
        } catch (Exception e) {
            System.err.println("error occurred while converting json to map.");
        }
        return result;
    }

    public static Map<String, Object> getResponseBodyAsMap(Response response) throws IOException {
        ResponseBody responseBody = response.body();
        String json = Optional.ofNullable(responseBody).orElseThrow(NullPointerException::new).string();
        return convertJsonToMap(json);
    }
}
