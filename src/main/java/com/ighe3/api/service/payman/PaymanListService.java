package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.dto.input.PaymanListIto;
import com.ighe3.api.model.response.PaymanListResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.PaymanRequestFilter;
import com.ighe3.api.dto.BaseResponse;
import com.ighe3.api.model.request.PaymanListRequest;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class PaymanListService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public PaymanListService(AccessTokenService accessTokenService, Urls urls) {
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public Object getList(PaymanListIto inputDto) throws Exception {
        BaseResponse paymanResponse = getResponseObject();
        PaymanListResponse paymanResponseBody = (PaymanListResponse) convertJsonToJavaObject(paymanResponse.getBody());
        return null;
    }

    private BaseResponse getResponseObject() throws Exception {
        RequestBody requestBody = createRequestBody();
        Request request = createRequest(requestBody, urls.getPaymanListUrl(), createHeaders());
        BaseResponse response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody() throws Exception {

        PaymanRequestFilter paymanRequestFilter = getPaymanRequestFilterObject();
        PaymanListRequest requestBodyObject = getRequestBodyObject(paymanRequestFilter);

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        // TODO
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanRequestFilter getPaymanRequestFilterObject() {
        PaymanRequestFilter paymanRequestFilter = new PaymanRequestFilter();
        paymanRequestFilter.setTransactionType(null);
        paymanRequestFilter.setFromTransactionAmount(null);
        paymanRequestFilter.setToTransactionAmount(null);
        // ...
        return paymanRequestFilter;
    }

    private PaymanListRequest getRequestBodyObject(PaymanRequestFilter paymanRequestFilter) {
        PaymanListRequest requestBodyObject = new PaymanListRequest();
        requestBodyObject.setFilter(paymanRequestFilter);
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

    @Override
    protected Object convertJsonToJavaObject(String value) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            PaymanListResponse response = mapper.readValue(value, PaymanListResponse.class);
            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
