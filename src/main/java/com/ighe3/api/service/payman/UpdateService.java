package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.CreateRequest;
import com.ighe3.api.dto.client.request.UpdateRequest;
import com.ighe3.api.dto.client.response.UpdateResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.Response;
import com.ighe3.api.dto.provider.request.PaymanUpdateRequest;
import com.ighe3.api.utils.ExceptionTranslator;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.RequestHeaderKeys;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UpdateService extends BaseService {

    @Value("${callback.url}")
    private String callbackUrl;

    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public UpdateService(ExceptionTranslator exceptionTranslator, AccessTokenService accessTokenService, Urls urls) {
        super(exceptionTranslator);
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public UpdateResponse update(UpdateRequest inputDto) throws RuntimeException {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getUpdateUrl(), createHeaders(inputDto));
        Response paymanResponse = sendRequest(request, UpdateService.class);
        Headers headers = paymanResponse.getHeaders();
        UpdateResponse appResponse = new UpdateResponse(headers.get("Location"));
        return appResponse;
    }

    private RequestBody createRequestBody(UpdateRequest inputDto) throws RuntimeException {
        PaymanUpdateRequest requestBodyObject = getRequestBodyObject(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
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

    /**
     * Overloading method and using parent's one inside it.
     */
    protected Headers createHeaders(UpdateRequest inputDto) throws RuntimeException {
        Headers generalHeaders = createHeaders(accessTokenService.getAccessToken());
        Headers headers = generalHeaders.newBuilder()
                .add(RequestHeaderKeys.MOBILE_NO.getValue(), inputDto.getMobileNumber())
                .add(RequestHeaderKeys.NATIONAL_CODE.getValue(), inputDto.getNationalCode())
                .build();
        return headers;
    }
}
