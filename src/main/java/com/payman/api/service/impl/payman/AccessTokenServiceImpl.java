package com.payman.api.service.impl.payman;

import com.payman.api.config.CredentialsPropertiesConfig;
import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.request.AccessTokenRequest;
import com.payman.api.dto.client.response.AccessTokenResponse;
import com.payman.api.dto.provider.response.CustomizedResponse;
import com.payman.api.dto.provider.response.PaymanAccessTokenResponse;
import com.payman.api.mapper.ResponseMapper;
import com.payman.api.service.HttpService;
import com.payman.api.service.payman.AccessTokenService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    public AccessTokenResponse getAccessToken(HttpServletRequest httpServletRequest, AccessTokenRequest accessTokenRequest)
            throws IOException {
        FormBody requestBody = getFormBody(accessTokenRequest.getClientId(), accessTokenRequest.getClientSecret());
        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getAccessToken(),
                httpService.createHeaders(httpServletRequest));

        CustomizedResponse paymanCustomizedResponse = httpService.sendRequest(paymanRequest, AccessTokenServiceImpl.class);
        return (AccessTokenResponse) ResponseMapper
                .map(paymanCustomizedResponse.getBody(), PaymanAccessTokenResponse.class, AccessTokenResponse.class);
    }

    private FormBody getFormBody(String clientId, String clientSecret) {
        return new FormBody.Builder()
                .addEncoded("client_id", clientId)
                .addEncoded("client_secret", clientSecret)

                /* Value of this property must always be equal to "client_credentials". */
                .addEncoded("grant_type", "client_credentials")
                .build();
    }
}
