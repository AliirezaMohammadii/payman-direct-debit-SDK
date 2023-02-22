package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.model.response.PaymanTraceCreateResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.Headers;
import okhttp3.Request;
import org.springframework.stereotype.Service;

@Service
public class TraceCreateService extends BaseService {
    private final AccessTokenService accessTokenService;

    public TraceCreateService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public Object trace(String traceId) throws Exception {
        ResponseObject paymanResponse = getResponseObject(traceId);
        PaymanTraceCreateResponse paymanResponseBody
                = (PaymanTraceCreateResponse) convertJsonToJavaObject(paymanResponse.getBody());
        return null;
    }

    private ResponseObject getResponseObject(String traceId) throws Exception {
        String url = Urls.TRACE_CREATE.getValue() + "?trace-id" + "=" + traceId;
        Request request = createRequest(url, createHeaders());
        ResponseObject response = sendRequest(request);
        return response;
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
