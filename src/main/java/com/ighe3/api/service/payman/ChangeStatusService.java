package com.ighe3.api.service.payman;

import com.ighe3.api.dal.dto.input.ChangeStatusInputDTO;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.model.requestBodies.PaymanChangeStatusRequestBodyObject;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChangeStatusService extends BaseService {
    private final AccessTokenService accessTokenService;

    public ChangeStatusService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public Object changeStatus(ChangeStatusInputDTO inputDto) throws Exception {
        ResponseObject response = getResponseObject();
        Map<String, Object> body = GeneralUtils.getResponseBodyAsMap(response.getBody());
        return null;
    }

    private ResponseObject getResponseObject() throws Exception {
        RequestBody requestBody = createRequestBody();
        Request request = createRequest(requestBody, Urls.CHANGE_STATUS.getValue(), createHeaders());
        ResponseObject response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody() throws Exception {
        PaymanChangeStatusRequestBodyObject requestBodyObject = getRequestBodyObject();
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanChangeStatusRequestBodyObject getRequestBodyObject() {
        PaymanChangeStatusRequestBodyObject requestBodyObject = new PaymanChangeStatusRequestBodyObject();
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
}
