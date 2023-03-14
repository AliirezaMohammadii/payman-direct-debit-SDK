package com.ighe3.api.service.impl.payman;

import com.ighe3.api.dto.client.request.UpdateRequest;
import com.ighe3.api.dto.client.response.UpdateResponse;
import com.ighe3.api.exception.BaseException;
import com.ighe3.api.service.HttpService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanUpdateRequest;
import com.ighe3.api.service.payman.PaymanUpdateService;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.CustomHttpHeaders;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymanUpdateServiceImpl implements PaymanUpdateService {

    @Value("${callback.url}")
    private String callbackUrl;

    private final HttpService httpService;
    private final Urls urls;
    private final AccessTokenServiceImpl accessTokenService;

    public PaymanUpdateServiceImpl(HttpService httpService, Urls urls, AccessTokenServiceImpl accessTokenService) {
        this.httpService = httpService;
        this.urls = urls;
        this.accessTokenService = accessTokenService;
    }

    @Override
    public UpdateResponse update(UpdateRequest inputDto) {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = httpService.createRequest(requestBody, urls.getUpdateUrl(), createHeaders(inputDto));
        Response paymanResponse = httpService.sendRequest(request, PaymanUpdateService.class);
        Headers headers = paymanResponse.getHeaders();
        return new UpdateResponse(headers.get("Location"));
    }

    private RequestBody createRequestBody(UpdateRequest inputDto) throws RuntimeException {
        PaymanUpdateRequest requestBodyObject = getRequestBodyObject(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        return RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
    }

    private PaymanUpdateRequest getRequestBodyObject(UpdateRequest inputDto) {
        PaymanUpdateRequest requestBodyObject = new PaymanUpdateRequest();
        requestBodyObject.setPaymanId(inputDto.getPaymanId());
        requestBodyObject.setExpirationDate(inputDto.getExpirationDate());
        requestBodyObject.setMaxDailyTransactionCount(inputDto.getMaxDailyTransactionCount());
        requestBodyObject.setMaxMonthlyTransactionCount(inputDto.getMaxMonthlyTransactionCount());
        requestBodyObject.setMaxTransactionAmount(inputDto.getMaxTransactionAmount());
        requestBodyObject.setMaxDailyTransactionCount(inputDto.getMaxDailyTransactionCount());
        requestBodyObject.setRedirectUrl(callbackUrl + "?userId=" + inputDto.getUserId());
        return requestBodyObject;
    }

    private Headers createHeaders(UpdateRequest inputDto) throws RuntimeException {
        Headers generalHeaders = httpService.createHeaders(accessTokenService.getAccessToken());
        Headers headers = generalHeaders.newBuilder()
                .add(CustomHttpHeaders.MOBILE_NO, inputDto.getMobileNumber())
                .add(CustomHttpHeaders.NATIONAL_CODE, inputDto.getNationalCode())
                .build();
        return headers;
    }
}
