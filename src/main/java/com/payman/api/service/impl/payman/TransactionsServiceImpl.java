package com.payman.api.service.impl.payman;

import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.request.TransactionsRequest;
import com.payman.api.dto.client.response.TransactionsResponse;
import com.payman.api.dto.provider.response.PaymanTransactionsResponse;
import com.payman.api.mapper.RequestMapper;
import com.payman.api.mapper.ResponseMapper;
import com.payman.api.service.HttpService;
import com.payman.api.dto.provider.response.Response;
import com.payman.api.dto.provider.request.PaymanTransactionsRequest;
import com.payman.api.service.payman.TransactionsService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenServiceImpl accessTokenService;

    public TransactionsServiceImpl(HttpService httpService, UrlPropertiesConfig urlPropertiesConfig, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public TransactionsResponse getTransactions(HttpServletRequest httpServletRequest, TransactionsRequest request) throws IOException {
        RequestBody requestBody = RequestMapper
                .map(request, TransactionsRequest.class, PaymanTransactionsRequest.class);

        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getTransactionsReport(),
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(paymanRequest, TransactionsReportServiceImpl.class);
        return (TransactionsResponse) ResponseMapper
                .map(paymanResponse.getBody(), PaymanTransactionsResponse.class, TransactionsResponse.class);
    }
}
