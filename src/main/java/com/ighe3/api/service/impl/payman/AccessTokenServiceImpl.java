package com.ighe3.api.service.impl.payman;

import com.ighe3.api.config.CredentialsPropertiesConfig;
import com.ighe3.api.config.UrlPropertiesConfig;
import com.ighe3.api.dto.client.request.AccessTokenRequest;
import com.ighe3.api.dto.client.response.AccessTokenResponse;
import com.ighe3.api.dto.provider.response.PaymanAccessTokenResponse;
import com.ighe3.api.mapper.ResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.service.payman.AccessTokenService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final CredentialsPropertiesConfig credentialsPropertiesConfig;

    public AccessTokenServiceImpl(HttpService httpService,
                                  UrlPropertiesConfig urlPropertiesConfig,
                                  CredentialsPropertiesConfig credentialsPropertiesConfig) {

        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.credentialsPropertiesConfig = credentialsPropertiesConfig;
    }

    @Override
    public String getAccessToken() {
        return credentialsPropertiesConfig.getAccessToken();
    }

    @Override
    public AccessTokenResponse getAccessToken(AccessTokenRequest request) throws IOException {
        FormBody requestBody = getFormBody(request);
        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getAccessToken(),
                httpService.createHeaders(request.getSourceInfo()));

        Response paymanResponse = httpService.sendRequest(paymanRequest, AccessTokenServiceImpl.class);
        return (AccessTokenResponse) ResponseMapper
                .mapResponse(paymanResponse.getBody(), PaymanAccessTokenResponse.class, AccessTokenResponse.class);
    }

    private FormBody getFormBody(AccessTokenRequest request) {
        return new FormBody.Builder()
                .addEncoded("client_id", request.getClientId())
                .addEncoded("client_secret", request.getClientSecret())

                // Value of this property must always be equal to "client_credentials".
                .addEncoded("grant_type", "client_credentials")
                .build();
    }
}
