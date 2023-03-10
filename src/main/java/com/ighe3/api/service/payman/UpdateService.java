package com.ighe3.api.service.payman;

import com.ighe3.api.dto.input.PaymanUpdateIto;
import com.ighe3.api.dto.output.PaymanUpdateOto;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.BaseResponse;
import com.ighe3.api.model.request.PaymanUpdateRequest;
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

    public UpdateService(AccessTokenService accessTokenService, Urls urls) {
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanUpdateOto update(PaymanUpdateIto inputDto) throws Exception {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getUpdateUrl(), createHeaders(inputDto));
        BaseResponse paymanResponse = sendRequest(request);
        Headers headers = paymanResponse.getHeaders();
        PaymanUpdateOto appResponse = new PaymanUpdateOto(headers.get("Location"));
        return appResponse;
    }

    private RequestBody createRequestBody(PaymanUpdateIto inputDto) throws Exception {
        PaymanUpdateRequest requestBodyObject = getRequestBodyObject(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanUpdateRequest getRequestBodyObject(PaymanUpdateIto inputDto) {
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

    private Headers createHeaders(PaymanUpdateIto inputDto) throws Exception {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(), GeneralUtils.BEARER_PREFIX + accessTokenService.getAccessToken())
                .add(RequestHeaderKeys.MOBILE_NO.getValue(), inputDto.getMobileNumber())
                .add(RequestHeaderKeys.NATIONAL_CODE.getValue(), inputDto.getNationalCode())
                .build();
        return headers;
    }

    @Override
    protected Headers createHeaders() throws Exception {
        return null;
    }
}
