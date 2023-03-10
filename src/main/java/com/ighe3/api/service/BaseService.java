package com.ighe3.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.dto.provider.response.error.PaymanErrorResponse;
import com.ighe3.api.model.Response;
import com.ighe3.api.utils.ExceptionTranslator;
import com.ighe3.api.utils.GeneralUtils;
import okhttp3.*;

import java.io.IOException;
import java.util.Optional;

public abstract class BaseService {

    private final ExceptionTranslator exceptionTranslator;

    public BaseService(ExceptionTranslator exceptionTranslator) {
        this.exceptionTranslator = exceptionTranslator;
    }

    protected <T extends BaseService> Response sendRequest(Request request, Class<T> servcieClass) throws RuntimeException {
        OkHttpClient client = GeneralUtils.buildOkhttpClient();
        okhttp3.Response response = executeSending(client, request);
        Response customizedResponse = createBaseResponse(response);

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

    private static boolean responseIsNotSuccessful(Response response) {
        return !response.getStatusCode().toString().startsWith("2");
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
            throw new RuntimeException(e);
        }
    }

    protected abstract Headers createHeaders() throws RuntimeException;

    protected <T extends Object> Object convertJsonToJavaObject(String value, Class<T> responseClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object response = mapper.readValue(value, responseClass);
            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Response createBaseResponse(okhttp3.Response response) throws RuntimeException {

        String responseBody = null;

        try {
            responseBody = Optional.ofNullable(response.body()).orElseThrow(NullPointerException::new).string();
        } catch (IOException e) {
//            throw new something like internal error exception
        }

        return new Response(response.headers(), responseBody, response.code(), response.isSuccessful());
    }
}
