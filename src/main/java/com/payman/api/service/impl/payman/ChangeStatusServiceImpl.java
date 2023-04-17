package com.payman.api.service.impl.payman;

import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.request.ChangeStatusRequest;
import com.payman.api.dto.client.response.ChangeStatusResponse;
import com.payman.api.dto.provider.response.PaymanChangeStatusResponse;
import com.payman.api.mapper.RequestMapper;
import com.payman.api.mapper.ResponseMapper;
import com.payman.api.service.payman.ChangeStatusService;
import com.payman.api.service.HttpService;
import com.payman.api.dto.provider.response.CustomizedResponse;
import com.payman.api.dto.provider.request.PaymanChangeStatusRequest;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class ChangeStatusServiceImpl implements ChangeStatusService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenServiceImpl accessTokenService;

    public ChangeStatusServiceImpl(HttpService httpService, UrlPropertiesConfig urlPropertiesConfig, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public ChangeStatusResponse changeStatus(HttpServletRequest httpServletRequest, ChangeStatusRequest request) throws IOException {
        RequestBody requestBody = RequestMapper
                .map(request, ChangeStatusRequest.class, PaymanChangeStatusRequest.class);

        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getChangeStatus(),
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken()));

        CustomizedResponse paymanCustomizedResponse = httpService.sendRequest(paymanRequest, ChangeStatusServiceImpl.class);
        return (ChangeStatusResponse) ResponseMapper
                .map(paymanCustomizedResponse.getBody(), PaymanChangeStatusResponse.class, ChangeStatusResponse.class);
    }
}
