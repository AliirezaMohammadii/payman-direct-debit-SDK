package com.payman.api.service.impl.payman;

import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.response.TraceCreationResponse;
import com.payman.api.dto.provider.response.PaymanTraceCreationResponse;
import com.payman.api.mapper.ResponseMapper;
import com.payman.api.service.HttpService;
import com.payman.api.dto.provider.response.Response;
import com.payman.api.service.payman.TraceCreationService;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class TraceCreationServiceImpl implements TraceCreationService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenServiceImpl accessTokenService;

    public TraceCreationServiceImpl(HttpService httpService, UrlPropertiesConfig urlPropertiesConfig, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public TraceCreationResponse trace(HttpServletRequest httpServletRequest, String traceId) throws IOException {
        String url = urlPropertiesConfig.getBase() + urlPropertiesConfig.getTraceCreation()
                + "?trace-id" + "=" + traceId;

        Request paymanRequest = httpService.createRequest(url,
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(paymanRequest, TraceCreationServiceImpl.class);
        return (TraceCreationResponse) ResponseMapper
                .mapResponse(paymanResponse.getBody(), PaymanTraceCreationResponse.class, TraceCreationResponse.class);
    }

    @Override
    public TraceCreationResponse trace(String traceId) throws IOException {
        String url = urlPropertiesConfig.getBase() + urlPropertiesConfig.getTraceCreation()
                + "?trace-id" + "=" + traceId;

        Request paymanRequest = httpService.createRequest(url,
                httpService.createInternalRequestHeaders(accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(paymanRequest, TraceCreationServiceImpl.class);
        return (TraceCreationResponse) ResponseMapper
                .mapResponse(paymanResponse.getBody(), PaymanTraceCreationResponse.class, TraceCreationResponse.class);
    }
}
