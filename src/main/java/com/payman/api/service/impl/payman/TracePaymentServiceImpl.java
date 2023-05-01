package com.payman.api.service.impl.payman;

import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.response.TracePaymentResponse;
import com.payman.api.dto.provider.response.CustomizedResponse;
import com.payman.api.dto.provider.response.PaymanTracePayResponse;
import com.payman.api.mapper.ResponseMapper;
import com.payman.api.service.HttpService;
import com.payman.api.service.payman.AccessTokenService;
import com.payman.api.service.payman.TracePaymentService;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Service
public class TracePaymentServiceImpl implements TracePaymentService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenService accessTokenService;

    public TracePaymentServiceImpl(HttpService httpService, UrlPropertiesConfig urlPropertiesConfig, AccessTokenService accessTokenService) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public TracePaymentResponse trace(HttpServletRequest httpServletRequest, String traceId, String date) throws IOException {
        String url = String.format("%s%s?trace-id=%s", urlPropertiesConfig.getBase(), urlPropertiesConfig.getTracePayment(), traceId);
        url += Objects.isNull(date) ? "" : String.format("&date=%s", date);

        Request paymanRequest = httpService.createRequest(url,
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken()));

        CustomizedResponse paymanCustomizedResponse = httpService.sendRequest(paymanRequest, TracePaymentServiceImpl.class);
        return (TracePaymentResponse) ResponseMapper
                .map(paymanCustomizedResponse.getBody(), PaymanTracePayResponse.class, TracePaymentResponse.class);
    }
}
