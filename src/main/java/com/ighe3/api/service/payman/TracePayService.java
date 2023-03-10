package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.model.response.PaymanTracePayResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.BaseResponse;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.RequestHeaderKeys;
import com.ighe3.api.utils.Urls;
import okhttp3.Headers;
import okhttp3.Request;
import org.springframework.stereotype.Service;

@Service
public class TracePayService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public TracePayService(AccessTokenService accessTokenService, Urls urls) {
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanTracePayResponse trace(String traceId, String date) throws Exception {
        String url = urls.getTracePayUrl() + "?trace-id" + "=" + traceId + "&date" + "=" + date.toString();
        Request request = createRequest(url, createHeaders());
        BaseResponse paymanResponse = sendRequest(request);
        PaymanTracePayResponse paymanResponseBody
                = (PaymanTracePayResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanTracePayResponse.class);
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
}
