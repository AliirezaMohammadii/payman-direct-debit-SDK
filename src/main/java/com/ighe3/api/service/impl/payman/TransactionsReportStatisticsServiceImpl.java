package com.ighe3.api.service.impl.payman;

import com.ighe3.api.config.UrlPropertiesConfig;
import com.ighe3.api.dto.client.request.TransactionsReportStatisticsRequest;
import com.ighe3.api.dto.client.response.TransactionsReportStatisticsResponse;
import com.ighe3.api.dto.provider.response.PaymanTransactionsReportStatisticsResponse;
import com.ighe3.api.mapper.RequestMapper;
import com.ighe3.api.mapper.ResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanTransactionsReportStatisticsRequest;
import com.ighe3.api.service.payman.TransactionsReportStatisticsService;
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
