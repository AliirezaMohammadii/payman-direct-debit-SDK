package com.ighe3.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ighe3.api.dto.provider.response.error.PaymanErrorResponse;
import com.ighe3.api.dto.Response;
import com.ighe3.api.exception.PaymanException;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.service.impl.payman.PaymanCreateServiceImpl;
import com.ighe3.api.service.impl.payman.PaymanUpdateServiceImpl;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.CustomHttpHeaders;
import com.ighe3.api.utils.HttpHeaderValues;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class HttpServiceImpl implements HttpService {

    @Value("${credentials.app-key}")
    private String appKey;

//    private final ExceptionTranslator exceptionTranslator;

//    public HttpServiceImpl(ExceptionTranslator exceptionTranslator) {
//        this.exceptionTranslator = exceptionTranslator;
//    }

    @Override
    public <T> Response sendRequest(Request request, Class<T> serviceClass) throws RuntimeException {
        OkHttpClient client = GeneralUtils.buildOkhttpClient();
        okhttp3.Response response = executeSending(client, request);
        Response customizedResponse = createCustomResponse(response);

        checkForErrors(customizedResponse, serviceClass);

        printResponse(customizedResponse);
        return customizedResponse;
    }

    @Override
    public Request createRequest(String url, Headers headers) {
        return new Request.Builder()
                .url(url)
                .headers(headers)
                .build();
    }

    @Override
    public Request createRequest(RequestBody body, String url, Headers headers) {
        return new Request.Builder()
                .url(url)
                .headers(headers)
                .post(body)
                .build();
    }

    @Override
    public okhttp3.Response executeSending(OkHttpClient client, Request request) {
        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            // TODO
        }
        return null;
    }

    @Override
    public Headers createHeaders() throws RuntimeException {
        return new Headers.Builder()
                // TODO
                .add(CustomHttpHeaders.APP_KEY, appKey)
                .add(CustomHttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                .add(CustomHttpHeaders.ACCEPT, HttpHeaderValues.APPLICATION_JSON)
                .add(CustomHttpHeaders.CLIENT_IP_ADDRESS, "127.0.0.1")
                .add(CustomHttpHeaders.CLIENT_PLATFORM_TYPE, HttpHeaderValues.WEB)
                .add(CustomHttpHeaders.CLIENT_DEVICE_ID, "127.0.0.1")
                .add(CustomHttpHeaders.CLIENT_USER_ID, "09120000000")
                .add(CustomHttpHeaders.CLIENT_USER_AGENT, "firefox5.0")
                .build();
    }

    @Override
    public Headers createHeaders(String accessToken) throws RuntimeException {
        Headers headers = createHeaders();
        return headers.newBuilder()
                .add(HttpHeaders.AUTHORIZATION, GeneralUtils.BEARER_PREFIX + accessToken)
                .build();
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

    private <T> void checkForErrors(Response response, Class<T> serviceClass) {

        boolean errorExists = false;

        if (serviceClass == PaymanCreateServiceImpl.class || serviceClass == PaymanUpdateServiceImpl.class) {
            if (!response.getStatusCode().equals(302))
                errorExists = true;
        } else if (!response.isSuccessful())
            errorExists = true;

        if (errorExists) {
            PaymanErrorResponse errorResponse = (PaymanErrorResponse) HttpResponseMapper.convertJsonToJavaObject(response.getBody(), PaymanErrorResponse.class);
            throw new PaymanException(errorResponse.getCode());
        }
    }

    private void printResponse(Response response) throws RuntimeException {
        System.out.println("status code: " + response.getStatusCode());

        try {
            System.out.println(GeneralUtils.beautifyJson(response.getBody()));
        } catch (JsonProcessingException e) {
//            throw new something like internal error exception
        }
    }
}
