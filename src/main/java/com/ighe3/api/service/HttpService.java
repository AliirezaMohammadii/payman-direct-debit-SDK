package com.ighe3.api.service;


import com.ighe3.api.dto.Response;
import com.ighe3.api.service.impl.HttpServiceImpl;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public interface HttpService {

    <T> Response sendRequest(Request request, Class<T> serviceClass);

    Request createRequest(String url, Headers headers);

    Request createRequest(RequestBody body, String url, Headers headers);

    okhttp3.Response executeSending(OkHttpClient client, Request request);

    Headers createHeaders() throws RuntimeException;

    Headers createHeaders(String accessToken) throws RuntimeException;
}
