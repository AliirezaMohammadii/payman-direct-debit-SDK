package com.ighe3.api.service.impl.payman;

import com.ighe3.api.config.UrlPropertiesConfig;
import com.ighe3.api.dto.client.request.TraceCreationRequest;
import com.ighe3.api.dto.client.response.TraceCreationResponse;
import com.ighe3.api.dto.provider.response.PaymanTraceCreationResponse;
import com.ighe3.api.mapper.ResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.service.payman.TraceCreationService;
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
}
