package com.ighe3.api.management.payman;

import com.ighe3.api.dal.dto.payman.input.PaymanChangeStatusInputDTO;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.model.payman.requestBody.PaymanChangeStatusRequestBodyObject;
import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class PaymanChangeStatusManager extends PaymanBaseManager {

    private PaymanService paymanService;

    public ResponseObject changeStatus(PaymanChangeStatusInputDTO inputDto) throws Exception {
        RequestBody body = createRequestBody();
        Request request = createRequest(body, Urls.CHANGE_PAYMAN_STATUS.getValue(), createHeaders());
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
        requestBodyObject.setNewStatus("...");
        requestBodyObject.setPaymanId("...");
        return requestBodyObject;
    }

    @Override
    protected Headers createHeaders() throws Exception {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(), GeneralUtils.BEARER_PREFIX + paymanService.getAccessToken())
                .build();
        return headers;
    }
}
