package com.ighe3.api.service.payman;

import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.Headers;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class TracePayService extends BaseService {
    private final AccessTokenService accessTokenService;

    public TracePayService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public ResponseObject trace(String traceId, Date date) throws Exception {
        ResponseObject response = getResponseObject(traceId, date);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        return null;
    }

    private ResponseObject getResponseObject(String traceId, Date date) throws Exception {
        String url = Urls.TRACE_PAY.getValue() + "?trace-id" + "=" + traceId + "&date" + "=" + date.toString();
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
}
