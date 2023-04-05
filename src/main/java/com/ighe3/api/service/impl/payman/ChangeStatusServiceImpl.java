package com.ighe3.api.service.impl.payman;

import com.ighe3.api.config.UrlPropertiesConfig;
import com.ighe3.api.dto.client.request.ChangeStatusRequest;
import com.ighe3.api.dto.client.response.ChangeStatusResponse;
import com.ighe3.api.dto.provider.response.PaymanChangeStatusResponse;
import com.ighe3.api.mapper.RequestMapper;
import com.ighe3.api.mapper.ResponseMapper;
import com.ighe3.api.service.payman.ChangeStatusService;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanChangeStatusRequest;
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
                .mapRequest(request, ChangeStatusRequest.class, PaymanChangeStatusRequest.class);

        Request paymanRequest = httpService.createRequest(requestBody,
                urlPropertiesConfig.getBase() + urlPropertiesConfig.getChangeStatus(),
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(paymanRequest, ChangeStatusServiceImpl.class);
        return (ChangeStatusResponse) ResponseMapper
                .mapResponse(paymanResponse.getBody(), PaymanChangeStatusResponse.class, ChangeStatusResponse.class);
    }
}
