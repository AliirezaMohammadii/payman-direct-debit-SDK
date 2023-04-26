package com.payman.api.service.impl;

import com.payman.api.config.CredentialsPropertiesConfig;
import com.payman.api.dto.provider.response.CustomizedResponse;
import com.payman.api.dto.provider.response.error.PaymanErrorResponse;
import com.payman.api.exception.PaymanException;
import com.payman.api.mapper.JsonMapper;
import com.payman.api.service.HttpService;
import com.payman.api.service.payman.CreateService;
import com.payman.api.service.payman.PaymanUpdateService;
import com.payman.api.utils.GeneralUtils;
import com.payman.api.utils.CustomHttpHeaders;
import com.payman.api.utils.HttpHeaderValues;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class HttpServiceImpl implements HttpService {

    private static final String BEARER_PREFIX = "Bearer ";
    private final CredentialsPropertiesConfig credentialsPropertiesConfig;

    public HttpServiceImpl(CredentialsPropertiesConfig credentialsPropertiesConfig) {
        this.credentialsPropertiesConfig = credentialsPropertiesConfig;
    }

    @Override
    public <S> CustomizedResponse sendRequest(Request request, Class<S> serviceClass) throws IOException {
        OkHttpClient client = buildOkhttpClient();
        Response response = client.newCall(request).execute();
        CustomizedResponse customizedResponse = mapToCustomizedResponse(response);

        checkForErrors(customizedResponse, serviceClass);

        logResponse(customizedResponse);
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
    public Headers createHeaders(HttpServletRequest httpServletRequest) {
        return new Headers.Builder()
                .add(CustomHttpHeaders.APP_KEY, credentialsPropertiesConfig.getAppKey())
                .add(CustomHttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                .add(CustomHttpHeaders.ACCEPT, HttpHeaderValues.APPLICATION_JSON)
                .add(CustomHttpHeaders.DEVICE_ID, httpServletRequest.getHeader(CustomHttpHeaders.DEVICE_ID))
                .add(CustomHttpHeaders.CLIENT_IP_ADDRESS, httpServletRequest.getHeader(CustomHttpHeaders.CLIENT_IP_ADDRESS))
                .add(CustomHttpHeaders.CLIENT_PLATFORM_TYPE, httpServletRequest.getHeader(CustomHttpHeaders.CLIENT_PLATFORM_TYPE))
                .add(CustomHttpHeaders.CLIENT_DEVICE_ID, httpServletRequest.getHeader(CustomHttpHeaders.CLIENT_DEVICE_ID))
                .add(CustomHttpHeaders.CLIENT_USER_ID, httpServletRequest.getHeader(CustomHttpHeaders.CLIENT_USER_ID))
                .add(CustomHttpHeaders.CLIENT_USER_AGENT, httpServletRequest.getHeader(CustomHttpHeaders.CLIENT_USER_AGENT))
                .build();
    }

    @Override
    public Headers createHeaders(HttpServletRequest httpServletRequest, String accessToken) {
        Headers headers = createHeaders(httpServletRequest);
        return headers.newBuilder()
                .add(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + accessToken)
                .build();
    }

    @Override
    public Headers createInternalRequestHeaders(String accessToken) {
        return new Headers.Builder()
                .add(CustomHttpHeaders.APP_KEY, credentialsPropertiesConfig.getAppKey())
                .add(CustomHttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                .add(CustomHttpHeaders.ACCEPT, HttpHeaderValues.APPLICATION_JSON)
                .add(CustomHttpHeaders.DEVICE_ID, HttpHeaderValues.APP_DEVICE_ID)
                .add(CustomHttpHeaders.CLIENT_IP_ADDRESS, HttpHeaderValues.APP_CLIENT_IP_ADDRESS)
                .add(CustomHttpHeaders.CLIENT_PLATFORM_TYPE, HttpHeaderValues.APP_CLIENT_PLATFORM_TYPE)
                .add(CustomHttpHeaders.CLIENT_DEVICE_ID, HttpHeaderValues.APP_CLIENT_DEVICE_ID)
                .add(CustomHttpHeaders.CLIENT_USER_ID, HttpHeaderValues.APP_CLIENT_USER_ID)
                .add(CustomHttpHeaders.CLIENT_USER_AGENT, HttpHeaderValues.APP_CLIENT_USER_AGENT)
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

    private CustomizedResponse mapToCustomizedResponse(Response response) throws IOException {
        try {
            String responseBody = Optional.ofNullable(response.body()).orElseThrow(NullPointerException::new).string();
            return new CustomizedResponse(response.headers(), responseBody, response.code(), response.isSuccessful());

        } catch (IOException e) {
            throw new IOException("IO exception occurred while reading response body content.");
        } catch (NullPointerException e) {
            throw new NullPointerException("Response body is null.");
        }
    }

    private <S> void checkForErrors(CustomizedResponse customizedResponse, Class<S> serviceClass) {
        boolean errorExists = false;

        if (serviceClass == CreateService.class || serviceClass == PaymanUpdateService.class) {
            if (!customizedResponse.getStatusCode().equals(302))
                errorExists = true;
        } else if (!customizedResponse.isSuccessful())
            errorExists = true;

        if (errorExists) {
            PaymanErrorResponse errorResponse = (PaymanErrorResponse) JsonMapper.mapJsonToJavaObject(customizedResponse.getBody(), PaymanErrorResponse.class);
            throw new PaymanException(errorResponse.getCode(), errorResponse.getMessage(), customizedResponse.getStatusCode(), errorResponse.getErrors());
        }
    }

    private void logResponse(CustomizedResponse customizedResponse) {
        log.info("response status code: {}", customizedResponse.getStatusCode());
        log.info("response body: {}", GeneralUtils.beautifyJson(customizedResponse.getBody()));
    }
}
