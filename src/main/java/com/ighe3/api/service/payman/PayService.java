package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.dto.input.PaymanPayIto;
import com.ighe3.api.model.response.PaymanPayResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.BaseResponse;
import com.ighe3.api.model.request.PaymanPayRequest;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.RequestHeaderKeys;
import com.ighe3.api.utils.TraceIdGenerator;
import com.ighe3.api.utils.Urls;
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

    public PaymanPayResponse pay(PaymanPayIto inputDto) throws Exception {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getPayUrl(), createHeaders());
        BaseResponse paymanResponse = sendRequest(request);
        PaymanPayResponse paymanResponseBody
                = (PaymanPayResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanPayResponse.class);
        return paymanResponseBody;
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
        requestBodyObject.setTraceId(TraceIdGenerator.generate());
        requestBodyObject.setAmount(inputDto.getAmount());
        requestBodyObject.setDescription(inputDto.getDescription());
        requestBodyObject.setClientTransactionDate(inputDto.getClientTransactionDate());
        requestBodyObject.setPaymanId(inputDto.getPaymanId());
        requestBodyObject.setPayId(inputDto.getPayId());
        requestBodyObject.setCommissionAmount(inputDto.getCommissionAmount());
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
