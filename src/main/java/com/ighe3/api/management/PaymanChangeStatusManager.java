package com.ighe3.api.management;

import com.ighe3.api.dal.dto.input.PaymanChangeStatusInputDTO;
import com.ighe3.api.model.paymanRequestBodies.PaymanChangeStatusRequestBodyObject;
import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PaymanChangeStatusManager extends PaymanBaseManager {

    private PaymanService paymanService;

    public Response changeStatus(PaymanChangeStatusInputDTO inputDto) throws IOException {
        RequestBody body = createRequestBody();
        Request request = getRequest(body, Urls.CHANGE_PAYMAN_STATUS.getValue(), getHeaders());
        Response response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody() {

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
    protected Headers getHeaders() throws IOException {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(), GeneralUtils.BEARER_PREFIX + paymanService.getAccessToken())
                .build();
        return headers;
    }
}
