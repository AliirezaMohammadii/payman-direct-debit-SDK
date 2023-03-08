package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.model.response.PaymanGetPaymanIdResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.dto.BaseResponse;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
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

    public String getPaymanId(String paymanCode) throws Exception {
        String url = urls.getPaymanIdUrl() + "?payman_code" + "=" + paymanCode;
        Request request = createRequest(url, createHeaders());
        BaseResponse paymanResponse = sendRequest(request);
        PaymanGetPaymanIdResponse paymanResponseBody
                = (PaymanGetPaymanIdResponse) convertJsonToJavaObject(paymanResponse.getBody());
        return paymanResponseBody.getPaymanId();
    }

    @Override
    protected Headers createHeaders() throws Exception {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(),
                        GeneralUtils.BEARER_PREFIX + accessTokenService.getAccessToken())
                .build();
        return headers;
    }

    @Override
    protected Object convertJsonToJavaObject(String value) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            PaymanGetPaymanIdResponse response = mapper.readValue(value, PaymanGetPaymanIdResponse.class);
            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
