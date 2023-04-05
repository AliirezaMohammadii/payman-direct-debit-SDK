package com.payman.api.service.impl.payman;

import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.response.GetPaymanIdResponse;
import com.payman.api.dto.provider.response.PaymanGetPaymanIdResponse;
import com.payman.api.mapper.ResponseMapper;
import com.payman.api.service.HttpService;
import com.payman.api.dto.Response;
import com.payman.api.service.payman.PaymanIdService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    public GetPaymanIdResponse getPaymanId(HttpServletRequest httpServletRequest, String paymanCode)
            throws IOException {
        String url = urlPropertiesConfig.getBase() + urlPropertiesConfig.getPaymanId()
                + "?payman_code" + "=" + paymanCode;

        Request paymanRequest = httpService.createRequest(url,
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(paymanRequest, PaymanIdServiceImpl.class);
        return (GetPaymanIdResponse) ResponseMapper
                .mapResponse(paymanResponse.getBody(), PaymanGetPaymanIdResponse.class, GetPaymanIdResponse.class);
    }
}
