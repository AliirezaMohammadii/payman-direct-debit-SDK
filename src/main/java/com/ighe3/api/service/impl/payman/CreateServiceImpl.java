package com.ighe3.api.service.impl.payman;

import com.ighe3.api.config.UrlPropertiesConfig;
import com.ighe3.api.dto.client.request.CreateRequest;
import com.ighe3.api.dto.client.response.CreateResponse;
import com.ighe3.api.mapper.RequestMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanCreateRequest;
import com.ighe3.api.service.payman.CreateService;
import com.ighe3.api.utils.*;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CreateServiceImpl implements CreateService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenServiceImpl accessTokenService;

    public CreateServiceImpl(HttpService httpService, UrlPropertiesConfig urlPropertiesConfig, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public CreateResponse create(CreateRequest request) throws IOException {
        RequestBody requestBody = RequestMapper.mapRequest(request, CreateRequest.class, PaymanCreateRequest.class
        );

        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getCreate(),
                createHeaders(request));

        Response paymanResponse = httpService.sendRequest(paymanRequest, CreateService.class);
        Headers headers = paymanResponse.getHeaders();
        return new CreateResponse(headers.get("Location"));
    }

    private Headers createHeaders(CreateRequest request) {
        Headers generalHeaders = httpService.createHeaders(request.getSourceInfo(), accessTokenService.getAccessToken());
        Headers headers = generalHeaders.newBuilder()
                .add(CustomHttpHeaders.MOBILE_NO, request.getMobileNumber())
                .add(CustomHttpHeaders.NATIONAL_CODE, request.getNationalCode())
                .build();
        return headers;
    }
}
