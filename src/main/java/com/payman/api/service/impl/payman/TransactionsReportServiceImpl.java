package com.payman.api.service.impl.payman;

import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.request.TransactionsReportRequest;
import com.payman.api.dto.client.response.TransactionsReportResponse;
import com.payman.api.dto.provider.response.PaymanTransactionsReportResponse;
import com.payman.api.mapper.RequestMapper;
import com.payman.api.mapper.ResponseMapper;
import com.payman.api.service.HttpService;
import com.payman.api.dto.provider.response.Response;
import com.payman.api.dto.provider.request.PaymanTransactionsReportRequest;
import com.payman.api.service.payman.TransactionsReportService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class TransactionsReportServiceImpl implements TransactionsReportService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenServiceImpl accessTokenService;

    public TransactionsReportServiceImpl(HttpService httpService, UrlPropertiesConfig urlPropertiesConfig, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public TransactionsReportResponse getReport(HttpServletRequest httpServletRequest, TransactionsReportRequest request) throws IOException {
        RequestBody requestBody = RequestMapper
                .map(request, TransactionsReportRequest.class, PaymanTransactionsReportRequest.class);

        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getTransactionsReport(),
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(paymanRequest, TransactionsReportServiceImpl.class);
        return (TransactionsReportResponse) ResponseMapper
                .map(paymanResponse.getBody(), PaymanTransactionsReportResponse.class, TransactionsReportResponse.class);
    }
}
