package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.model.response.PaymanGetPaymanIdResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.BaseResponse;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.RequestHeaderKeys;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class PaymanIdService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public PaymanIdService(AccessTokenService accessTokenService, Urls urls) {
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public String getPaymanId(String paymanCode) throws RuntimeException {
        String url = urls.getPaymanIdUrl() + "?payman_code" + "=" + paymanCode;
        Request request = createRequest(url, createHeaders());
        BaseResponse paymanResponse = sendRequest(request);
        PaymanGetPaymanIdResponse paymanResponseBody
                = (PaymanGetPaymanIdResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanGetPaymanIdResponse.class);
        return paymanResponseBody.getPaymanId();
    }

    @Override
    protected Headers createHeaders() throws RuntimeException {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(),
                        GeneralUtils.BEARER_PREFIX + accessTokenService.getAccessToken())
                .build();
        return headers;
    }
}
