package com.ighe3.api.service.impl;

import com.ighe3.api.config.CredentialsPropertiesConfig;
import com.ighe3.api.dto.client.request.SourceInfo;
import com.ighe3.api.dto.provider.response.error.PaymanErrorResponse;
import com.ighe3.api.dto.Response;
import com.ighe3.api.exception.enums.ExceptionCodes;
import com.ighe3.api.exception.InternalException;
import com.ighe3.api.exception.PaymanException;
import com.ighe3.api.mapper.JsonMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.service.payman.CreateService;
import com.ighe3.api.service.payman.PaymanUpdateService;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.CustomHttpHeaders;
import com.ighe3.api.utils.HttpHeaderValues;
import okhttp3.*;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class HttpServiceImpl implements HttpService {

    private static final String BEARER_PREFIX = "Bearer ";
    private final CredentialsPropertiesConfig credentialsPropertiesConfig;

    public HttpServiceImpl(CredentialsPropertiesConfig credentialsPropertiesConfig) {
        this.credentialsPropertiesConfig = credentialsPropertiesConfig;
    }

    @Override
    public <S> Response sendRequest(Request request, Class<S> serviceClass) throws IOException {
        OkHttpClient client = buildOkhttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        Response internalResponse = mapToInternalResponse(response);

        checkForErrors(internalResponse, serviceClass);

        printResponse(internalResponse);
        return internalResponse;
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
    public Headers createHeaders(SourceInfo sourceInfo) {
        return new Headers.Builder()
                .add(CustomHttpHeaders.APP_KEY, credentialsPropertiesConfig.getAppKey())
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
                .add(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + accessToken)
                .build();
    }

    private OkHttpClient buildOkhttpClient() {
        return new OkHttpClient()
                .newBuilder()
                .followRedirects(false)
                .hostnameVerifier((hostname, sslSession) -> true)
                .build();
    }

    private Response mapToInternalResponse(okhttp3.Response response) throws IOException {

        try {
            String responseBody = Optional.ofNullable(response.body()).orElseThrow(NullPointerException::new).string();
            return new Response(response.headers(), responseBody, response.code(), response.isSuccessful());

        } catch (IOException e) {
            throw new IOException("IO exception occurred while reading response body content.");
        } catch (NullPointerException e) {
            throw new NullPointerException("Body of response is null.");
        }
    }

    private <S> void checkForErrors(Response response, Class<S> serviceClass) {

        boolean errorExists = false;

        if (serviceClass == CreateService.class || serviceClass == PaymanUpdateService.class) {
            if (!response.getStatusCode().equals(302))
                errorExists = true;
        } else if (!response.isSuccessful())
            errorExists = true;

        if (errorExists) {
            PaymanErrorResponse errorResponse = (PaymanErrorResponse) JsonMapper.mapJsonToJavaObject(response.getBody(), PaymanErrorResponse.class);
            throw new PaymanException(errorResponse.getCode(), errorResponse.getMessage(), response.getStatusCode());
        }
    }

    private void printResponse(Response response) {
        System.out.println("status code: " + response.getStatusCode());
        System.out.println(GeneralUtils.beautifyJson(response.getBody()));
    }
}
