package com.ighe3.api.management.payman;

import com.ighe3.api.dal.dto.payman.input.PaymanCreateInputDTO;
import com.ighe3.api.model.payman.ContractObject;
import com.ighe3.api.model.payman.PaymanObject;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.model.payman.requestBody.PaymanCreateRequestBodyObject;
import com.ighe3.api.service.PaymanService;
import com.ighe3.api.util.GeneralUtils;
import com.ighe3.api.util.RequestHeaderKeys;
import com.ighe3.api.util.Urls;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class PaymanCreateManager extends PaymanBaseManager {

    private final PaymanService paymanService;

    public PaymanCreateManager(PaymanService paymanService) {
        this.paymanService = paymanService;
    }

    public ResponseObject create(PaymanCreateInputDTO inputDto) throws Exception {
        RequestBody body = createRequestBody(inputDto);
        Request request = createRequest(body, Urls.CREATE_PAYMAN.getValue(), createHeaders());
        ResponseObject response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody(PaymanCreateInputDTO inputDto) throws Exception {
        ContractObject contractObject = getContractObject(inputDto);
        PaymanObject paymanObject = getPaymanObject(contractObject, inputDto);
        PaymanCreateRequestBodyObject requestBodyObject = getRequestBodyObject(paymanObject, inputDto);

        String json = GeneralUtils.convertJavaObjectToJson(requestBodyObject);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json);
        return body;
    }

    private PaymanCreateRequestBodyObject getRequestBodyObject(PaymanObject paymanObject, PaymanCreateInputDTO inputDto) {
        PaymanCreateRequestBodyObject requestBodyObject = new PaymanCreateRequestBodyObject();
        requestBodyObject.setTraceId(inputDto.getTraceId());
        requestBodyObject.setRedirectUrl(inputDto.getRedirectUrl());
        requestBodyObject.setPaymanObject(paymanObject);
        return requestBodyObject;
    }

    private PaymanObject getPaymanObject(ContractObject contractObject, PaymanCreateInputDTO inputDto) {
        PaymanObject paymanObject = new PaymanObject();
        paymanObject.setUserId(inputDto.getUserId());
        paymanObject.setBankCode(inputDto.getBankCode());
        paymanObject.setPermissionIds(inputDto.getPermissionIds());
        paymanObject.setContractObject(contractObject);
        return paymanObject;
    }

    private ContractObject getContractObject(PaymanCreateInputDTO inputDto) {
        ContractObject contractObject = new ContractObject();
        contractObject.setStartDate(inputDto.getStartDate());
        contractObject.setExpirationDate(inputDto.getExpirationDate());
        contractObject.setMaxDailyTransactionCount(inputDto.getMaxDailyTransactionCount());
        contractObject.setMaxMonthlyTransactionCount(inputDto.getMaxMonthlyTransactionCount());
        contractObject.setDailyMaxTransactionAmount(inputDto.getDailyMaxTransactionAmount());
        contractObject.setMaxTransactionAmount(inputDto.getMaxTransactionAmount());
        return contractObject;
    }

    protected Headers createHeaders(PaymanCreateInputDTO inputDto) throws Exception {
        Headers generalHeaders = GeneralUtils.getGeneralHeaders();
        Headers headers = new Headers.Builder()
                .addAll(generalHeaders)
                .add(RequestHeaderKeys.AUTHORIZATION.getValue(), GeneralUtils.BEARER_PREFIX +
                        paymanService.getAccessToken())
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
