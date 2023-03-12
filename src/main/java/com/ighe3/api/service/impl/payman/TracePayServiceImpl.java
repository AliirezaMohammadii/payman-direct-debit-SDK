package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.provider.response.PaymanTracePayResponse;
import com.ighe3.api.exception.BaseException;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.service.payman.TracePayService;
import com.ighe3.api.utils.Urls;
import okhttp3.Request;
import org.springframework.stereotype.Service;

@Service
public class TracePayServiceImpl implements TracePayService {

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;

    public TracePayServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public PaymanTracePayResponse trace(String traceId, String date) throws BaseException {
        String url = urls.getTracePayUrl() + "?trace-id" + "=" + traceId + "&date" + "=" + date.toString();
        Request request = httpService.createRequest(url, httpService.createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = httpService.sendRequest(request, TracePayServiceImpl.class);
        return (PaymanTracePayResponse) HttpResponseMapper.convertJsonToJavaObject(paymanResponse.getBody(), PaymanTracePayResponse.class);
    }
}
