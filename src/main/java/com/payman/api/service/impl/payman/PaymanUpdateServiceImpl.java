package com.payman.api.service.impl.payman;

import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.request.UpdateRequest;
import com.payman.api.dto.client.response.UpdateResponse;
import com.payman.api.dto.provider.response.CustomizedResponse;
import com.payman.api.mapper.RequestMapper;
import com.payman.api.service.HttpService;
import com.payman.api.dto.provider.request.PaymanUpdateRequest;
import com.payman.api.service.payman.AccessTokenService;
import com.payman.api.service.payman.PaymanUpdateService;
import com.payman.api.utils.CustomHttpHeaders;
import okhttp3.*;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class PaymanUpdateServiceImpl implements PaymanUpdateService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenService accessTokenService;

    public PaymanUpdateServiceImpl(HttpService httpService, UrlPropertiesConfig urlPropertiesConfig, AccessTokenService accessTokenService) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public UpdateResponse update(HttpServletRequest httpServletRequest, UpdateRequest request) throws IOException {
        RequestBody requestBody = RequestMapper.map(request, UpdateRequest.class, PaymanUpdateRequest.class);
        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getUpdate(),
                createHeaders(httpServletRequest, request));

        CustomizedResponse paymanCustomizedResponse = httpService.sendRequest(paymanRequest, PaymanUpdateService.class);
        Headers headers = paymanCustomizedResponse.getHeaders();
        return new UpdateResponse(headers.get(HttpHeaders.LOCATION));
    }

    private Headers createHeaders(HttpServletRequest httpServletRequest, UpdateRequest request) {
        Headers generalHeaders = httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken());
        return generalHeaders.newBuilder()
                .add(CustomHttpHeaders.MOBILE_NO, request.getMobileNumber())
                .add(CustomHttpHeaders.NATIONAL_CODE, request.getNationalCode())
                .build();
    }
}
