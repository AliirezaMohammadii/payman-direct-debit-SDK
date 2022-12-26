package com.ighe3.api.management.payman;

import com.ighe3.api.dal.dto.payman.input.PaymanUpdateInputDTO;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.model.payman.requestBody.PaymanUpdateRequestBodyObject;
import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class PaymanUpdateManager extends PaymanBaseManager {

    private PaymanService paymanService;

    public ResponseObject update(PaymanUpdateInputDTO inputDto) throws Exception {
        RequestBody body = createRequestBody();
        Request request = createRequest(body, Urls.UPDATE_PAYMAN.getValue(), createHeaders());
        ResponseObject response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody() throws Exception {

        PaymanUpdateRequestBodyObject requestBodyObject = getRequestBodyObject();

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanUpdateRequestBodyObject getRequestBodyObject() {
        PaymanUpdateRequestBodyObject requestBodyObject = new PaymanUpdateRequestBodyObject();
        requestBodyObject.setPaymanId("...");
        requestBodyObject.setExpirationDate("...");
        requestBodyObject.setMaxDailyTransactionCount("...");
        requestBodyObject.setMaxMonthlyTransactionCount("...");
        requestBodyObject.setMaxTransactionAmount("...");
        requestBodyObject.setRedirectUrl("...");
        return requestBodyObject;
    }

    @Override
    protected Headers createHeaders() throws Exception {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(), GeneralUtils.BEARER_PREFIX
                        + paymanService.getAccessToken())
                .add(RequestHeaderKeys.MOBILE_NO.getValue(), "09120000000")
                .add(RequestHeaderKeys.NATIONAL_CODE.getValue(), "0123456789")
                .build();
        return headers;
    }
}
