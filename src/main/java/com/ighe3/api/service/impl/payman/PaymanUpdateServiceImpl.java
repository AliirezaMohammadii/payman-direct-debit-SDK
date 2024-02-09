package com.ighe3.api.service.impl.payman;

import com.ighe3.api.config.UrlPropertiesConfig;
import com.ighe3.api.dto.client.request.UpdateRequest;
import com.ighe3.api.dto.client.response.UpdateResponse;
import com.ighe3.api.mapper.RequestMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanUpdateRequest;
import com.ighe3.api.service.payman.PaymanUpdateService;
import com.ighe3.api.utils.CustomHttpHeaders;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PaymanUpdateServiceImpl implements PaymanUpdateService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenServiceImpl accessTokenService;

    public PaymanUpdateServiceImpl(HttpService httpService, UrlPropertiesConfig urlPropertiesConfig, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public UpdateResponse update(UpdateRequest request) throws IOException {
        RequestBody requestBody = RequestMapper.mapRequest(request, UpdateRequest.class, PaymanUpdateRequest.class);
        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getUpdate(),
                createHeaders(request));

        Response paymanResponse = httpService.sendRequest(paymanRequest, PaymanUpdateService.class);
        Headers headers = paymanResponse.getHeaders();
        return new UpdateResponse(headers.get("Location"));
    }

    private Headers createHeaders(UpdateRequest request) {
        Headers generalHeaders = httpService.createHeaders(request.getSourceInfo(), accessTokenService.getAccessToken());
        Headers headers = generalHeaders.newBuilder()
                .add(CustomHttpHeaders.MOBILE_NO, request.getMobileNumber())
                .add(CustomHttpHeaders.NATIONAL_CODE, request.getNationalCode())
                .build();
        return headers;
    }
}
