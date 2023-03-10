package com.ighe3.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.model.BaseResponse;
import com.ighe3.api.model.response.PaymanGetAccessTokenResponse;
import com.ighe3.api.utils.GeneralUtils;
import okhttp3.*;

import java.io.IOException;
import java.util.Optional;

public abstract class BaseService {
    protected BaseResponse sendRequest(Request request) throws RuntimeException {
        OkHttpClient client = GeneralUtils.buildOkhttpClient();
        Response response = executeSending(client, request);
        BaseResponse baseResponse = createBaseResponse(response);

        printResponse(baseResponse);
        return baseResponse;
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

    protected void printResponse(BaseResponse response) throws RuntimeException {
        System.out.println("status code: " + response.getStatusCode());

        try {
            System.out.println(GeneralUtils.beautifyJson(response.getBody()));
        } catch (JsonProcessingException e) {
//            throw new some thing like internal error exception
        }
    }

    protected Response executeSending(OkHttpClient client, Request request) {
        try {
            Response response = client.newCall(request).execute();
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

    private BaseResponse createBaseResponse(Response response) throws RuntimeException {

        String responseBody = null;

        try {
            responseBody = Optional.ofNullable(response.body()).orElseThrow(NullPointerException::new).string();
        } catch (IOException e) {
//            throw new some thing like internal error exception
        }

        return new BaseResponse(response.headers(), responseBody, response.code());
    }
}
