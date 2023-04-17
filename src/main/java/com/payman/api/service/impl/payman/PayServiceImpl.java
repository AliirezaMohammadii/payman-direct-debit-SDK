package com.payman.api.service.impl.payman;

import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.request.PayRequest;
import com.payman.api.dto.client.response.PayResponse;
import com.payman.api.dto.provider.response.PaymanPayResponse;
import com.payman.api.mapper.RequestMapper;
import com.payman.api.mapper.ResponseMapper;
import com.payman.api.service.HttpService;
import com.payman.api.dto.provider.response.CustomizedResponse;
import com.payman.api.dto.provider.request.PaymanPayRequest;
import com.payman.api.service.payman.PayService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    public PayResponse pay(HttpServletRequest httpServletRequest, PayRequest request) throws IOException {
        RequestBody requestBody = RequestMapper.map(request, PayRequest.class, PaymanPayRequest.class);
        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getPay(),
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken()));

        CustomizedResponse paymanCustomizedResponse = httpService.sendRequest(paymanRequest, PayServiceImpl.class);
        return (PayResponse) ResponseMapper
                .map(paymanCustomizedResponse.getBody(), PaymanPayResponse.class, PayResponse.class);
    }
}
