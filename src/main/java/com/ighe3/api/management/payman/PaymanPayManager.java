package com.ighe3.api.management.payman;

import com.ighe3.api.dal.dto.payman.input.PaymanPayInputDTO;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.model.payman.requestBody.PaymanPayRequestBodyObject;
import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class PaymanPayManager extends PaymanBaseManager {

    private final PaymanService paymanService;

    public PaymanPayManager(PaymanService paymanService) {
        this.paymanService = paymanService;
    }

    public ResponseObject pay(PaymanPayInputDTO inputDto) throws Exception {
        RequestBody body = createRequestBody(inputDto);
        Request request = createRequest(body, Urls.PAYMAN_PAY.getValue(), createHeaders());
        ResponseObject response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody(PaymanPayInputDTO inputDto) throws Exception {
        PaymanPayRequestBodyObject requestBodyObject = getRequestBodyObject(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanPayRequestBodyObject getRequestBodyObject(PaymanPayInputDTO inputDto) {
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
                        GeneralUtils.BEARER_PREFIX + paymanService.getAccessToken())
                .build();
        return headers;
    }
}
