package com.ighe3.api.service.payman;

import com.ighe3.api.dal.dto.input.TransactionsInputDTO;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.FilterObject;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.model.requestBodies.PaymanTransactionsRequestBodyObject;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TransactionsService extends BaseService {
    private final AccessTokenService accessTokenService;

    public TransactionsService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public Object getTransactions(TransactionsInputDTO inputDto) throws Exception {
        ResponseObject response = getResponseObject();
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        return response;
    }

    private ResponseObject getResponseObject() throws Exception {
        RequestBody requestBody = createRequestBody();
        Request request = createRequest(requestBody, Urls.TRANSACTIONS.getValue(), createHeaders());
        ResponseObject response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody() throws Exception {

        FilterObject filterObject = getPaymanRequestFilterObject();
        PaymanTransactionsRequestBodyObject requestBodyObject = getRequestBodyObject(filterObject);

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private FilterObject getPaymanRequestFilterObject() {
        FilterObject filterObject = new FilterObject();
        filterObject.setTransactionType(null);
        filterObject.setFromTransactionAmount(null);
        filterObject.setToTransactionAmount(null);
        // ...
        return filterObject;
    }

    private PaymanTransactionsRequestBodyObject getRequestBodyObject(FilterObject filterObject) {
        PaymanTransactionsRequestBodyObject requestBodyObject = new PaymanTransactionsRequestBodyObject();
        requestBodyObject.setFilter(filterObject);
        requestBodyObject.setLength(null);
        requestBodyObject.setOffset(null);
        return requestBodyObject;
    }

    @Override
    protected Headers createHeaders() throws Exception {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(),
                        GeneralUtils.BEARER_PREFIX + accessTokenService.getAccessToken())
                .build();
        return headers;
    }
}
