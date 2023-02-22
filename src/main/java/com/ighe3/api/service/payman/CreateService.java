package com.ighe3.api.service.payman;

import com.ighe3.api.dal.dto.input.CreateInputDTO;
import com.ighe3.api.dal.dto.output.CreateOutputDto;
import com.ighe3.api.service.BaseService;
import com.ighe3.api.model.PaymanContractModel;
import com.ighe3.api.model.PaymanObject;
import com.ighe3.api.model.ResponseObject;
import com.ighe3.api.model.request.PaymanCreateRequestBodyObject;
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

    public CreateOutputDto create(CreateInputDTO inputDto) throws Exception {
        ResponseObject paymanResponse = getResponseObject(inputDto);
        Headers headers = paymanResponse.getHeaders();
        CreateOutputDto appResponse = new CreateOutputDto(headers.get("Location"));
        return appResponse;
    }

    private ResponseObject getResponseObject(CreateInputDTO inputDto) throws Exception {
        RequestBody requestBody = createRequestBody(inputDto);
        Request request = createRequest(requestBody, Urls.CREATE.getValue(), createHeaders(inputDto));
        ResponseObject response = sendRequest(request);
        return response;
    }

    private RequestBody createRequestBody(CreateInputDTO inputDto) throws Exception {
        PaymanContractModel paymanContractModel = getContractObject(inputDto);
        PaymanObject paymanObject = getPaymanObject(paymanContractModel, inputDto);
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

    private PaymanObject getPaymanObject(PaymanContractModel paymanContractModel, CreateInputDTO inputDto) {
        PaymanObject paymanObject = new PaymanObject();
        paymanObject.setUserId(inputDto.getUserId());
        paymanObject.setBankCode(inputDto.getBankCode());
        paymanObject.setPermissionIds(inputDto.getPermissionIds());
        paymanObject.setPaymanContractModel(paymanContractModel);
        return paymanObject;
    }

    private PaymanContractModel getContractObject(CreateInputDTO inputDto) {
        PaymanContractModel paymanContractModel = new PaymanContractModel();
        paymanContractModel.setStartDate(inputDto.getStartDate());
        paymanContractModel.setExpirationDate(inputDto.getExpirationDate());
        paymanContractModel.setMaxDailyTransactionCount(inputDto.getMaxDailyTransactionCount());
        paymanContractModel.setMaxMonthlyTransactionCount(inputDto.getMaxMonthlyTransactionCount());
        paymanContractModel.setDailyMaxTransactionAmount(inputDto.getDailyMaxTransactionAmount());
        paymanContractModel.setMaxTransactionAmount(inputDto.getMaxTransactionAmount());
        return paymanContractModel;
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

    @Override
    protected Object convertJsonToJavaObject(String value) {
        return null;
    }
}
