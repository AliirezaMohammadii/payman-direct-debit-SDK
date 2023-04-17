package com.payman.api.service.impl.payman;

import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.response.TracePaymentResponse;
import com.payman.api.dto.provider.response.CustomizedResponse;
import com.payman.api.dto.provider.response.PaymanTracePayResponse;
import com.payman.api.mapper.ResponseMapper;
import com.payman.api.service.HttpService;
import com.payman.api.service.payman.TracePaymentService;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class TracePaymentServiceImpl implements TracePaymentService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenServiceImpl accessTokenService;

    public TracePaymentServiceImpl(HttpService httpService, UrlPropertiesConfig urlPropertiesConfig, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public TracePaymentResponse trace(HttpServletRequest httpServletRequest, String traceId, String date)
            throws IOException {
        String url = urlPropertiesConfig.getBase() + urlPropertiesConfig.getTracePayment() +
                "?trace-id" + "=" + traceId + "&date" + "=" + date;

        Request paymanRequest = httpService.createRequest(url,
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken()));

        CustomizedResponse paymanCustomizedResponse = httpService.sendRequest(paymanRequest, TracePaymentServiceImpl.class);
        return (TracePaymentResponse) ResponseMapper
                .map(paymanCustomizedResponse.getBody(), PaymanTracePayResponse.class, TracePaymentResponse.class);
    }
}
