package com.ighe3.api.service.payman;

import com.ighe3.api.dal.dto.input.PayInputDTO;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.model.requestBody.PaymanPayRequestBodyObject;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PayService extends BaseService {
    private final AccessTokenService accessTokenService;

    public PayService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public Object pay(PayInputDTO inputDto) throws Exception {
        ResponseObject response = getResponseObject(inputDto);
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        return null;
    }

    private ResponseObject getResponseObject(PayInputDTO inputDto) throws Exception {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, Urls.PAY.getValue(), createHeaders());
        ResponseObject response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody(PayInputDTO inputDto) throws Exception {
        PaymanPayRequestBodyObject requestBodyObject = getRequestBodyObject(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanPayRequestBodyObject getRequestBodyObject(PayInputDTO inputDto) {
        PaymanPayRequestBodyObject requestBodyObject = new PaymanPayRequestBodyObject();
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
}
