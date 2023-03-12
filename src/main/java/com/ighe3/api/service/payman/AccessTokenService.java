package com.ighe3.api.service.payman;

import com.ighe3.api.dto.provider.response.PaymanAccessTokenResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.utils.ExceptionTranslator;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenService extends BaseService {

    @Value("${credentials.use-static-access-token}")
    private String useStaticAccessToken;
    @Value("${credentials.access-token}")
    private String accessToken;

    @Value("${credentials.app-key}")
    private String appKey;
    @Value("${credentials.client-secret}")
    private String clientSecret;

    private final Urls urls;

    public AccessTokenService(ExceptionTranslator exceptionTranslator, Urls urls) {
        super(exceptionTranslator);
        this.urls = urls;
    }

    public String getAccessToken() throws RuntimeException {

        if (Boolean.parseBoolean(useStaticAccessToken))
            return accessToken;

        FormBody requestBody = getFormBody();
        Request request = createRequest(requestBody, urls.getAccessTokenUrl(), createHeaders());
        Response paymanResponse = sendRequest(request, AccessTokenService.class);
        PaymanAccessTokenResponse paymanResponseBody
                = (PaymanAccessTokenResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanAccessTokenResponse.class);

        return paymanResponseBody.getAccessToken();
    }

    private FormBody getFormBody() {
        FormBody requestBody = new FormBody.Builder()
                .addEncoded("client_id", appKey)
                .addEncoded("client_secret", clientSecret)
                .addEncoded("grant_type", "client_credentials")
                .build();
        return requestBody;
    }
}
