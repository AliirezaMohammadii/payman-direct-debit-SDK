package com.ighe3.api.management.payman;

import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.Headers;
import okhttp3.Request;
import org.springframework.stereotype.Component;

@Component
public class PaymanTraceCreateManager extends PaymanBaseManager {

    private PaymanService paymanService;

    public ResponseObject trace(String traceId) throws Exception {
        Request request = createRequest(Urls.TRACE_CREATE_PAYMAN.getValue()
                        + "?trace-id" + "=" + traceId,
                createHeaders());
        ResponseObject response = sendRequest(request);
        return response;
    }

    @Override
    protected Headers createHeaders() throws Exception {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(),
                        GeneralUtils.BEARER_PREFIX + paymanService.getAccessToken())
                .build();
        return headers;
    }
}
