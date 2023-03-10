package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.model.response.PaymanGetAccessTokenResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.BaseResponse;
import com.ighe3.api.utils.GeneralUtils;
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

    public AccessTokenService(Urls urls) {
        this.urls = urls;
    }

    public String getAccessToken() throws Exception {

//        try {
//            if (Boolean.parseBoolean(useStaticAccessToken))
//                return accessToken;
//            // TODO: change to parse exception and test it.
//        } catch (Exception e) {}

        FormBody requestBody = getFormBody();
        Request request = createRequest(requestBody, urls.getAccessTokenUrl(), createHeaders());
        BaseResponse paymanResponse = sendRequest(request);
        PaymanGetAccessTokenResponse paymanResponseBody
                = (PaymanGetAccessTokenResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanGetAccessTokenResponse.class);

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

    @Override
    protected Headers createHeaders() {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        return generalHeaders;
    }
}
