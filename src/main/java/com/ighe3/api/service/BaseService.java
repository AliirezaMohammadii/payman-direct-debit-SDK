package com.ighe3.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.dto.provider.response.error.PaymanErrorResponse;
import com.ighe3.api.model.Response;
import com.ighe3.api.utils.ExceptionTranslator;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.RequestHeaderKeys;
import com.ighe3.api.utils.RequestHeaderValues;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Optional;

public abstract class BaseService {

    @Value("${credentials.app-key}")
    private String appKey;

    private final ExceptionTranslator exceptionTranslator;

    public BaseService(ExceptionTranslator exceptionTranslator) {
        this.exceptionTranslator = exceptionTranslator;
    }

    protected <T extends BaseService> Response sendRequest(Request request, Class<T> servcieClass) throws RuntimeException {
        OkHttpClient client = GeneralUtils.buildOkhttpClient();
        okhttp3.Response response = executeSending(client, request);
        Response customizedResponse = createCustomResponse(response);

        checkForErrors(customizedResponse, servcieClass);

        printResponse(customizedResponse);
        return customizedResponse;
    }

    private <T extends BaseService> void checkForErrors(Response response, Class<T> servcieClass) {
        if (!response.isSuccessful()) {
            PaymanErrorResponse errorResponse = (PaymanErrorResponse) convertJsonToJavaObject(response.getBody(), PaymanErrorResponse.class);
            throw exceptionTranslator.translate(errorResponse.getCode(), servcieClass);
        }
    }

    protected Request createRequest(String url, Headers headers) {
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .build();
        return request;
    }

    protected Request createRequest(RequestBody body, String url, Headers headers) {
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(body)
                .build();
        return request;
    }

    protected void printResponse(Response response) throws RuntimeException {
        System.out.println("status code: " + response.getStatusCode());

        try {
            System.out.println(GeneralUtils.beautifyJson(response.getBody()));
        } catch (JsonProcessingException e) {
//            throw new something like internal error exception
        }
    }

    protected okhttp3.Response executeSending(OkHttpClient client, Request request) {
        try {
            okhttp3.Response response = client.newCall(request).execute();
            return response;
        } catch (IOException e) {
            // TODO
        }
        return null;
    }

    protected Headers createHeaders() throws RuntimeException {
        Headers headers = new Headers.Builder()
                // TODO
                .add(RequestHeaderKeys.APP_KEY.getValue(), appKey)
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

    protected Headers createHeaders(String accessToken) throws RuntimeException {
        Headers headers = createHeaders();
        Headers headersIncludingToken = headers.newBuilder()
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(), GeneralUtils.BEARER_PREFIX + accessToken)
                .build();
        return headersIncludingToken;
    }

    protected <T extends Object> Object convertJsonToJavaObject(String value, Class<T> responseClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object response = mapper.readValue(value, responseClass);
            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Response createCustomResponse(okhttp3.Response response) throws RuntimeException {

        String responseBody = null;

        try {
            responseBody = Optional.ofNullable(response.body()).orElseThrow(NullPointerException::new).string();
        } catch (IOException e) {
//            throw new something like internal error exception
        }

        return new Response(response.headers(), responseBody, response.code(), response.isSuccessful());
    }
}
