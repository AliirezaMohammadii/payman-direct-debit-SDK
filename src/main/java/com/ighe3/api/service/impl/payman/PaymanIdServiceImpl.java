package com.ighe3.api.service.impl.payman;

import com.ighe3.api.config.UrlPropertiesConfig;
import com.ighe3.api.dto.client.request.GetPaymanIdRequest;
import com.ighe3.api.dto.client.response.GetPaymanIdResponse;
import com.ighe3.api.dto.provider.response.PaymanGetPaymanIdResponse;
import com.ighe3.api.mapper.ResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.service.payman.PaymanIdService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PaymanIdServiceImpl implements PaymanIdService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenServiceImpl accessTokenService;

    public PaymanIdServiceImpl(HttpService httpService, UrlPropertiesConfig urlPropertiesConfig, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public GetPaymanIdResponse getPaymanId(GetPaymanIdRequest request) throws IOException {
        String url = urlPropertiesConfig.getBase() + urlPropertiesConfig.getPaymanId() + "?payman_code" + "=" + request.getPaymanCode();
        Request paymanRequest = httpService.createRequest(url,
                httpService.createHeaders(request.getSourceInfo(), accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(paymanRequest, PaymanIdServiceImpl.class);
        return (GetPaymanIdResponse) ResponseMapper
                .mapResponse(paymanResponse.getBody(), PaymanGetPaymanIdResponse.class, GetPaymanIdResponse.class);
    }
}
