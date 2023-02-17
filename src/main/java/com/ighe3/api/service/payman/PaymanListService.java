package com.ighe3.api.service.payman;

import com.ighe3.api.dal.dto.input.ListInputDTO;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.FilterObject;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.model.requestBodies.PaymanListRequestBodyObject;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymanListService extends BaseService {
    private final AccessTokenService accessTokenService;

    public PaymanListService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public Object getList(ListInputDTO inputDto) throws Exception {
        ResponseObject response = getResponseObject();
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        return response;
    }

    private ResponseObject getResponseObject() throws Exception {
        RequestBody requestBody = createRequestBody();
        Request request = createRequest(requestBody, Urls.PAYMAN_LIST.getValue(), createHeaders());
        ResponseObject response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody() throws Exception {

        FilterObject filterObject = getPaymanRequestFilterObject();
        PaymanListRequestBodyObject requestBodyObject = getRequestBodyObject(filterObject);

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

    private PaymanListRequestBodyObject getRequestBodyObject(FilterObject filterObject) {
        PaymanListRequestBodyObject requestBodyObject = new PaymanListRequestBodyObject();
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
