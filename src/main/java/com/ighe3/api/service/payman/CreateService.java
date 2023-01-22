package com.ighe3.api.service.payman;

import com.ighe3.api.dal.dto.input.CreateInputDTO;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.ContractObject;
import com.ighe3.api.model.PaymanObject;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.model.requestBody.PaymanCreateRequestBodyObject;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Service
public class CreateService extends BaseService {
    private final AccessTokenService accessTokenService;

    public CreateService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    public Object create(CreateInputDTO inputDto) throws Exception {
        ResponseObject response = getResponseObject(inputDto);
        Headers headers = response.getHeaders();
        String redirectUrl = headers.get("Location");
        return redirectUrl;
    }

    private ResponseObject getResponseObject(CreateInputDTO inputDto) throws Exception {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, Urls.CREATE.getValue(), createHeaders(inputDto));
        ResponseObject response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody(CreateInputDTO inputDto) throws Exception {
        ContractObject contractObject = getContractObject(inputDto);
        PaymanObject paymanObject = getPaymanObject(contractObject, inputDto);
        PaymanCreateRequestBodyObject requestBodyObject = getRequestBodyObject(paymanObject, inputDto);

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanCreateRequestBodyObject getRequestBodyObject(PaymanObject paymanObject, CreateInputDTO inputDto) {
        PaymanCreateRequestBodyObject requestBodyObject = new PaymanCreateRequestBodyObject();
        requestBodyObject.setTraceId(inputDto.getTraceId());
        requestBodyObject.setRedirectUrl(inputDto.getRedirectUrl());
        requestBodyObject.setPaymanObject(paymanObject);
        return requestBodyObject;
    }

    private PaymanObject getPaymanObject(ContractObject contractObject, CreateInputDTO inputDto) {
        PaymanObject paymanObject = new PaymanObject();
        paymanObject.setUserId(inputDto.getUserId());
        paymanObject.setBankCode(inputDto.getBankCode());
        paymanObject.setPermissionIds(inputDto.getPermissionIds());
        paymanObject.setContractObject(contractObject);
        return paymanObject;
    }

    private ContractObject getContractObject(CreateInputDTO inputDto) {
        ContractObject contractObject = new ContractObject();
        contractObject.setStartDate(inputDto.getStartDate());
        contractObject.setExpirationDate(inputDto.getExpirationDate());
        contractObject.setMaxDailyTransactionCount(inputDto.getMaxDailyTransactionCount());
        contractObject.setMaxMonthlyTransactionCount(inputDto.getMaxMonthlyTransactionCount());
        contractObject.setDailyMaxTransactionAmount(inputDto.getDailyMaxTransactionAmount());
        contractObject.setMaxTransactionAmount(inputDto.getMaxTransactionAmount());
        return contractObject;
    }

    protected Headers createHeaders(CreateInputDTO inputDto) throws Exception {
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
}
