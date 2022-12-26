package com.ighe3.api.management.payman;

import com.ighe3.api.dal.dto.payman.input.PaymanTransactionsInputDTO;
import com.ighe3.api.model.payman.PaymanRequestFilterObject;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.model.payman.requestBody.PaymanTransactionsRequestBodyObject;
import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class PaymanTransactionsManager extends PaymanBaseManager {

    private PaymanService paymanService;

    public ResponseObject getTransactions(PaymanTransactionsInputDTO inputDto) throws Exception {
        RequestBody body = createRequestBody();
        Request request = createRequest(body, Urls.PAYMAN_TRANSACTION.getValue(), createHeaders());
        ResponseObject response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody() throws Exception {

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
    protected Headers createHeaders() throws Exception {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(),
                        GeneralUtils.BEARER_PREFIX + paymanService.getAccessToken())
                .build();
        return headers;
    }
}
