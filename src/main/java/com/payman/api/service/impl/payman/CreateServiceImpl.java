package com.payman.api.service.impl.payman;

import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.request.CreateRequest;
import com.payman.api.dto.client.response.CreateResponse;
import com.payman.api.mapper.RequestMapper;
import com.payman.api.service.HttpService;
import com.payman.api.dto.Response;
import com.payman.api.dto.provider.request.PaymanCreateRequest;
import com.payman.api.service.payman.CreateService;
import com.payman.api.utils.CustomHttpHeaders;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    public CreateResponse create(HttpServletRequest httpServletRequest, CreateRequest request) throws IOException {
        RequestBody requestBody = RequestMapper.mapRequest(request, CreateRequest.class, PaymanCreateRequest.class);

        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getCreate(),
                createHeaders(httpServletRequest, request));

        Response paymanResponse = httpService.sendRequest(paymanRequest, CreateService.class);
        Headers headers = paymanResponse.getHeaders();
        return new CreateResponse(headers.get("Location"));
    }

    private Headers createHeaders(HttpServletRequest httpServletRequest, CreateRequest request) {
        Headers generalHeaders = httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken());
        Headers headers = generalHeaders.newBuilder()
                .add(CustomHttpHeaders.MOBILE_NO, request.getMobileNumber())
                .add(CustomHttpHeaders.NATIONAL_CODE, request.getNationalCode())
                .build();
        return headers;
    }
}
