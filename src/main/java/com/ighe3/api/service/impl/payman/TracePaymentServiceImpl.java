package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.provider.response.PaymanTracePayResponse;
import com.ighe3.api.exception.BaseException;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.service.payman.TracePaymentService;
import com.ighe3.api.utils.Urls;
import okhttp3.Request;
import org.springframework.stereotype.Service;

@Service
public class TracePaymentServiceImpl implements TracePaymentService {

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;

    public TracePaymentServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public PaymanTracePayResponse trace(String traceId, String date) {
        String url = urls.getTracePayUrl() + "?trace-id" + "=" + traceId + "&date" + "=" + date.toString();
        Request request = httpService.createRequest(url, httpService.createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = httpService.sendRequest(request, TracePaymentServiceImpl.class);
        return (PaymanTracePayResponse) HttpResponseMapper.convertJsonToJavaObject(paymanResponse.getBody(), PaymanTracePayResponse.class);
    }
}
