package com.ighe3.api.service.impl.payman;

import com.ighe3.api.config.UrlPropertiesConfig;
import com.ighe3.api.dto.client.request.TransactionsRequest;
import com.ighe3.api.dto.client.response.TransactionsResponse;
import com.ighe3.api.dto.provider.response.PaymanTransactionsResponse;
import com.ighe3.api.mapper.RequestMapper;
import com.ighe3.api.mapper.ResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanTransactionsRequest;
import com.ighe3.api.service.payman.TransactionsService;
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
                .mapRequest(request, TransactionsRequest.class, PaymanTransactionsRequest.class);

        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getTransactionsReport(),
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(paymanRequest, TransactionsReportServiceImpl.class);
        return (TransactionsResponse) ResponseMapper
                .mapResponse(paymanResponse.getBody(), PaymanTransactionsResponse.class, TransactionsResponse.class);
    }
}
