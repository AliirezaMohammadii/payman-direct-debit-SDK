package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.client.request.PayRequest;
import com.ighe3.api.dto.provider.response.PaymanPayResponse;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanPayRequest;
import com.ighe3.api.service.payman.PaymanPayService;
import com.ighe3.api.utils.*;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class PaymanPayServiceImpl implements PaymanPayService {

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;

    public PaymanPayServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public PaymanPayResponse pay(PayRequest inputDto) {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = httpService.createRequest(requestBody, urls.getPayUrl(), httpService.createHeaders(inputDto.getSourceInfo(), accessTokenService.getAccessToken()));
        Response paymanResponse = httpService.sendRequest(request, PaymanPayServiceImpl.class);
        return (PaymanPayResponse) HttpResponseMapper.convertJsonToJavaObject(paymanResponse.getBody(), PaymanPayResponse.class);
    }

    private RequestBody createRequestBody(PayRequest inputDto) {
        PaymanPayRequest requestBody = new PaymanPayRequest(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBody);
        return RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
    }
}
