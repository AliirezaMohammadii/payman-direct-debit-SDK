package com.ighe3.api.service.payman;

import com.ighe3.api.dto.provider.response.PaymanTraceCreateResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.utils.ExceptionTranslator;
import com.ighe3.api.utils.Urls;
import okhttp3.Request;
import org.springframework.stereotype.Service;

@Service
public class TraceCreateService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public TraceCreateService(ExceptionTranslator exceptionTranslator, AccessTokenService accessTokenService, Urls urls) {
        super(exceptionTranslator);
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanTraceCreateResponse trace(String traceId) throws RuntimeException {
        String url = urls.getTraceCreateUrl() + "?trace-id" + "=" + traceId;
        Request request = createRequest(url, createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = sendRequest(request, TraceCreateService.class);
        PaymanTraceCreateResponse paymanResponseBody
                = (PaymanTraceCreateResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanTraceCreateResponse.class);
        return paymanResponseBody;
    }
}
