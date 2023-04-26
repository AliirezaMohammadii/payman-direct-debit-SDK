package com.payman.api.service.impl.payman;

import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.response.GetPaymanIdResponse;
import com.payman.api.dto.provider.response.PaymanGetPaymanIdResponse;
import com.payman.api.mapper.ResponseMapper;
import com.payman.api.service.HttpService;
import com.payman.api.dto.provider.response.CustomizedResponse;
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
        String url = String.format("%s%s?payman_code=%s", urlPropertiesConfig.getBase(), urlPropertiesConfig.getPaymanId(), paymanCode);

        Request paymanRequest = httpService.createRequest(url,
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken()));

        CustomizedResponse paymanCustomizedResponse = httpService.sendRequest(paymanRequest, PaymanIdServiceImpl.class);
        return (GetPaymanIdResponse) ResponseMapper
                .map(paymanCustomizedResponse.getBody(), PaymanGetPaymanIdResponse.class, GetPaymanIdResponse.class);
    }

    @Override
    public GetPaymanIdResponse getPaymanId(String paymanCode)
            throws IOException {
        String url = String.format("%s%s?payman_code=%s", urlPropertiesConfig.getBase(), urlPropertiesConfig.getPaymanId(), paymanCode);

        Request paymanRequest = httpService.createRequest(url,
                httpService.createInternalRequestHeaders(accessTokenService.getAccessToken()));

        CustomizedResponse paymanCustomizedResponse = httpService.sendRequest(paymanRequest, PaymanIdServiceImpl.class);
        return (GetPaymanIdResponse) ResponseMapper
                .map(paymanCustomizedResponse.getBody(), PaymanGetPaymanIdResponse.class, GetPaymanIdResponse.class);
    }
}
