package com.payman.api.service.impl.payman;

import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.request.TransactionsReportStatisticsRequest;
import com.payman.api.dto.client.response.TransactionsReportStatisticsResponse;
import com.payman.api.dto.provider.response.PaymanTransactionsReportStatisticsResponse;
import com.payman.api.mapper.RequestMapper;
import com.payman.api.mapper.ResponseMapper;
import com.payman.api.service.HttpService;
import com.payman.api.dto.provider.response.Response;
import com.payman.api.dto.provider.request.PaymanTransactionsReportStatisticsRequest;
import com.payman.api.service.payman.TransactionsReportStatisticsService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class TransactionsReportStatisticsServiceImpl implements TransactionsReportStatisticsService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenServiceImpl accessTokenService;

    public TransactionsReportStatisticsServiceImpl(HttpService httpService,
                                                   UrlPropertiesConfig urlPropertiesConfig,
                                                   AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public TransactionsReportStatisticsResponse getReportStatistics(HttpServletRequest httpServletRequest, TransactionsReportStatisticsRequest request) throws IOException {
        RequestBody requestBody = RequestMapper
                .mapRequest(request, TransactionsReportStatisticsRequest.class, PaymanTransactionsReportStatisticsRequest.class);

        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getTransactionsReport(),
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(paymanRequest, TransactionsReportServiceImpl.class);
        return (TransactionsReportStatisticsResponse) ResponseMapper
                .mapResponse(paymanResponse.getBody(), PaymanTransactionsReportStatisticsResponse.class, TransactionsReportStatisticsResponse.class);
    }
}
