package com.ighe3.api.management;

import com.ighe3.api.dal.dto.input.PaymanPayInputDTO;
import com.ighe3.api.model.paymanRequestBodies.PaymanPayRequestBodyObject;
import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PaymanPayManager extends PaymanBaseManager {

    private PaymanService paymanService;

    public Response pay(PaymanPayInputDTO inputDto) throws IOException {
        RequestBody body = createRequestBody();
        Request request = getRequest(body, Urls.PAYMAN_PAY.getValue(), getHeaders());
        Response response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody() {
        PaymanPayRequestBodyObject requestBodyObject = getRequestBodyObject();
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanPayRequestBodyObject getRequestBodyObject() {
        PaymanPayRequestBodyObject requestBodyObject = new PaymanPayRequestBodyObject();
        requestBodyObject.setTraceId("11");
        requestBodyObject.setAmount(100_000D);
        requestBodyObject.setDescription("some description");
        requestBodyObject.setClientTransactionDate("2022-12-20T08:28:09.5662061+04:30");
        requestBodyObject.setPaymanId("mIA5qLv581Fn");
        return requestBodyObject;
    }

    @Override
    protected Headers getHeaders() throws IOException {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(), GeneralUtils.BEARER_PREFIX + paymanService.getAccessToken())
                .build();
        return headers;
    }
}
