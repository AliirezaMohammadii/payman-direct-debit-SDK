package com.ighe3.api.service.impl.payman;

import com.ighe3.api.config.UrlPropertiesConfig;
import com.ighe3.api.dto.client.request.TracePaymentRequest;
import com.ighe3.api.dto.client.response.TracePaymentResponse;
import com.ighe3.api.dto.provider.response.PaymanTracePayResponse;
import com.ighe3.api.mapper.ResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.service.payman.TracePaymentService;
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

        Response paymanResponse = httpService.sendRequest(paymanRequest, TracePaymentServiceImpl.class);
        return (TracePaymentResponse) ResponseMapper
                .mapResponse(paymanResponse.getBody(), PaymanTracePayResponse.class, TracePaymentResponse.class);
    }
}
