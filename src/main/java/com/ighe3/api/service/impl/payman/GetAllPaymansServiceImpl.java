package com.ighe3.api.service.impl.payman;

import com.ighe3.api.config.UrlPropertiesConfig;
import com.ighe3.api.dto.client.request.GetAllPaymansRequest;
import com.ighe3.api.dto.client.response.GetAllPaymansResponse;
import com.ighe3.api.dto.provider.response.PaymanGetAllPaymansResponse;
import com.ighe3.api.mapper.RequestMapper;
import com.ighe3.api.mapper.ResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanGetAllPaymansRequest;
import com.ighe3.api.service.payman.GetAllPaymansService;
import okhttp3.*;
import org.springframework.stereotype.Service;

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
    public GetAllPaymansResponse get(GetAllPaymansRequest request) throws IOException {
        RequestBody requestBody = RequestMapper.mapRequest(request, GetAllPaymansRequest.class, PaymanGetAllPaymansRequest.class);
        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getList(),
                httpService.createHeaders(request.getSourceInfo(), accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(paymanRequest, GetAllPaymansServiceImpl.class);
        return (GetAllPaymansResponse) ResponseMapper
                .mapResponse(paymanResponse.getBody(), PaymanGetAllPaymansResponse.class, GetAllPaymansResponse.class);
    }
}
