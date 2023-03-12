package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.PayRequest;
import com.ighe3.api.dto.provider.response.PaymanPayResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.dto.Response;
import com.ighe3.api.dto.provider.request.PaymanPayRequest;
import com.ighe3.api.utils.*;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class PayService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public PayService(ExceptionTranslator exceptionTranslator, AccessTokenService accessTokenService, Urls urls) {
        super(exceptionTranslator);
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanPayResponse pay(PayRequest inputDto) throws RuntimeException {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getPayUrl(), createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = sendRequest(request, PayService.class);

        // TODO: do it for all services
        return (PaymanPayResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanPayResponse.class);
    }

    private RequestBody createRequestBody(PayRequest inputDto) throws RuntimeException {
        PaymanPayRequest requestBodyObject = getRequestBodyObject(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        // TODO: mediaType from spring boot
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanPayRequest getRequestBodyObject(PayRequest inputDto) {
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
}
