package com.ighe3.api.service.payman;

import com.ighe3.api.dto.client.request.ChangeStatusRequest;
import com.ighe3.api.dto.provider.response.PaymanChangeStatusResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.Response;
import com.ighe3.api.dto.provider.request.PaymanChangeStatusRequest;
import com.ighe3.api.utils.ExceptionTranslator;
import com.ighe3.api.utils.GeneralUtils;
import com.ighe3.api.utils.RequestHeaderKeys;
import com.ighe3.api.utils.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class ChangeStatusService extends BaseService {

    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public ChangeStatusService(ExceptionTranslator exceptionTranslator, AccessTokenService accessTokenService, Urls urls) {
        super(exceptionTranslator);
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanChangeStatusResponse changeStatus(ChangeStatusRequest inputDto) throws RuntimeException {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getChangeStatusUrl(), createHeaders(accessTokenService.getAccessToken()));
        Response paymanResponse = sendRequest(request, ChangeStatusService.class);
        PaymanChangeStatusResponse paymanResponseBody
                = (PaymanChangeStatusResponse) convertJsonToJavaObject(paymanResponse.getBody(), PaymanChangeStatusResponse.class);
        return paymanResponseBody;
    }

    private RequestBody createRequestBody(ChangeStatusRequest inputDto) throws RuntimeException {
        PaymanChangeStatusRequest requestBodyObject = getRequestBodyObject(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanChangeStatusRequest getRequestBodyObject(ChangeStatusRequest inputDto) {
        PaymanChangeStatusRequest requestBodyObject = new PaymanChangeStatusRequest();
        requestBodyObject.setNewStatus(inputDto.getNewStatus());
        requestBodyObject.setPaymanId(inputDto.getPaymanId());
        return requestBodyObject;
    }
}
