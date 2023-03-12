package com.ighe3.api.service.payman;

import com.ighe3.api.dto.provider.response.PaymanTracePayResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.utils.ExceptionTranslator;
import com.ighe3.api.utils.Urls;
import okhttp3.Request;
import org.springframework.stereotype.Service;

@Service
public class TracePayService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public TracePayService(ExceptionTranslator exceptionTranslator, AccessTokenService accessTokenService, Urls urls) {
        super(exceptionTranslator);
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanTracePayResponse trace(String traceId, String date) throws RuntimeException {
        String url = urls.getTracePayUrl() + "?trace-id" + "=" + traceId + "&date" + "=" + date.toString();
        Request request = createRequest(url, createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = sendRequest(request, TracePayService.class);
        PaymanTracePayResponse paymanResponseBody
                = (PaymanTracePayResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanTracePayResponse.class);
        return paymanResponseBody;
    }
}
