package com.ighe3.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ighe3.api.dto.client.request.SourceInfo;
import com.ighe3.api.dto.provider.request.PaymanTransactionsRequest;
import com.ighe3.api.dto.provider.response.error.PaymanErrorResponse;
import com.ighe3.api.dto.Response;
import com.ighe3.api.exception.PaymanException;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.service.payman.PaymanCreateService;
import com.ighe3.api.service.payman.PaymanUpdateService;
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
    public <S> Response sendRequest(Request request, Class<S> serviceClass) {
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
    public Headers createHeaders(SourceInfo sourceInfo) {
        return new Headers.Builder()
                .add(CustomHttpHeaders.APP_KEY, appKey)
                .add(CustomHttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                .add(CustomHttpHeaders.ACCEPT, HttpHeaderValues.APPLICATION_JSON)
                .add(CustomHttpHeaders.DEVICE_ID, sourceInfo.getDeviceId())
                .add(CustomHttpHeaders.CLIENT_IP_ADDRESS, sourceInfo.getClientIpAddress())
                .add(CustomHttpHeaders.CLIENT_PLATFORM_TYPE, sourceInfo.getClientPlatformType())
                .add(CustomHttpHeaders.CLIENT_DEVICE_ID, sourceInfo.getClientDeviceId())
                .add(CustomHttpHeaders.CLIENT_USER_ID, sourceInfo.getClientUserId())
                .add(CustomHttpHeaders.CLIENT_USER_AGENT, sourceInfo.getClientUserAgent())
                .build();
    }

    @Override
    public Headers createHeaders(SourceInfo sourceInfo, String accessToken) {
        Headers headers = createHeaders(sourceInfo);
        return headers.newBuilder()
                .add(HttpHeaders.AUTHORIZATION, GeneralUtils.BEARER_PREFIX + accessToken)
                .build();
    }

    @Override
    public <R, PR> RequestBody createRequestBody(Class<PR> paymanRequestClass, Class<R> requestClass, R request) {

        Object requestBody = null;

        try {
            requestBody = paymanRequestClass.getConstructor(requestClass).newInstance(request);
        } catch (Exception e) {
            // TODO
        }

        String json = GeneralUtils.convertJavaObjectToJson(requestBody);
        return RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
    }

    private Response createCustomResponse(okhttp3.Response response) {

        String responseBody = null;

        try {
            responseBody = Optional.ofNullable(response.body()).orElseThrow(NullPointerException::new).string();
        } catch (IOException e) {
//            throw new something like internal error exception
        }

        return new Response(response.headers(), responseBody, response.code(), response.isSuccessful());
    }

    private <S> void checkForErrors(Response response, Class<S> serviceClass) {

        boolean errorExists = false;

        if (serviceClass == PaymanCreateService.class || serviceClass == PaymanUpdateService.class) {
            if (!response.getStatusCode().equals(302))
                errorExists = true;
        } else if (!response.isSuccessful())
            errorExists = true;

        if (errorExists) {
            PaymanErrorResponse errorResponse = (PaymanErrorResponse) HttpResponseMapper.convertJsonToJavaObject(response.getBody(), PaymanErrorResponse.class);
            throw new PaymanException(errorResponse.getCode(), errorResponse.getMessage(), response.getStatusCode());
        }
    }

    private void printResponse(Response response) {
        System.out.println("status code: " + response.getStatusCode());

        try {
            System.out.println(GeneralUtils.beautifyJson(response.getBody()));
        } catch (JsonProcessingException e) {
//            throw new something like internal error exception
        }
    }
}
