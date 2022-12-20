package com.ighe3.api.management;

import com.ighe3.api.dal.dto.input.PaymanUpdateInputDTO;
import com.ighe3.api.model.paymanRequestBodies.PaymanUpdateRequestBodyObject;
import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PaymanUpdateManager extends PaymanBaseManager {

    private PaymanService paymanService;

    public Response update(PaymanUpdateInputDTO inputDto) throws IOException {
        RequestBody body = createRequestBody();
        Request request = getRequest(body, Urls.UPDATE_PAYMAN.getValue(), getHeaders());
        Response response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody() {

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
    protected Headers getHeaders() throws IOException {
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
