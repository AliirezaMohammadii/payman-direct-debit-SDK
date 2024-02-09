package com.ighe3.api.service.impl.payman;

import com.ighe3.api.config.UrlPropertiesConfig;
import com.ighe3.api.dto.client.request.PayRequest;
import com.ighe3.api.dto.client.response.PayResponse;
import com.ighe3.api.dto.provider.response.PaymanPayResponse;
import com.ighe3.api.mapper.RequestMapper;
import com.ighe3.api.mapper.ResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanPayRequest;
import com.ighe3.api.service.payman.PayService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PayServiceImpl implements PayService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenServiceImpl accessTokenService;

    public PayServiceImpl(HttpService httpService, UrlPropertiesConfig urlPropertiesConfig, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public PayResponse pay(PayRequest request) throws IOException {
        RequestBody requestBody = RequestMapper.mapRequest(request, PayRequest.class, PaymanPayRequest.class);
        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getPay(),
                httpService.createHeaders(request.getSourceInfo(), accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(paymanRequest, PayServiceImpl.class);
        return (PayResponse) ResponseMapper
                .mapResponse(paymanResponse.getBody(), PaymanPayResponse.class, PayResponse.class);
    }
}
