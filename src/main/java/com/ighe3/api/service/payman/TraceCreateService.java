package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.model.response.PaymanTraceCreateResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.BaseResponse;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.RequestHeaderKeys;
import com.ighe3.api.utils.Urls;
import okhttp3.Headers;
import okhttp3.Request;
import org.springframework.stereotype.Service;

@Service
public class TraceCreateService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public TraceCreateService(AccessTokenService accessTokenService, Urls urls) {
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanTraceCreateResponse trace(String traceId) throws Exception {
        String url = urls.getTraceCreateUrl() + "?trace-id" + "=" + traceId;
        Request request = createRequest(url, createHeaders());
        BaseResponse paymanResponse = sendRequest(request);
        PaymanTraceCreateResponse paymanResponseBody
                = (PaymanTraceCreateResponse) convertJsonToJavaObject(paymanResponse.getBody());
        return paymanResponseBody;
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
            PaymanTraceCreateResponse response = mapper.readValue(value, PaymanTraceCreateResponse.class);
            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
