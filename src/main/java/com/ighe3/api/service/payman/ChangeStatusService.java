package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.dto.input.PaymanChangeStatusIto;
import com.ighe3.api.model.response.PaymanChangeStatusResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.BaseResponse;
import com.ighe3.api.model.request.PaymanChangeStatusRequest;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.RequestHeaderKeys;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class ChangeStatusService extends BaseService {

    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public ChangeStatusService(AccessTokenService accessTokenService, Urls urls) {
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanChangeStatusResponse changeStatus(PaymanChangeStatusIto inputDto) throws Exception {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getChangeStatusUrl(), createHeaders());
        BaseResponse paymanResponse = sendRequest(request);
        PaymanChangeStatusResponse paymanResponseBody
                = (PaymanChangeStatusResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanChangeStatusResponse.class);
        return paymanResponseBody;
    }

    private RequestBody createRequestBody(PaymanChangeStatusIto inputDto) throws Exception {
        PaymanChangeStatusRequest requestBodyObject = getRequestBodyObject(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanChangeStatusRequest getRequestBodyObject(PaymanChangeStatusIto inputDto) {
        PaymanChangeStatusRequest requestBodyObject = new PaymanChangeStatusRequest();
        requestBodyObject.setNewStatus(inputDto.getNewStatus());
        requestBodyObject.setPaymanId(inputDto.getPaymanId());
        return requestBodyObject;
    }

    @Override
    protected Headers createHeaders() throws Exception {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(), GeneralUtils.BEARER_PREFIX + accessTokenService.getAccessToken())
                .build();
        return headers;
    }
}
