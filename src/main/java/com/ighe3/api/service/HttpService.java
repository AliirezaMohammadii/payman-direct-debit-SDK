package com.ighe3.api.service;


import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.client.request.SourceInfo;
import com.ighe3.api.dto.client.request.TransactionsRequest;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public interface HttpService {

    <S> Response sendRequest(Request request, Class<S> serviceClass);

    Request createRequest(String url, Headers headers);

    Request createRequest(RequestBody body, String url, Headers headers);

    okhttp3.Response executeSending(OkHttpClient client, Request request);

    Headers createHeaders(SourceInfo sourceInfo);

    Headers createHeaders(SourceInfo sourceInfo, String accessToken);

    <R, PR> RequestBody createRequestBody(Class<PR> paymanRequestClass, Class<R> requestClass, R request);
}
