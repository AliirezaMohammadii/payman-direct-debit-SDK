package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.client.request.PayRequest;
import com.ighe3.api.dto.provider.response.PaymanPayResponse;
import com.ighe3.api.exception.BaseException;
import com.ighe3.api.mapper.HttpResponseMapper;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanPayRequest;
import com.ighe3.api.service.payman.PayService;
import com.ighe3.api.utils.*;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;

    public PayServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public PaymanPayResponse pay(PayRequest inputDto) throws BaseException {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = httpService.createRequest(requestBody, urls.getPayUrl(), httpService.createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = httpService.sendRequest(request, PayServiceImpl.class);
        return (PaymanPayResponse) HttpResponseMapper.convertJsonToJavaObject(paymanResponse.getBody(), PaymanPayResponse.class);
    }

    private RequestBody createRequestBody(PayRequest inputDto) throws RuntimeException {
        PaymanPayRequest requestBodyObject = getRequestBodyObject(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        return RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
    }

    private PaymanPayRequest getRequestBodyObject(PayRequest inputDto) {
        PaymanPayRequest requestBodyObject = new PaymanPayRequest();
        requestBodyObject.setTraceId(TraceIdGenerator.generate());
        requestBodyObject.setAmount(inputDto.getAmount());
        requestBodyObject.setDescription(inputDto.getDescription());
        requestBodyObject.setClientTransactionDate(inputDto.getClientTransactionDate());
        requestBodyObject.setPaymanId(inputDto.getPaymanId());
        requestBodyObject.setPayId(inputDto.getPayId());
        requestBodyObject.setCommissionAmount(inputDto.getCommissionAmount());
        return requestBodyObject;
    }
}
