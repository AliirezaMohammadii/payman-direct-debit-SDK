package com.ighe3.api.management;

import com.ighe3.api.dal.dto.input.PaymanTransactionsInputDTO;
import com.ighe3.api.model.PaymanRequestFilterObject;
import com.ighe3.api.model.paymanRequestBodies.PaymanTransactionsRequestBodyObject;
import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PaymanTransactionsManager extends PaymanBaseManager {

    private PaymanService paymanService;

    public Response getTransactions(PaymanTransactionsInputDTO inputDto) throws IOException {
        RequestBody body = createRequestBody();
        Request request = getRequest(body, Urls.PAYMAN_TRANSACTION.getValue(), getHeaders());
        Response response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody() {

        PaymanRequestFilterObject filterObject = getPaymanRequestFilterObject();
        PaymanTransactionsRequestBodyObject requestBodyObject = getRequestBodyObject(filterObject);

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanRequestFilterObject getPaymanRequestFilterObject() {
        PaymanRequestFilterObject filterObject = new PaymanRequestFilterObject();
        filterObject.setTransactionType("...");
        filterObject.setFromTransactionAmount("...");
        filterObject.setToTransactionAmount("...");
        // ...
        return filterObject;
    }

    private PaymanTransactionsRequestBodyObject getRequestBodyObject(PaymanRequestFilterObject filterObject) {
        PaymanTransactionsRequestBodyObject requestBodyObject = new PaymanTransactionsRequestBodyObject();
        requestBodyObject.setFilter(filterObject);
        requestBodyObject.setLength("...");
        requestBodyObject.setOffset("...");
        return requestBodyObject;
    }

    @Override
    protected Headers getHeaders() throws IOException {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(),
                        GeneralUtils.BEARER_PREFIX + paymanService.getAccessToken())
                .build();
        return headers;
    }
}
