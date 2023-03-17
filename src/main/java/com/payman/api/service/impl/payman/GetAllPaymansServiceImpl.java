package com.payman.api.service.impl.payman;

import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.request.GetAllPaymansRequest;
import com.payman.api.dto.client.response.GetAllPaymansResponse;
import com.payman.api.dto.provider.response.PaymanGetAllPaymansResponse;
import com.payman.api.mapper.RequestMapper;
import com.payman.api.mapper.ResponseMapper;
import com.payman.api.service.HttpService;
import com.payman.api.dto.Response;
import com.payman.api.dto.provider.request.PaymanGetAllPaymansRequest;
import com.payman.api.service.payman.GetAllPaymansService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class GetAllPaymansServiceImpl implements GetAllPaymansService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenServiceImpl accessTokenService;

    public GetAllPaymansServiceImpl(HttpService httpService, UrlPropertiesConfig urlPropertiesConfig, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public GetAllPaymansResponse get(HttpServletRequest httpServletRequest, GetAllPaymansRequest request) throws IOException {
        RequestBody requestBody = RequestMapper.mapRequest(request, GetAllPaymansRequest.class, PaymanGetAllPaymansRequest.class);
        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getList(),
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(paymanRequest, GetAllPaymansServiceImpl.class);
        return (GetAllPaymansResponse) ResponseMapper
                .mapResponse(paymanResponse.getBody(), PaymanGetAllPaymansResponse.class, GetAllPaymansResponse.class);
    }
}
