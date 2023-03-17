package com.ighe3.api.service.impl.payman;

import com.ighe3.api.config.UrlPropertiesConfig;
import com.ighe3.api.dto.client.request.TransactionsReportRequest;
import com.ighe3.api.dto.client.response.TransactionsReportResponse;
import com.ighe3.api.dto.provider.response.PaymanTransactionsReportResponse;
import com.ighe3.api.mapper.RequestMapper;
import com.ighe3.api.mapper.ResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanTransactionsReportRequest;
import com.ighe3.api.service.payman.TransactionsReportService;
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
                .mapRequest(request, TransactionsReportRequest.class, PaymanTransactionsReportRequest.class);

        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getTransactionsReport(),
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(paymanRequest, TransactionsReportServiceImpl.class);
        return (TransactionsReportResponse) ResponseMapper
                .mapResponse(paymanResponse.getBody(), PaymanTransactionsReportResponse.class, TransactionsReportResponse.class);
    }
}
