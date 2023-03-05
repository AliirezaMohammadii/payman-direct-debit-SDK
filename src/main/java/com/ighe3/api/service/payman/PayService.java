package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.controller.dto.input.PayInputDto;
import com.ighe3.api.model.response.PaymanPayResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.ResponseModel;
import com.ighe3.api.model.request.PaymanPayRequest;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class PayService extends BaseService {
    private final AccessTokenService accessTokenService;

    public PayService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public Object pay(PayInputDto inputDto) throws Exception {
        ResponseModel paymanResponse = getResponseObject(inputDto);
        PaymanPayResponse paymanResponseBody = (PaymanPayResponse) convertJsonToJavaObject(paymanResponse.getBody());
        return null;
    }

    private ResponseModel getResponseObject(PayInputDto inputDto) throws Exception {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, Urls.PAY.getValue(), createHeaders());
        ResponseModel response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody(PayInputDto inputDto) throws Exception {
        PaymanPayRequest requestBodyObject = getRequestBodyObject(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanPayRequest getRequestBodyObject(PayInputDto inputDto) {
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
