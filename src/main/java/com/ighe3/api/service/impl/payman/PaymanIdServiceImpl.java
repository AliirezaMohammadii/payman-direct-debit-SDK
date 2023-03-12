package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.provider.response.PaymanGetPaymanIdResponse;
import com.ighe3.api.exception.BaseException;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.service.payman.PaymanIdService;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class PaymanIdServiceImpl implements PaymanIdService {

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;

    public PaymanIdServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public String getPaymanId(String paymanCode) throws BaseException {
        String url = urls.getPaymanIdUrl() + "?payman_code" + "=" + paymanCode;
        Request request = httpService.createRequest(url, httpService.createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = httpService.sendRequest(request, PaymanIdServiceImpl.class);
        PaymanGetPaymanIdResponse paymanResponseBody
                = (PaymanGetPaymanIdResponse) HttpResponseMapper.convertJsonToJavaObject(paymanResponse.getBody(), PaymanGetPaymanIdResponse.class);
        return paymanResponseBody.getPaymanId();
    }
}
