package com.ighe3.api.service.payman;

import com.ighe3.api.dto.input.PaymanCreateIto;
import com.ighe3.api.dto.output.PaymanCreateOto;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.PaymanContract;
import com.ighe3.api.model.PaymanModel;
import com.ighe3.api.dto.BaseResponse;
import com.ighe3.api.model.request.PaymanCreateRequest;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class CreateService extends BaseService {
    private final AccessTokenService accessTokenService;
    private final Urls urls;

    public CreateService(AccessTokenService accessTokenService, Urls urls) {
        this.accessTokenService = accessTokenService;
        this.urls = urls;
    }

    public PaymanCreateOto create(PaymanCreateIto inputDto) throws Exception {
        BaseResponse paymanResponse = getResponseObject(inputDto);
        Headers headers = paymanResponse.getHeaders();
        PaymanCreateOto appResponse = new PaymanCreateOto(headers.get("Location"));
        return appResponse;
    }

    private BaseResponse getResponseObject(PaymanCreateIto inputDto) throws Exception {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, urls.getCreateUrl(), createHeaders(inputDto));
        BaseResponse response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody(PaymanCreateIto inputDto) throws Exception {
        PaymanContract paymanContract = getContractObject(inputDto);
        PaymanModel paymanObject = getPaymanObject(paymanContract, inputDto);
        PaymanCreateRequest requestBodyObject = getRequestBodyObject(paymanObject, inputDto);

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanCreateRequest getRequestBodyObject(PaymanModel paymanObject, PaymanCreateIto inputDto) {
        PaymanCreateRequest requestBodyObject = new PaymanCreateRequest();
        requestBodyObject.setTraceId(inputDto.getTraceId());
        requestBodyObject.setRedirectUrl(inputDto.getRedirectUrl());
        requestBodyObject.setPayman(paymanObject);
        return requestBodyObject;
    }

    private PaymanModel getPaymanObject(PaymanContract paymanContract, PaymanCreateIto inputDto) {
        PaymanModel paymanObject = new PaymanModel();
        paymanObject.setUserId(inputDto.getUserId());
        paymanObject.setBankCode(inputDto.getBankCode());
        paymanObject.setPermissionIds(inputDto.getPermissionIds());
        paymanObject.setContract(paymanContract);
        return paymanObject;
    }

    private PaymanContract getContractObject(PaymanCreateIto inputDto) {
        PaymanContract paymanContract = new PaymanContract();
        paymanContract.setStartDate(inputDto.getStartDate());
        paymanContract.setExpirationDate(inputDto.getExpirationDate());
        paymanContract.setMaxDailyTransactionCount(inputDto.getMaxDailyTransactionCount());
        paymanContract.setMaxMonthlyTransactionCount(inputDto.getMaxMonthlyTransactionCount());
        paymanContract.setMaxTransactionAmount(inputDto.getMaxTransactionAmount());
        paymanContract.setCurrency(inputDto.getCurrency());
        return paymanContract;
    }

    protected Headers createHeaders(PaymanCreateIto inputDto) throws Exception {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(), GeneralUtils.BEARER_PREFIX + accessTokenService.getAccessToken())
                .add(RequestHeaderKeys.MOBILE_NO.getValue(), inputDto.getMobileNumber())
                .add(RequestHeaderKeys.NATIONAL_CODE.getValue(), inputDto.getNationalCode())
                .build();
        return headers;
    }

    @Override
    protected Headers createHeaders() throws Exception {
        return null;
    }

    @Override
    protected Object convertJsonToJavaObject(String value) {
        return null;
    }
}
