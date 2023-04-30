package com.payman.api.service.impl.payman;

import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.request.TransactionsStatisticReportRequest;
import com.payman.api.dto.client.response.TransactionsStatisticReportResponse;
import com.payman.api.dto.provider.response.CustomizedResponse;
import com.payman.api.dto.provider.response.PaymanTransactionsStatisticReportResponse;
import com.payman.api.mapper.RequestMapper;
import com.payman.api.mapper.ResponseMapper;
import com.payman.api.service.HttpService;
import com.payman.api.dto.provider.request.PaymanTransactionsStatisticReportRequest;
import com.payman.api.service.payman.AccessTokenService;
import com.payman.api.service.payman.TransactionsStatisticReportService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class TransactionsStatisticReportServiceImpl implements TransactionsStatisticReportService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenService accessTokenService;

    public TransactionsStatisticReportServiceImpl(HttpService httpService,
                                                  UrlPropertiesConfig urlPropertiesConfig,
                                                  AccessTokenService accessTokenService) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public TransactionsStatisticReportResponse getReport(HttpServletRequest httpServletRequest, TransactionsStatisticReportRequest request) throws IOException {
        RequestBody requestBody = RequestMapper
                .map(request, TransactionsStatisticReportRequest.class, PaymanTransactionsStatisticReportRequest.class);

        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getTransactionsStatisticReport(),
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken()));

        CustomizedResponse paymanCustomizedResponse = httpService.sendRequest(paymanRequest, TransactionsReportServiceImpl.class);
        return (TransactionsStatisticReportResponse) ResponseMapper
                .map(paymanCustomizedResponse.getBody(), PaymanTransactionsStatisticReportResponse.class, TransactionsStatisticReportResponse.class);
    }
}
