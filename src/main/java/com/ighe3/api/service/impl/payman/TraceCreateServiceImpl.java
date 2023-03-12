package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.provider.response.PaymanTraceCreateResponse;
import com.ighe3.api.exception.BaseException;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.service.payman.TraceCreateService;
import com.ighe3.api.utils.Urls;
import okhttp3.Request;
import org.springframework.stereotype.Service;

@Service
public class TraceCreateServiceImpl implements TraceCreateService {

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;

    public TraceCreateServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public PaymanTraceCreateResponse trace(String traceId) throws BaseException {
        String url = urls.getTraceCreateUrl() + "?trace-id" + "=" + traceId;
        Request request = httpService.createRequest(url, httpService.createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = httpService.sendRequest(request, TraceCreateServiceImpl.class);
        return (PaymanTraceCreateResponse) HttpResponseMapper.convertJsonToJavaObject(paymanResponse.getBody(), PaymanTraceCreateResponse.class);
    }
}
