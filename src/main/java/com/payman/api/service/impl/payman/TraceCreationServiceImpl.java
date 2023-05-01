package com.payman.api.service.impl.payman;

import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.response.TraceCreationResponse;
import com.payman.api.dto.provider.response.CustomizedResponse;
import com.payman.api.dto.provider.response.PaymanTraceCreationResponse;
import com.payman.api.mapper.ResponseMapper;
import com.payman.api.service.HttpService;
import com.payman.api.service.payman.AccessTokenService;
import com.payman.api.service.payman.TraceCreationService;
import okhttp3.Headers;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Service
public class TraceCreationServiceImpl implements TraceCreationService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenService accessTokenService;

    public TraceCreationServiceImpl(HttpService httpService, UrlPropertiesConfig urlPropertiesConfig, AccessTokenService accessTokenService) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public TraceCreationResponse trace(HttpServletRequest httpServletRequest, String traceId) throws IOException {
        String url = String.format("%s%s?trace-id=%s", urlPropertiesConfig.getBase(), urlPropertiesConfig.getTraceCreation(), traceId);

        Headers headers = Objects.isNull(httpServletRequest) ?
                httpService.createInternalRequestHeaders(accessTokenService.getAccessToken()) :
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken());

        Request paymanRequest = httpService.createRequest(url, headers);

        CustomizedResponse paymanCustomizedResponse = httpService.sendRequest(paymanRequest, TraceCreationServiceImpl.class);
        return (TraceCreationResponse) ResponseMapper
                .map(paymanCustomizedResponse.getBody(), PaymanTraceCreationResponse.class, TraceCreationResponse.class);
    }
}
