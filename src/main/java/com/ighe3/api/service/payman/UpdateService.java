package com.ighe3.api.service.payman;

import com.ighe3.api.dal.dto.input.UpdateInputDTO;
import com.ighe3.api.dal.dto.output.UpdateOutputDto;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.model.request.PaymanUpdateRequestBodyObject;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class UpdateService extends BaseService {
    private final AccessTokenService accessTokenService;

    public UpdateService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public UpdateOutputDto update(UpdateInputDTO inputDto) throws Exception {
        ResponseObject paymanResponse = getResponseObject(inputDto);
        Headers headers = paymanResponse.getHeaders();
        UpdateOutputDto appResponse = new UpdateOutputDto(headers.get("Location"));
        return appResponse;
    }

    private ResponseObject getResponseObject(UpdateInputDTO inputDto) throws Exception {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, Urls.UPDATE.getValue(), createHeaders());
        ResponseObject response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody(UpdateInputDTO inputDto) throws Exception {
        PaymanUpdateRequestBodyObject requestBodyObject = getRequestBodyObject(inputDto);
        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanUpdateRequestBodyObject getRequestBodyObject(UpdateInputDTO inputDto) {
        PaymanUpdateRequestBodyObject requestBodyObject = new PaymanUpdateRequestBodyObject();
        requestBodyObject.setPaymanId(null);
        requestBodyObject.setExpirationDate(null);
        requestBodyObject.setMaxDailyTransactionCount(null);
        requestBodyObject.setMaxMonthlyTransactionCount(null);
        requestBodyObject.setMaxTransactionAmount(null);
        requestBodyObject.setRedirectUrl(null);
        return requestBodyObject;
    }

    @Override
    protected Headers createHeaders() throws Exception {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(), GeneralUtils.BEARER_PREFIX + accessTokenService.getAccessToken())
                .add(RequestHeaderKeys.MOBILE_NO.getValue(), "09120000000")
                .add(RequestHeaderKeys.NATIONAL_CODE.getValue(), "0123456789")
                .build();
        return headers;
    }

    @Override
    protected Object convertJsonToJavaObject(String value) {
        return null;
    }
}
