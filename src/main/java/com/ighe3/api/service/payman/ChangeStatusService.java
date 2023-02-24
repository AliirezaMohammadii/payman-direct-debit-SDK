package com.ighe3.api.service.payman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ighe3.api.dal.dto.input.ChangeStatusInputDto;
import com.ighe3.api.model.response.PaymanChangeStatusResponse;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.ResponseModel;
import com.ighe3.api.model.request.PaymanChangeStatusRequest;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class ChangeStatusService extends BaseService {
    private final AccessTokenService accessTokenService;

    public ChangeStatusService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public Object changeStatus(ChangeStatusInputDto inputDto) throws Exception {
        ResponseModel paymanResponse = getResponseObject();
        PaymanChangeStatusResponse paymanResponseBody
                = (PaymanChangeStatusResponse) convertJsonToJavaObject(paymanResponse.getBody());
        return null;
    }

    private ResponseModel getResponseObject() throws Exception {
        RequestBody requestBody = createRequestBody();
        Request request = createRequest(requestBody, Urls.CHANGE_STATUS.getValue(), createHeaders());
        ResponseModel response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody() throws Exception {
        PaymanChangeStatusRequest requestBodyObject = getRequestBodyObject();
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanChangeStatusRequest getRequestBodyObject() {
        PaymanChangeStatusRequest requestBodyObject = new PaymanChangeStatusRequest();
        requestBodyObject.setNewStatus(null);
        requestBodyObject.setPaymanId(null);
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

    @Override
    protected Object convertJsonToJavaObject(String value) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            PaymanChangeStatusResponse response = mapper.readValue(value, PaymanChangeStatusResponse.class);
            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
