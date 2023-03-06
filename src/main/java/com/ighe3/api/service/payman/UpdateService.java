package com.ighe3.api.service.payman;

import com.ighe3.api.controller.dto.input.PaymanUpdateIto;
import com.ighe3.api.controller.dto.output.PaymanUpdateOto;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.ResponseModel;
import com.ighe3.api.model.request.PaymanUpdateRequestBodyObject;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class UpdateService extends BaseService {
    private final AccessTokenService accessTokenService;

    public UpdateService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public PaymanUpdateOto update(PaymanUpdateIto inputDto) throws Exception {
        ResponseModel paymanResponse = getResponseObject(inputDto);
        Headers headers = paymanResponse.getHeaders();
        PaymanUpdateOto appResponse = new PaymanUpdateOto(headers.get("Location"));
        return appResponse;
    }

    private ResponseModel getResponseObject(PaymanUpdateIto inputDto) throws Exception {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, Urls.UPDATE.getValue(), createHeaders());
        ResponseModel response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody(PaymanUpdateIto inputDto) throws Exception {
        PaymanUpdateRequestBodyObject requestBodyObject = getRequestBodyObject(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanUpdateRequestBodyObject getRequestBodyObject(PaymanUpdateIto inputDto) {
        PaymanUpdateRequestBodyObject requestBodyObject = new PaymanUpdateRequestBodyObject();
        requestBodyObject.setPaymanId(null);
        requestBodyObject.setExpirationDate(null);
        requestBodyObject.setMaxDailyTransactionCount(null);
        requestBodyObject.setMaxMonthlyTransactionCount(null);
        requestBodyObject.setMaxTransactionAmount(null);
        requestBodyObject.setRedirectUrl(null);
        return requestBodyObject;
    }

    @Override
    protected Headers createHeaders() throws Exception {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(), GeneralUtils.BEARER_PREFIX + accessTokenService.getAccessToken())
                .add(RequestHeaderKeys.MOBILE_NO.getValue(), "09120000000")
                .add(RequestHeaderKeys.NATIONAL_CODE.getValue(), "0123456789")
                .build();
        return headers;
    }

    @Override
    protected Object convertJsonToJavaObject(String value) {
        return null;
    }
}
