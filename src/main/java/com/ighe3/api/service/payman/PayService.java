package com.ighe3.api.service.payman;

import com.ighe3.api.dto.input.PaymanPayIto;
import com.ighe3.api.model.response.PaymanPayResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.CustomizedResponse;
import com.ighe3.api.model.request.PaymanPayRequest;
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

    public PaymanPayResponse pay(PaymanPayIto inputDto) throws RuntimeException {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getPayUrl(), createHeaders());
        CustomizedResponse paymanResponse = sendRequest(request, PayService.class);
        PaymanPayResponse paymanResponseBody
                = (PaymanPayResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanPayResponse.class);
        return paymanResponseBody;
    }

    private RequestBody createRequestBody(PaymanPayIto inputDto) throws RuntimeException {
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
    protected Headers createHeaders() throws RuntimeException {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(),
                        GeneralUtils.BEARER_PREFIX + accessTokenService.getAccessToken())
                .build();
        return headers;
    }
}
