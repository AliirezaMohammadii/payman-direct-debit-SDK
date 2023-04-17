package com.payman.api.service.impl.payman;

import com.payman.api.config.UrlPropertiesConfig;
import com.payman.api.dto.client.response.GetPaymanResponse;
import com.payman.api.dto.provider.response.PaymanGetPaymanResponse;
import com.payman.api.mapper.ResponseMapper;
import com.payman.api.service.HttpService;
import com.payman.api.dto.provider.response.Response;
import com.payman.api.service.payman.GetPaymanService;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class GetPaymanServiceImpl implements GetPaymanService {

    private final HttpService httpService;
    private final UrlPropertiesConfig urlPropertiesConfig;
    private final AccessTokenServiceImpl accessTokenService;

    public GetPaymanServiceImpl(HttpService httpService, UrlPropertiesConfig urlPropertiesConfig, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urlPropertiesConfig = urlPropertiesConfig;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public GetPaymanResponse get(HttpServletRequest httpServletRequest, String paymanId) throws IOException {
        String url = urlPropertiesConfig.getBase() + urlPropertiesConfig.getReport() + "/" + paymanId;
        Request paymanRequest = httpService.createRequest(url,
                httpService.createHeaders(httpServletRequest, accessTokenService.getAccessToken()));

        Response paymanResponse = httpService.sendRequest(paymanRequest, GetPaymanServiceImpl.class);
        return (GetPaymanResponse) ResponseMapper
                .mapResponse(paymanResponse.getBody(), PaymanGetPaymanResponse.class, GetPaymanResponse.class);
    }
}
