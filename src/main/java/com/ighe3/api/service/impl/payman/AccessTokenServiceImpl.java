package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.provider.response.PaymanAccessTokenResponse;
import com.ighe3.api.exception.BaseException;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.service.payman.AccessTokenService;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Value("${credentials.use-static-access-token}")
    private String useStaticAccessToken;
    @Value("${credentials.access-token}")
    private String accessToken;

    @Value("${credentials.app-key}")
    private String appKey;
    @Value("${credentials.client-secret}")
    private String clientSecret;

    private final HttpService httpService;
    private final Urls urls;

    public AccessTokenServiceImpl(HttpService httpService, Urls urls) {
        this.httpService = httpService;
        this.urls = urls;
    }

    @Override
    public String getAccessToken() {

        if (Boolean.parseBoolean(useStaticAccessToken))
            return accessToken;

        FormBody requestBody = getFormBody();
        Request request = httpService.createRequest(requestBody, urls.getAccessTokenUrl(), httpService.createHeaders());
        Response paymanResponse = httpService.sendRequest(request, AccessTokenServiceImpl.class);
        PaymanAccessTokenResponse paymanResponseBody
                // TODO: mapper class
                = (PaymanAccessTokenResponse) HttpResponseMapper.convertJsonToJavaObject(paymanResponse.getBody(), PaymanAccessTokenResponse.class);

        return paymanResponseBody.getAccessToken();
    }

    private FormBody getFormBody() {
        return new FormBody.Builder()
                .addEncoded("client_id", appKey)
                .addEncoded("client_secret", clientSecret)
                .addEncoded("grant_type", "client_credentials")
                .build();
    }
}
