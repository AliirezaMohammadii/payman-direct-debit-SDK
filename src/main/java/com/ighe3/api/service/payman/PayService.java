package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.dto.input.PaymanPayIto;
import com.ighe3.api.model.response.PaymanPayResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.dto.BaseResponse;
import com.ighe3.api.model.request.PaymanPayRequest;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class PayService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public PayService(AccessTokenService accessTokenService, Urls urls) {
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public Object pay(PaymanPayIto inputDto) throws Exception {
        BaseResponse paymanResponse = getResponseObject(inputDto);
        PaymanPayResponse paymanResponseBody = (PaymanPayResponse) convertJsonToJavaObject(paymanResponse.getBody());
        return null;
    }

    private BaseResponse getResponseObject(PaymanPayIto inputDto) throws Exception {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getPayUrl(), createHeaders());
        BaseResponse response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody(PaymanPayIto inputDto) throws Exception {
        PaymanPayRequest requestBodyObject = getRequestBodyObject(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        // TODO: mediaType from spring boot
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanPayRequest getRequestBodyObject(PaymanPayIto inputDto) {
        PaymanPayRequest requestBodyObject = new PaymanPayRequest();
        requestBodyObject.setTraceId(inputDto.getTraceId());
        requestBodyObject.setAmount(inputDto.getAmount());
        requestBodyObject.setDescription(inputDto.getDescription());
        requestBodyObject.setClientTransactionDate(inputDto.getClientTransactionDate());
        requestBodyObject.setPaymanId(inputDto.getPaymanId());
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
            PaymanPayResponse response = mapper.readValue(value, PaymanPayResponse.class);
            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
